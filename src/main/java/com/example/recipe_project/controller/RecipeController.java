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
    public String editIngredients(@PathVariable(name = "id") Long id, Model model) {
        Recipe recipe = recipeService.findById(id);

        model.addAttribute("recipe", recipe);

        return "editingredients";
    }

    @PostMapping(value = "/edit")
    public String updateIngredients(Recipe recipe, Model model) {
        Recipe recipeOrigin = recipeService.findById(recipe.getId());
        recipeOrigin.setNumOfIngredients(recipe.getNumOfIngredients());
        recipeService.createIngredientDummies(recipeOrigin);
        List<Ingredient> ingredients = recipeOrigin.getIngredients();

        model.addAttribute("recipe", recipeOrigin);
        model.addAttribute("ingredients", ingredients);

        return "editrecipe";
    }

    /*@GetMapping(value = "/edit/{id}")
    public String showEditRecipe(@PathVariable(name = "id") Long id, Model model) {
        Recipe recipe = recipeService.findById(id);
        List<Ingredient> ingredients = ingredientService.getAllById(recipe.getId());

        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", ingredients);

        return "editrecipe";
    }*/

    @PostMapping(value = "/edit-recipe")
    public String updateRecipe(Recipe recipe, @RequestParam(value = "photo", required = false) MultipartFile photo) {

        if (photo.isEmpty()) {
            recipeService.updatePhoto(recipe);
            recipeService.processIngredientsFromForm(recipe);
            recipeService.saveRecipe(recipe);

            return "redirect:/home";
        } else {
            try {
                recipe.setPhotoName(photo.getOriginalFilename());
                recipe.setPhotoType(photo.getContentType());
                recipe.setPhotoData(photo.getBytes());

                recipeService.processIngredientsFromForm(recipe);
                recipeService.saveRecipe(recipe);

                return "redirect:/home";
            } catch (IOException e) {
                e.printStackTrace();

                return "redirect:/home";
            }
        }

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

            System.out.println();
            if (recipe.getIngredients().size() == 0   //true
                  //|| recipe.getPhotoData() == null
                    || recipe.getInstruction().isBlank()) {
                model.addAttribute("error", "Próbáld újra");
                return "create";
            } else {
                recipe.setPhotoName(photo.getOriginalFilename());
                recipe.setPhotoType(photo.getContentType());
                recipe.setPhotoData(photo.getBytes());

                recipeService.processIngredientsFromForm(recipe);
                recipeService.saveRecipe(recipe);

                model.addAttribute("newrecipe", recipe);
                return "redirect:/home";
            }

        } catch (IOException e) {
            e.printStackTrace();

            return "redirect:/home";
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
