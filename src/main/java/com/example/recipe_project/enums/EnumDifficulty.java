package com.example.recipe_project.enums;

public enum EnumDifficulty {

    UNDEFINED("Nehézség"),
    VERY_EASY("Gyerekjáték"),
    EASY("Könnyű"),
    NORMAL("Normál"),
    DIFFICULT("Bonyolultabb"),
    VERY_DIFFICULT("Gordon Ramsay"),
    IMPOSSIBLE("Gordon SE");

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
