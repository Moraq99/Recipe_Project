package com.example.recipe_project.controller;


import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.repo.RecipeRepo;
import com.example.recipe_project.service.TestDataLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {

    private TestDataLoader testDataLoader;

    RecipeRepo recipeRepo;

    public RecipeController(TestDataLoader testDataLoader, RecipeRepo recipeRepo) {
        this.testDataLoader = testDataLoader;
        this.recipeRepo = recipeRepo;
    }

    @GetMapping(value = {"/mainPage", "/home"})
    public String getRecipe(Model model) {

        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);

        recipeRepo.save(recipe);

        return "index";
    }

    @GetMapping(value = {"/test"})
    public String loadRecipes() {
        testDataLoader.loadRecipes();
        return "redirect:/home";                //átirányitás a főoldalra
    }



    /*private final com.example.recept_x_project.service.recipeService recipeService;
    private com.example.recept_x_project.repo.recipeRepo recipeRepo;


    @Autowired
    public recipeController (recipeService recipeService) {
        this.recipeService = recipeService;
    }*/


}
