package android.scu.edu.sensordemo;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;


public class DisplayListOfSensors extends ActionBarActivity {
    private List<Sensor> listOfSensors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_of_sensors);
        TextView textView1  = (TextView) findViewById(R.id.textView);
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        listOfSensors = sm.getSensorList(Sensor.TYPE_ALL);
        StringBuilder tmp = new StringBuilder();
        for (Sensor each : listOfSensors) {
            tmp = tmp.append(each.getName() + ", " + each.getPower() + "\n");
        }
        textView1.setText(tmp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_list_of_sensors, menu);
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
        } else if (id == R.id.menu_item_weather_sensor) {
            Intent intent = new Intent(DisplayListOfSensors.this, WeatherSensor.class);
            startActivity(intent);
        } else if (id == R.id.menu_item_shake_sensor) {
            Intent intent = new Intent(DisplayListOfSensors.this, ShakeSensor.class);
            startActivity(intent);
        } else if (id == R.id.menu_item_shake_sensor2) {
            Intent intent = new Intent(DisplayListOfSensors.this, ShakeSensor2.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
