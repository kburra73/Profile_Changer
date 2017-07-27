package com.example.gopesh.profilechanger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
public class pro51 extends AppCompatActivity implements View.OnClickListener {

    Button button1,button2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro51);
        button1 = (Button) findViewById(R.id.but_s1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.but_s2);
        button2.setOnClickListener(this);
        setTitle("Profile Changer");
    }



    @Override
    public void onClick(View view) {
        Intent start = new Intent(this, Service_pro.class);
        switch (view.getId()) {
            case R.id.but_s1:
                Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show();
                this.startService(start);
                Intent in = new Intent(pro51.this,MyService.class);
                startService(in);
                break;
            case R.id.but_s2:
                Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
                this.stopService(start);
                Intent intent = new Intent(pro51.this,MyService.class);
                stopService(intent);
                break;
        }

   }
}