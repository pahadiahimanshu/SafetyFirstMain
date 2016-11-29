package com.safety.hss.safetyfirst.ui;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class TextToSpeechService extends Service implements TextToSpeech.OnInitListener {
    public final static String TAG = "TAG";

    public static final String TEXT_TO_READ = "text";
    private final String UTTERANCE_ID = "FINISHED_PLAYING";
    private final int MULTI_LINE = 2;

    private TextToSpeech tts;
    private ArrayList<String> texts;
    private String tname;
    private boolean isInit;

    private UtteranceProgressListener utteranceProgressListener = new UtteranceProgressListener() {
        @Override
        public void onStart(String utteranceId) {

        }

        @Override
        public void onDone(String utteranceId) {
            if (utteranceId.equals(UTTERANCE_ID)) {
                stopSelf();
            }
        }

        @Override
        public void onError(String utteranceId) {
            stopSelf();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        tts = new TextToSpeech(getApplicationContext(), this);
        tts.setOnUtteranceProgressListener(utteranceProgressListener);
        Log.d(TAG, "onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        tname = intent.getStringExtra("sender name");


        if (isInit) {
            //Toast.makeText(getApplicationContext(),"Phone Ringing "+ tname,Toast.LENGTH_SHORT).show();
            while (tts.isSpeaking())
            {

            }
            Log.e(TAG, "Inside with Init");
            speak();
        }

        return TextToSpeechService.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        Log.e(TAG, "onInit");
        if ( status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.getDefault());
            if (result != TextToSpeech.LANG_MISSING_DATA
                    && result != TextToSpeech.LANG_NOT_SUPPORTED) {
                //Toast.makeText(getApplicationContext(),"Phone Ringing1.... "+ tname,Toast.LENGTH_SHORT).show();

                speak();
                isInit = true;
            }
        }
    }

    private void speak() {
        if (tts != null) {

            // Speak with 3 parameters deprecated but necessary on pre 21 version codes
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // This is a single message
                String utteranceId = UTTERANCE_ID;

                tts.speak(tname, TextToSpeech.QUEUE_FLUSH, null, utteranceId);

            } else {
                //Toast.makeText(getApplicationContext(),"Speaking"+ tname,Toast.LENGTH_SHORT).show();

                HashMap<String, String> myHashAlarm = null;

                myHashAlarm = new HashMap<>();
                myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, UTTERANCE_ID);

                tts.speak(tname, TextToSpeech.QUEUE_FLUSH, myHashAlarm);

            }
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}