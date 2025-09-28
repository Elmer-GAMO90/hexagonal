package com.tecsup.example.hexagonal.infraestructure.adapter.output.persistence.mapper;

import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infraestructure.adapter.input.rest.dto.UserRequest;
import com.tecsup.example.hexagonal.infraestructure.adapter.input.rest.dto.UserResponse;
import com.tecsup.example.hexagonal.infraestructure.adapter.output.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    //Mappers.getMapper(UserMapper.class) → le pide a MapStruct que le devuelva la implementación concreta de tu interfaz UserMapper.
    //Tú solo defines la interfaz con métodos (toEntity, toDomain).
    //MapStruct, al compilar, genera una clase (ej. UserMapperImpl) que contiene la implementación real de esos métodos (copiando valores entre User y UserEntity).
    //UserMapper INSTANCE = ... → crea una instancia estática y accesible del mapper.
    //Así puedes usar el mapper sin necesidad de new ni de inyectarlo manualmente.
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * Convert User domain to UserEntity
     * @param domain
     * @return
     */
    UserEntity toEntity(User domain);
    /**
     * Convert UserEntity to User domain
     * @param entity
     * @return
     */
    User toDomain(UserEntity entity);

    @Mapping(target = "id", ignore = true) // New users don't have ID
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    User toDomain(UserRequest request);

    UserResponse toResponse(User createUser);

}
