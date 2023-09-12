package com.whtdo.pet.controllers;


import com.whtdo.pet.dto.ModelDTO;
import com.whtdo.pet.dto.VehicleDTO;
import com.whtdo.pet.entities.Model;
import com.whtdo.pet.entities.Vehicle;
import com.whtdo.pet.repositories.ModelRepository;
import com.whtdo.pet.repositories.VehicleRepository;
import com.whtdo.pet.utils.mappers.ModelMapper;
import com.whtdo.pet.utils.mappers.VehicleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ModelController {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @GetMapping(value = "get_models")
    public List<ModelDTO> getModels() {
        List<Model> models = modelRepository.findAll();
        List<ModelDTO> modelDTOS = new ArrayList<>();

        for (Model model : models) {
            ModelDTO modelDTO = modelMapper.EntityToDTO(model);
            modelDTOS.add(modelDTO);
        }

        return modelDTOS;
    }

    @GetMapping(value = "get_model_by_id")
    public ModelDTO getModelBuyId(@RequestParam(name = "id", required = true) UUID id) {
        return modelMapper.EntityToDTO(modelRepository.findById(id).orElseThrow());
    }
}
