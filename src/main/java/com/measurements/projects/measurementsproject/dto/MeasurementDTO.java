package com.measurements.projects.measurementsproject.dto;

import com.measurements.projects.measurementsproject.models.Sensor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@Setter
@Getter
public class MeasurementDTO {

    @Range(min = -100, max = 100, message = "Value should be have value between -100 to 100")
    @NotNull(message = "Value should not be empty")
    private Double value;

    @NotNull(message = "Raining should not be empty")
    private Boolean raining;

    @NotNull(message = "This sensor does not exist")
    private Sensor sensor;

    public MeasurementDTO(Double value, Boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }
}
