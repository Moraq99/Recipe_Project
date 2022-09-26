package com.example.recipe_project.enums;

public enum EnumDifficulty {

    UNDEFINED("Difficulty"),
    VERY_EASY("Very easy"),
    EASY("Easy"),
    NORMAL("Normal"),
    DIFFICULT("Difficult"),
    VERY_DIFFICULT("Very difficult"),
    IMPOSSIBLE("Impossible");

    private final String displayValue;

    EnumDifficulty(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public String getName(){
        return this.name();
    }

}
