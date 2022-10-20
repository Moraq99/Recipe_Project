package com.example.recipe_project.controller;

import com.example.recipe_project.exceptions.EmptyRegistrationFieldsException;
import com.example.recipe_project.exceptions.UsernameTakenException;
import com.example.recipe_project.model.AppUser;
import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
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

        if (appUser.getId() == null){
            appUser = appUserService.getLoggedInUser();
        }

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

    @GetMapping("registration")
    public String getRegistration(Model model){
        model.addAttribute("appuser", new AppUser());

        return "registration";
    }

    @PostMapping("registration")
    public String register(AppUser appUser, Model model, @RequestParam("photo") MultipartFile photo) {

        try {
            if (appUser.getPassword().isEmpty()
                    || appUser.getEmail().isEmpty()
                    || appUser.getUsername().isEmpty()
                    || appUser.getFirstName().isEmpty()
                    || appUser.getLastName().isEmpty()
            ) {
                throw new EmptyRegistrationFieldsException();
            }


            if (!photo.isEmpty()) {
                appUser.setPhotoName(photo.getOriginalFilename());
                appUser.setPhotoType(photo.getContentType());
                appUser.setPhotoData(photo.getBytes());

            }
            appUserService.saveUser(appUser);
            return "redirect:/appuser/" + appUser.getId();

        } catch (IOException e) {
            e.printStackTrace();

            return "redirect:/home";
        } catch (UsernameTakenException e) {
            model.addAttribute("regError", e.getMessage());
            model.addAttribute("appuser", new AppUser());

            return "registration";
        } catch (EmptyRegistrationFieldsException f) {
            model.addAttribute("fieldError", f.getMessage());
            model.addAttribute("appuser", new AppUser());

            return "registration";
        }

    }



    @GetMapping(value = "/appuser/photo/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] downloadPhoto(@PathVariable long id) {
        AppUser user = appUserService.getById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found.")
        );
        return user.getPhotoData();
    }
}

