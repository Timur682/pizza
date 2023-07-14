package com.example.pizza.repository;

import models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);
    User findByUsername(String username);

    boolean existsByUsername(String username);

    User findById(long id);

    User update(User user);

    void deleteById(long id);
}
