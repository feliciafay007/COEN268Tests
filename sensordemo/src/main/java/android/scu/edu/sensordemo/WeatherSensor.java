package android.scu.edu.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class WeatherSensor extends ActionBarActivity {
    private SensorManager sensorManager;
    private TextView temperatureTextView;
    private TextView pressureTextView;
    private TextView lightTextView;
    private float currentTemperature = Float.NaN;
    private float currentPressure = Float.NaN;
    private float currentLight = Float.NaN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_sensor);
        temperatureTextView = (TextView) findViewById(R.id.textView2);
        pressureTextView= (TextView) findViewById(R.id.textView3);
        lightTextView = (TextView) findViewById(R.id.textView4);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Timer updateTimer = new Timer("Weather Timer");
        updateTimer.scheduleAtFixedRate(new TimerTask(){
            public void run() {
                updateGUI();
            }
        }, 0, 1000);

    }

    private final SensorEventListener tempSensorEventListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        public void onSensorChanged(SensorEvent event) {
            currentTemperature = event.values[0];
        }
    };

    private final SensorEventListener pressureSensorEventListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        public void onSensorChanged(SensorEvent event) {
            currentPressure = event.values[0];
        }
    };

    private final SensorEventListener lightSensorEventListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        public void onSensorChanged(SensorEvent event) {
            currentLight = event.values[0];
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor !=  null) {
            sensorManager.registerListener(lightSensorEventListener,
                    lightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        else
            lightTextView.setText("Light Sensor Unavailable");

        Sensor pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (pressureSensor != null)
            sensorManager.registerListener(pressureSensorEventListener,
                    pressureSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        else
            pressureTextView.setText("Pressure Sensor Unavailable");

        Sensor temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (temperatureSensor != null)
            sensorManager.registerListener(tempSensorEventListener,
                    temperatureSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        else
            temperatureTextView.setText("Temperature Sensor Unavailable");
    }


    @Override
    protected void onPause() {
        sensorManager.unregisterListener(pressureSensorEventListener);
        sensorManager.unregisterListener(lightSensorEventListener);
        sensorManager.unregisterListener(tempSensorEventListener);
        super.onPause();
    }


    private void updateGUI() {
        runOnUiThread( new Runnable() {
            public void run () {
                if (! Float.isNaN(currentPressure)) {
                    pressureTextView.setText(currentPressure + "(mbars)");
                    pressureTextView.invalidate();
                }
                if (! Float.isNaN(currentLight)) {
                    String lightStr = "Sunny";
                    if (currentLight <= SensorManager.LIGHT_CLOUDY)
                        lightStr = "Night";
                    else if (currentLight <= SensorManager.LIGHT_OVERCAST)
                        lightStr = "Cloudy";
                    else if (currentLight <= SensorManager.LIGHT_SUNLIGHT)
                        lightStr = "Overcast";
                    lightTextView.setText(lightStr);
                    lightTextView.invalidate();
                }
                if (! Float.isNaN(currentTemperature)) {
                    temperatureTextView.setText(currentTemperature + "C");
                    temperatureTextView.invalidate();
                    /**
                     * If in the course of processing the event, the view's bounds may need to
                     * be changed, the view will call requestLayout().
                     * Similarly, if in the course of processing the event the view's appearance
                     * may need to be changed, the view will call invalidate().
                     */
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather_sensor, menu);
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
}
