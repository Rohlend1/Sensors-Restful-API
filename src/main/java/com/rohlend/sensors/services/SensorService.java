package com.rohlend.sensors.services;

import com.rohlend.sensors.models.Sensor;
import com.rohlend.sensors.repositories.SensorRepository;
import com.rohlend.sensors.util.SensorNotCreatedExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor){
        if(checkIfExist(sensor.getName())){
            throw new SensorNotCreatedExecption("This sensor already exists");
        }
        else{
            sensorRepository.save(sensor);
        }
    }

    public boolean checkIfExist(String name){
        return sensorRepository.findByName(name) == null;
    }

}
