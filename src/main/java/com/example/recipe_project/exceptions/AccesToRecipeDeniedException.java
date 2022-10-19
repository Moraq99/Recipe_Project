package com.example.recipe_project.exceptions;

public class AccesToRecipeDeniedException extends Exception{

    public AccesToRecipeDeniedException() {
        super("Nincs hozzáférésed ehhez a recepthez!");
    }
}
