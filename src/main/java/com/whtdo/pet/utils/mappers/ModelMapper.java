package com.whtdo.pet.utils.mappers;


import com.whtdo.pet.dto.ModelDTO;
import com.whtdo.pet.dto.VehicleDTO;
import com.whtdo.pet.entities.Model;
import com.whtdo.pet.entities.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelMapper {
//    private final VehicleMapper vehicleMapper;
    private final BrandMapper brandMapper;

    public ModelDTO EntityToDTO(Model model) {
        List<String> vehicleVins = new ArrayList<>();
        List<Vehicle> vehicles = model.getVehicles();
        for (Vehicle vehicle : vehicles) {
            vehicleVins.add(vehicle.getVin());
        }
        return new ModelDTO(
                model.getId(),
                model.getName(),
                brandMapper.EntityToDTO(model.getBrand()),
                vehicleVins
        );
    };

    public Model DTOToEntity (ModelDTO modelDTO) {
        Model model = new Model();
        model.setId(modelDTO.getId());
        model.setName(modelDTO.getName());
        return model;
    }
}
