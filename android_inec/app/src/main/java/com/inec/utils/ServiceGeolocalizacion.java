package com.inec.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.inec.utils.GeoPunto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Created by root on 06/09/16.
 */
public class ServiceGeolocalizacion implements LocationListener {
    private LocationManager locationManager;
    private Context context;
    private Activity activity;
    public static GeoPunto currentGeoPunto=new GeoPunto();
    public static Location currentLocation;
    public static Double currentLatitude;
    public static Double currentLongitude;
    public static Double currentAltitud;
    public static Float currentAccuracy;
    public static Float currentBearing;
    public static Long currentElapsedRealtimeNanos;
    public static Float currentSpeed;
    public static Long currentTime;
    public static LatLng latLng;
    public static Boolean isActiveService = false;
    private Criteria criterio;
    private String locationProvider;
    public static HashSet<GeoPunto> hashSet = new HashSet<GeoPunto>();
    public static Integer sizeHashSet;
    public static ArrayList<GeoPunto> lista = new ArrayList<GeoPunto>();
    public static Integer sizeLista;
    public static final String TAG = ServiceGeolocalizacion.class.getSimpleName();
    private Integer startRemove;
    private int minutes;
    private Double distanciaColoniaDestinoCurrent=1205200923021988.12052009;
    public static final double OVERJYL=1205200923021988.12052009;
    public static String currentPais;
    public static String currentRegion;
    public static String currentLocalidad;
    public static String currentProvincia;


    public ServiceGeolocalizacion(Activity activity, Context context, Integer startRemove, Integer minutes) {
        this.activity = activity;
        this.context = context;
        this.startRemove=startRemove;
        this.minutes = 1000 * 60 * minutes;
        init(context);
    }

    private void init(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    public boolean onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        criterio = new Criteria();
        criterio.setSpeedRequired(true);//false
        criterio.setSpeedAccuracy(Criteria.ACCURACY_HIGH);//Criteria.NO_REQUIREMENT
        criterio.setVerticalAccuracy(Criteria.ACCURACY_HIGH);//Criteria.NO_REQUIREMENT
        criterio.setAccuracy(Criteria.ACCURACY_FINE);
        criterio.setBearingRequired(true);//false
        criterio.setBearingAccuracy(Criteria.ACCURACY_HIGH);//Criteria.NO_REQUIREMENT
        criterio.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
        criterio.setPowerRequirement(Criteria.POWER_HIGH);
        criterio.setCostAllowed(false);
        criterio.setAltitudeRequired(true);
        try {
            locationProvider = locationManager.getBestProvider(criterio, true);
        }catch(Exception ex){
            isActiveService = false;
            return isActiveService;
        }
        locationManager.requestLocationUpdates(locationProvider, 0, 0, this);
        Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
        if (lastKnownLocation == null) {
            isActiveService = false;
            Log.d(TAG+"isactivegps", isActiveService.toString());
            locationManager.removeUpdates(this);
        } else {
            isActiveService = true;
            Log.d(TAG+"isactivegps", isActiveService.toString());
            handleNewLocation(lastKnownLocation);
        }
        return isActiveService;
    }

    public boolean isConected(){
        return locationManager.isProviderEnabled(locationProvider);
    }

    public void disconnect() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if(locationManager!=null){
            locationManager.removeUpdates(this);
        }
    }

    public boolean lastKnownLocation() {
        locationProvider = locationManager.getBestProvider(criterio, true);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        locationManager.requestLocationUpdates(locationProvider, 0, 0, this);
        Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
        if (lastKnownLocation == null) {
            isActiveService=false;
            Log.d(TAG+"isactivegps", isActiveService.toString());
            locationManager.removeUpdates(this);
            return isActiveService;
        }
        else {
            isActiveService=true;
            Log.d(TAG+"isactivegps", isActiveService.toString());
            handleNewLocation(lastKnownLocation);
            return isActiveService;
        }
    }

    private void handleNewLocation(Location location) {
        if(isBetterLocation(location,currentLocation)){
            Log.d(TAG, location.toString());
            currentLocation = location;
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();
            currentAltitud = location.getAltitude();
            currentAccuracy=location.getAccuracy();
            currentBearing=location.getBearing();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                currentElapsedRealtimeNanos=location.getElapsedRealtimeNanos();
            }
            currentSpeed=location.getSpeed();
            currentTime=location.getTime();
            latLng = new LatLng(currentLatitude, currentLongitude);
            Log.d(TAG + "latitud", String.valueOf(currentLatitude));
            Log.d(TAG + "longitud", String.valueOf(currentLongitude));
            Log.d(TAG + "altitud", String.valueOf(currentAltitud));
            Log.d(TAG + "accuracy", String.valueOf(currentAccuracy));
            Log.d(TAG + "bearing", String.valueOf(currentBearing));
            Log.d(TAG + "ElapsedRealtimeNanos", String.valueOf(currentElapsedRealtimeNanos));
            Log.d(TAG + "speed", String.valueOf(currentSpeed));
            Log.d(TAG + "time", String.valueOf(currentTime));
            storeLocationCurrents(location);
            currentGeoPunto.setLatitude(currentLatitude);
            currentGeoPunto.setLongitude(currentLongitude);
        }
    }

    private void storeLocationCurrents(Location location){
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();
        GeoPunto geo=new GeoPunto();
        geo.setLatitude(latitude);
        geo.setLongitude(longitude);
        geo.setLocation(location);
        if(lista.size()<startRemove) {
            if(lista.size()>0 && !lista.get(lista.size()-1).equals(geo)) {
                lista.add(geo);
                hashSet.add(geo);
            }else{
                lista.add(geo);
                hashSet.add(geo);
            }
        }else{
            GeoPunto geoPuntoToRemove=null;
            for(int i=0;i<lista.size();i++){
                geoPuntoToRemove=lista.get(i);
                lista.remove(geoPuntoToRemove);
                if(hashSet.size()>=5) {
                    hashSet.remove(geoPuntoToRemove);
                }
                break;
            }
        }
        sizeHashSet=hashSet.size();
        sizeLista=lista.size();
        Log.d(TAG+"sizehash", String.valueOf(sizeHashSet));
        Log.d(TAG+"sizelist", String.valueOf(sizeLista));
    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(TAG+"Provider", provider);
        Log.d(TAG+"status", String.valueOf(status));
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG+"onProviderEnabled", provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG+"onProviderDisabled", provider);
    }

    public Boolean getActiveService() {
        return isActiveService;
    }

    protected boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > minutes;
        boolean isSignificantlyOlder = timeDelta < -minutes;
        boolean isNewer = timeDelta > 0;

        // If it's been ic_mas than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;
            // If the new location is ic_mas than two minutes older, it must be worse
        } else if (isSignificantlyOlder) {
            return false;
        }

        // Check whether the new location fix is ic_mas or ic_menos accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(),
                currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }

    /** Checks whether two providers are the same */
    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }






    public Double getCurrentLatitude() {
        return currentLatitude;
    }

    public Double getCurrentLongitude() {
        return currentLongitude;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getCurrentPais() {
        return currentPais;
    }

    public String getCurrentRegion() {
        return currentRegion;
    }

    public String getCurrentLocalidad() {
        return currentLocalidad;
    }

    public String getCurrentProvincia() {
        return currentProvincia;
    }


}
