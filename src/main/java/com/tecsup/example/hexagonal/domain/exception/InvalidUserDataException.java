package com.tecsup.example.hexagonal.domain.exception;

public class InvalidUserDataException extends RuntimeException {

    public InvalidUserDataException(String msg) {
        //Llamada a super(...): super(...) llama al constructor de la clase padre (Exception o RuntimeException).
        //Lo que hace es pasarle un mensaje compuesto:

        super("Invalid user data: " + msg);
    }
}
