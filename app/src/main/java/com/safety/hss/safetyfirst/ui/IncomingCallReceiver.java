package com.safety.hss.safetyfirst.ui;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Sakti Saurav on 11/13/2016.
 */
public class IncomingCallReceiver extends BroadcastReceiver {
    Context context;
   // TextToSpeech tts;
    int i =0;
    static PhoneStateListener psl;


    @SuppressWarnings("deprecation")
    private void ttsUnder20(String text) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
        //tts.speak(text, TextToSpeech.QUEUE_FLUSH, map);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String text) {
        String utteranceId=this.hashCode() + "";
        //tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        try{
            i++;
            Log.e("TAG","onReceive");

            String state1 = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

            Log.e("TAG","Create" + i);
        //final String num = TelephonyManager.EXTRA_INCOMING_NUMBER;

        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

            if (psl==null)
            {
                psl = new PhoneStateListener(){
                    public void onCallStateChanged(int state,String incomingNumber)
                    {

                        if(state == TelephonyManager.CALL_STATE_RINGING)
                        {
                            Log.e("TAG","RingingState" +i);
                            //String num = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                            String name = null;
                            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(incomingNumber));
                            Cursor cursor = context.getContentResolver().query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, incomingNumber, null, null );
                            if(cursor.moveToFirst()){
                                name =  cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
                            }
                            cursor.close();
//
//                  Toast.makeText(context,"Phone Ringing "+ name,Toast.LENGTH_LONG).show();

                            Intent speechIntent = new Intent(context, TextToSpeechService.class);
                            speechIntent.putExtra("sender name",name);
                            context.startService(speechIntent);

                            //new SpeakingTask(context).execute(name);

//                  tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
//                      @Override
//                      public void onInit(int i) {
//                          Log.e("TAG", "INIT");
//                          if(i == TextToSpeech.SUCCESS)
//                          {
//                              int result = tts.setLanguage(Locale.US);
//                              if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
//                              {
//                                  Log.e("TAG", "This Language is not supported");
//                              }
//                          }
//                          else {
//                              Log.e("TAG", "Initilization Failed!");
//                          }
//                      }
//                  });

//                  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    //ttsGreater21(name + "calling");
//                    //tts.speak(name + "calling",TextToSpeech.QUEUE_FLUSH,null,null);
//                } else {
//                    //ttsUnder20(name + "calling");
//                    //tts.speak(name + "calling", TextToSpeech.QUEUE_FLUSH, null);
//                }
                        }
                        if(state == TelephonyManager.CALL_STATE_OFFHOOK)
                        {
                            Toast.makeText(context,"Phone Currently in a call",Toast.LENGTH_LONG).show();
                        }
                        if(state == TelephonyManager.CALL_STATE_IDLE)
                        {
                            //Toast.makeText(context,"Phone Neither ringing nor in a call",Toast.LENGTH_LONG).show();
                        }

                    }
                };
            }


        tm.listen(psl,PhoneStateListener.LISTEN_CALL_STATE);
//            Log.e("TAG",state);
//            Log.e("TAG",TelephonyManager.EXTRA_STATE_RINGING);
//            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
////                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                    ttsGreater21(name + "calling");
////                    //tts.speak(name + "calling",TextToSpeech.QUEUE_FLUSH,null,null);
////                } else {
////                    ttsUnder20(name + "calling");
////                    //tts.speak(name + "calling", TextToSpeech.QUEUE_FLUSH, null);
////                }
//                //tts.speak(name + "calling",TextToSpeech.QUEUE_FLUSH,null);
//                Log.e("TAG","RingingState");
//                String num = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
//                String name = null;
//                Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(num));
//                Cursor cursor = context.getContentResolver().query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, num, null, null );
//                if(cursor.moveToFirst()){
//                    name =  cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
//                }
//                cursor.close();
//
//                Log.e("TAG",name);
//
//
//                Log.e("TAG",name);
//                Toast.makeText(context, "Phone Is Ringing " + name , Toast.LENGTH_LONG).show();
//            }
//
//            if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
//                Toast.makeText(context, "Call Recieved", Toast.LENGTH_LONG).show();
//            }
//
//            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
//                Toast.makeText(context, "Phone Is Idle", Toast.LENGTH_LONG).show();
//            }


            
        }
        catch(Exception e){e.printStackTrace();}
    }


}
