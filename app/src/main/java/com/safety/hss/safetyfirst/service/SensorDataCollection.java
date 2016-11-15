package com.safety.hss.safetyfirst.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

public class SensorDataCollection extends IntentService {

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
            } else if (ACTION_STOP.equals(action)) {
                handleStop();
            }
        }
    }

    private void handleActionCollect() {
        startCollecting(this);
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void handleStop() {
        stopCollecting(this);
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
