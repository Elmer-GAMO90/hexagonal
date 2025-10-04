package com.tecsup.example.hexagonal.infraestructure.adapter.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    String email;
    String password;


}
