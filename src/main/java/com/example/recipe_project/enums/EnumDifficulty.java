package com.example.recipe_project.enums;

public enum EnumDifficulty {
    VERY_EASY("very easy"),
    EASY("easy"),
    NORMAL("normal"),
    DIFFICULT("difficult"),
    VERY_DIFFICULT("very difficult"),
    IMPOSSIBLE("impossible");

    private final String displayValue;

    EnumDifficulty(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
