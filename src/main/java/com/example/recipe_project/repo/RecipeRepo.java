package com.example.recipe_project.repo;


import com.example.recipe_project.model.Ingredient;
import com.example.recipe_project.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Repository
public interface RecipeRepo extends CrudRepository<Recipe, Long> {





}
