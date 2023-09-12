package com.whtdo.pet;

import com.whtdo.pet.entities.User;
import com.whtdo.pet.entities.Vehicle;
import com.whtdo.pet.repositories.UserRepository;
import com.whtdo.pet.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetApplication {
	public static void main(String[] args) {
		SpringApplication.run(PetApplication.class, args);
	}
}
