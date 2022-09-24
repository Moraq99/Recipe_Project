package com.example.recipe_project.repo;

import com.example.recipe_project.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepo extends CrudRepository<Ingredient, Long> {

    List<Ingredient> findAllByRecipeId(long id);
}
