package com.example.recipe_project.controller;


import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.repo.RecipeRepo;
import com.example.recipe_project.service.TestDataLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class RecipeController {

    private final TestDataLoader testDataLoader;

    private final RecipeRepo recipeRepo;

    public RecipeController(TestDataLoader testDataLoader, RecipeRepo recipeRepo) {
        this.testDataLoader = testDataLoader;
        this.recipeRepo = recipeRepo;
    }

    @GetMapping(value = {"/", "/home"})
    public String getHomePage(Model model) {
        List<Recipe> recipes = new ArrayList<>((Collection) recipeRepo.findAll());
        model.addAttribute("recipes", recipes);

        return "index";
    }

    @GetMapping(value = {"/test"})
    public String loadRecipes() {
        testDataLoader.loadRecipes();
        return "redirect:/home";                //átirányitás a főoldalra
    }

    @GetMapping(value = "/recipe/{id}")
    public String getRecipe(@PathVariable("id") long id, Model model) {
        Recipe recipe = recipeRepo.findById(id).orElseThrow();
        model.addAttribute("recipe", recipe);

        return "recipeTemp";
    }



    /*private final com.example.recept_x_project.service.recipeService recipeService;
    private com.example.recept_x_project.repo.recipeRepo recipeRepo;


    @Autowired
    public recipeController (recipeService recipeService) {
        this.recipeService = recipeService;
    }*/


}
