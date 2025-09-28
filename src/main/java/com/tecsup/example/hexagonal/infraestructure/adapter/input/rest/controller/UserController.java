package com.tecsup.example.hexagonal.infraestructure.adapter.input.rest.controller;

import com.tecsup.example.hexagonal.application.port.input.UserService;
import com.tecsup.example.hexagonal.domain.exception.InvalidUserDataException;
import com.tecsup.example.hexagonal.domain.exception.UserNotFoundException;
import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infraestructure.adapter.input.rest.dto.UserRequest;
import com.tecsup.example.hexagonal.infraestructure.adapter.input.rest.dto.UserResponse;
import com.tecsup.example.hexagonal.infraestructure.adapter.output.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
//@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    //Crear usuario
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody(required = false) UserRequest request) {
        try {
            if (request == null) {
                log.warn("Received null UserRequest");
                return ResponseEntity.badRequest().build();
            }
            log.info("Creating request with name: {} and email: {}", request.getName(), request.getEmail());
            User newUser = this.userMapper.toDomain(request);

            log.info("Mapped User entity: {}", newUser);
            User createUser = this.userService.createUser(newUser);

            UserResponse response = this.userMapper.toResponse(createUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (InvalidUserDataException e) {
            log.warn(e.getMessage());
            return ResponseEntity.badRequest().build();

        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity <UserResponse> getUser(@PathVariable Long id) {
        //log.info("Retrieving user with id: {}", id);
        try {

            User user = this.userService.findUser(id);
            UserResponse response = this.userMapper.toResponse(user);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            log.warn("User not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error fetching user with ID: {}", id, e);
            return ResponseEntity.badRequest().build();

        }
    }
}