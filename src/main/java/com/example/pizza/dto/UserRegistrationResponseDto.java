package com.example.pizza.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationResponseDto {
    private String email;
    private String username;
    private String message;
}
