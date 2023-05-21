package com.rohlend.sensors.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @Min(value = -100,message = "Value should be more than -100")
    @Max(value = 100, message = "Value should be less than 100")
    private double value;

    @Column(name = "raining")
    @NotEmpty
    private boolean raining;

    @ManyToOne()
    @JoinColumn(name = "sensor_id",referencedColumnName = "id")
    @NotEmpty
    private Sensor sensor;

    @Column(name="measured_at")
    private LocalDateTime measured_at;

    public Measurement() {
    }

    public Measurement(double value, boolean raining, Sensor sensor, LocalDateTime measured_at) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
        this.measured_at = measured_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDateTime getMeasured_at() {
        return measured_at;
    }

    public void setMeasured_at(LocalDateTime measured_at) {
        this.measured_at = measured_at;
    }
}
