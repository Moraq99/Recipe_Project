package com.example.recipe_project.model;

import com.example.recipe_project.enums.EnumDifficulty;

import javax.persistence.*;


public class SearchFields {

    private String name;
    private boolean vegan;
    private boolean lactose_free;
    private boolean gluten_free;
    @Enumerated(EnumType.STRING)
    private EnumDifficulty difficulty;
    private String ingredient;

    private int prepTime;

    public SearchFields(){

    }

    public SearchFields(String name, boolean vegan, boolean lactose_free, boolean gluten_free, EnumDifficulty difficulty, String ingredient, int prepTime) {
        this.name = name;
        this.vegan = vegan;
        this.lactose_free = lactose_free;
        this.gluten_free = gluten_free;
        this.difficulty = difficulty;
        this.ingredient = ingredient;
        this.prepTime = prepTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isLactose_free() {
        return lactose_free;
    }

    public void setLactose_free(boolean lactose_free) {
        this.lactose_free = lactose_free;
    }

    public boolean isGluten_free() {
        return gluten_free;
    }

    public void setGluten_free(boolean gluten_free) {
        this.gluten_free = gluten_free;
    }

    public EnumDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(EnumDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }
}
