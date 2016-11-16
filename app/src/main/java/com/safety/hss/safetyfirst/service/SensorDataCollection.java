package com.safety.hss.safetyfirst.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.ArrayList;

import static java.lang.System.in;

public class SensorDataCollection extends IntentService implements SensorEventListener {

    // Log tag
    public static final String aa = "LOGGING";

    // service actions
    private static final String ACTION_COLLECT = "com.safety.hss.safetyfirst.service.action.COLLECT";
    private static final String ACTION_STOP = "com.safety.hss.safetyfirst.service.action.STOP";

    // parameters (if any)
    // private static final String EXTRA_PARAM1 = "com.safety.hss.safetyfirst.service.extra.PARAM1";

    // Sensor variables
    public Sensor acc;
    public SensorManager sm;

    // Sensor data is collected here
    public static ArrayList<Float[]> data = new ArrayList<Float[]>();

    public static int currentAccuracy;
    public static boolean started = false, finished = false, successful = true;


    public SensorDataCollection() {
        super("SensorDataCollection");
    }

    public static void startCollecting(Context context) {
        Intent intent = new Intent(context, SensorDataCollection.class);
        intent.setAction(ACTION_COLLECT);
        context.startService(intent);
    }

    public static void stopCollecting(Context context) {
        Intent intent = new Intent(context, SensorDataCollection.class);
        intent.setAction(ACTION_STOP);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_COLLECT.equals(action)) {
                // final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                handleActionCollect();

                while (!successful){
                    try {
                        Thread.sleep(4000);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            } else if (ACTION_STOP.equals(action)) {
                handleStop();
            }
        }
    }

    private void handleActionCollect() {
        Log.d(aa,"Collecting data");
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        acc=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sm.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    public boolean stopService(Intent name) {
        handleStop();
        return super.stopService(name);

    }

    private void handleStop() {
        Log.d(aa, "Stop collection");
        for(Float[] b: data)
        {
            Log.d(aa, "(x,y,z,accuracy = "+ b);
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(started) {
            Float[] temp = {sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2], currentAccuracy * 1.0f};
            data.add(temp);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        if(started) {
            currentAccuracy = i;
        }
    }
}
