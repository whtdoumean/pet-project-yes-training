package com.whtdo.pet.controllers;

import com.whtdo.pet.dto.UserDTO;
import com.whtdo.pet.dto.VehicleDTO;
import com.whtdo.pet.entities.User;
import com.whtdo.pet.entities.Vehicle;
import com.whtdo.pet.repositories.UserRepository;
import com.whtdo.pet.repositories.VehicleRepository;
import com.whtdo.pet.utils.mappers.UserMapper;
import com.whtdo.pet.utils.mappers.VehicleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserController userController;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final VehicleRepository vehicleRepository;
    private final VehicleController vehicleController;
    private final VehicleMapper vehicleMapper;

    @GetMapping(value = "/")
    public String home() {
        return "home";
    }

    @GetMapping(value = "users")
    public String users(Model model) {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : users) {
            userDTOS.add(userMapper.EntityToDTO(user));
        }

        model.addAttribute("userDTOS", userDTOS);
        model.addAttribute("vehicleController", vehicleController);

        return "users";
    }

    @GetMapping(value = "user")
    public String user(Model model, @RequestParam(name = "passport_number", required = true) String passportNumber) {
        UserDTO userDTO = userController.getUserByPassportNumber(passportNumber);
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("vehicleController", vehicleController);
        return "user";
    }

    @GetMapping(value = "vehicles")
    public String vehicles(Model model) {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<VehicleDTO> vehicleDTOS = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            vehicleDTOS.add(vehicleMapper.EntityToDTO(vehicle));
        }

        model.addAttribute("vehicleDTOS", vehicleDTOS);
        model.addAttribute("vehicleController", vehicleController);
        model.addAttribute("userController", userController);
        return "vehicles";
    }

    @GetMapping(value = "vehicle")
    public String vehicle(Model model, @RequestParam(name = "vin", required = true) String vin) {
        VehicleDTO vehicleDTO = vehicleController.getVehicleByVin(vin);
        model.addAttribute("vehicleDTO", vehicleDTO);
        model.addAttribute("vehicleController", vehicleController);
        model.addAttribute("userController", userController);
        return "vehicle";
    }
}
