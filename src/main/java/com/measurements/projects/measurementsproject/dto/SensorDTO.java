package com.measurements.projects.measurementsproject.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
public class SensorDTO {

    @NotEmpty(message = "Name should not be empty.")
    @Size(min = 3, max = 30, message = "Name size must be between 3 and 30 characters.")
    private String name;

    public SensorDTO(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
