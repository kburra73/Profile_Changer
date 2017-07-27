package com.example.gopesh.incomingcalldetector;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Gopesh on 30-04-2017.
 */

public class CallDetectService extends Service {
    private CallHelper callHelper;

    public CallDetectService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        callHelper = new CallHelper(this);
        callHelper.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        callHelper.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // not supporting binding
        return null;
    }
}
