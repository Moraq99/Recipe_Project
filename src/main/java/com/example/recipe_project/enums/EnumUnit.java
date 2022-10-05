package com.example.recipe_project.enums;

public enum EnumUnit {

    Undefined("Unit"),

    L("litre"),

    ML("millilitre"),

    G("gram"),

    DKG("decagram"),

    KG("kilogram"),

    TBS("tablespoon"),

    TSP("teaspoon"),

    CUP("cup"),

    PCS("pieces"),

    PKG("package"),

    PINCH("pinch");

    private final String displayValue;

    EnumUnit(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public String getName(){
        return this.name();
    }
}
