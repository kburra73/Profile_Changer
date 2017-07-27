package com.example.gopesh.profilechanger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private static Button but_mot;
    private static Button but_sav;
    private static Button but_test;
    private static Button but_alt;
    private static Button but_inc;
    private static Button but_pro;
    private String packagename="com.example.gopesh.senseapp";
    private String classname="com.example.gopesh.senseapp.CallApp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onButtonClickListener();
        setTitle("SenseApp");
    }

    public void onButtonClickListener()
    {


        but_alt=(Button)findViewById(R.id.but_alt);
        but_pro=(Button)findViewById(R.id.but_profile);
        but_alt.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {

                        Intent i = new Intent(MainActivity.this,alt11.class);
                        startActivity(i);
                    }
                }
        );


        but_pro.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {

                        Intent i = new Intent(MainActivity.this,pro51.class);
                        startActivity(i);
                    }
                }
        );
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButtonClick(View view) {
    }*/

}
