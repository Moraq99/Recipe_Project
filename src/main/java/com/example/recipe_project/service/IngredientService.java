package com.example.recipe_project.service;

import com.example.recipe_project.model.Ingredient;
import com.example.recipe_project.repo.IngredientRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class IngredientService {

    private IngredientRepo ingredientRepo;

    public IngredientService(IngredientRepo ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    public List<Ingredient> getAllById(long recipeId) {
        return new ArrayList<>((Collection) ingredientRepo.findAllByRecipeId(recipeId));
    }
}
