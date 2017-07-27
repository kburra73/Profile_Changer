package com.example.gopesh.profilechanger;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CallActivity extends AppCompatActivity
{

    private boolean detectEnabled;

    private TextView textViewDetectState;
    private Button buttonToggleDetect;
    private Button buttonExit;
    public static EditText t2;
    public static String t;
    SQLiteDatabase sd=null;

    ComponentName componentName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        setTitle("Call Profile Changer");
        Intent ob=this.getIntent();
        t2= (EditText) findViewById(R.id.t2);
        t2.setText("+91");
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

    public String get()
    {
         t=t2.getText().toString();

        sd=openOrCreateDatabase("number.sqlite",SQLiteDatabase.CREATE_IF_NECESSARY,null);
        sd.execSQL("CREATE TABLE IF NOT EXISTS number(number VARCHAR2)");

        ContentValues val=new ContentValues();
        val.put("number",t);

        long a=sd.insert("number",null,val);
        if(a>0)
            Toast.makeText(CallActivity.this,"Number Insert !!",Toast.LENGTH_LONG).show();

        return String.valueOf(this.t);
    }

    private void setDetectEnabled(boolean enable) {

        detectEnabled = enable;
        componentName = startService(new Intent(this, CallDetectService.class));



        Intent intent = new Intent(this, CallDetectService.class);
       // setTitle("Call Profile Changer");
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
