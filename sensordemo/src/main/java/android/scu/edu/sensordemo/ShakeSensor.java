package android.scu.edu.sensordemo;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


/**
 * STYLE ONE:
 * this class implements SensorEventListener
 */

public class ShakeSensor extends ActionBarActivity implements SensorEventListener {
    private SensorManager sensorManger;
    private boolean color = false;
    private View view;
    private long lastUpdate;
    TextView textx, texty,textz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * according to Stack Overflow,
         * requestWindowFeature should be put before super.conCreate();
         */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shake_sensor);
        textx = (TextView) findViewById(R.id.textView6);
        texty = (TextView) findViewById(R.id.textView7);
        textz = (TextView) findViewById(R.id.textView8);
        view = findViewById(R.id.textView5);
        view.setBackgroundColor(Color.BLUE);
        sensorManger = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManger.registerListener(this,
                sensorManger.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        lastUpdate = System.currentTimeMillis();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shake_sensor, menu);
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


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            displayAccelerometer(event);
            checkShake(event);
        }
    }

    private void displayAccelerometer (SensorEvent event) {
        // Many sensors return 3 values, one for each axis.
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        //display values using Textview
        textx.setText("X axis\t\t" + x);
        texty.setText("Y axis\t\t" + y);
        textz.setText("Z axis\t\t" + z);

    }

    private void checkShake(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        float acceletionSquareRoot = (x * x + y * y + z * z) /
                (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITwY_EARTH);
        long actualTime = System.currentTimeMillis();
        if (acceletionSquareRoot >= 2) {
            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;
            Toast.makeText(this, "Don't shake me!", Toast.LENGTH_SHORT).show();
            if (color) {
                view.setBackgroundColor(Color.BLUE);
            } else {
                view.setBackgroundColor(Color.RED);
            }
            color = !color;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManger.registerListener(this,
                sensorManger.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause () {
        super.onPause();
        sensorManger.unregisterListener(this);
    }
}
