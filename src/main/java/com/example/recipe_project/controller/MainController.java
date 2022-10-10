package com.example.recipe_project.controller;


import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.model.SearchFields;
import com.example.recipe_project.service.RecipeService;
import com.example.recipe_project.service.TestDataLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController {
    private TestDataLoader testDataLoader;

    private final RecipeService recipeService;


    public MainController(TestDataLoader testDataLoader, RecipeService recipeService) {
        this.testDataLoader = testDataLoader;
        this.recipeService = recipeService;
    }

    @GetMapping(value = {"/", "/home"})
    public String getHomePage(Model model) {
        List<Recipe> topRated = recipeService.getRandomRecipes();
        List<Recipe> recipes = recipeService.getAll();

        if (recipes.size() < 2) {
            try {
                testDataLoader.loadRecipes();
                topRated = recipeService.getRandomRecipes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("recipes", topRated);

        return "index";
    }

    @GetMapping(value = {"/test"})
    public String loadRecipes() throws Exception {
        testDataLoader.loadRecipes();
        return "redirect:/home";
    }

    @GetMapping(value = "/search")
    public String getSearchPage(Model model) {
        model.addAttribute("search", new SearchFields());

        return "search";
    }

    @PostMapping(value = "/search")
    public String displaySeachResults(SearchFields searchFields, Model model) {
        List<Recipe> recipes = recipeService.searchRecipes(searchFields);

        if (recipes.size() == 0) {
            model.addAttribute("message", "Nincs találat");
        } else {
            model.addAttribute("recipes", recipes);
        }
        return "searchresult";
    }

    @GetMapping(value = "/recipes")
    public String displayRecipes(Model model) {
        List<Recipe> recipes = new ArrayList<>((Collection) recipeService.getAll());

        model.addAttribute("recipes", recipes);

        return "recipes";
    }

}
