package com.example.pizza.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, unique = true)
    @NotNull
    @Size(min = 2)
    private String username;

    @Column(name = "password")
    @NotNull
    @Size(min = 5)
    private String password;

    @Getter
    @Column(name = "email", unique = true)
    @NotNull
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    @JoinTable(
            name = "user_roles",
            joinColumns =
            @JoinColumn(name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns =
            @JoinColumn(name = "role_id",
                    referencedColumnName = "id"
            )

    )    private Set<Role> roles = new HashSet<>();


}
