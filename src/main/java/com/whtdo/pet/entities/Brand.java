package com.whtdo.pet.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "brand")
@Data // add getters and setters
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Brand {
    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brand")
    private List<Model> models = new ArrayList<>();
}
