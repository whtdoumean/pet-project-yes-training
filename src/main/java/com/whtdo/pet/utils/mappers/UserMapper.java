package com.whtdo.pet.utils.mappers;


import com.whtdo.pet.dto.UserDTO;
import com.whtdo.pet.dto.VehicleDTO;
import com.whtdo.pet.entities.User;
import com.whtdo.pet.entities.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final VehicleMapper vehicleMapper;
    public UserDTO EntityToDTO(User user) {
        List<VehicleDTO> vehicleDTOS = new ArrayList<>();
        List<Vehicle> vehicles = user.getVehicles();
        for (Vehicle vehicle : vehicles) {
            vehicleDTOS.add(vehicleMapper.EntityToDTO(vehicle));
        }
        return new UserDTO(
                user.getPassportNumber(),
                user.getSurname(),
                user.getName(),
                user.getPatronymic(),
                vehicleDTOS
        );
    };

    public User DTOToEntity(UserDTO userDTO) {
        User user = new User();
        user.setPassportNumber(userDTO.getPassportNumber());
        user.setSurname(userDTO.getSurname());
        user.setName(userDTO.getName());
        user.setPatronymic(userDTO.getPatronymic());
        return user;
    }
}
