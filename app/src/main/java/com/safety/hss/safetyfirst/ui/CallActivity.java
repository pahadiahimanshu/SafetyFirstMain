package com.safety.hss.safetyfirst.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;



import java.lang.reflect.Method;

interface ITelephony {

    boolean endCall();

    void answerRingingCall();

    void silenceRinger();

}

public class CallActivity extends Activity {
    private ITelephony telephonyService;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTheme(android.R.style.Theme_Dialog);

    }


    @Override
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
    }
}