package com.tecsup.example.hexagonal.infraestructure.adapter.output.persistence.repository;

import com.tecsup.example.hexagonal.infraestructure.adapter.output.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {

}
