package com.example.recipe_project.service;

import com.example.recipe_project.exceptions.UsernameTakenException;
import com.example.recipe_project.model.AppUser;
import com.example.recipe_project.repo.AppUserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Service
public class AppUserService implements UserDetailsService {

    private AppUserRepo appUserRepo;

    public AppUserService(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // can be used after AppUser class implements UserDetails
        /*return appUserRepo.findAppUserByUserName(username);*/
        return null;
    }

    public AppUser getLoggedInUser() {
        return (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Transactional
    public void saveUser(AppUser user) throws UsernameTakenException {
        if (!isUsernameTaken(user.getUsername())) {
            //TODO
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
        //TODO
    }
}
