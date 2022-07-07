package com.measurements.projects.measurementsproject.repositories;

import com.measurements.projects.measurementsproject.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {


}
