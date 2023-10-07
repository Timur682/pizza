package com.example.pizza.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Hidden
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<Object> sayHello() {
        return ResponseEntity.ok().body(Map.of("message",  "Hello world"));
    }
}