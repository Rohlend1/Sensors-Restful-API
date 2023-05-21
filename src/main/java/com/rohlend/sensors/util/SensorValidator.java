package com.rohlend.sensors.util;

import com.rohlend.sensors.models.Sensor;
import com.rohlend.sensors.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SensorValidator{
    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    public void validate(Sensor sensor){
        if(sensor == null) throw new SensorNotCreatedExecption("Sensor should be null");
        String name = sensor.getName();
        if(name == null || name.length() < 3 || name.length() > 30 || sensorService.checkIfExist(name)){
            throw new SensorNotCreatedExecption("Sensor's name is incorrect");
        }
    }
}
