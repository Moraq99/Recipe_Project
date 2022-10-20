package com.example.recipe_project.repo;


import com.example.recipe_project.model.AppUser;
import com.example.recipe_project.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecipeRepo extends CrudRepository<Recipe, Long> {

    Recipe findByName(String name);

    List<Recipe> findAllByCreatedBy(AppUser user);

}
