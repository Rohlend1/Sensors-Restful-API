package com.rohlend.sensors.dto;


import com.rohlend.sensors.models.Sensor;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class MeasurementDTO {

    @Min(value = -100,message = "Value should be more than -100")
    @Max(value = 100, message = "Value should be less than 100")
    private double value;

    @NotEmpty
    private boolean raining;

    @NotEmpty
    private Sensor sensor;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
