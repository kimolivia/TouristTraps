package hu.ait.android.touristinfo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
    implements TouristLocationManager.NewLocationListener, OnMapReadyCallback {

    private TouristLocationManager touristLocationManager;
    private GoogleMap mMap;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        touristLocationManager = new TouristLocationManager(this,this);

        requestNeededPermission();

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mainPageMap);
        mapFragment.getMapAsync(this);

        setUpDrawer();
        setUpToolBar();
    }

    private void setUpToolBar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //myToolbar.setNavigationIcon(R.drawable.smallglobe);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void setUpDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView navigationView
                = (NavigationView) findViewById(R.id.navigationView);
        navigationView.bringToFront();
        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        switch (menuItem.getItemId()) {
                            case R.id.action_seeAgenda:
                                Intent intent = new Intent();
                                intent.setClass(MainActivity.this, MyAgenda.class);
                                startActivity(intent);

                                drawerLayout.closeDrawer(GravityCompat.START);
                                break;
                            case R.id.action_help:
                                Toast.makeText(MainActivity.this,
                                        "1"
                                        ,
                                        Toast.LENGTH_LONG
                                ).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                break;
                            case R.id.action_about:
                                Toast.makeText(MainActivity.this,
                                        R.string.creators
                                        ,
                                        Toast.LENGTH_LONG
                                ).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                break;
                        }
                        return false;
                    }
                });
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
    public void onNewLocation(Location location) {
        LatLng currentLocation = new LatLng(location.getLatitude(),location.getLongitude());
        Marker marker =
                mMap.addMarker(new MarkerOptions()
                                .position(currentLocation)
                                .title("Current Location")
                                .snippet("Drag the marker to the city you want to go")
                );

        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(currentLocation , 6.0f) );

        marker.setDraggable(true);
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                LatLng dragToLocation = marker.getPosition();
//                Geocoder gcd = new Geocoder(MainActivity.this, Locale.getDefault());
//                List<Address> addresses = null;
//                try {
//                    addresses = gcd.getFromLocation(dragToLocation.latitude, dragToLocation.longitude, 1);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                if (addresses.size() > 0){
//                    Toast.makeText(MainActivity.this,
//                            "You set location to: " +
//                                    addresses.get(0).getLocality(), Toast.LENGTH_SHORT).show();
//                }
                mMap.animateCamera(CameraUpdateFactory.newLatLng(dragToLocation));
            }
        });
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
    }
}