package com.measurements.projects.measurementsproject.util;

import com.measurements.projects.measurementsproject.models.Sensor;
import com.measurements.projects.measurementsproject.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {

    private final SensorsService sensorsService;

    @Autowired
    public MeasurementValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Sensor sensor = (Sensor) target;

        if (sensorsService.findByName(sensor.getName()).isEmpty())
            errors.rejectValue("sensor", "", "This Sensor does not exist.");


    }
}
