package com.example.gopesh.profilechanger;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class alt11 extends AppCompatActivity implements LocationListener {

    Button getloc;
    TextView address;
    LocationManager loc;
    double late, lng;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alt11);
        getloc = (Button) findViewById(R.id.getloc);
        address = (TextView) findViewById(R.id.address);
        setTitle("Address");
        loc = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
          return;
        }
        loc.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

    }

    public void getloc(View v){
        String s="geo:"+late+","+lng+"";
        Uri u= Uri.parse(s);
        Intent i=new Intent(Intent.ACTION_VIEW);
        i.setData(u);
        startActivity(i);
    }

    @Override
    public void onLocationChanged(Location location) {

        ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true )
        {
            //Toast.makeText(alt11.this, "Network Available", Toast.LENGTH_LONG).show();
            late=location.getLatitude();
            lng=location.getLongitude();
            String s="";
            Geocoder g=new Geocoder(this);
            try{
                List<Address> add=g.getFromLocation(late, lng,2);
                Address ad=add.get(0);

                for(int i=0;i<ad.getMaxAddressLineIndex();i++)
                {
                    s+=ad.getAddressLine(i)+"\n";
                }
                address.setText(s);
            }
            catch(Exception ex)
            {
                Toast.makeText(alt11.this,"Error",Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(alt11.this, "Network Not Available\nPlease Enable Internet", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}