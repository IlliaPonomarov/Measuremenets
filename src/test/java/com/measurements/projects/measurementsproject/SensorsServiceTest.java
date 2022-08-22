package com.measurements.projects.measurementsproject;

import com.measurements.projects.measurementsproject.models.Sensor;
import com.measurements.projects.measurementsproject.repositories.SensorsRepository;
import com.measurements.projects.measurementsproject.services.SensorsService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class SensorsServiceTest {

    @InjectMocks
    private SensorsService sensorsService;

    @Mock
    private SensorsRepository sensorsRepository;



    public SensorsServiceTest(){}

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByName(){

      SensorsService sensorsService1 = mock(SensorsService.class, RETURNS_DEEP_STUBS);
      Optional<Sensor> sensor = Optional.of(mock(Sensor.class));

       when(sensorsService1.findByName(anyString())).thenReturn(sensor);

        assertEquals(sensor, sensorsService1.findByName("Sensor name"));

    }
}
