package com.whtdo.pet.repositories;

import com.whtdo.pet.dto.ModelDTO;
import com.whtdo.pet.entities.Brand;
import com.whtdo.pet.entities.Model;
import com.whtdo.pet.projections.ModelProjection;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ModelRepository extends JpaRepository<Model, UUID> {
    Optional<Model> findById(UUID id);
    Optional<Model> findByName(String name);
}

