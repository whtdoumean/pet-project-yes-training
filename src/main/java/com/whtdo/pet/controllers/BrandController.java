package com.whtdo.pet.controllers;


import com.whtdo.pet.dto.BrandDTO;
import com.whtdo.pet.dto.ModelDTO;
import com.whtdo.pet.entities.Brand;
import com.whtdo.pet.entities.Model;
import com.whtdo.pet.repositories.BrandRepository;
import com.whtdo.pet.repositories.ModelRepository;
import com.whtdo.pet.utils.mappers.BrandMapper;
import com.whtdo.pet.utils.mappers.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BrandController {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @GetMapping(value = "get_brands")
    public List<BrandDTO> getBrands() {
        List<Brand> brands = brandRepository.findAll();
        List<BrandDTO> brandDTOS = new ArrayList<>();

        for (Brand brand : brands) {
            BrandDTO brandDTO = brandMapper.EntityToDTO(brand);
            brandDTOS.add(brandDTO);
        }

        return brandDTOS;
    }

    @GetMapping(value = "get_brand_by_id")
    public BrandDTO getBrandBuyId(@RequestParam(name = "id", required = true) UUID id) {
        return brandMapper.EntityToDTO(brandRepository.findById(id).orElseThrow());
    }
}
