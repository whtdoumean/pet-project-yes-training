package com.whtdo.pet.entities.utils;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FormViewRelationship {
    @Id
    @NotEmpty(message = "Поле обязательно для заполнения!")
    @Size(min = 10, max = 10, message = "Паспорт должен быть длинной 10 символов!")
    String passportNumber;
    @Id
    @NotEmpty(message = "Поле обязательно для заполнения!")
    @Size(min = 17, max = 17, message = "VIN-номер должен быть длинной 17 символов!")
    String vin;
}
