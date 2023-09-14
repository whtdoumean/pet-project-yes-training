package com.whtdo.pet.entities;

import jakarta.persistence.*;
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
    private String passportNumber;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_vehicle",
            joinColumns = {@JoinColumn(name = "passport_number")},
            inverseJoinColumns = {@JoinColumn(name = "vin")}
    )
    private Set<Vehicle> vehicles = new HashSet<>();
}
