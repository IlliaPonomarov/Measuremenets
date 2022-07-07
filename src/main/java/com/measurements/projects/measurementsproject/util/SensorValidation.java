package com.measurements.projects.measurementsproject.util;

import com.measurements.projects.measurementsproject.models.Sensor;
import com.measurements.projects.measurementsproject.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidation implements Validator {

    private final SensorsService sensorsService;


    @Autowired
    public SensorValidation(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Sensor sensor = (Sensor) target;

        System.out.println(sensor.getName());

        if (sensorsService.findByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "", "Sensor with this name is exist.");
        }
    }
}
