package com.example.recipe_project.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class AppUser {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String emailAddress;
    private boolean alreadyLoggedIn;
    private boolean isEnabled;
    private boolean isAdmin;
    @OneToMany
    private List<Recipe> ownRecipes;
    @OneToMany
    private List<Recipe> favouriteRecipes;
    @OneToMany
    private List<AppUser> friends;
    @OneToMany
    private List<Comment> comments;
    public AppUser() {
    }
    public AppUser(String firstName, String lastName,
                   String userName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.emailAddress = emailAddress;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public boolean isAlreadyLoggedIn() {
        return alreadyLoggedIn;
    }
    public void setAlreadyLoggedIn(boolean alreadyLoggedIn) {
        this.alreadyLoggedIn = alreadyLoggedIn;
    }
    public boolean isEnabled() {
        return isEnabled;
    }
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    public List<Recipe> getOwnRecipes() {
        return ownRecipes;
    }
    public void setOwnRecipes(List<Recipe> ownRecipes) {
        this.ownRecipes = ownRecipes;
    }
    public List<Recipe> getFavouriteRecipes() {
        return favouriteRecipes;
    }
    public void setFavouriteRecipes(List<Recipe> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
    }
    public List<AppUser> getFriends() {
        return friends;
    }
    public void setFriends(List<AppUser> friends) {
        this.friends = friends;
    }
}