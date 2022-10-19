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
    private String username;
    private String email;
    private String password;
    private boolean alreadyLoggedIn;
    private boolean isEnabled;
    private boolean isAdmin;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Recipe> ownRecipes;
    @OneToMany
    private List<Recipe> favouriteRecipes;
    @OneToMany
    private List<AppUser> friends;
    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<Comment> comments;
    private String photoType;

    private String photoName;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] photoData;
    public AppUser() {
        this.isEnabled = true;
    }
    public AppUser (String firstName, String lastName, String username){
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }
    public AppUser(String firstName, String lastName,
                   String username, String email, String password) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
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
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    public void setUsername(String userName) {
        this.username = userName;
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

    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "username='" + username + '\'' +
                '}';
    }
}