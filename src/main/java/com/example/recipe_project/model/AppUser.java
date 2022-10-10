package com.example.recipe_project.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
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
        this.isEnabled = true;
    }
    public AppUser(String firstName, String lastName,
                   String userName, String email, String password) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isAlreadyLoggedIn() {
        return alreadyLoggedIn;
    }
    public void setAlreadyLoggedIn(boolean alreadyLoggedIn) {
        this.alreadyLoggedIn = alreadyLoggedIn;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authors = new ArrayList<>();

        SimpleGrantedAuthority authorityUser = new SimpleGrantedAuthority("USER");
        SimpleGrantedAuthority authorityAdmin = new SimpleGrantedAuthority("ADMIN");

        authors.add(authorityUser);
        authors.add(authorityAdmin);

        return authors;
    }

    @Override
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {

    }

    @Override
    public String getUsername() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled;
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