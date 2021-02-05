package com.example.a35exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor mLightSensor;
    private TextView valueOfLight;
    private float [] lightValue;
    private final int MUSIC_BORDER = 30;
    private float previousLightValue = 60;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        setContentView(R.layout.activity_main);
        lightValue= new float[0];
        valueOfLight = (TextView) findViewById(R.id.valueOfLight);
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_UI);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    private void playMusic(){
        Intent intent = new Intent(this, MusicService.class);
        startService(intent.putExtra("status", MusicService.MUSIC_PLAY));
    }
    private void stopMusic(){
        Intent intent = new Intent(this, MusicService.class);
        startService(intent.putExtra("status", MusicService.MUSIC_STOP));
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        String toShow = loadSensorData(event)[0]+"";
        valueOfLight.setText(toShow);
        if (MUSIC_BORDER > Double.parseDouble(valueOfLight.getText().toString()) && previousLightValue > 30) {
           playMusic();
        }
        else if (MUSIC_BORDER < Double.parseDouble(valueOfLight.getText().toString()) && previousLightValue < 29){
            stopMusic();
        }
        previousLightValue = loadSensorData(event)[0];
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    private float [] loadSensorData(SensorEvent event) {
       return event.values.clone();
    }
}