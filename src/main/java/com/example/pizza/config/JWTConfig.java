package com.example.pizza.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "pizza")
public class JWTConfig{
    private String secret;
    private Long expiration;
    public JWTConfig() {
    }
    public JWTConfig(String secret, Long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }



}