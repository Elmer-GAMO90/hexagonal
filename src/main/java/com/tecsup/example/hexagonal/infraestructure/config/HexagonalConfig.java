package com.tecsup.example.hexagonal.infraestructure.config;

import com.tecsup.example.hexagonal.application.port.input.UserService;
import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.application.service.UserServiceImpl;
import com.tecsup.example.hexagonal.infraestructure.adapter.output.persistence.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HexagonalConfig {
    //Instanciar la clase, el bean
    @Bean
    public UserService userService (UserRepository userRepository ) {

        return new UserServiceImpl(userRepository);
    }
}
