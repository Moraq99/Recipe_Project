package com.example.recipe_project.service;


import com.example.recipe_project.enums.EnumDifficulty;
import com.example.recipe_project.model.Ingredient;
import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.model.SearchFields;
import com.example.recipe_project.repo.RecipeRepo;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;
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

        for (Ingredient ingredient: ingredientList){
            ingredient.setRecipe(recipe);
        }
    }

    public void createIngredientDummies(Recipe recipe) {
        for (int i = 0; i < recipe.getNumOfIngredients(); i++) {
            recipe.addIngredient(new Ingredient());
        }
    }

    public List<Recipe> searchRecipes(SearchFields searchFields) throws IllegalAccessException {
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
    private List<Recipe> getListFromCriteriaBuilder (SearchFields searchFields) throws IllegalAccessException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Recipe> criteriaQuery = criteriaBuilder.createQuery(Recipe.class);
        Root<Recipe> recipe = criteriaQuery.from(Recipe.class);
        List<Predicate> predicates = new ArrayList<>();

        //Class<SearchFields> srchFields = (Class<SearchFields>) searchFields.getClass();
        Field[] fields = searchFields.getClass().getDeclaredFields();
        if (!searchFields.getName().isBlank()) {
            Predicate namePredicate = criteriaBuilder.like(recipe.get("name"), "%" + searchFields.getName() + "%");
            predicates.add(namePredicate);
        }

        //ReflectionUtils.doWithFields(searchFields.getClass(), field -> {
        for (Field field1 : fields){
            if (field1.getName() == "name"){continue;}
            if (!field1.getName().isBlank()) {
                Predicate tempPredicate = criteriaBuilder.like(recipe.get(field1.getName()), (String) field1.get(searchFields));
                        predicates.add(tempPredicate);
            }
        }
        //});

        //predicates.add(getNameToSearch(searchFields));
        if (!searchFields.getName().isBlank()) {
            Predicate namePredicate = criteriaBuilder.like(recipe.get("name"), "%" + searchFields.getName() + "%");
            predicates.add(namePredicate);
        }
        //predicates.add(getIsVeganToSearch(searchFields));
        if (searchFields.isVegan()) {
            Predicate veganPredicate = criteriaBuilder.equal(recipe.get("vegan"), searchFields.isVegan());
            predicates.add(veganPredicate);
        }
        //predicates.add(getIsLactoseFreeToSearch(searchFields));
        if (searchFields.isLactose_free()) {
            Predicate lactosePredicate = criteriaBuilder.equal(recipe.get("lactose_free"), searchFields.isLactose_free());
            predicates.add(lactosePredicate);
        }
        //predicates.add(getIsGlutenFreeToSearch(searchFields));
        if (searchFields.isGluten_free()) {
            Predicate glutenPredicate = criteriaBuilder.equal(recipe.get("gluten_free"), searchFields.isGluten_free());
            predicates.add(glutenPredicate);
        }
        //predicates.add(getDifficultyToSearch(searchFields));
        if (!searchFields.getDifficulty().equals(EnumDifficulty.UNDEFINED)) {
            Predicate difficultyPredicate = criteriaBuilder.equal(recipe.get("difficulty"), searchFields.getDifficulty());
            predicates.add(difficultyPredicate);
        }
        //predicates.add(getPrepTimeToSearch(searchFields));
        if (searchFields.getPrepTime() > 0) {
            Predicate prepTimePredicate = criteriaBuilder.lessThanOrEqualTo(recipe.get("preparationTime"), searchFields.getPrepTime());
            predicates.add(prepTimePredicate);
        }

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

    public List<Recipe> findByIngredient(String keyword, List<Recipe> recipes){

        List<Recipe> hasKeyword = new ArrayList<>();

        for (Recipe recipe : recipes) {
            if (doesRecipeContain(keyword, recipe)) {
                hasKeyword.add(recipe);
            }
        }

        return hasKeyword;
    }

    public void deleteById(Long id){
        repo.deleteById(id);
    }


    public void kiscica(){
        List<String> valami = new ArrayList<>();

        if( valami.size() == 0){
            System.out.println("Üres lista");
        }else{
            System.out.println();
        }
    }
}