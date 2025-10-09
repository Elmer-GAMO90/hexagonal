package com.tecsup.example.hexagonal.infraestructure.adapter.input.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.example.hexagonal.application.port.input.UserService;
import com.tecsup.example.hexagonal.domain.model.User;
import com.tecsup.example.hexagonal.infraestructure.adapter.input.rest.dto.UserRequest;
import com.tecsup.example.hexagonal.infraestructure.adapter.input.rest.dto.UserResponse;
import com.tecsup.example.hexagonal.infraestructure.adapter.output.persistence.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//Simula un contenedor embebido
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class UserControllerTest {

    //Alguien que simula una petición http
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private UserMapper userMapper;

    //Sirve para serializar el contenido
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @WithMockUser(roles = "ADMIN")
    void createUser()  throws Exception {

        Long ID = 50L;
        String NAME = "Juana";
        String LASTNAME = "Arco";
        String LASTMATTERN = "Arias";
        String EMAIL = "juana@demo.com";
        String DNI = "00460258";
        Integer AGE = 22;

        // Initial Condition
        UserRequest request = new UserRequest(NAME, LASTNAME, LASTMATTERN, EMAIL, DNI, AGE);
        User newUser =  User.builder()
                .name(NAME)
                .lastname(LASTNAME)
                .lastmattern(LASTMATTERN)
                .email(EMAIL)
                .dni(DNI)
                .age(AGE)
                .build(); //new User(null, NAME, EMAIL); // UserRequest
        User savedUser = User.builder()
                .id(ID)
                .name(NAME)
                .lastname(LASTNAME)
                .lastmattern(LASTMATTERN)
                .email(EMAIL)
                .dni(DNI)
                .age(AGE)
                .build();

        //new User(ID, NAME, EMAIL);  // Save UserEntity
        UserResponse response   = new UserResponse(ID, NAME, LASTNAME, LASTMATTERN, EMAIL, DNI, AGE);


        // Mocking the repository behavior
        when(userMapper.toDomain(request)).thenReturn(newUser);
        when(userService.createUser(newUser)).thenReturn(savedUser);
        when(userMapper.toResponse(savedUser)).thenReturn(response);

        // Execute the mvc method
        //Simula como si estuvieras en el navegador (post)
        this.mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.lastname").value(LASTNAME))
                .andExpect(jsonPath("$.lastmattern").value(LASTMATTERN))
                .andExpect(jsonPath("$.email").value(EMAIL))
                .andExpect(jsonPath("$.dni").value(DNI))
                .andExpect(jsonPath("$.age").value(AGE))
                .andDo(print());

    }


    //@Test
    void getUser() {
    }
}