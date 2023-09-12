package com.whtdo.pet;

import com.whtdo.pet.entities.User;
import com.whtdo.pet.entities.Vehicle;
import com.whtdo.pet.repositories.UserRepository;
import com.whtdo.pet.repositories.VehicleRepository;
import com.whtdo.pet.utils.etc.DataLoader;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
@SpringBootTest
class PetApplicationTests {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VehicleRepository vehicleRepository;

	@Test
	@Rollback(value = false)
	void contextLoads() {
	}

	@Test
	void test() {
		int amountOfUsers = DataLoader.amountOfUsers;
		int amountOfVehicles = DataLoader.amountOfVehicles;

		assertEquals(amountOfUsers, userRepository.findAll().size());
		assertEquals(amountOfVehicles, vehicleRepository.findAll().size());
	}

}
