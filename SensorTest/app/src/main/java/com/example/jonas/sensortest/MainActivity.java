package com.example.jonas.sensortest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.*;
import android.view.*;
import android.widget.TextView;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private FusedLocationProviderClient mFusedLocationClient;
    private TextView mTextLatitude;
    private TextView mTextLongitude;


    private TextView mXLocation;
    private TextView mYLocation;
    private TextView MZLocation;

    private SensorManager mSensorManager;
    private Sensor mSensor;



    private Location mLastLocation;
    private Double hej;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor
                (Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mXLocation = (TextView) findViewById(R.id.mXLocation);
        mYLocation = (TextView) findViewById(R.id.mYLocation);


        mTextLatitude = (TextView) findViewById(R.id.textLatitude);
        mTextLongitude = (TextView) findViewById(R.id.textLongitude);
        mTextLongitude.setText("hej mor");
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);






            return;
        }

        @Override
        public void onStart(){
            super.onStart();


            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},100);

            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            }
                mFusedLocationClient.getLastLocation()
                        .addOnCompleteListener(this, new OnCompleteListener<Location>() {


                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                // Got last known location. In some rare situations this can be null.
                                mLastLocation = task.getResult();

                                if (mLastLocation != null) {
                                    mTextLongitude.setText("latitude " + String.valueOf(mLastLocation.getLongitude()));
                                    mTextLatitude.setText("longitude " + String.valueOf(mLastLocation.getLatitude()));
                                    // Logic to handle location object
                                }
                            }
                        });

        }

        @SuppressLint("SetText")
        @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        mXLocation.setText("X: " + sensorEvent.values[0]);
        mYLocation.setText("Y: " + sensorEvent.values[1]);
}

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}

