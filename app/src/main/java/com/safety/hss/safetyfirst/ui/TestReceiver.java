package com.safety.hss.safetyfirst.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Sakti Saurav on 11/27/2016.
 */
public class TestReceiver extends BroadcastReceiver {
//    @Override
//    public void onReceive(final Context context, Intent intent) {
//        if (intent.getAction().equals(MyCallReceiver.CUSTOM_INTENT)) {
//            System.out.println("GOT THE INTENT");
//            //final String mobileNumber = intent.getExtras().getString("number");
//            Thread thread = new Thread(){
//                private int sleepTime = 400;
//
//                @Override
//                public void run() {
//                    super.run();
//                    try {
//                        int wait_Time = 0;
//
//                        while (wait_Time < sleepTime ) {
//                            sleep(100);
//                            wait_Time += 100;
//                        }
//                    }catch (Exception e) {
//                        Toast.makeText(context,
//                                "Error Occured Because:" + e.getMessage(),
//                                Toast.LENGTH_SHORT).show();
//                    }
//                    finally {
//
//                    }
//
//                    context.startActivity(new Intent(context, CallActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//                }
//            };
//            thread.run();
//        }
//    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(MyCallReceiver.CUSTOM_INTENT)) {
            System.out.println("GOT THE INTENT");
            context.startActivity(new Intent(context, CallActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
}

