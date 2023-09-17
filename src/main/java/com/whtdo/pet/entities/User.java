package com.whtdo.pet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @Column(name = "passport_number")
    @EqualsAndHashCode.Include
    @NotEmpty(message = "Поле обязательно для заполнения!")
    @Size(min = 10, max = 10, message = "Паспорт должен быть длинной 10 символов!")
    private String passportNumber;

    @Column(name = "surname")
    @NotEmpty(message = "Поле обязательно для заполнения!")
    private String surname;

    @Column(name = "name")
    @NotEmpty(message = "Поле обязательно для заполнения!")
    private String name;

    @Column(name = "patronymic")
    @NotEmpty(message = "Поле обязательно для заполнения!")
    private String patronymic;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_vehicle",
            joinColumns = {@JoinColumn(name = "passport_number")},
            inverseJoinColumns = {@JoinColumn(name = "vin")}
    )
    private Set<Vehicle> vehicles = new HashSet<>();
}
