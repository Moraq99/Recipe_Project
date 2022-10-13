package com.example.recipe_project.model;

import com.example.recipe_project.enums.EnumDifficulty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy="recipe",cascade=CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();

    @Transient
    private int numOfIngredients;

    @Lob
    private String instruction;

    private String photoType;

    private String photoName;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] photoData;

    @ManyToOne
    private AppUser createdBy;


    public Recipe () {};

    public Recipe(long id) {
        this.id = id;
    }

    public Recipe(String name, EnumDifficulty difficulty,
                  int preparationTime, boolean vegan,
                  boolean lactose_free, boolean gluten_free,
                  List<Ingredient> ingredients, String instruction) {
        this.name = name;
        this.difficulty = difficulty;
        this.preparationTime = preparationTime;
        this.vegan = vegan;
        this.lactose_free = lactose_free;
        this.gluten_free = gluten_free;
        this.ingredients = ingredients;
        this.instruction = instruction;
    }

    public Recipe(long id, String name, EnumDifficulty difficulty,
                  int preparationTime) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.preparationTime = preparationTime;
    }

    public Recipe(long id, String name, EnumDifficulty difficulty, int preparationTime, boolean vegan, boolean lactose_free,
                  boolean gluten_free, List<Ingredient> ingredients, int numOfIngredients, String instruction, String photoType, String photoName, byte[] photoData) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.preparationTime = preparationTime;
        this.vegan = vegan;
        this.lactose_free = lactose_free;
        this.gluten_free = gluten_free;
        this.ingredients = ingredients;
        this.numOfIngredients = numOfIngredients;
        this.instruction = instruction;
        this.photoType = photoType;
        this.photoName = photoName;
        this.photoData = photoData;
    }

    public Recipe(String name, EnumDifficulty difficulty, int preparationTime, boolean vegan, boolean lactose_free,
                  boolean gluten_free, List<Ingredient> ingredients, String instruction, String photoType,
                  String photoName, byte[] photoData) {
        this.name = name;
        this.difficulty = difficulty;
        this.preparationTime = preparationTime;
        this.vegan = vegan;
        this.lactose_free = lactose_free;
        this.gluten_free = gluten_free;
        this.ingredients = ingredients;
        this.instruction = instruction;
        this.photoType = photoType;
        this.photoName = photoName;
        this.photoData = photoData;
    }

    public Recipe(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getNumOfIngredients() {
        return numOfIngredients;
    }

    public void setNumOfIngredients(int numOfIngredients) {
        this.numOfIngredients = numOfIngredients;
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

    public AppUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(AppUser createdBy) {
        this.createdBy = createdBy;
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

