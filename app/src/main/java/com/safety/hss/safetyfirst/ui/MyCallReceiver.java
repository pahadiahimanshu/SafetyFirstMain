package com.safety.hss.safetyfirst.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by Sakti Saurav on 11/27/2016.
 */
public class MyCallReceiver extends BroadcastReceiver{
    public static final String CUSTOM_INTENT = "jason.wei.custom.intent.action.TEST";
    Context context = null;
    private static final String TAG = "Phone call";


    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();
        if (extras != null) {
            String state = extras.getString(TelephonyManager.EXTRA_STATE);
            Log.e("DEBUG", state);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

                Intent i = new Intent();
                i.setAction(CUSTOM_INTENT);
                context.sendBroadcast(i);

            }
        }
    }
}
