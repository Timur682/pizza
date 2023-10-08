package com.example.pizza.controller;

import com.example.pizza.service.PizzaService;
import com.example.pizza.entity.Pizza;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class PizzaController {

    private final PizzaService pizzaService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Pizza> createPizza(@RequestBody Pizza pizza) {
        Pizza createdPizza = pizzaService.createPizza(pizza);
        return ResponseEntity.created(URI.create("/api/pizzas/" + createdPizza.getId())).body(createdPizza);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable("id") long id) {
        Pizza pizza = pizzaService.getPizzaById(id);
        if (pizza != null) {
            return ResponseEntity.ok(pizza);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pizza> updatePizza(@PathVariable("id") long id, @RequestBody Pizza pizza) {
        pizza.setId(id);
        Pizza updatedPizza = pizzaService.updatePizza(pizza);
        if (updatedPizza != null) {
            return ResponseEntity.ok(updatedPizza);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePizza(@PathVariable("id") long id) {
        Pizza deleted = pizzaService.deletePizza(id);
        if (deleted == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }






    @GetMapping
    public List<Pizza> getPizzas() {
        return pizzaService.getAllPizzas();
    }
}
