package com.example.recept_x_project.controller;

import com.example.recept_x_project.service.TestDataLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private TestDataLoader testDataLoader;


    public MainController(TestDataLoader testDataLoader) {
        this.testDataLoader = testDataLoader;
    }



}
