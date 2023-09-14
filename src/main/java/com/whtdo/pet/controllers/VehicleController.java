package com.whtdo.pet.controllers;


import com.whtdo.pet.dto.BrandDTO;
import com.whtdo.pet.dto.ModelDTO;
import com.whtdo.pet.dto.VehicleDTO;
import com.whtdo.pet.entities.Brand;
import com.whtdo.pet.entities.Model;
import com.whtdo.pet.entities.Vehicle;
import com.whtdo.pet.repositories.ModelRepository;
import com.whtdo.pet.repositories.VehicleRepository;
import com.whtdo.pet.utils.mappers.BrandMapper;
import com.whtdo.pet.utils.mappers.ModelMapper;
import com.whtdo.pet.utils.mappers.VehicleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    private final BrandMapper brandMapper;

    private final ModelMapper modelMapper;

    @GetMapping(value = "get_vehicles")
    public List<VehicleDTO> getVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<VehicleDTO> vehicleDTOS = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            VehicleDTO vehicleDTO = vehicleMapper.EntityToDTO(vehicle);
            vehicleDTOS.add(vehicleDTO);
        }

        return vehicleDTOS;
    }

    @GetMapping(value = "get_vehicle_by_vin")
    public VehicleDTO getVehicleByVin(@RequestParam(name = "vin", required = true) String vin) {
        return vehicleMapper.EntityToDTO(vehicleRepository.findByVin(vin).orElseThrow());
    }

    public ModelDTO getModelByVehicleVin(String vehicleVin) {
        Vehicle vehicle = vehicleRepository.findByVin(vehicleVin).orElseThrow();
        Model model = vehicle.getModel();
        return modelMapper.EntityToDTO(model);
    }

    public BrandDTO getBrandByVehicleVin(String vehicleVin) {
        Vehicle vehicle = vehicleRepository.findByVin(vehicleVin).orElseThrow();
        Model model = vehicle.getModel();
        Brand brand = model.getBrand();
        return brandMapper.EntityToDTO(brand);
    }

    public String getBrandAndModelByVehicleVin(String vehicleVin) {
        Vehicle vehicle = vehicleRepository.findByVin(vehicleVin).orElseThrow();
        Model model = vehicle.getModel();
        Brand brand = model.getBrand();
        return brand.getName() + " " + model.getName();
    }

    @GetMapping(value = "add_vehicle")
    public String addVehicle(@RequestParam(name = "vin") String vin) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(vin);

        vehicleRepository.save(vehicle);
        return "Successfully added vehicle with vin = " + vehicle.getVin();
    }

    @GetMapping(value = "get_vehicles_by_user")
    public List<VehicleDTO> getVehiclesBuyUser(@RequestParam(name = "passport_number") String passportNumber) {
        List<Vehicle> vehicles = vehicleRepository.findVehiclesByUser(passportNumber);
        List<VehicleDTO> vehicleDTOS = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            vehicleDTOS.add(vehicleMapper.EntityToDTO(vehicle));
        }

        return vehicleDTOS;
    }
}
