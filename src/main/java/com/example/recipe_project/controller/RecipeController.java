package com.example.recipe_project.controller;

import com.example.recipe_project.model.Comment;
import com.example.recipe_project.exceptions.AccesToRecipeDeniedException;
import com.example.recipe_project.model.Ingredient;
import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.service.CommentService;
import com.example.recipe_project.service.AppUserService;
import com.example.recipe_project.service.IngredientService;
import com.example.recipe_project.service.RecipeService;
import com.example.recipe_project.service.TestDataLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final CommentService commentService;

    private final AppUserService appUserService;

    public RecipeController(TestDataLoader testDataLoader, RecipeService recipeService, IngredientService ingredientService, AppUserService appUserService, CommentService commentService) {
        this.testDataLoader = testDataLoader;
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.commentService = commentService;
        this.appUserService = appUserService;
    }

    @GetMapping(value = "/recipe/{id}")
    public String getRecipe(@PathVariable("id") long id, Model model) {
        Recipe recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);

        List<Comment> commentList = commentService.getCommentsByRecipe(recipe);
        model.addAttribute("comments", commentList);

        return "recipe";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteRecipe(@PathVariable(name = "id") Long id, Model model) {
        try {
            Recipe recipe = recipeService.findById(id);

            appUserService.gotAccesToRecipe(recipe);
            model.addAttribute("recipe", recipe);

            recipeService.deleteById(id);
            return "redirect:/home";

        } catch (AccesToRecipeDeniedException e) {
            model.addAttribute("denied", e.getMessage());

            return "error";
        }
    }

    @GetMapping(value = "/edit/{id}")
    public String editIngredients(@PathVariable(name = "id") Long id, Model model) {
        try {
            Recipe recipe = recipeService.findById(id);

            appUserService.gotAccesToRecipe(recipe);
            model.addAttribute("recipe", recipe);

            return "editingredients";

        } catch (AccesToRecipeDeniedException e) {
            model.addAttribute("denied", e.getMessage());

            return "error";
        }
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

    @PostMapping(value = "/edit-recipe")
    public String updateRecipe(Recipe recipe, @RequestParam(value = "photo", required = false) MultipartFile photo) {

        if (photo.isEmpty()) {
            recipeService.updatePhoto(recipe);
            recipeService.processIngredientsFromForm(recipe);
            recipe.setCreatedBy(appUserService.getLoggedInUser());
            recipeService.saveRecipe(recipe);

            return "redirect:/home";
        } else {
            try {
                recipe.setPhotoName(photo.getOriginalFilename());
                recipe.setPhotoType(photo.getContentType());
                recipe.setPhotoData(photo.getBytes());

                recipe.setCreatedBy(appUserService.getLoggedInUser());
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
            if (recipe.getIngredients().size() == 0
                    || recipe.getInstruction().isBlank()) {
                model.addAttribute("error", "Pr??b??ld ??jra");
                return "create";
            } else {
                recipe.setPhotoName(photo.getOriginalFilename());
                recipe.setPhotoType(photo.getContentType());
                recipe.setPhotoData(photo.getBytes());

                recipe.setCreatedBy(appUserService.getLoggedInUser());
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

    @GetMapping(value = "/photo/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] downloadPhoto(@PathVariable long id) {
        Recipe recipe = recipeService.getById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found.")
        );
        return recipe.getPhotoData();
    }


}
