package com.example.pizza.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Entity
@Table(name = "pizza")
public class Pizza {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "image")
    private String image;

    @NotNull
    @Positive
    @Column(name = "price")
    private BigDecimal price;

    @NotNull
    @Column(name = "description")
    private String description;


}
