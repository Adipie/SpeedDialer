package com.example.speeddialer;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class UpdateService extends Service {

    private MyReceiver mReceiver;

    public UpdateService() {
    }

    @Override
    public void onCreate(){

        super.onCreate();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        mReceiver = new MyReceiver();
        registerReceiver(mReceiver, filter);
    }

    public void onStartCommand(Intent intent, int startId){
        boolean screenOn = intent.getBooleanExtra("screen_state", false);
        if(screenOn){
            Toast.makeText(getApplicationContext(), "Awake from service", Toast.LENGTH_LONG)
                    .show();

        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
