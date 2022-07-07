package com.measurements.projects.measurementsproject.services;


import com.measurements.projects.measurementsproject.models.Sensor;
import com.measurements.projects.measurementsproject.repositories.SensorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {

    private SensorsRepository sensorsRepository;


    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    @Transactional
    public Sensor save(Sensor sensor){
        Sensor sensor1 = sensorsRepository.save(sensor);

        return sensor1;
    }

    public Optional<Sensor> findByName(String name){

        Optional<Sensor> sensor = sensorsRepository.findByName(name);

        return sensorsRepository.findByName(name);
    }


}
