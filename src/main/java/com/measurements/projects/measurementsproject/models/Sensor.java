package com.measurements.projects.measurementsproject.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sensor")
@Getter
@Setter
@NoArgsConstructor
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty.")
    @Size(min = 3, max = 30, message = "Name size must be between 3 and 30 characters.")
    private String name;

    public Sensor(String name) {
        this.name = name;
    }
}
