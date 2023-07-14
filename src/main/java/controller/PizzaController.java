package controller;

import com.example.pizza.service.PizzaService;
import models.Pizza;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public List<Pizza> getPizzas() {
        return pizzaService.getAllPizzas();
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

    @PostMapping
    public ResponseEntity<Pizza> createPizza(@RequestBody Pizza pizza) {
        Pizza createdPizza = pizzaService.createPizza(pizza);
        return ResponseEntity.created(URI.create("/api/pizzas/" + createdPizza.getId())).body(createdPizza);
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
        pizzaService.deletePizza(id);
        return ResponseEntity.noContent().build();
    }
}

