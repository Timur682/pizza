package com.example.pizza.service;

import com.example.pizza.repository.PizzaRepository;
import com.example.pizza.entity.Pizza;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    public Pizza getPizzaById(Long id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    public Pizza createPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }
    public Pizza updatePizza(Pizza pizza) {
        if (pizzaRepository.existsById(pizza.getId())) {
            return pizzaRepository.save(pizza);
        } else {
            return null;
        }
    }

    public Pizza deletePizza(Long id) {
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Pizza with id " + id + " not found")
        );
        pizzaRepository.delete(pizza);
        return pizza;
    }


}
