package com.example.recipe_project.controller;

import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.service.RecipeService;
import com.example.recipe_project.service.TestDataLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


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
    @GetMapping(value = "/edit/{id}")
    public ModelAndView showEditRecipe(@PathVariable(name = "id") Long id) {
        ModelAndView editView = new ModelAndView("edit-recipe");
        Recipe recipe = recipeService.findById(id);
        editView.addObject("recipe", recipe);

        return editView;
    }

    @GetMapping(value = "/create")
    public String createIngredientList(Model model) {
        model.addAttribute("recipe", new Recipe());

        return "create";
    }

    @PostMapping(value = "/create")
    public String saveRecipeList(Recipe recipe, Model model) {

            recipeService.createIngredientDummies(recipe);

            model.addAttribute("newrecipe", recipe);

            return "newrecipe";
    }

    @PostMapping(value = "/create-recipe")
    public String saveNewRecipe(Recipe recipe, @RequestParam("photo")MultipartFile photo){

        try {
            recipe.setPhotoName(photo.getOriginalFilename());
            recipe.setPhotoType(photo.getContentType());
            recipe.setPhotoData(photo.getBytes());

            recipeService.processIngredientsFromForm(recipe);
            recipeService.saveRecipe(recipe);

            return "redirect:/home";
        }catch (IOException e) {
            e.printStackTrace();


            return "redirect:/newrecipe";
        }
    }

    @GetMapping(value = "/photo/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] downloadPhoto(@PathVariable long id){
        Recipe recipe = recipeService.getById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found.")
        );
        return recipe.getPhotoData();
    }



}
