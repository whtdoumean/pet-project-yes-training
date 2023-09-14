package com.whtdo.pet.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class UserVehicle implements Serializable {
    private String passportNumber;
    private String vin;
}
