package com.example.speeddialer;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by adi on 14/09/16.
 */
public class MyReceiver extends BroadcastReceiver {

    static int countPowerClicks = 0;
    private boolean screenOff;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF) || intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            countPowerClicks++;
        }

        if (countPowerClicks == 3) {
            countPowerClicks = 0;
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            String numberString = preferences.getString(context.getString(R.string.contact_number), "default");
            Uri number = Uri.parse("tel:" + numberString);
            Intent callIntent = new Intent(Intent.ACTION_CALL, number);
            callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            context.startActivity(callIntent);
        }

    }
}
