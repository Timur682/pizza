package com.example.pizza.repository;

import com.example.pizza.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  PizzaRepository extends JpaRepository<Pizza, Long> {

}
