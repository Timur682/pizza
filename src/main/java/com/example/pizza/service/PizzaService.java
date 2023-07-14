package com.example.pizza.service;

import com.example.pizza.repository.PizzaRepository;
import models.Pizza;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    public Pizza getPizzaById(long id) {
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

    public void deletePizza(long id) {
        pizzaRepository.deleteById(id);
    }
}
