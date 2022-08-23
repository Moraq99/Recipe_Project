package com.example.recept_x_project.service;

import com.example.recept_x_project.model.Recipe;
import com.example.recept_x_project.repo.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RecipeService {

    private RecipeRepo repo;

    @Autowired
    public RecipeService(RecipeRepo recipeRepo){
        this.repo = recipeRepo;
    }

    public boolean doesRecipeContain(String keyWord, Recipe recipe) {

        return recipe.getIngredients().contains(keyWord);
    }










    }
