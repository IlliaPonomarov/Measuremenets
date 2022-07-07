package com.measurements.projects.measurementsproject.repositories;

import com.measurements.projects.measurementsproject.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {

    @Query(value = "SELECT COUNT(raining) FROM Measurement WHERE raining = true")
    int findByCountingRainingTrue();
}
