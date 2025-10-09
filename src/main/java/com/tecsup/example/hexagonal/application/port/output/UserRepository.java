package com.tecsup.example.hexagonal.application.port.output;

import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infraestructure.adapter.output.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);

    //Se usa Optional<User> para evitar null y NullPointerException,
    // y para dejar explícito en el contrato de la interfaz que ese método puede devolver o no un resultado.
    Optional<User> findById(Long id);

    Optional<User> findByLastname(String lastName);

    Optional<User> findByEmail(String email);

    Optional<User> findByDni(String dni);

    Optional<User> findByAge(Integer age);

    List<User> findUsersYoungerThan18();

}
