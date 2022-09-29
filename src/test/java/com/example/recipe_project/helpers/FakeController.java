package com.example.recipe_project.helpers;

import com.example.recipe_project.controller.MainController;
import com.example.recipe_project.service.TestDataLoader;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class FakeController {

    private TestDataLoader testDataLoader;

    public FakeController(TestDataLoader testDataLoader) {
        this.testDataLoader = testDataLoader;
    }

    public void loadRecipes() {
        /*testDataLoader.loadRecipes();*/
    }

}
