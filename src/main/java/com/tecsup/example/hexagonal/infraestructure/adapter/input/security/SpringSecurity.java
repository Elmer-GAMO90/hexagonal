package com.tecsup.example.hexagonal.infraestructure.adapter.input.security;


import com.tecsup.example.hexagonal.infraestructure.adapter.output.security.AuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
//@RequiredArgsConstructor
//Para habilitar seguridad a nivel de metodo
public class SpringSecurity {

    private  final AuthFilter authFilter;


    public SpringSecurity(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/auth/**").permitAll()
                        //.requestMatchers("/api/users/**").authenticated() //.authenticated() permitAll()
                        //.requestMatchers("/api/users/**").authenticated()
                        //.requestMatchers("/api/users/**").hasAnyRole("MONITOR", "ADMIN")
                        .anyRequest().authenticated()
                )
        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class) ;
        return http.build();

    }
}
