package com.example.recipe_project.service;

import com.example.recipe_project.enums.EnumDifficulty;
import com.example.recipe_project.model.Ingredient;
import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.repo.RecipeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RecipeServiceTest {

    @Mock
    private RecipeRepo recipeRepo;

    @InjectMocks
    private RecipeService recipeService;

    @BeforeEach
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void getAllTest() {
        List<Recipe> recipeList = new ArrayList<>();
        List<Ingredient> temp = new ArrayList<>();

        Recipe r1 = new Recipe("Egy", EnumDifficulty.EASY, 0,
                true, false, true, temp, "fasza lesz :");
        Recipe r2 = new Recipe("Kettő", EnumDifficulty.NORMAL, 26,
                true, false, true, temp, "fasza  :D");
        Recipe r3 = new Recipe("Három", EnumDifficulty.DIFFICULT, 27,
                true, false, true, temp, "fasza lesz :D");
        r1.setId(1);
        r2.setId(2);
        r3.setId(3);
        recipeList.add(r1);
        recipeList.add(r3);
        recipeList.add(r2);

        when(recipeRepo.findAll()).thenReturn(recipeList);

        List<Recipe> recipeTemp = recipeService.getAll();
        assertEquals(3, recipeTemp.size());
        assertEquals("Egy", recipeTemp.get(0).getName());

        //verify(recipeRepo, times(1)).findAll();
    }

    @Test
    public void findByIdTest() {
        when(recipeRepo.findById(1L)).thenReturn(Optional.of(new Recipe(1, "Bakker",
                EnumDifficulty.VERY_DIFFICULT, 30)));

        Recipe r1 = recipeService.findById(1);
        assertEquals("Bakker", r1.getName());
        assertEquals(30, r1.getPreparationTime());
        assertEquals(EnumDifficulty.VERY_DIFFICULT, r1.getDifficulty());
    }

}