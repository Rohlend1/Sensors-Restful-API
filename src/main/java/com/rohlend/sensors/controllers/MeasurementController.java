package com.rohlend.sensors.controllers;

import com.rohlend.sensors.dto.MeasurementDTO;
import com.rohlend.sensors.dto.SensorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @GetMapping("")
    public List<MeasurementDTO> getAllMeasurements(){
        return null;
    }

    @GetMapping("/rainyDaysCount")
    public Integer getRainyDays(){
        return null;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody MeasurementDTO measurementDTO){

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
