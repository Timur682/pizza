package com.example.pizza.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupDto {
    @Email
    @NotNull
    //UniqueEmailValidator
    private String email;
    @NotNull
    @Size(min = 2)
    private String username;
    @NotNull
    @Size(min = 2)
    private String password;
}