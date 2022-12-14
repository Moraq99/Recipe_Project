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
import javax.persistence.criteria.*;
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

    public Recipe saveRecipe(Recipe recipe) {
        repo.save(recipe);

       return recipe;
    }

    public void processIngredientsFromForm(Recipe recipe) {
        List<Ingredient> ingredientList= recipe.getIngredients().stream()
                .filter(ingredient -> !ingredient.getName().isBlank())
                .toList();

        recipe.setIngredients(ingredientList);

        ingredientList.forEach(ingredient -> ingredient.setRecipe(recipe));
    }

    public void createIngredientDummies(Recipe recipe) {
        for (int i = 0; i < recipe.getNumOfIngredients(); i++) {
            recipe.addIngredient(new Ingredient());
        }
    }

    public void updatePhoto(Recipe recipe) {
        Recipe origin = findById(recipe.getId());

        if (recipe.getPhotoName() == null) {
            recipe.setPhotoName(origin.getPhotoName());
            recipe.setPhotoType(origin.getPhotoType());
            recipe.setPhotoData(origin.getPhotoData());
        }
    }

    public List<Recipe> searchRecipes(SearchFields searchFields) {
        List<Recipe> results = getListFromCriteriaBuilder(searchFields);
        return getListIfIngredient(results, searchFields);
    }

    private List<Recipe> getListIfIngredient (List<Recipe> results, SearchFields searchFields) {
        List<Recipe> finalResults = new ArrayList<>();
        if (searchFields.getIngredient().length() > 0) {
            List<Recipe> ingredientResult = findByIngredient(searchFields.getIngredient(), results);
            finalResults.addAll(ingredientResult);
            return finalResults;
        }
        return results;
    }

    private List<Recipe> getListFromCriteriaBuilder (SearchFields searchFields) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Recipe> criteriaQuery = criteriaBuilder.createQuery(Recipe.class);
        Root<Recipe> recipe = criteriaQuery.from(Recipe.class);
        List<Predicate> predicates = new ArrayList<>();

        if (!searchFields.getName().isBlank()) {
            Predicate namePredicate = criteriaBuilder.like(recipe.get("name"), "%" + searchFields.getName() + "%");
            predicates.add(namePredicate);
        }

        if (!searchFields.getDifficulty().equals(EnumDifficulty.UNDEFINED)) {
            Predicate difficultyPredicate = criteriaBuilder.equal(recipe.get("difficulty"), searchFields.getDifficulty());
            predicates.add(difficultyPredicate);
        }

        if (searchFields.getPrepTime() > 0) {
            Predicate prepTimePredicate = criteriaBuilder.lessThanOrEqualTo(recipe.get("preparationTime"), searchFields.getPrepTime());
            predicates.add(prepTimePredicate);
        }

        if (searchFields.isVegan()) {
            Predicate veganPredicate = criteriaBuilder.equal(recipe.get("vegan"), searchFields.isVegan());
            predicates.add(veganPredicate);
        }

        if (searchFields.isLactose_free()) {
            Predicate lactosePredicate = criteriaBuilder.equal(recipe.get("lactose_free"), searchFields.isLactose_free());
            predicates.add(lactosePredicate);
        }

        if (searchFields.isGluten_free()) {
            Predicate glutenPredicate = criteriaBuilder.equal(recipe.get("gluten_free"), searchFields.isGluten_free());
            predicates.add(glutenPredicate);
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Recipe> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }


    public void deleteById(Long id){
        repo.deleteById(id);
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


    public List<Recipe> findByIngredient(String keyword, List<Recipe> recipes){

        List<Recipe> hasKeyword = new ArrayList<>();

        for (Recipe recipe : recipes) {
            if (doesRecipeContain(keyword, recipe)) {
                hasKeyword.add(recipe);
            }
        }

        return hasKeyword;
    }


      public Optional<Recipe> getById(long id) {
        return repo.findById(id);
      }

      public List<Recipe> getRandomRecipes() {
        List<Recipe> recipes = (List<Recipe>) repo.findAll();
        List<Recipe> randomRecipes = new ArrayList<>();

        int numRecipes = (int) ((Math.random() * (recipes.size() / 2)));

        for (int i = 0; i < numRecipes; i++) {
            randomRecipes.add(recipes.get(i));
        }

        randomRecipes.add(repo.findByName("Kis cica"));

        return randomRecipes;
      }


}
