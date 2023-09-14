package com.whtdo.pet.utils.mappers;


import com.whtdo.pet.dto.UserDTO;
import com.whtdo.pet.dto.VehicleDTO;
import com.whtdo.pet.entities.User;
import com.whtdo.pet.entities.Vehicle;
import com.whtdo.pet.projections.VehicleProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleMapper {
//    private final UserMapper userMapper;
//    private final ModelMapper modelMapper;
    public VehicleDTO EntityToDTO(Vehicle vehicle) {
        List<String> userPasswordNumbers = new ArrayList<>();
        List<User> users = vehicle.getUsers().stream().toList();
        for (User user : users) {
            userPasswordNumbers.add(user.getPassportNumber());
        }
        return new VehicleDTO(
                vehicle.getVin(),
                vehicle.getModel().getId(),
                userPasswordNumbers
        );
    };

    public Vehicle DTOToEntity (VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(vehicleDTO.getVin());
        return vehicle;
    }
}
