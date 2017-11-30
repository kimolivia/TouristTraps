package hu.ait.android.touristinfo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity
    implements TouristLocationManager.OnNewLocationAvailable, OnMapReadyCallback {

    private TouristLocationManager touristLocationManager;
    private double currentLongitude;
    private double currentLatitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        touristLocationManager = new TouristLocationManager(this,this);

        requestNeededPermission();

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mainPageMap);
        mapFragment.getMapAsync(this);
    }

    private void requestNeededPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Toast...
            }

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    101);
        } else {
            // start our job
            touristLocationManager.startLocationMonitoring();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 101) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();

                // start our job
                touristLocationManager.startLocationMonitoring();
            } else {
                Toast.makeText(this, "Permission not granted :(", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        touristLocationManager.stopLocationMonitoring();
        super.onDestroy();
    }

    @Override
    public void newLocationAvailable(Location location) {
        currentLongitude = location.getLongitude();
        currentLatitude = location.getLatitude();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng currentLocation = new LatLng(currentLatitude,currentLongitude);
        googleMap.addMarker(new MarkerOptions()
                .position(currentLocation)
                .title("current location"));
        googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom(currentLocation , 3.0f) );
    }
}
