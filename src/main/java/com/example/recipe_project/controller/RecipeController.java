package com.example.recipe_project.controller;

import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.service.RecipeService;
import com.example.recipe_project.service.TestDataLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RecipeController {

    private final TestDataLoader testDataLoader;

    private final RecipeService recipeService;

    public RecipeController(TestDataLoader testDataLoader, RecipeService recipeService) {
        this.testDataLoader = testDataLoader;
        this.recipeService = recipeService;
    }

    @GetMapping(value = "/recipe/{id}")
    public String getRecipe(@PathVariable("id") long id, Model model) {
        Recipe recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);

        return "recipe";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id){
        recipeService.deleteById(id);

        return "redirect:/home";
    }

    @GetMapping(value = "/create")
    public String createIngredientList(Model model) {
        model.addAttribute("recipe", new Recipe());

        return "createTemp";
    }

    @PostMapping(value = "/create")
    public String saveRecipeList(Recipe recipe, Model model) {
        recipeService.createIngredientDummies(recipe);

        model.addAttribute("newrecipe", recipe);

        return "newrecipe";
    }

    @PostMapping(value = "/create-recipe")
    public String saveNewRecipe(Recipe recipe){
        recipeService.processIngredientsFromForm(recipe);
        recipeService.saveRecipe(recipe);


        return "redirect:/home";
    }


}
