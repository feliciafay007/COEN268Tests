package accelerometer.example.com.mylocationsonmap;
import android.os.Bundle;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.app.Activity;
import android.view.View;

public class MainActivity extends Activity implements OnMapReadyCallback {
    private  final LatLng LOCATION_UNIV = new LatLng(37.349642, -121.938987);
    private  final LatLng LOCATION_BUILDING = new LatLng(37.348190, -121.937975);
    private GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();
     //   map.addMarker(new MarkerOptions().position(LOCATION_BUILDING).title("Find Me Here!"));
        onMapReady(map);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LOCATION_BUILDING , 13));
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
}

