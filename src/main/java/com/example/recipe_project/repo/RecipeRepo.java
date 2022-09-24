package com.example.recipe_project.repo;


import com.example.recipe_project.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecipeRepo extends CrudRepository<Recipe, Long> {





}
