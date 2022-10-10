package com.example.recipe_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AppUserController {


    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "/logout")
    public String getLogOut(){
        return "redirect:/logout";
    }

    @GetMapping(value = "/login-error")
    public String getLoginErrorPage(Model model) {
        model.addAttribute("loginError", true);

        return "login";
    }

    @GetMapping("/user")
    public String user(){
        return("redirect:/User");
    }


    @GetMapping("/admin")
    public String admin(){
        return("redirect:/admin");
    }

    @GetMapping("/register")
    public String register() {
        return ("redirect:/home");
    }
}

