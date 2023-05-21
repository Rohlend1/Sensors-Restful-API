package com.rohlend.sensors.util;

public class SensorNotCreatedExecption extends RuntimeException {
    private String errorMsg;
    public SensorNotCreatedExecption(String message) {
        super(message);
    }
}
