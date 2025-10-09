package com.tecsup.example.hexagonal.domain.exception;

public class UserDniNotFoundException extends RuntimeException {

    public UserDniNotFoundException(String dni) {

        super("User not found with DNI: " + dni);
    }
}
