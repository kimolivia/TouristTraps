package hu.ait.android.touristinfo;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
/**
 * Created by zhaozhaoxia on 11/30/17.
 */

public class TouristLocationManager implements LocationListener{
    public interface OnNewLocationAvailable {
        public void newLocationAvailable(Location location);
    }

    private OnNewLocationAvailable onNewLocationAvailable;
    private Context context;
    private LocationManager locationManager;

    public TouristLocationManager(Context context,
                              OnNewLocationAvailable onNewLocationAvailable) {
        this.context = context;
        this.onNewLocationAvailable = onNewLocationAvailable;
    }

    public void startLocationMonitoring() throws SecurityException {
        locationManager =
                (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                0, 0, this);

        // DOES NOT WORK ON EMULATOR
        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
        //        0, 0, this);
    }

    public void stopLocationMonitoring() {
        locationManager.removeUpdates(this);
    }


    @Override
    public void onLocationChanged(Location location) {
        onNewLocationAvailable.newLocationAvailable(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
