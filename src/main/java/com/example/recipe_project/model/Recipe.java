package com.example.recipe_project.model;

import javax.persistence.*;

@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;
    @Enumerated(EnumType.STRING) // ! név szerint , nem a számsora szerint.
    private EnumDifficulty difficulty;
    public int preparationTime;
    private boolean vegan;
    private boolean lactose_free;
    private boolean gluten_free;
    private String ingredients;
    private String instruction;

    public Recipe () {};

    public Recipe(long id) {
        this.id = id;
    }

    public Recipe(String name, EnumDifficulty difficulty,
                  int preparationTime, boolean vegan,
                  boolean lactose_free, boolean gluten_free,
                  String ingredients, String instruction) {
        this.name = name;
        this.difficulty = difficulty;
        this.preparationTime = preparationTime;
        this.vegan = vegan;
        this.lactose_free = lactose_free;
        this.gluten_free = gluten_free;
        this.ingredients = ingredients;
        this.instruction = instruction;
    }

    public Recipe(String ingredients) {
        this.ingredients = ingredients;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnumDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(EnumDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", preparationTime=" + preparationTime +
                ", vegan=" + vegan +
                ", lactose_free=" + lactose_free +
                ", gluten_free=" + gluten_free +
                ", ingredients='" + ingredients + '\'' +
                ", instruction='" + instruction + '\'' +
                '}';
    }
}

