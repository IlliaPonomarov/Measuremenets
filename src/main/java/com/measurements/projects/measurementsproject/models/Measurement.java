package com.measurements.projects.measurementsproject.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
@Getter
@Setter

@NoArgsConstructor
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Range(min = -100, max = 100, message = "Value should be have value between -100 to 100")
    @NotNull(message = "Value should not be empty")
    @Column(name = "value")
    private Double value;

    @NotNull(message = "Raining should not be empty")
    @Column(name = "raining")
    private Boolean raining;


    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "sensor_id")
    @NotNull(message = "This sensor does not exist")
    @JsonBackReference
    private Sensor sensor;

    @Column(name = "created_at")
    private LocalDate createdAt;

    public Measurement(Double value, Boolean raining) {
        this.value = value;
        this.raining = raining;
    }


    @Override
    public String toString() {
        return
                "id: " + id +
                "\nvalue: " + value +
                "\nraining: " + raining +
                "\nsensor: " + sensor.getName()

                        +
                "\ncreatedAt: " + createdAt;
    }
}
