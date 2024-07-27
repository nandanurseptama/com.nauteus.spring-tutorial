package com.nauteus.spring_tutorial.domain.requests.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterUserWithEmailRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 16)
    private String password;
}
