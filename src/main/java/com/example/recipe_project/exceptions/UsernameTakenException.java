package com.example.recipe_project.exceptions;

public class UsernameTakenException extends Exception{

    public UsernameTakenException() {
        super("Ez a felhasználónév már foglalt! Kérem válasszon másikat!");
    }
}
