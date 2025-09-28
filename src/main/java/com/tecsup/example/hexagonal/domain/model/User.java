package com.tecsup.example.hexagonal.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;

    //Logica de negocio
    //Es una regla de negocio que verifica si el campo email del usuario es válido bajo ciertas condiciones simples.
    //email != null → valida que el email no sea null.
    //email.contains("@") → revisa que contenga el carácter @.
    //email.contains(".") → revisa que tenga al menos un punto ".".
    //email.length() > 5 → comprueba que la longitud sea mayor a 5 caracteres (para evitar emails demasiado cortos).
    //👉 Si todas estas condiciones son verdaderas, retorna true (email válido).
    //👉 Si alguna falla, retorna false (email inválido).
    public boolean hasValidEmail() {
        return email != null &&
                email.contains("@") &&
                email.contains(".") &&
                email.length() > 5;
    }

    public boolean hasValidName() {
        return name != null &&
                !name.trim().isEmpty() &&
                name.length() >= 2;
    }

    //Para ver cuando envias nulos
    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
