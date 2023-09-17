package com.whtdo.pet.controllers;

import com.whtdo.pet.dto.UserDTO;
import com.whtdo.pet.dto.VehicleDTO;
import com.whtdo.pet.entities.User;
import com.whtdo.pet.entities.Vehicle;
import com.whtdo.pet.entities.utils.FormViewVehicle;
import com.whtdo.pet.repositories.ModelRepository;
import com.whtdo.pet.repositories.UserRepository;
import com.whtdo.pet.repositories.VehicleRepository;
import com.whtdo.pet.utils.exeptions.NotFoundException;
import com.whtdo.pet.utils.mappers.UserMapper;
import com.whtdo.pet.utils.mappers.VehicleMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    private final ModelRepository modelRepository;

    @GetMapping(value = "error")
    public String error(Model model) {
        return "redirect:error";
    }

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("title", "ТСРА - Тестовый Сервис Регистрации Автомобилей");
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

        model.addAttribute("title", "ТСРА - Клиенты");
        return "users";
    }

    @GetMapping(value = "user")
    public String user(@RequestParam(name = "passport_number", required = true) String passportNumber,
                       Model model) throws NotFoundException {
        try {
            UserDTO userDTO = userMapper.EntityToDTO(userRepository.findByPassportNumber(passportNumber).orElseThrow(() -> new NotFoundException("Пользователь не найден")));
            model.addAttribute("userDTO", userDTO);
            model.addAttribute("vehicleController", vehicleController);

            model.addAttribute("title",
                    "ТСРА - ".concat(userController.getUserInitials(userDTO.getPassportNumber())));
            model.addAttribute("mainTitle", userDTO.getFullName());
            return "user";
        } catch (NotFoundException e) {
            return "redirect:error";
        }
    }

    @GetMapping(value = "new_user")
    public String newUserForm(User user, Model model) {
        model.addAttribute("user", user);

        model.addAttribute("title","ТСРА - Добавить нового клиента");
        return "new_user";
    }

    @PostMapping("/new_user")
    public String newUserValidate(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new_user";
        } else {
            System.out.println(user);
            userRepository.save(user);

            return "redirect:user?passport_number=".concat(user.getPassportNumber());
        }
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

        model.addAttribute("title", "ТСРА - Автомобили");
        return "vehicles";
    }

    @GetMapping(value = "vehicle")
    public String vehicle(@RequestParam(name = "vin", required = true) String vin, Model model) {
        VehicleDTO vehicleDTO = vehicleController.getVehicleByVin(vin);
        model.addAttribute("vehicleDTO", vehicleDTO);
        model.addAttribute("vehicleController", vehicleController);
        model.addAttribute("userController", userController);

        model.addAttribute("title",
                "ТСРА - ".concat(vehicleController.getBrandAndModelByVehicleVin(vehicleDTO.getVin())));
        model.addAttribute("mainTitle", vehicleController.getBrandAndModelByVehicleVin(vehicleDTO.getVin()));
        return "vehicle";
    }

    @GetMapping(value = "new_vehicle")
    public String newVehicleForm(FormViewVehicle formViewVehicle, Model model) {
        model.addAttribute("formViewVehicle", formViewVehicle);

        model.addAttribute("title","ТСРА - Добавить новый автомобиль");
        return "new_vehicle";
    }

    @PostMapping("/new_vehicle")
    public String newVehicleValidate(@Valid @ModelAttribute("formViewVehicle") FormViewVehicle formViewVehicle,
                                     BindingResult result,
                                     Model model) throws NotFoundException {
        if (result.hasErrors()) {
            return "new_vehicle";
        } else {
            try {
                com.whtdo.pet.entities.Model vehicleModel = modelRepository.
                        findByName(formViewVehicle.getModelName()).
                        orElseThrow(() -> new NotFoundException("Модель "
                                .concat(formViewVehicle.getModelName())
                                .concat(" не найдена")));
                Vehicle vehicle = new Vehicle();
                vehicle.setVin(formViewVehicle.getVin());
                vehicle.setModel(vehicleModel);

                vehicleRepository.save(vehicle);
                return "redirect:vehicle?vin=".concat(vehicle.getVin());
            } catch (NotFoundException e) {
                return "redirect:error";
            }
        }
    }

    @GetMapping(value = "new_vehicle")
    public String newVehicleForm(FormViewVehicle formViewVehicle, Model model) {
        model.addAttribute("formViewVehicle", formViewVehicle);

        model.addAttribute("title","ТСРА - Добавить новый автомобиль");
        return "new_vehicle";
    }

    @PostMapping("/new_vehicle")
    public String newVehicleValidate(@Valid @ModelAttribute("formViewVehicle") FormViewVehicle formViewVehicle,
                                     BindingResult result,
                                     Model model) throws NotFoundException {
        if (result.hasErrors()) {
            return "new_vehicle";
        } else {
            try {
                com.whtdo.pet.entities.Model vehicleModel = modelRepository.
                        findByName(formViewVehicle.getModelName()).
                        orElseThrow(() -> new NotFoundException("Модель "
                                .concat(formViewVehicle.getModelName())
                                .concat(" не найдена")));
                Vehicle vehicle = new Vehicle();
                vehicle.setVin(formViewVehicle.getVin());
                vehicle.setModel(vehicleModel);

                vehicleRepository.save(vehicle);
                return "redirect:vehicle?vin=".concat(vehicle.getVin());
            } catch (NotFoundException e) {
                return "redirect:error";
            }
        }
    }

}
