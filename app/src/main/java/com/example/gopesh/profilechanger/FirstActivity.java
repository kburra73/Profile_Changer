package com.example.gopesh.profilechanger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void sense(View v){

        Intent in = new Intent(FirstActivity.this,MainActivity.class);
        startActivity(in);
    }

    public void changeProfile(View v){

        Intent in = new Intent(FirstActivity.this,CallActivity.class);
        startActivity(in);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.help) {
            Intent in = new Intent(FirstActivity.this,HelpActivity.class);
            startActivity(in);
        }

        if (id == R.id.about) {
            Intent in = new Intent(FirstActivity.this,AboutActivity.class);
            startActivity(in);
        }
        return super.onOptionsItemSelected(item);
    }
}
