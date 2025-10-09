package com.tecsup.example.hexagonal.infraestructure.adapter.input.rest.controller;

import com.tecsup.example.hexagonal.application.port.input.UserService;
import com.tecsup.example.hexagonal.domain.exception.*;
import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infraestructure.adapter.input.rest.dto.UserRequest;
import com.tecsup.example.hexagonal.infraestructure.adapter.input.rest.dto.UserResponse;
import com.tecsup.example.hexagonal.infraestructure.adapter.output.persistence.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('MONITOR')")
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

    @GetMapping("/lastname/{lastname}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity <UserResponse> getUserLastname(@PathVariable String lastname) {
        //log.info("Retrieving user with id: {}", id);
        try {

            User user = this.userService.findUserLastname(lastname);
            UserResponse response = this.userMapper.toResponse(user);
            return ResponseEntity.ok(response);
        } catch (UserLastNameFoundException e) {
            log.warn("User not found with lastName: {}", lastname);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error fetching user with ID: {}", lastname, e);
            return ResponseEntity.badRequest().build();

        }
    }

    @GetMapping("/dni/{dni}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity <UserResponse> getUserDni(@PathVariable String dni) {
        //log.info("Retrieving user with id: {}", id);
        try {

            User user = this.userService.findUserDni(dni);
            UserResponse response = this.userMapper.toResponse(user);
            return ResponseEntity.ok(response);
        } catch (UserDniNotFoundException e) {
            log.warn("User not found with dni: {}", dni);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error fetching user with ID: {}", dni, e);
            return ResponseEntity.badRequest().build();

        }
    }

    @GetMapping("/age/{age}")
    @PreAuthorize("hasRole('MONITOR')")
    public ResponseEntity <UserResponse> getUserAge(@PathVariable Integer age) {
        //log.info("Retrieving user with id: {}", id);
        try {

            User user = this.userService.findUserAge(age);
            UserResponse response = this.userMapper.toResponse(user);
            return ResponseEntity.ok(response);
        } catch (UserAgeNotFoundException e) {
            log.warn("User not found with age: {}", age);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error fetching user with ID: {}", age, e);
            return ResponseEntity.badRequest().build();

        }
    }

    @GetMapping("/minors")
    @PreAuthorize("hasRole('MONITOR')")
    public ResponseEntity <List<UserResponse>> getUsersMinors() {

        try {
            List<User> minors = this.userService.findUsersYoungerThan18();
            List<UserResponse> response = minors.stream()
                    .map(this.userMapper::toResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (UserAgeNotFoundException e) {
            log.warn("No minors found");
            return ResponseEntity.noContent().build(); // 204 sin contenido
        } catch (Exception e) {
            log.error("Error fetching minors: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}