package com.example.recipe_project.model;

import com.example.recipe_project.enums.EnumUnit;

import javax.persistence.*;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue
    private Long id;

    private String name ;
    private double quantity ;
    private EnumUnit unit ;
    @ManyToOne
    private Recipe recipe;

    public Ingredient() {
    };
    public Ingredient(String name, double quantity, EnumUnit unit, Recipe recipe) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.recipe = recipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public EnumUnit getUnit() {
        return unit;
    }

    public void setUnit(EnumUnit unit) {
        this.unit = unit;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
