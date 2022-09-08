package com.example.recipe_project.controller;


import com.example.recipe_project.repo.RecipeRepo;
import com.example.recipe_project.service.TestDataLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {

    private final TestDataLoader testDataLoader;

    private final RecipeRepo recipeRepo;

    public RecipeController(TestDataLoader testDataLoader, RecipeRepo recipeRepo) {
        this.testDataLoader = testDataLoader;
        this.recipeRepo = recipeRepo;
    }

    @GetMapping(value = {"/", "/home"})
    public String getHomePage() {
        return "index";
    }

    @GetMapping(value = {"/test"})
    public String loadRecipes() {
        testDataLoader.loadRecipes();
        return "redirect:/home";                //átirányitás a főoldalra
    }

    @GetMapping(value = "/recipe")
    public String getRecipe() {
        return "recipe";
    }



    /*private final com.example.recept_x_project.service.recipeService recipeService;
    private com.example.recept_x_project.repo.recipeRepo recipeRepo;


    @Autowired
    public recipeController (recipeService recipeService) {
        this.recipeService = recipeService;
    }*/


}
