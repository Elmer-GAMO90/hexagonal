package com.tecsup.example.hexagonal.application.service;

import com.tecsup.example.hexagonal.application.port.input.UserService;
import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.domain.exception.UserNotFoundException;
import com.tecsup.example.hexagonal.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    //Lo que vamos a mockear es el UserRepository
    @Mock
    private UserRepository userRepository;

    private UserServiceImpl userService;


    //Antes de cada prueba
    @BeforeEach
    void setup(){
        //Aqui puedes inicializar los mocks y el servicio
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void createUser() {

        Long ID = 50L;
        String NAME = "Juana";
        String EMAIL = "juana@demo.com";

        // Initial Condition
        User newUser = new User(null, NAME, EMAIL); // UserRequest
        User savedUser = new User(ID, NAME, EMAIL);  // Save UserEntity

        // Mocking the repository behavior
        when(userRepository.save(newUser)).thenReturn(savedUser);

        // Execute the service method
        User realUser = userService.createUser(newUser);

        // Validate the results
        assertNotNull(realUser);
        assertEquals(ID, realUser.getId());
        assertEquals(NAME, realUser.getName());
        assertEquals(EMAIL, realUser.getEmail());
    }

    @Test
    void findUser() {

        //Definir los datos
        Long ID = 100l;
        String NAME = "Jaime";
        String EMAIL = "jaime@demo.com";

        // Initial Condition
        User existingUser = new User(ID, NAME, EMAIL);

        // Mocking the repository behavior
        when(userRepository.findById(100L)).thenReturn(Optional.of(existingUser));

        // Execute the service method
        User realUser = userService.findUser(100L);

        // Validate the results
        assertNotNull(realUser);

        // hope values, real values
        assertEquals(ID, realUser.getId());
        assertEquals(NAME, realUser.getName());
        assertEquals(EMAIL, realUser.getEmail());

    }

    //Hacer la prueba para un usuario que no existe
    @Test
    public void findUser_NotFound() {
        Long ID_UNKNOW = 999L;

        // Mocking the repository behavior to return empty
        when(userRepository.findById(ID_UNKNOW)).thenReturn(Optional.empty());

        // Execute the service method and expect an exception
        assertThrows(UserNotFoundException.class,
                () -> userService.findUser(ID_UNKNOW));

    }
}