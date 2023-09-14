package com.whtdo.pet.dto;

import com.whtdo.pet.entities.Brand;
import com.whtdo.pet.entities.Model;
import com.whtdo.pet.entities.User;
import com.whtdo.pet.repositories.BrandRepository;
import com.whtdo.pet.repositories.ModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Value
public class VehicleDTO {

    String vin;
    UUID modelId;
    List<String> passportNumbers;
}
