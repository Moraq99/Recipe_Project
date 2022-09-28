package com.example.recipe_project.enums;

public enum EnumUnit {
    L("liter"),

    ML("mililiter"),

    G("gramm"),

    DKG("dekagramm"),

    KG("kilogramm"),

    TBS("evőkanál"),

    TSP("teáskanál"),

    CUP("bögre"),

    PCS("darab"),

    PKG("csomag"),

    PINCH("csipet");

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
