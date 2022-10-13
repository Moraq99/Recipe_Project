package com.example.recipe_project.enums;

public enum EnumUnit {

    Undefined("Egység"),

    L("liter"),

    ML("ml"),

    G("g"),

    DKG("dkg"),

    KG("kg"),

    TBS("evőkanál"),

    TSP("teáskanál"),

    CUP("bögre"),

    PCS("db"),

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
