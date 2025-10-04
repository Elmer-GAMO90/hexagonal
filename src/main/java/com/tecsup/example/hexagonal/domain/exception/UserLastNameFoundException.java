package com.tecsup.example.hexagonal.domain.exception;

public class UserLastNameFoundException extends RuntimeException {

    public UserLastNameFoundException(String lastName) {

        super("User not found with lastName: " + lastName);
    }
}
