package com.hirube.talons.sensor;

import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.List;

/**
 * Created by Rube on 17/1/16.
 */
public abstract class SensorTalons implements SensorEventListener {
    public int getSensorDelay() {
        return sensorDelay;
    }

    public void setSensorDelay(int sensorDelay) {
        this.sensorDelay = sensorDelay;
    }

    private int sensorDelay = SensorManager.SENSOR_DELAY_FASTEST;

    public abstract List<Integer> getType();
}
