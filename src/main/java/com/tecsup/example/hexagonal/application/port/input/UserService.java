package com.tecsup.example.hexagonal.application.port.input;

import com.tecsup.example.hexagonal.domain.model.User;

public interface UserService {
    //Crear un metodo para crear y buscar usuario
    User createUser(User newUser);

    User findUser(Long id);

    User findUserLastname(String lastName);
}
