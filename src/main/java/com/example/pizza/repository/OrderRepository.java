package com.example.pizza.repository;

import com.example.pizza.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  OrderRepository extends JpaRepository<Order, Long> {

}
