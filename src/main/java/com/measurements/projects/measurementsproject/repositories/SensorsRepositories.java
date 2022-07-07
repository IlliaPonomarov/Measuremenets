package com.measurements.projects.measurementsproject.repositories;

import com.measurements.projects.measurementsproject.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SensorsRepositories extends JpaRepository<Sensor, Integer> {
}
