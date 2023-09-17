package com.whtdo.pet.entities.utils;

import com.whtdo.pet.entities.Model;
import com.whtdo.pet.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@Data // add getters and setters
public class FormViewVehicle {
    @Id
    @NotEmpty(message = "Поле обязательно для заполнения!")
    @Size(min = 17, max = 17, message = "VIN-номер должен быть длинной 17 символов!")
    private String vin;

    @NotEmpty(message = "Поле обязательно для заполнения!")
    private String modelName;
}
