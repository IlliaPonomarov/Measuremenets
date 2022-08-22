package com.measurements.projects.measurementsproject.controllers;

import com.measurements.projects.measurementsproject.dto.MeasurementDTO;
import com.measurements.projects.measurementsproject.dto.SensorDTO;
import com.measurements.projects.measurementsproject.models.Measurement;
import com.measurements.projects.measurementsproject.models.Sensor;
import com.measurements.projects.measurementsproject.services.MeasurementService;
import com.measurements.projects.measurementsproject.util.MeasurementValidator;
import com.measurements.projects.measurementsproject.util.exceptions.ErrorResponse;
import com.measurements.projects.measurementsproject.util.exceptions.MeasurementsNotCreatedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import javax.validation.Valid;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {


    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;


    @Autowired
    public MeasurementsController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }


    @GetMapping
    public List<MeasurementDTO> getAllMeasurements(){

       measurementService.findAll().forEach(System.out::println);

        return measurementService.findAll().stream().map(this::convertMeasurementToMeasurementDTO)
                .collect(Collectors.toList());
    }


    @GetMapping("/1000")
    public void test(){
        measurementService.test1000();
    }


    @GetMapping("/rainyDaysCount")
    public int rainyDaysCount(){
        int n = measurementService.findByRainingTrue();

        System.out.println(n);

        return n;
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasure(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){

        measurementValidator.validate(convertMeasurementDTOtoMeasurement(measurementDTO), bindingResult);

        if (bindingResult.hasErrors()){

            StringBuilder messageError = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for(FieldError fieldError : fieldErrors)
                messageError.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append(";");

            throw new MeasurementsNotCreatedException(messageError.toString());
        }


        measurementService.save(convertMeasurementDTOtoMeasurement(measurementDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementsNotCreatedException exception){

        ErrorResponse response = new ErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertMeasurementDTOtoMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertMeasurementToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }




}
