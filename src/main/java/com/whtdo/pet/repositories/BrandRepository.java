package com.whtdo.pet.repositories;

import com.whtdo.pet.dto.BrandDTO;
import com.whtdo.pet.entities.Brand;
import com.whtdo.pet.projections.BrandProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> {
    Optional<Brand> findById(UUID id);

    Optional<Brand> findByName(String name);
}

