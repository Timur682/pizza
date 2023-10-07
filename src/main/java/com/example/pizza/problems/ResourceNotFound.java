package com.example.pizza.problems;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class ResourceNotFound extends RuntimeException {
    private final String resourceName;
    private final String fieldName;
    private final String fieldValue;
    public ResourceNotFound(String resourceName, String fieldName, Object fieldValue) {
        super("%s not found with %s:%s".formatted(resourceName, fieldName, fieldValue));
        this.fieldName = fieldName;
        this.resourceName = resourceName;
        this.fieldValue =  fieldValue.toString();
    }
}