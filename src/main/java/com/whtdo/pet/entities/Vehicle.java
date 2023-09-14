package com.whtdo.pet.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;

@Entity
@Table(name = "vehicle")
@Data // add getters and setters
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Vehicle {
    @Id
    @Column(name = "vin")
    @EqualsAndHashCode.Include
    private String vin;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_vehicle",
            joinColumns = {@JoinColumn(name = "vin")},
            inverseJoinColumns = {@JoinColumn(name = "passport_number")}
    )
    private Set<User> users = new HashSet<>();
}
