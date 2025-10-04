package com.tecsup.example.hexagonal.infraestructure.adapter.output.persistence.repository;

import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infraestructure.adapter.output.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByEmail(String email);

    //Siempre trabajar con la entidad, no con el modelo
    Optional<UserEntity> findByLastname(String lastName);


}
