package com.nauteus.spring_tutorial.data.entities;


import java.util.UUID;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Nullable
    @Column(name = "email", nullable = true, length = 255)
    private String email;

    @Nullable
    @Column(name = "phone_number", nullable = true, length = 16)
    private String phoneNumber;

    @Nonnull
    @Column(name = "password", length = 256)
    private String password;
}
