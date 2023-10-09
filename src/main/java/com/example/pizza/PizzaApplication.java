package com.example.pizza;

import com.example.pizza.config.JWTConfig;
import com.example.pizza.entity.Role;
import com.example.pizza.entity.User;
import com.example.pizza.repository.RoleRepository;
import com.example.pizza.repository.UserRepository;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
@EnableJpaRepositories
@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(JWTConfig.class)
@Transactional
public class PizzaApplication implements CommandLineRunner {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(PizzaApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (userRepository.findAll().isEmpty()) {
			// Создание ролей
			var roleUser = roleRepository.save(Role.builder().name("USER").build());
			var roleAdmin = roleRepository.save(Role.builder().name("ADMIN").build());

// Создание пользователей
			var admin = User.builder()
					.username("admin")
					.roles(Set.of(roleAdmin))
					.email("admin@gmail.com")
					.password(passwordEncoder.encode("admin"))
					.build();

			var user = User.builder()
					.username("user")
					.roles(Set.of(roleUser))
					.email("user@gmail.com")
					.password(passwordEncoder.encode("user"))
					.build();

			userRepository.saveAll(Set.of(admin, user));

		}
	}

}
