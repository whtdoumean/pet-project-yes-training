package com.whtdo.pet.controllers;
import com.whtdo.pet.dto.UserDTO;
import com.whtdo.pet.dto.VehicleDTO;
import com.whtdo.pet.entities.User;
import com.whtdo.pet.entities.Vehicle;
import com.whtdo.pet.repositories.ModelRepository;
import com.whtdo.pet.repositories.UserRepository;
import com.whtdo.pet.repositories.VehicleRepository;
import com.whtdo.pet.utils.exeptions.NotFoundException;
import com.whtdo.pet.utils.mappers.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final ModelRepository modelRepository;
    private final UserMapper userMapper;
    private final VehicleController vehicleController;

    @GetMapping(value = "get_users")
    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : users) {
            UserDTO userDTO = userMapper.EntityToDTO(user);
            userDTOS.add(userDTO);
        }

        return userDTOS;
    }

    @GetMapping(value = "get_user_by_passport_number")
    public UserDTO getUserByPassportNumber(@RequestParam(name = "passport_number", required = true) String passportNumber) {
        return userMapper.EntityToDTO(userRepository.findByPassportNumber(passportNumber).orElseThrow());
    }

    @GetMapping(value = "add_user_manually")
    public String addUser(@RequestParam(name = "passport_number", required = true) String passportNumber,
                        @RequestParam(name = "name", required = true) String name,
                        @RequestParam(name = "surname", required = true) String surname,
                        @RequestParam(name = "patronymic", required = true) String patronymic
                        ) {
        User user = new User();

        user.setPassportNumber(passportNumber);
        user.setSurname(surname);
        user.setName(name);
        user.setPatronymic(patronymic);

        userRepository.save(user);
        return "Successfully added user with passport_number = " + user.getPassportNumber();
    }

    @Transactional
    @GetMapping(value = "add_vehicle_to_user")
    public String addVehicleToUser(@RequestParam(name = "vin") String vin,
                                   @RequestParam(name = "passport_number") String passportNumber) throws NotFoundException {
        Vehicle vehicle = vehicleRepository.findByVin(vin).orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        User user = userRepository.findByPassportNumber(passportNumber).orElseThrow(() -> new NotFoundException("Автомобиль не найден"));

        if (!user.getVehicles().contains(vehicle) & !vehicle.getUsers().contains(user)) {
            user.getVehicles().add(vehicle);
            vehicle.getUsers().add(user);
            userRepository.save(user);
            vehicleRepository.save(vehicle);
        } else {
            return "adding denied";
        }
        return MessageFormat.format("successfully added vehicle with vin = {0} to user with passport_number = {1}", vehicle.getVin(), user.getPassportNumber());
    }

    @GetMapping(value = "get_users_by_vehicle")
    public List<UserDTO> getUsersBuyVehicle(@RequestParam(name = "vin") String vin) {
        List<User> users = userRepository.findByVehicle(vin);
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : users) {
            userDTOS.add(userMapper.EntityToDTO(user));
        }

        return userDTOS;
    }

    // initials
    public String getUserInitials(String passportNumber) {
        User user = userRepository.findByPassportNumber(passportNumber).orElseThrow();
        return user.getSurname() + " " + user.getName().charAt(0) + ". " + user.getPatronymic().charAt(0) + ".";
    }
}
