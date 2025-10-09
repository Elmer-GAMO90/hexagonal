package com.tecsup.example.hexagonal.infraestructure.adapter.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String lastname;
    private String lastmattern;
    private String email;
    private String dni;
    private Integer age;
}
