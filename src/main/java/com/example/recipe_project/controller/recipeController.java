package com.example.recept_x_project.controller;

import com.example.recept_x_project.model.Recipe;
import com.example.recept_x_project.repo.RecipeRepo;
import com.example.recept_x_project.service.TestDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class recipeController {


    @Autowired
    private TestDataLoader testDataLoader;

@GetMapping(value = {"/mainPage", "/home"})
public String getRecipe(Model model){

    Recipe recipe = new Recipe();
    model.addAttribute("recipe",recipe);

    recipeRepo.save(recipe);

    return "mainPage";
}

    @GetMapping(value = {"/test"})
    public String Recipes(){
        testDataLoader.Recipes();
        return "redirect:/home";                //átirányitás a főoldalra
    }

@Autowired
RecipeRepo recipeRepo;

    /*private final com.example.recept_x_project.service.recipeService recipeService;
    private com.example.recept_x_project.repo.recipeRepo recipeRepo;


    @Autowired
    public recipeController (recipeService recipeService) {
        this.recipeService = recipeService;
    }*/



}
