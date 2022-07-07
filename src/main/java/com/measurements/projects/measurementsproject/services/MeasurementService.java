package com.measurements.projects.measurementsproject.services;


import com.measurements.projects.measurementsproject.models.Measurement;
import com.measurements.projects.measurementsproject.models.Sensor;
import com.measurements.projects.measurementsproject.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;


@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }

    public List<Measurement> findAll(){
        return measurementsRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement){

       // Optional<Sensor> sensor = sensorsService.findByName(measurement.getSensor().getName());
        measurement.setSensor(sensorsService.findByName(measurement.getSensor().getName()).get());


        measurement.setCreatedAt(LocalDate.now());

        measurementsRepository.save(measurement);
    }

}
