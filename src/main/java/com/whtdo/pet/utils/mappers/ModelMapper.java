package com.whtdo.pet.utils.mappers;


import com.whtdo.pet.dto.ModelDTO;
import com.whtdo.pet.dto.VehicleDTO;
import com.whtdo.pet.entities.Model;
import com.whtdo.pet.entities.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelMapper {
    public ModelDTO EntityToDTO(Model model) {
        List<String> vehicleVins = new ArrayList<>();
        List<Vehicle> vehicles = model.getVehicles();
        for (Vehicle vehicle : vehicles) {
            vehicleVins.add(vehicle.getVin());
        }
        return new ModelDTO(
                model.getId(),
                model.getName(),
                model.getBrand().getId(),
                vehicleVins
        );
    };

//    public Model DTOToEntity (ModelDTO modelDTO) {
//        Model model = new Model();
//        model.setId(modelDTO.getId());
//        model.setName(modelDTO.getName());
//        return model;
//    }
}
