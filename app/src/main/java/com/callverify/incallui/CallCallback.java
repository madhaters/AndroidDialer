package com.callverify.incallui;

import android.content.Context;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by c_srayav on 7/23/17.
 */

public class CallCallback extends android.telecom.Call.Callback {
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onStateChanged(Call call, int state) {
        // comm_lib = new Common_lib();
        super.onStateChanged(call, state);
        switch (state) {
            case Call.STATE_ACTIVE:
                showToast("KPI_ACTIVE");
                tracePrint("SCRIPT", "LOGCAT_VOICE_STATUS", "KPI_ACTIVE");
                break;
            case Call.STATE_DIALING:
                showToast("KPI_DIALING");
                tracePrint("SCRIPT", "LOGCAT_VOICE_STATUS", "KPI_DIALING");
                break;
            case Call.STATE_DISCONNECTED:
                showToast("KPI_DISCONNECTED");
                tracePrint("SCRIPT", "LOGCAT_VOICE_STATUS", "KPI_DISCONNECTED");
                break;
            case Call.STATE_RINGING:
                showToast("KPI_INCOMING");
                tracePrint("SCRIPT", "LOGCAT_VOICE_STATUS", "KPI_INCOMING");
                break;
            case Call.STATE_NEW:
                showToast("KPI_IDLE");
                tracePrint("SCRIPT", "LOGCAT_VOICE_STATUS", "KPI_IDLE");
                break;

        }
    }

    public void tracePrint(String Tag, String service, String content) {
        String strMessage;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        System.out.println("Current time => " + strDate);
        strMessage = "DROID2PY>>" + strDate + ";" + "phone_" + 1 + ";" + service + ";" + content;
        Log.v(Tag, strMessage);
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
