package com.callverify.incallui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telecom.Call;
import android.telephony.TelephonyManager;
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

    private String networkType() {
        TelephonyManager teleMan = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = teleMan.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return "1xRTT";
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return "CDMA";
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return "EDGE";
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return "eHRPD";
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return "EVDO rev. 0";
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return "EVDO rev. A";
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return "EVDO rev. B";
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return "GPRS";
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return "HSDPA";
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return "HSPA";
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return "HSPA+";
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return "HSUPA";
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return "iDen";
            case TelephonyManager.NETWORK_TYPE_LTE:
                return "LTE";
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return "UMTS";
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return "Unknown";
        }
        throw new RuntimeException("New type of network");
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
        String networkType = getNetworkType();
        Toast.makeText(context, message + " " + networkType, Toast.LENGTH_SHORT).show();
    }

    private String getNetworkType() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (activeNetworkInfo == null) return "Unknown";
        switch (activeNetworkInfo.getType()) {
            case ConnectivityManager.TYPE_WIFI:
                return "WIFI";
            case ConnectivityManager.TYPE_WIMAX:
                return "WiMax";
            case ConnectivityManager.TYPE_BLUETOOTH:
                return "Bluetooth";
            case ConnectivityManager.TYPE_MOBILE:
                return networkType();
            default:
                return "Unknown";
        }
    }
}
