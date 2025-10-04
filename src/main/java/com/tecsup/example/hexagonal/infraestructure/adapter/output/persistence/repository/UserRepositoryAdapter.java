package com.tecsup.example.hexagonal.infraestructure.adapter.output.persistence.repository;

import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infraestructure.adapter.output.persistence.entity.UserEntity;
import com.tecsup.example.hexagonal.infraestructure.adapter.output.persistence.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
//@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;
    private final UserMapper userMapper;

    //Constructor
    public UserRepositoryAdapter(UserJpaRepository jpaRepository, UserMapper userMapper) {
        this.jpaRepository = jpaRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        //Convertir el modelo de dominio a la entidad JPA
        //del dominio a entity
        UserEntity userEntity = this.userMapper.toEntity(user); // (UserEntity)user;

        //Guardar entity
        UserEntity entityCreated = this.jpaRepository.save(userEntity);
        log.info("User created: {}", entityCreated);

        //Entity a domain
        //User userCreated = this.userMapper.toDomain(entityCreated); //(User)entityCreated;
        return this.userMapper.toDomain(entityCreated);
    }

    @Override
    public Optional<User> findById(Long id) {

        return this.jpaRepository.findById(id).map(this.userMapper::toDomain);
    }

    @Override
    public Optional<User> findByLastname(String lastName) {
        return this.jpaRepository.findByLastname(lastName).map(this.userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.jpaRepository.findByEmail(email).map(this.userMapper::toDomain);
    }


}
