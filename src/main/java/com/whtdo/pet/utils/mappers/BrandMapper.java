package com.whtdo.pet.utils.mappers;


import com.whtdo.pet.dto.BrandDTO;
import com.whtdo.pet.dto.ModelDTO;
import com.whtdo.pet.entities.Brand;
import com.whtdo.pet.entities.Model;
import com.whtdo.pet.entities.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrandMapper {
//    private final ModelMapper modelMapper;

    public BrandDTO EntityToDTO(Brand brand) {
        List<UUID> modelUUIDS = new ArrayList<>();
        List<Model> models = brand.getModels();
        for (Model model : models) {
            modelUUIDS.add(model.getId());
        }
        return new BrandDTO(
                brand.getId(),
                brand.getName(),
                modelUUIDS
        );
    };

    public Brand DTOToEntity (BrandDTO brandDTO) {
        Brand brand = new Brand();
        brand.setId(brandDTO.getId());
        brand.setName(brandDTO.getName());
        return brand;
    }
}
