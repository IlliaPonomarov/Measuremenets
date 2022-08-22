package com.measurements.projects.measurementsproject.services;


import com.measurements.projects.measurementsproject.models.Measurement;
import com.measurements.projects.measurementsproject.models.Sensor;
import com.measurements.projects.measurementsproject.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

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

    public int findByRainingTrue(){
        return measurementsRepository.findByCountingRainingTrue();
    }

    @Transactional
    public void save(Measurement measurement){

       // Optional<Sensor> sensor = sensorsService.findByName(measurement.getSensor().getName());
        measurement.setSensor(sensorsService.findByName(measurement.getSensor().getName()).get());


        measurement.setCreatedAt(LocalDate.now());

        measurementsRepository.save(measurement);
    }

    public void test1000() {
        Random random = new Random();
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> jsonToSend = new HashMap<>();
        List<Sensor> list = Arrays.asList(new Sensor("Sensor name"), new Sensor("Sensor name 2"),
                new Sensor("Sensor name 3"), new Sensor("Sensor name 4"));



        for (int i = 0; i < 1000; i++) {
            jsonToSend.put("value",(-100 + Math.random() * 100));
            jsonToSend.put("raining", random.nextBoolean());
            jsonToSend.put("sensor", list.get(random.nextInt(list.size())));



            HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(jsonToSend);


            String  request = "http://localhost:8091/measurements/add";

            String response = restTemplate.postForObject(request, httpEntity, String.class);
        }
    }
}
