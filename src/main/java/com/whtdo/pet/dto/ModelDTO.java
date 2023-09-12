package com.whtdo.pet.dto;

import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class ModelDTO {
    UUID id;
    String name;
    UUID brandId;
    List<String> vehicleVins;
}
