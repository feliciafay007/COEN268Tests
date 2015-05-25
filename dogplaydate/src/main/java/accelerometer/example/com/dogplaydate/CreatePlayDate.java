package accelerometer.example.com.dogplaydate;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
//import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;

public class CreatePlayDate extends Activity implements OnMapReadyCallback {
    private  final LatLng LOCATION_UNIV = new LatLng(37.349642, -121.938987);
    private  final LatLng LOCATION_BUILDING = new LatLng(37.348190, -121.937975);
    private GoogleMap map;
    private Button btnStartDate;
    private final int DATE_DIALOG_ID = 999;
    private int year;
    private int month;
    private int day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_play_date);
        map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();
        //   map.addMarker(new MarkerOptions().position(LOCATION_BUILDING).title("Find Me Here!"));
        onMapReady(map);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LOCATION_BUILDING, 13));
        map.addMarker(new MarkerOptions()
                .title("Find Me Here!")
                .snippet("Santa Clara University is located in the bay area.")
                .position(LOCATION_BUILDING));

    }

    public void onClick_City(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_UNIV, 10);
        map.animateCamera(update);
    }

    public void onClick_University(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_UNIV, 13);
        map.animateCamera(update);
    }

    public void onClick_Building(View v) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_BUILDING, 17);
        map.animateCamera(update);
    }
    public void onClickCreate(View v) {

        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_BUILDING, 17);
        map.animateCamera(update);
    }


    public void onClickStartDate(View v) {
        DialogFragment dialogFragment = new StartDatePicker();
        dialogFragment.show(getFragmentManager(), "start_date_picker");
    }
    //--start
    Calendar c = Calendar.getInstance();
    int startYear = c.get(Calendar.YEAR);
    int startMonth = c.get(Calendar.MONTH);
    int startDay = c.get(Calendar.DAY_OF_MONTH);

    class StartDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            DatePickerDialog dialog = new DatePickerDialog(CreatePlayDate.this, this, startYear, startMonth, startDay);
            return dialog;

        }
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // Do something with the date chosen by the user
            startYear = year;
            startMonth = monthOfYear;
            startDay = dayOfMonth;
            System.out.println("picked time = " + startYear + "-" + startMonth + "-" + startDay);
            Log.i("FF", "picked time = " + startYear + "-" + startMonth + "-" + startDay);
        }
    }

    //---end
}

