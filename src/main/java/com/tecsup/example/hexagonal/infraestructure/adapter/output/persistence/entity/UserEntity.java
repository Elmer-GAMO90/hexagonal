package com.tecsup.example.hexagonal.infraestructure.adapter.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String lastname;

    @Column(length = 100)
    private String lastmattern;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(length = 8, nullable = false)
    private String dni;

    @Column(nullable = false)
    private Integer age;

    @Column(length = 20)
    private String phonenumber;

    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;


}
