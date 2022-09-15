package com.example.recipe_project.service;


import com.example.recipe_project.model.Ingredient;
import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.repo.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RecipeService {

    private RecipeRepo repo;

    @Autowired
    public RecipeService(RecipeRepo recipeRepo){
        this.repo = recipeRepo;
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

        for(int i=0; i<RecipeName.size(); i++) {
            if(doesRecipeContain(keyword,RecipeName.get(i))==true){
                hasKeyword.add(RecipeName.get(i));
            }
        }

        return hasKeyword;
    }

    }
