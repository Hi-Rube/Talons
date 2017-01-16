package com.hirube.talons.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rube on 17/1/16.
 */
public class DirectionTalons extends SensorTalons  {

    float[] accelerometerValues=new float[3];
    float[] magneticFieldValues=new float[3];
    float[] values=new float[3];
    float[] rotate=new float[9];

    Float[] rsValues = new Float[3];

    private SensorTalonsEventListener listener = null;

    public DirectionTalons(SensorTalonsEventListener listener){
        this.listener = listener;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            accelerometerValues=event.values;
        }
        if(event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            magneticFieldValues=event.values;
        }

        SensorManager.getRotationMatrix(rotate, null, accelerometerValues, magneticFieldValues);
        SensorManager.getOrientation(rotate, values);
        rsValues[0] = new Float(Math.toDegrees(values[0]));
        rsValues[1] = new Float(Math.toDegrees(values[1]));
        rsValues[2] = new Float(Math.toDegrees(values[2]));

        listener.work(rsValues);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public int getSensorDelay(){
        return SensorManager.SENSOR_DELAY_FASTEST;
    }

    @Override
    public List<Integer> getType() {
        ArrayList<Integer> types = new ArrayList<>(2);
        types.add(Sensor.TYPE_ACCELEROMETER);
        types.add(Sensor.TYPE_MAGNETIC_FIELD);
        return types;
    }
}
