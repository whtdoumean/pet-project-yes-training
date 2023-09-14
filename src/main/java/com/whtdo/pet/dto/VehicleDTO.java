package com.whtdo.pet.dto;

import com.whtdo.pet.entities.Model;
import com.whtdo.pet.entities.User;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Value
public class VehicleDTO {
    String vin;
    ModelDTO modelDTO;
    List<String> userPassportNumbers;

    public String getBrandAndModel() {
        return modelDTO.getBrandDTO().getName() + " " + modelDTO.getName();
    }
}
