package com.whtdo.pet.utils.mappers;


import com.whtdo.pet.dto.UserDTO;
import com.whtdo.pet.dto.VehicleDTO;
import com.whtdo.pet.entities.User;
import com.whtdo.pet.entities.Vehicle;
import com.whtdo.pet.projections.VehicleProjection;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleMapper {
    public VehicleDTO EntityToDTO(Vehicle vehicle) {
        List<String> userPassportNumbers = new ArrayList<>();
        List<User> users = vehicle.getUsers();
        for (User user : users) {
            userPassportNumbers.add(user.getPassportNumber());
        }
        return new VehicleDTO(
                vehicle.getVin(),
                vehicle.getModel().getId(),
                userPassportNumbers
        );
    };

//    public Vehicle DTOToEntity (VehicleDTO vehicleDTO) {
//        Vehicle vehicle = new Vehicle();
//        vehicle.setVin(vehicleDTO.getVin());
//        return vehicle;
//    }
}
