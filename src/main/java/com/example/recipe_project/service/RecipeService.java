package com.example.recipe_project.service;


import com.example.recipe_project.enums.EnumDifficulty;
import com.example.recipe_project.model.Ingredient;
import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.model.SearchFields;
import com.example.recipe_project.repo.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;


@Service
public class RecipeService {

    @PersistenceContext
    private EntityManager entityManager;

    private final RecipeRepo repo;

    @Autowired
    public RecipeService(RecipeRepo recipeRepo){
        this.repo = recipeRepo;
    }

    public List<Recipe> getAll() {
        return new ArrayList<>((Collection) repo.findAll());
    }

    public List<Recipe> searchRecipes(SearchFields searchFields) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Recipe> criteriaQuery = criteriaBuilder.createQuery(Recipe.class);

        Root<Recipe> recipe = criteriaQuery.from(Recipe.class);

        List<Predicate> predicates = new ArrayList<>();

        if(!searchFields.getName().isBlank()) {
            Predicate namePredicate = criteriaBuilder.like(recipe.get("name"), "%" + searchFields.getName() + "%");
            predicates.add(namePredicate);
        }

        if(searchFields.isVegan()) {
            Predicate veganPredicate = criteriaBuilder.equal(recipe.get("vegan"), searchFields.isVegan());
            predicates.add(veganPredicate);
        }

        if(searchFields.isLactose_free()) {
            Predicate lactosePredicate = criteriaBuilder.equal(recipe.get("lactose_free"), searchFields.isLactose_free());
            predicates.add(lactosePredicate);
        }

        if(searchFields.isGluten_free()) {
            Predicate glutenPredicate = criteriaBuilder.equal(recipe.get("gluten_free"), searchFields.isGluten_free());
            predicates.add(glutenPredicate);
        }

        if (!searchFields.getDifficulty().equals(EnumDifficulty.UNDEFINED)) {
            Predicate difficultyPredicate = criteriaBuilder.equal(recipe.get("difficulty"), searchFields.getDifficulty());
            predicates.add(difficultyPredicate);
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));


        TypedQuery<Recipe> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public Recipe findById(long id) {
        return repo.findById(id).orElseThrow();
    }

    public boolean doesRecipeContain(String keyWord, Recipe recipe) {

        List<Ingredient> ingredients = recipe.getIngredients();

        for(int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getName().contains(keyWord)) {
                return true;
            }
        }
        return false;
    }

    public List<Recipe> findByIngredient(String keyword){

        List<Recipe> hasKeyword = new ArrayList<>();
        List<Recipe> RecipeName = (List<Recipe>)repo.findAll();

        for (Recipe recipe : RecipeName) {
            if (doesRecipeContain(keyword, recipe)) {
                hasKeyword.add(recipe);
            }
        }

        return hasKeyword;
    }

    public List<Recipe> findRecipesWhereNameContains(String keyword) {
        List<Recipe> dbRecipes = (List<Recipe>) repo.findAll();

        return dbRecipes.stream()
                .filter(recipe -> recipe.getName().contains(keyword))
                .toList();
    }

    public List<Recipe> findVeganRecipes() {
        List<Recipe> dbRecipes = (List<Recipe>) repo.findAll();

        return dbRecipes.stream()
                .filter(Recipe::isVegan)
                .toList();
    }

    public List<Recipe> findLactoseFreeRecipes() {
        List<Recipe> dbRecipes = (List<Recipe>) repo.findAll();

        return dbRecipes.stream()
                .filter(Recipe::isLactose_free)
                .toList();
    }

    public List<Recipe> findGlutenFreeRecipes() {
        List<Recipe> dbRecipes = (List<Recipe>) repo.findAll();

        return dbRecipes.stream()
                .filter(Recipe::isGluten_free)
                .toList();
    }

    public List<Recipe> findRecipesWhereDifficulty(EnumDifficulty difficulty) {
        List<Recipe> dbRecipes = (List<Recipe>) repo.findAll();

        return dbRecipes.stream()
                .filter(recipe -> recipe.getDifficulty().equals(difficulty))
                .toList();
    }

    public List<Recipe> findRecipesWherePrepTime(int prepTime) {
        List<Recipe> dbRecipes = (List<Recipe>) repo.findAll();

        return dbRecipes.stream()
                .filter(recipe -> recipe.getPreparationTime() <= prepTime)
                .toList();
    }

    public Set<Recipe> getSearchResults(SearchFields searchFields) {
        List<Recipe> nameResult = new ArrayList<>();
        List<Recipe> veganResult = new ArrayList<>();
        List<Recipe> lactoseResult = new ArrayList<>();
        List<Recipe> glutenResult = new ArrayList<>();
        List<Recipe> difficultyResult = findRecipesWhereDifficulty(searchFields.getDifficulty());
        List<Recipe> ingredientResult = new ArrayList<>();
        List<Recipe> prepTimeResult = new ArrayList<>();
        Set<Recipe> result = new HashSet<>();

        if (!searchFields.getDifficulty().equals(EnumDifficulty.UNDEFINED)) {
            result.addAll(difficultyResult);
        }

        if (searchFields.getName().length() > 0) {
            nameResult = findRecipesWhereNameContains(searchFields.getName());
            result.addAll(nameResult);
        }

        if (searchFields.isVegan()) {
            veganResult = findVeganRecipes();
            result.addAll(veganResult);
        }

        if (searchFields.isLactose_free()) {
            lactoseResult = findLactoseFreeRecipes();
            result.addAll(lactoseResult);
        }

        if (searchFields.isGluten_free()) {
            glutenResult = findGlutenFreeRecipes();
            result.addAll(glutenResult);
        }

        if (searchFields.getIngredient().length() > 0) {
            ingredientResult = findByIngredient(searchFields.getIngredient());
            result.addAll(ingredientResult);
        }

        if (searchFields.getPrepTime() > 0) {
            prepTimeResult = findRecipesWherePrepTime(searchFields.getPrepTime());
            result.addAll(prepTimeResult);
        }

        return result;
    }

    }
