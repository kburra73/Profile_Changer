package com.example.gopesh.callapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;


/**
 * Created by Gopesh on 30-04-2017.
 */

public class CallHelper extends CallActivity
{


    public CallHelper() {

    }

//private TextView t2= (TextView) findViewById(R.id.t2);

    private class CallStateListener extends PhoneStateListener {

        //private TextView t2= (TextView) findViewById(R.id.t2);

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            int st=state;
            String s=incomingNumber;

            switch (st) {
                case  TelephonyManager.CALL_STATE_RINGING:
                    // called when someone is ringing to this phone

                   /* Toast.makeText(ctx,
                            "Incoming: " + s,Toast.LENGTH_LONG).show();*/


            }

            try {
                Toast.makeText(ctx,
                        "Incoming: " + s,Toast.LENGTH_LONG).show();
                String n="+919584710377";

                if (n.equals(s)) {
                    int streamMaxVolume = am.getStreamMaxVolume(AudioManager.STREAM_RING);
                    am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    am.setStreamVolume(AudioManager.STREAM_RING, streamMaxVolume,
                            AudioManager.FLAG_ALLOW_RINGER_MODES);


                  //  am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

                    // Toast.makeText(CallHelper.this, "volume="+ Integer.toString(streamMaxVolume), Toast.LENGTH_LONG).show(); //I got 7
                 /*   int streamMaxVolume = am.getStreamMaxVolume(AudioManager.STREAM_RING);
                    am.setStreamVolume(AudioManager.STREAM_RING, streamMaxVolume,
                            AudioManager.FLAG_ALLOW_RINGER_MODES);*/

                }
            } catch (Exception e) {

            }



         /*   switch (st) {
                case  TelephonyManager.CALL_STATE_RINGING:
                    // called when someone is ringing to this phone

                    Toast.makeText(ctx,
                            "Incoming: " + s,Toast.LENGTH_LONG).show();


            }*/
        }


    }



    /**
     * Broadcast receiver to detect the outgoing calls.
     */
    public class OutgoingReceiver extends BroadcastReceiver {
        public OutgoingReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

            Toast.makeText(ctx,
                    "Outgoing: "+number,
                    LENGTH_LONG).show();
        }

    }

    private Context ctx;
    private TelephonyManager tm;
    private CallStateListener callStateListener;
    private AudioManager am;

    private OutgoingReceiver outgoingReceiver;

    public CallHelper(Context ctx) {
        this.ctx = ctx;


        callStateListener = new CallStateListener();
        outgoingReceiver = new OutgoingReceiver();
    }

    /**
     * Start calls detection.
     */
    public void start() {
        tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(callStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        am= (AudioManager) ctx. getSystemService(Context.AUDIO_SERVICE);

        //am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
     /*   int streamMaxVolume = am.getStreamMaxVolume(AudioManager.STREAM_RING);
        // Toast.makeText(CallHelper.this, "volume="+ Integer.toString(streamMaxVolume), Toast.LENGTH_LONG).show(); //I got 7
        am.setStreamVolume(AudioManager.STREAM_RING, streamMaxVolume,
                AudioManager.FLAG_ALLOW_RINGER_MODES);*/



        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_NEW_OUTGOING_CALL);
        ctx.registerReceiver(outgoingReceiver, intentFilter);
    }

    /**
     * Stop calls detection.
     */
    public void stop() {
        tm.listen(callStateListener, PhoneStateListener.LISTEN_NONE);
        ctx.unregisterReceiver(outgoingReceiver);
    }

}

