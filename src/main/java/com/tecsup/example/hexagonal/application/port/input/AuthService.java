package com.tecsup.example.hexagonal.application.port.input;

import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infraestructure.adapter.input.rest.dto.AuthResponse;

public interface AuthService {

    //Creación de metodos
    AuthResponse login(String email, String password);

    String generateToken(User user);
}
