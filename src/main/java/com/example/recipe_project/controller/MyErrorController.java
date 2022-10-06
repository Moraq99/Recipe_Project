package com.example.recipe_project.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status =
                request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            model.addAttribute("ergya", statusCode);
        }
        return "error";
    }
}
