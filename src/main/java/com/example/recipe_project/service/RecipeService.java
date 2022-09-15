package com.example.recipe_project.service;


import com.example.recipe_project.enums.EnumDifficulty;
import com.example.recipe_project.model.Ingredient;
import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.model.SearchFields;
import com.example.recipe_project.repo.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class RecipeService {

    private final RecipeRepo repo;

    @Autowired
    public RecipeService(RecipeRepo recipeRepo){
        this.repo = recipeRepo;
    }

    public List<Recipe> getAll() {
        return new ArrayList<>((Collection) repo.findAll());
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

    public Set<Recipe> getSearchResults(SearchFields searchFields) {
        List<Recipe> nameResult = new ArrayList<>();
        List<Recipe> veganResult = new ArrayList<>();
        List<Recipe> lactoseResult = new ArrayList<>();
        List<Recipe> glutenResult = new ArrayList<>();
        List<Recipe> difficultyResult = findRecipesWhereDifficulty(searchFields.getDifficulty());
        List<Recipe> ingredientResult = new ArrayList<>();
        Set<Recipe> result = new HashSet<>();

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

        return result;
    }

    }
