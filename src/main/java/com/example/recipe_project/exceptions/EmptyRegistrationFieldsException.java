package com.example.recipe_project.exceptions;

public class EmptyRegistrationFieldsException extends Exception{

    public EmptyRegistrationFieldsException() {
        super("A mezők kitöltése kötelező!");
    }
}
