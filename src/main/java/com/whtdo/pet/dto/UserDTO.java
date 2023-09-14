package com.whtdo.pet.dto;

import com.whtdo.pet.entities.Vehicle;
import lombok.Value;

import java.util.List;

@Value
public class UserDTO {
    String passportNumber;
    String surname;
    String name;
    String patronymic;
    List<VehicleDTO> vehicleDTOS;

    public String getFullName() {
        return this.surname + " " + this.name + " " + this.patronymic;
    }
}
