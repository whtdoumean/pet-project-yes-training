package com.whtdo.pet.repositories;

import com.whtdo.pet.entities.Vehicle;
import com.whtdo.pet.projections.VehicleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Optional<Vehicle> findByVin(String vin);
    @Query("SELECT v FROM User u INNER JOIN u.vehicles v WHERE u.passportNumber = :passportNumber")
    List<Vehicle> findVehiclesByUser(String passportNumber);

}

