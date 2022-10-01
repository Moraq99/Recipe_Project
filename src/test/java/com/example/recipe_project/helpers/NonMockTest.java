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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class NonMockTest {

    @Autowired
    private RecipeRepo recipeRepo;


    @BeforeEach
    void setup(){
        recipeRepo.findAll();
    }

    @Test
    public void searchRecipesTest() throws Exception {


    }

    @Test
    public void findByIngredientTest(String keyWord, Recipe recipe){


    }




}
