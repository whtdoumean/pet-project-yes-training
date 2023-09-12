package com.whtdo.pet.dto;

import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class BrandDTO {
    UUID id;
    String name;
    List<UUID> modelUUIDS;
}
