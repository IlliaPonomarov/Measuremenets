package com.measurements.projects.measurementsproject.util;

import com.measurements.projects.measurementsproject.models.Sensor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SensorValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Sensor sensor = (Sensor) target;

    }
}
