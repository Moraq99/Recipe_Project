package com.example.recipe_project.controller;

import com.example.recipe_project.model.AppUser;
import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Controller
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


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

    @GetMapping("/appuser/{id}")
    public String user(Model model, @PathVariable(name = "id") Long id){
        AppUser appUser = appUserService.loadUserById(id);
        List<Recipe> ownRecipes = appUser.getOwnRecipes();
        List<Recipe> favouriteRecipes = appUser.getFavouriteRecipes();
        List<AppUser> friends = appUser.getFriends();

        model.addAttribute("appuser", appUser);
        model.addAttribute("ownrecipes", ownRecipes);
        model.addAttribute("favouriterecipes", favouriteRecipes);
        model.addAttribute("friends", friends);

        return("appuser");
    }


    @GetMapping("/admin")
    public String admin(){
        return("redirect:/admin");
    }

    @GetMapping("/register")
    public String register() {
        return ("redirect:/home");
    }

    @GetMapping(value = "/appuser/photo/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] downloadPhoto(@PathVariable long id) {
        AppUser user = appUserService.getById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found.")
        );
        return user.getPhotoData();
    }
}

