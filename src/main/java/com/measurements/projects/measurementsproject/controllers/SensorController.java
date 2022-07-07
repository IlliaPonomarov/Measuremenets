package com.measurements.projects.measurementsproject.controllers;

import com.measurements.projects.measurementsproject.dto.SensorDTO;
import com.measurements.projects.measurementsproject.models.Sensor;
import com.measurements.projects.measurementsproject.services.SensorsService;
import com.measurements.projects.measurementsproject.util.SensorValidation;
import com.measurements.projects.measurementsproject.util.exceptions.ErrorResponse;
import com.measurements.projects.measurementsproject.util.exceptions.SensorExistNameException;
import com.measurements.projects.measurementsproject.util.exceptions.SensorNotCreatedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    private final SensorValidation sensorValidation;
    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;


    @Autowired
    public SensorController(SensorValidation sensorValidation, SensorsService sensorsService, ModelMapper modelMapper) {
        this.sensorValidation = sensorValidation;
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registrationSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){


        sensorValidation.validate(convertSensorDTOtoSensor(sensorDTO), bindingResult);

        if (bindingResult.hasErrors()){

            StringBuilder messageError = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();;

            for(FieldError fieldError : fieldErrors)
                messageError.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append(";");

            throw new SensorNotCreatedException(messageError.toString());
        }

        // Write method  convert sensorDTO to sensor
        Sensor sensor = sensorsService.save(convertSensorDTOtoSensor(sensorDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    // Exception

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorExistNameException exception){

        ErrorResponse response = new ErrorResponse(
                "Sensor with this name is exist",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotCreatedException exception){

        ErrorResponse response = new ErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }





    public Sensor convertSensorDTOtoSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }



}
