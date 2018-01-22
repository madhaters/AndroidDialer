package com.callverify.incallui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PhonecallReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        //We listen to two intents.  The new outgoing call only tells us of an outgoing call.  We use it to get the number.
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            //do nothing
        } else {
            String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            if (stateStr != null) {
                if (stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                    Toast.makeText(context, "Ringing", Toast.LENGTH_SHORT).show();
                    tracePrint("SCRIPT", "LOGCAT_VOICE_STATUS", "KPI_INCOMING");
                }
            }
        }
    }

    public void tracePrint(String Tag, String service, String content) {
        String strMessage;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        System.out.println("Current time => " + strDate);
        strMessage = "DROID2PY>>" + strDate + ";" + "phone_" + 1 + ";" + service + ";" + content;
        android.util.Log.v(Tag, strMessage);
    }
}