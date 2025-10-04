package com.tecsup.example.hexagonal.infraestructure.config;

import com.tecsup.example.hexagonal.application.port.input.AuthService;
import com.tecsup.example.hexagonal.application.port.input.UserService;
import com.tecsup.example.hexagonal.application.port.output.UserRepository;
import com.tecsup.example.hexagonal.application.service.AuthServiceImpl;
import com.tecsup.example.hexagonal.application.service.UserServiceImpl;
import com.tecsup.example.hexagonal.infraestructure.adapter.output.persistence.mapper.UserMapper;
import com.tecsup.example.hexagonal.infraestructure.adapter.output.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class HexagonalConfig {
    //Instanciar la clase, el bean
    @Bean
    public UserService userService (UserRepository userRepository ) {

        return new UserServiceImpl(userRepository);
    }

    @Bean
    public AuthService authService(UserRepository userRepository,
                                   PasswordEncoder passwordEncoder,
                                   JwtTokenProvider jwtTokenProvider) {
        return new AuthServiceImpl(userRepository, passwordEncoder, jwtTokenProvider);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
