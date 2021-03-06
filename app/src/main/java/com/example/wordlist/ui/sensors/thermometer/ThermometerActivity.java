package com.example.wordlist.ui.sensors.thermometer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wordlist.R;

import java.util.Timer;
import java.util.TimerTask;


/**
 * The class Thermometer activity extends application compat activity implements sensor event listener
 */
public class ThermometerActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Thermometer thermometer;
    private float temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermometer);
        thermometer = (Thermometer) findViewById(R.id.thermometer);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        }
        setTitle("Thermometer");
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAmbientTemperature();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterAll();
    }


    /**
     *
     * Simulate ambient temperature
     *
     */
    private void simulateAmbientTemperature() {

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override

/**
 *
 * Run
 *
 */
            public void run() {

                temperature = Utils.randInt(-10, 35);

                runOnUiThread(new Runnable() {
                    @Override

                    /**
                     *
                     * Run
                     *
                     */
                    public void run() {

                        thermometer.setCurrentTemp(temperature);
                        getSupportActionBar().setTitle(getString(R.string.app_name) + " : " + temperature);
                    }
                });
            }
        }, 0, 3500);
    }


    /**
     *
     * Load ambient temperature
     *
     */
    private void loadAmbientTemperature() {

        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        } else {
            Toast.makeText(this, "No Ambient Temperature Sensor !", Toast.LENGTH_LONG).show();
        }
    }


    /**
     *
     * Unregister all
     *
     */
    private void unregisterAll() {

        sensorManager.unregisterListener(this);
    }

    @Override

/**
 *
 * On sensor changed
 *
 * @param sensorEvent  the sensor event
 */
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.values.length > 0) {
            temperature = sensorEvent.values[0];
            thermometer.setCurrentTemp(temperature);
            getSupportActionBar().setTitle("Thermometer : " + temperature);
        }
    }

    @Override

/**
 *
 * On accuracy changed
 *
 * @param sensor  the sensor
 * @param i  the i
 */
    public void onAccuracyChanged(Sensor sensor, int i) {


    }
}
