package com.measurements.projects.measurementsproject.util.exceptions;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SensorExistNameException extends RuntimeException{

    public SensorExistNameException(String message){
        super(message);
    }
}
