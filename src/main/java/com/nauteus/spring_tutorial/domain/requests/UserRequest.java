package com.nauteus.spring_tutorial.domain.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserRequest {
    @Getter
    @Setter
    @Builder
    public static class RegisterWithEmailRequest {
        @NotBlank
        @Email
        private String email;

        @NotBlank
        @Size(max = 16)
        private String password;
    }
}
