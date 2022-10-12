package com.example.recipe_project.service;

import com.example.recipe_project.exceptions.UsernameTakenException;
import com.example.recipe_project.model.AppUser;
import com.example.recipe_project.repo.AppUserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AppUserService implements UserDetailsService {

    private AppUserRepo appUserRepo;

    private final PasswordEncoder encoder;

    public AppUserService(AppUserRepo appUserRepo, PasswordEncoder encoder) {
        this.appUserRepo = appUserRepo;
        this.encoder = encoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepo.findAppUserByUserName(username);
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
        } else {
            throw new UsernameTakenException();
        }
    }

    private boolean isUsernameTaken(String username) {
        try {
            loadUserByUsername(username);
            return true;
        } catch (NoResultException e) {
            return false;
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
}
