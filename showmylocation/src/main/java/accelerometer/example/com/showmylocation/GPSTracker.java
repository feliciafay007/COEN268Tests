package accelerometer.example.com.showmylocation;

import android.content.Context;
import android.app.Service;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import java.security.Provider;

/**
 * Created by feliciafay on 5/18/15.
 */
public class GPSTracker extends Service implements LocationListener{
    private final Context mContext;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;
    Location location;
    double latitude;
    double longitude;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
    protected LocationManager locationManger;
    public GPSTracker(Context context) {
        this.mContext = context;
        //getLocation();
    }

    //Felicia add it
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    public void onProviderEnabled(String provider) {}

    public void onProviderDisabled(String provider) {}
    //Felicia add it
}
