package com.safety.hss.safetyfirst.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safety.hss.safetyfirst.R;
import com.safety.hss.safetyfirst.service.SensorDataCollection;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    // Log tag
    public static final String aa = "LOGGING";

    // service actions
    private static final String ACTION_COLLECT = "com.safety.hss.safetyfirst.service.action.COLLECT";
    private static final String ACTION_STOP = "com.safety.hss.safetyfirst.service.action.STOP";

    // color hex codes
    final String color_bgGrey = "#bcbbbb";
    final String color_primary = "#3fb57c";

    // declare app ui components
    ImageView img_btn_start;
    LinearLayout middle_section;
    TextView lbl_text_middleMsg;

    // declare helper vairables
    boolean middle_section_color = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lbl_text_middleMsg = (TextView) findViewById(R.id.lbl_text_middleMsg);

        img_btn_start = (ImageView)findViewById(R.id.img_btn_start);
        middle_section = (LinearLayout)findViewById(R.id.middle_section);

        img_btn_start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, SensorDataCollection.class);
                intent.setAction(ACTION_COLLECT);
                if(middle_section_color)
                {
                    middle_section.setBackgroundColor(Color.parseColor(color_bgGrey));
                    middle_section_color = false;
                    lbl_text_middleMsg.setText("Service stoppped");
                    SensorDataCollection.started = false;
                    SensorDataCollection.finished = true;
                    Log.d(aa, "Stop collection");
                    for(Float[] b: SensorDataCollection.data)
                    {
                        Log.d(aa, "(x,y,z,accuracy) = (" + b[0]+", "+b[1]+", "+b[2]+", "+b[3]+")");
                    }
                    stopService(intent);
                    Log.d(aa, "Stopped");
                    if(!SensorDataCollection.finished)
                    {

                    }
                }
                else
                {

                    middle_section.setBackgroundColor(Color.parseColor(color_primary));
                    middle_section_color = true;
                    lbl_text_middleMsg.setText("Service started");
                    SensorDataCollection.started = true;
                    SensorDataCollection.finished = false;

                    startService(intent);
                }

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(SensorDataCollection.started) {
            Log.d(aa,sensorEvent.values[0]+", "+sensorEvent.values[1]+", "+sensorEvent.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        if(SensorDataCollection.started) {
            Log.d(aa,"sensor accuracy changed ");
        }
    }
}
