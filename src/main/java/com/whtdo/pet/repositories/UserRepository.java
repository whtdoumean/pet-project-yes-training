package com.whtdo.pet.repositories;

import com.whtdo.pet.entities.User;
import com.whtdo.pet.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByPassportNumber(String passportNumber);
    @Query("SELECT u FROM Vehicle v INNER JOIN v.users u WHERE v.vin = :vin")
    List<User> findByVehicle(String vin);
}

