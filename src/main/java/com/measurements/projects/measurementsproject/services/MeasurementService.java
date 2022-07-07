package com.measurements.projects.measurementsproject.services;


import com.measurements.projects.measurementsproject.models.Measurement;
import com.measurements.projects.measurementsproject.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementsRepository measurementsRepository;

    @Autowired
    public MeasurementService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    public void save(Measurement measurement){

        measurement.setCreatedAt(LocalDate.now());
        measurementsRepository.save(measurement);
    }

}
