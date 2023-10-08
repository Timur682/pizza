package com.example.pizza.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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


    @Getter
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @Size(max = 255)
    @NotBlank
    @Column(name = "name")
    private String name;

    @Size(max = 255)
    @NotNull
    @Column(name = "image")
    private String image;

    @DecimalMin("0.01")
    @DecimalMax("9999.99")
    @NotNull
    @Positive
    @Column(name = "price")
    private BigDecimal price;

    @Size(max = 255)
    @NotNull
    @Column(name = "description")
    private String description;


    public void setId(Long id) {
        this.id = id;
    }

}
