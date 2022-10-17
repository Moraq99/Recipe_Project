package com.example.recipe_project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private AppUser appUser;
    @ManyToOne
    private Recipe recipe;
    private LocalDateTime createComm;
    @Lob
    private String comment;

    public Comment() {
    }

    public Comment(Recipe recipe, String comment) {
        this.recipe = recipe;
        this.comment = comment;
        this.createComm = LocalDateTime.now();
    }

    public Comment(AppUser appUser, Recipe recipe, String comment) {
        this.appUser = appUser;
        this.recipe = recipe;
        this.comment = comment;
        this.createComm = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }
    public String getAppuserUsername(){
        return appUser.getUsername();
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalDateTime getCreateComm() {
        return createComm;
    }

    public void setCreateComm(LocalDateTime create) {
        this.createComm = create;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", appUser=" + appUser +
                ", recipe=" + recipe +
                ", createComm=" + createComm +
                ", comment='" + comment + '\'' +
                '}';
    }
}