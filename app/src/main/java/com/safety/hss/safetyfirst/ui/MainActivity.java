package com.safety.hss.safetyfirst.ui;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.safety.hss.safetyfirst.R;

public class MainActivity extends AppCompatActivity {

    public static final String aa = "LOGGING";
    final String color_bgGrey = "#bcbbbb";
    final String color_primary = "#3fb57c";

    ImageView img_btn_start;
    LinearLayout middle_section;
    TextView lbl_text_middleMsg;

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
                if(middle_section_color)
                {
                    middle_section.setBackgroundColor(Color.parseColor(color_bgGrey));
                    middle_section_color = false;
                    lbl_text_middleMsg.setText("Service stoppped");
                }
                else
                {
                    middle_section.setBackgroundColor(Color.parseColor(color_primary));
                    middle_section_color = true;
                    lbl_text_middleMsg.setText("Service started");
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
}
