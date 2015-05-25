package accelerometer.example.com.mylocationsonmap;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends FragmentActivity implements android.app.LoaderManager.LoaderCallbacks<Cursor> {
    private  final LatLng LOCATION_UNIV = new LatLng(37.349642, -121.938987);
    private  final LatLng LOCATION_BUILDING = new LatLng(37.348190, -121.937975);
    private GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LOCATION_BUILDING, 13));
        getLoaderManager().initLoader(0, null, this);

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                map.addMarker(new MarkerOptions().position(latLng));
                ContentValues values = new ContentValues();
                values.put(PositionContentProvider.LATITUDE, Double.toString(latLng.latitude));
                values.put(PositionContentProvider.LONGITUTE, Double.toString(latLng.longitude));
                values.put(PositionContentProvider.ZOOMLEVEL, Float.toString(map.getCameraPosition().zoom));
                //Uri uri = getContentResolver().insert(PositionContentProvider.CONTENT_URI, values);
                new LocationInsertTask().execute(values);
                //Toast.makeText(getBaseContext(), uri.toString() + ", zoom level:" + map.getCameraPosition().zoom, Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(), "Marker is added to the Map", Toast.LENGTH_SHORT).show();
            }
        });

        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                //int res = getContentResolver().delete(PositionContentProvider.CONTENT_URI, null, null);
                LocationDeleteTask deleteTask = new LocationDeleteTask();
                deleteTask.execute();
                map.clear();
                Toast.makeText(getBaseContext(),"All markers are deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class LocationInsertTask extends AsyncTask<ContentValues, Void, Void> {
        @Override
        protected  Void doInBackground(ContentValues... contentValues) {
            // insert
            Uri uri = getContentResolver().insert(PositionContentProvider.CONTENT_URI, contentValues[0]);
            return null;
        }
    }


    private class LocationDeleteTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected  Void doInBackground(Void... params) {
            // delete
            int res = getContentResolver().delete(PositionContentProvider.CONTENT_URI, null, null);
            return null;
        }
    }

    /**
     * This usually happens when initLoader() is
     * called. The loaderID argument contains the ID value passed to the
     * initLoader() call.
     */
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        Loader<Cursor> c = null;
        Uri uri = PositionContentProvider.CONTENT_URI;
        c = new CursorLoader(this, uri, null, null, null, null);
        return c;
    }

    public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
        int locationCount = 0;
        double lat = 0;
        double lng = 0;
        float zoom = 0;
        LatLng latLng = null;
        if(arg1 != null) {
            locationCount = arg1.getCount();
            arg1.moveToFirst();
        } else {
            locationCount = 0;
        }

        for (int i = 0; i < locationCount; ++i) {
            lat = Double.parseDouble(arg1.getString(arg1.getColumnIndex(PositionContentProvider.LATITUDE)));
            lng = Double.parseDouble(arg1.getString(arg1.getColumnIndex(PositionContentProvider.LONGITUTE)));
            zoom = Float.parseFloat(arg1.getString(arg1.getColumnIndex(PositionContentProvider.ZOOMLEVEL)));
            latLng = new LatLng(lat, lng);
            map.addMarker(new MarkerOptions().position(latLng));
            arg1.moveToNext();
        }
        if (locationCount > 0) {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        }
    }


    public void onLoaderReset(Loader<Cursor> arg0) {
        return;
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


//    @Override
//    public void onMapReady(GoogleMap map) {
//        map.setMyLocationEnabled(true);
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LOCATION_BUILDING, 13));
//        retrieveAndShowLocations(map);
//    }


//    public void retrieveAndShowLocations(GoogleMap map) {
//        String URL = PositionContentProvider.URL;
//        Uri positions = Uri.parse(URL);
//        Cursor c = managedQuery(positions, null, null, null, PositionContentProvider.LATITUDE);
//        if (c.moveToFirst()) {
//            do{
//                LatLng latLng = new LatLng(
//                        Double.parseDouble(c.getString(c.getColumnIndex(PositionContentProvider.LATITUDE))),
//                        Double.parseDouble(c.getString(c.getColumnIndex(PositionContentProvider.LONGITUTE)))
//                        );
//                Marker mMark = map.addMarker(new MarkerOptions().position(latLng));
//                if (mMark != null) {
//                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(
//                            latLng,
//                            Float.parseFloat(c.getString(c.getColumnIndex(PositionContentProvider.ZOOMLEVEL)))
//                    ));
//                }
//                Toast.makeText(this, c.getString(c.getColumnIndex(PositionContentProvider._ID)) +
//                        ", " + c.getString(c.getColumnIndex(PositionContentProvider.LATITUDE)) +
//                        ", " + c.getString(c.getColumnIndex(PositionContentProvider.LONGITUTE)) +
//                        ", " + c.getString(c.getColumnIndex(PositionContentProvider.ZOOMLEVEL)),
//                        Toast.LENGTH_SHORT).show();
//            } while (c.moveToNext());
//        }
//    }

}



