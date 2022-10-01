package com.example.recipe_project.model;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Rating {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Recipe recipe;
    @ElementCollection
    private Map<Integer, Integer> rating;

    public Rating() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Map<Integer, Integer> getRatings() {
        return rating;
    }

    public void setRatings(Map<Integer, Integer> ratings) {
        this.rating = ratings;
    }
}