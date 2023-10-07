package com.example.pizza.service;

import com.example.pizza.dto.SignupDto;
import com.example.pizza.dto.UserRegistrationResponseDto;
import com.example.pizza.entity.Role;
import com.example.pizza.problems.BadRequestException;
import com.example.pizza.repository.RoleRepository;
import com.example.pizza.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(username)
        );

        // Map our Roles to Spring Security Domain Roles.
        var roles = user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList();

        // Spring security User:
        return new User(user.getUsername(), user.getPassword(), roles);
    }

    @Transactional
    public UserRegistrationResponseDto registerUser(SignupDto dto) {
        // Search for the "ROLE_USER" role in the database and extract it
        Role role = roleRepository.findByNameIgnoreCase("ROLE_USER").orElseThrow(() -> new RuntimeException("Role not found"));

        // Check if there is a user with the same email or username
        var byEmail = userRepository.findUserByEmailIgnoreCase(dto.getEmail());
        var byUsername = userRepository.findByUsername(dto.getUsername());

        if (byEmail.isPresent()) {
            throw new BadRequestException("email", "email already exists");
        } else if (byUsername.isPresent()) {
            throw new BadRequestException("username", "username already exists");
        }

        var user = com.example.pizza.entity.User
                .builder()
                .username(dto.getUsername().trim())
                .email(dto.getEmail().trim().toLowerCase())
                .password(passwordEncoder.encode(dto.getPassword().trim()))
                .roles(Set.of(role)) // Use the extracted role
                .build();

        var saved = userRepository.save(user);
        return UserRegistrationResponseDto.builder()
                .email(saved.getEmail())
                .username(saved.getUsername())
                .message("Sign up successfully")
                .build();
    }
}
