package com.example.pizza.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface  PizzaRepository extends JpaRepository<Pizza, Long> {
    Pizza findByName(String name);

    List<Pizza> findByPrice(@NotNull @Positive BigDecimal price);

    List<Pizza> findByNameAndPrice(@NotBlank String name, @NotNull @Positive BigDecimal price);

}
