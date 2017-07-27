package com.example.gopesh.profilechanger;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service
{

    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(MyService.this,"Service Started....",Toast.LENGTH_LONG).show();

        startService(new Intent(this,pro51.class));
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        stopService(new Intent(this,pro51.class));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
