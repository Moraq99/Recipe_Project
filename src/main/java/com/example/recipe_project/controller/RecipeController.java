package com.example.recipe_project.controller;


import com.example.recipe_project.model.Ingredient;
import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.model.SearchFields;
import com.example.recipe_project.repo.RecipeRepo;
import com.example.recipe_project.service.RecipeService;
import com.example.recipe_project.service.TestDataLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Controller
public class RecipeController {

    private final TestDataLoader testDataLoader;

    private final RecipeService recipeService;

    public RecipeController(TestDataLoader testDataLoader, RecipeService recipeService) {
        this.testDataLoader = testDataLoader;
        this.recipeService = recipeService;
    }

    @GetMapping(value = {"/", "/home"})
    public String getHomePage(Model model) {
        List<Recipe> recipes = new ArrayList<>((Collection) recipeService.getAll());

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
        Recipe recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);

        return "recipe";
    }

    @GetMapping(value = "/search")
    public String getSearchPage(Model model) {
        model.addAttribute("search", new SearchFields());

        return "search";
    }

    @PostMapping(value = "/search")
    public String displaySeachResults(SearchFields searchFields, Model model) {
        /*Set<Recipe> recipes = recipeService.getSearchResults(searchFields);*/
        List<Recipe> recipes = recipeService.searchRecipes(searchFields);
        model.addAttribute("recipes", recipes);

        return "searchresult";
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

        for (int i = 0; i < recipe.getNumOfIngredients(); i++) {
            recipe.addIngredient(new Ingredient());
        }

        model.addAttribute("newrecipe", recipe);

        return "newrecipeTemp";
    }

    @PostMapping(value = "/create-recipe")
    public String saveNewRecipe(Recipe recipe){

        List<Ingredient> ingredientList= recipe.getIngredients();
        for (Ingredient ingredient: ingredientList){
            if (!ingredient.getName().isBlank()) {
                ingredient.setRecipe(recipe);
            } else {
                ingredientList.remove(ingredient);
            }

        }
        recipeService.saveRecipe(recipe);


        return "redirect:/home";
    }



    /*private final com.example.recept_x_project.service.recipeService recipeService;
    private com.example.recept_x_project.repo.recipeRepo recipeRepo;


    @Autowired
    public recipeController (recipeService recipeService) {
        this.recipeService = recipeService;
    }*/


}
