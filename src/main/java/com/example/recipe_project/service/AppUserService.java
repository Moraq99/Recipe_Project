package com.example.recipe_project.service;

import com.example.recipe_project.exceptions.AccesToRecipeDeniedException;
import com.example.recipe_project.exceptions.UsernameTakenException;
import com.example.recipe_project.model.AppUser;
import com.example.recipe_project.model.Recipe;
import com.example.recipe_project.repo.AppUserRepo;
import com.example.recipe_project.repo.RecipeRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService implements UserDetailsService {

    private AppUserRepo appUserRepo;

    private RecipeRepo recipeRepo;

    private final PasswordEncoder encoder;

    public AppUserService(AppUserRepo appUserRepo, RecipeRepo recipeRepo, PasswordEncoder encoder) {
        this.appUserRepo = appUserRepo;
        this.recipeRepo = recipeRepo;
        this.encoder = encoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        return appUserRepo.findAppUserByUsername(username);
    }

    public AppUser loadUserById(Long id){
        return appUserRepo.findById(id).orElseThrow();
    }
    public Optional<AppUser> getById(long id) {
        return appUserRepo.findById(id);
    }
    public AppUser getByAppUser (AppUser appuser){return (AppUser) loadUserByUsername(appuser.getUsername());}

    public AppUser getLoggedInUser() {
        return (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Transactional
    public void saveUser(AppUser user) throws UsernameTakenException {
        if (!isUsernameTaken(user.getUsername())) {
            user.setPassword(encoder.encode(user.getPassword()));
            appUserRepo.save(user);
        } else {
            throw new UsernameTakenException();
        }
    }

    private boolean isUsernameTaken(String username) {
        if (loadUserByUsername(username) == null) {
            return false;
        } else  {
            return true;
        }
    }

    @Transactional
    public void changePassword(String newPassword) {
        AppUser loggedInUser = getLoggedInUser();
        AppUser dbUser = (AppUser) loadUserByUsername(loggedInUser.getUsername());

        dbUser.setPassword(encoder.encode(newPassword));
        dbUser.setAlreadyLoggedIn(true);

        loggedInUser.setAlreadyLoggedIn(true);
    }

    public boolean gotAccesToRecipe(Recipe recipe) throws AccesToRecipeDeniedException{
        List<Recipe> recipes = recipeRepo.findAllByCreatedBy(getLoggedInUser());

        if (recipes.contains(recipe)) {
            return true;
        } else {
            throw new AccesToRecipeDeniedException();
        }
    }
}
