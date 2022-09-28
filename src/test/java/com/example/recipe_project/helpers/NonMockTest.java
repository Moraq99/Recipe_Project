package com.example.recipe_project.helpers;

import com.example.recipe_project.enums.EnumDifficulty;
import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.model.SearchFields;
import com.example.recipe_project.repo.RecipeRepo;
import com.example.recipe_project.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NonMockTest {

    @Autowired
    private RecipeRepo recipeRepo;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private FakeController fakeController;

    @BeforeEach
    void setup(){
        fakeController.loadRecipes();
    }

    @Test
    public void searchRecipesTest() throws Exception {

        SearchFields searchFields = new SearchFields();
        searchFields.setIngredient("");
        searchFields.setName("alma");
        searchFields.setDifficulty(EnumDifficulty.UNDEFINED);
        List<Recipe> d1 = recipeService.searchRecipes(searchFields);
        assertEquals(0, d1.size());

    }



}
