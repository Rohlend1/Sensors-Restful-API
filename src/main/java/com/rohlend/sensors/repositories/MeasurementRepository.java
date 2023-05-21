package com.rohlend.sensors.repositories;

import com.rohlend.sensors.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    int countByRainingIsTrue();
}
