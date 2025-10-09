package com.tecsup.example.hexagonal.domain.exception;

public class UserAgeNotFoundException extends RuntimeException {

    public UserAgeNotFoundException(String age) {

        super("User not found with age: " + age);
    }
}
