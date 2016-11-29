package com.safety.hss.safetyfirst.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver  extends BroadcastReceiver {

    String str = "";
    String no = "";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        if (bundle != null)
        {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);

                str += msgs[i].getMessageBody().toString();
                str += "\n";
                no = msgs[i].getOriginatingAddress();

                //Resolving the contact name from the contacts.
                Uri lookupUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(no));
                Cursor c = context.getContentResolver().query(lookupUri, new String[]{ContactsContract.Data.DISPLAY_NAME},null,null,null);
                try {
                    c.moveToFirst();
                    String  displayName = c.getString(0);
                    String ContactName = displayName;
                    Toast.makeText(context, ContactName + " texted", Toast.LENGTH_LONG).show();

                    Log.e("SMS",str);

                    Intent speechIntent = new Intent(context, TextToSpeechService.class);
                    speechIntent.putExtra("sender name",ContactName + " texted " + str);
                    context.startService(speechIntent);

                } catch (Exception e) {
                    // TODO: handle exception
                }finally{
                    c.close();
                }

            }
        }
    }
}