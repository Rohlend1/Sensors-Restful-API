package com.rohlend.sensors.services;

import com.rohlend.sensors.models.Measurement;
import com.rohlend.sensors.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement){
        measurement.setMeasured_at(LocalDateTime.now());
        measurementRepository.save(measurement);
    }

    public int findRainyDays(){
        return measurementRepository.countByRainingIsTrue();
    }
}
