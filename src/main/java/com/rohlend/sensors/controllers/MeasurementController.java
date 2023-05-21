package com.rohlend.sensors.controllers;

import com.rohlend.sensors.dto.MeasurementDTO;
import com.rohlend.sensors.dto.SensorDTO;
import com.rohlend.sensors.models.Measurement;
import com.rohlend.sensors.models.Sensor;
import com.rohlend.sensors.services.MeasurementService;
import com.rohlend.sensors.util.ErrorResponse;
import com.rohlend.sensors.util.MeasurementNotCreatedException;
import com.rohlend.sensors.util.SensorNotCreatedExecption;
import com.rohlend.sensors.util.SensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping("")
    public List<MeasurementDTO> getAllMeasurements(){
        return measurementService.findAll().stream()
                .map(this::convertToMeasurementDTO).toList();
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDays(){
        return measurementService.findRainyDays();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ");
            }
            throw new MeasurementNotCreatedException(errorMsg.toString());
        }
        measurementService.save(convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotCreatedExecption e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementNotCreatedException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        Measurement measurement = modelMapper.map(measurementDTO,Measurement.class);
        measurement.setSensor(convertToSensor(measurementDTO.getSensor()));
        return measurement;
    }
    private MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement,MeasurementDTO.class);
    }
    private Sensor convertToSensor(SensorDTO sensorDTO){
        Sensor sensor = modelMapper.map(sensorDTO, Sensor.class);
        sensorValidator.validate(sensor);
        return sensor;
    }

}
