package com.example.recipe_project.controller;

import com.example.recipe_project.model.Ingredient;
import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.service.IngredientService;
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

import java.util.List;


@Controller
public class RecipeController {

    private final TestDataLoader testDataLoader;

    private final RecipeService recipeService;

    private final IngredientService ingredientService;

    public RecipeController(TestDataLoader testDataLoader, RecipeService recipeService, IngredientService ingredientService) {
        this.testDataLoader = testDataLoader;
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping(value = "/recipe/{id}")
    public String getRecipe(@PathVariable("id") long id, Model model) {
        Recipe recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);

        return "recipe";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteRecipe(@PathVariable(name = "id") Long id) {
        recipeService.deleteById(id);

        return "redirect:/home";
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView showEditRecipe(@PathVariable(name = "id") Long id) {
        ModelAndView editView = new ModelAndView("editrecipe");
        Recipe recipe = recipeService.findById(id);
        List<Ingredient> ingredients = ingredientService.getAllById(recipe.getId());

        editView.addObject("recipe", recipe);
        editView.addObject("ingredients", ingredients);

        return editView;
    }

    @PostMapping(value = "/edit/{id}")
    public String updateRecipe(@PathVariable(name = "id") Long id, Recipe recipe) {
        recipeService.processIngredientsFromForm(recipe);
        recipeService.saveRecipe(recipe);

        return "redirect:/home";
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
    public String saveNewRecipe(Recipe recipe, Model model, @RequestParam("photo") MultipartFile photo) {

        try {

            if (recipe.getIngredients() == null
                    /* || recipe.getPhotoData() == null
                    || recipe.getInstruction() == null*/) {
                model.addAttribute("error", "Probáld újra");
                return "create";
            } else {
                recipe.setPhotoName(photo.getOriginalFilename());
                recipe.setPhotoType(photo.getContentType());
                recipe.setPhotoData(photo.getBytes());

                recipeService.processIngredientsFromForm(recipe);
                recipeService.saveRecipe(recipe);

                model.addAttribute("newrecipe", recipe);
                return "newrecipe";
            }

        } catch (IOException e) {
            e.printStackTrace();

            return "redirect:/newrecipe";
        }

    }

     /*@PostMapping(value = "/create-recipe")
    public String saveNewRecipe(Recipe recipe, @RequestParam("photo") MultipartFile photo) {

        try {

                recipe.setPhotoName(photo.getOriginalFilename());
                recipe.setPhotoType(photo.getContentType());
                recipe.setPhotoData(photo.getBytes());

                recipeService.processIngredientsFromForm(recipe);
                recipeService.saveRecipe(recipe);


        } catch (IOException e) {
            e.printStackTrace();

            return "redirect:/newrecipe";
        }

         return "redirect:/home";
    }*/

    @GetMapping(value = "/photo/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] downloadPhoto(@PathVariable long id) {
        Recipe recipe = recipeService.getById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found.")
        );
        return recipe.getPhotoData();
    }


}
