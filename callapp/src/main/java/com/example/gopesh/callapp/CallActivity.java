package com.example.gopesh.callapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CallActivity extends Activity
{

    private boolean detectEnabled;

    private TextView textViewDetectState;
    private Button buttonToggleDetect;
    private Button buttonExit;

    ComponentName componentName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        textViewDetectState = (TextView) findViewById(R.id.textViewDetectState);
        //am= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        buttonToggleDetect = (Button) findViewById(R.id.buttonDetectToggle);
        buttonToggleDetect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setDetectEnabled(!detectEnabled);
            }
        });

        buttonExit = (Button) findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setDetectEnabled(false);
                CallActivity.this.finish();
            }
        });
    }

    public void start(View view)
    {
        componentName = startService(new Intent(this, CallDetectService.class));
    }



    private void setDetectEnabled(boolean enable) {
        detectEnabled = enable;

        Intent intent = new Intent(this, CallDetectService.class);
        if (enable) {
            // start detect service
            startService(intent);

            buttonToggleDetect.setText("Turn off");
            textViewDetectState.setText("Detecting");
        }
        else {
            // stop detect service
            stopService(intent);
            buttonToggleDetect.setText("Turn on");
            textViewDetectState.setText("Not detecting");
        }
    }

}
