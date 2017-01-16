package com.hirube.talons.sensor;

import android.content.Context;
import android.hardware.SensorManager;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Rube on 17/1/16.
 */
public class SensorTalonsManager {

    public static int SENSOR_DELAY_FASTEST = SensorManager.SENSOR_DELAY_FASTEST;
    public static int SENSOR_DELAY_GAME = SensorManager.SENSOR_DELAY_GAME;
    public static int SENSOR_DELAY_NORMAL = SensorManager.SENSOR_DELAY_NORMAL;
    public static int SENSOR_DELAY_UI = SensorManager.SENSOR_DELAY_UI;

    private SensorManager mSensorManager;
    private ArrayList<SensorTalons> workQueue = new ArrayList<SensorTalons>();
    private int sensorDelay = -1;

    public SensorTalonsManager(Context cxt){
        this.mSensorManager = (SensorManager)cxt.getSystemService(cxt.SENSOR_SERVICE);
    }

    public void register(SensorTalons work){
        Iterator<Integer> iterator = work.getType().iterator();

        while (iterator.hasNext()){
            mSensorManager.registerListener(work, mSensorManager.getDefaultSensor(iterator.next()), sensorDelay == -1 ? work.getSensorDelay() : sensorDelay );
            workQueue.add(work);
        }
    }

    public void unregister(SensorTalons work){
        mSensorManager.unregisterListener(work);
        workQueue.remove(work);
    }

    public void setSensorDelay(int sensorDelay){
        this.sensorDelay = sensorDelay;
    }
}
