package com.whtdo.pet.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.util.*;

@Entity
@Table(name = "model")
@Data // add getters and setters
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Model {
    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "model")
    private List<Vehicle> vehicles = new ArrayList<>();
}
