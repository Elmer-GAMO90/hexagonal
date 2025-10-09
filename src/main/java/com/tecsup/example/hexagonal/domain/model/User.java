package com.tecsup.example.hexagonal.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//Evita la necesidad de escribir manualmente constructores con muchos parámetros o métodos para cada campo
@Builder
public class User {
    private Long id;
    private String name;
    private String lastname;
    private String lastmattern;
    private String email;
    private String dni;
    private Integer age;
    private String phonenumber;
    private String password;
    private boolean enabled;

    private Role role;

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

    public boolean hasValidLastname() {
        return lastname != null &&
                !lastname.trim().isEmpty() &&
                lastname.length() >= 2;
    }

    public boolean hasValidLastmattern() {
        return lastmattern != null &&
                !lastmattern.trim().isEmpty() &&
                lastmattern.length() >= 2;
    }

    public boolean hasValidDni() {
        return dni != null &&
                !dni.trim().isEmpty() &&
                dni.length() <= 8;
    }

    public boolean hasValidAge() {
        return age != null &&
                age > 0 &&
                age <= 120;
    }

    //Para ver cuando envias nulos
    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', lastname='" + lastname + "', email='" + email + "', password='" + password + "'}";
    }
}
