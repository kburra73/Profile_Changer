package com.example.gopesh.incomingcalldetector;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{

    private boolean detectEnabled;

    private TextView textViewDetectState;
    private Button buttonToggleDetect;
    private Button buttonExit;

    ComponentName componentName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                MainActivity.this.finish();
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
