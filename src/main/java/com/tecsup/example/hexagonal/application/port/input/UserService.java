package com.tecsup.example.hexagonal.application.port.input;

import com.tecsup.example.hexagonal.domain.model.User;

import java.util.List;

public interface UserService {
    //Crear un metodo para crear y buscar usuario
    User createUser(User newUser);

    User findUser(Long id);

    User findUserLastname(String lastName);

    User findUserDni(String dni);

    User findUserAge(Integer age);

    List<User> findUsersYoungerThan18();
}
