package com.example.pizza.service;

import com.example.pizza.repository.PizzaRepository;
import com.example.pizza.entity.Pizza;
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

    public Pizza deletePizza(long id) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
        if (pizzaOptional.isPresent()) {
            Pizza pizza = pizzaOptional.get();
            pizzaRepository.deleteById(id);
            return pizza; // Return the deleted pizza
        } else {
            return null; // Return null if the pizza with the specified ID does not exist
        }
    }

}
