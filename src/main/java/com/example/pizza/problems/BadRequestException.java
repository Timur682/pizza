package com.example.pizza.problems;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class BadRequestException extends RuntimeException {
    private final String resourceName;

    public BadRequestException(String resourceName, String message) {
        super(message);
        this.resourceName = resourceName;
    }
}