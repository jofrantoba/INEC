package com.inec.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashSet;


/**
 * Created by root on 05/09/16.
 */
public class ServiceGPS implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Context context;
    private Activity activity;
    public static GeoPunto currentGeoPunto=new GeoPunto();
    public static Double currentLatitude;
    public static Double currentLongitude;
    public static LatLng latLng;
    public static Location currentLocation;
    public static Double currentAltitud;
    public static Float currentAccuracy;
    public static Float currentBearing;
    public static Long currentElapsedRealtimeNanos;
    public static Float currentSpeed;
    public static Long currentTime;
    public static Boolean isActiveGps=false;
    public static final String TAG = ServiceGPS.class.getSimpleName();
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private int minutes;
    private Integer startRemove;
    public static HashSet<GeoPunto> hashSet = new HashSet<GeoPunto>();
    public static Integer sizeHashSet;
    public static ArrayList<GeoPunto> lista = new ArrayList<GeoPunto>();
    public static Integer sizeLista;
    private Double distanciaColoniaDestinoCurrent;
    public static final double OVERJYL=1205200923021988.12052009;
    public static String currentPais;
    public static String currentRegion;
    public static String currentLocalidad;
    public static String currentProvincia;
    private GeoPunto geo;
    public static boolean aceptoUsoGeolocalizacion=false;



    public ServiceGPS(Activity activity, Context context, Integer startRemove, Integer minutes) {
        this.activity=activity;
        this.context=context;
        this.startRemove=startRemove;
        this.minutes = 1000 * 60 * minutes;
        init(context);
    }

    private void init(Context context) {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds
    }

    /**
     * execute in onResume of the Activity
     */
    public void connect() {
        mGoogleApiClient.connect();
    }

    public boolean isConected(){
        return mGoogleApiClient.isConnected();
    }

    /**
     * execute in onPause of the Activity
     */
    public void disconnect() {
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, (LocationListener) this);
            mGoogleApiClient.disconnect();
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

    /*inicio de metodos de geolocalizacion*/
    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }
    /*Fin de metodos de geolocalizacion*/

    /*inicio de metodos de google apis*/
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try{
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (location == null) {
                isActiveGps=false;
                Log.d(TAG+"isactivegps", isActiveGps.toString());
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest,this);
            }
            else {
                isActiveGps=true;
                Log.d(TAG+"isactivegps", isActiveGps.toString());
                handleNewLocation(location);
            }
        }catch(Exception ex){
            isActiveGps=false;
            Log.d(TAG+"ONCONNECTED", ex.getMessage());
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG+"onConnectionSuspended", String.valueOf(i));
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(activity, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
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

    /*Fin de metodos de google apis*/

    public Boolean getActiveGps() {
        return isActiveGps;
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

    public GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
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
    public boolean activarGeolocalizacion(final Activity actividad,final Integer codeUi) {
        if (mGoogleApiClient != null) {
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .setAlwaysShow(true)
                    .addLocationRequest(mLocationRequest);

            PendingResult<LocationSettingsResult> result =
                    LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            if(ActivityCompat.checkSelfPermission(actividad.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(actividad.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, ServiceGPS.this);
                            aceptoUsoGeolocalizacion=true;
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                status.startResolutionForResult(
                                        actividad, codeUi);
                            } catch (IntentSender.SendIntentException e) {
                                aceptoUsoGeolocalizacion=false;
                            }

                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            aceptoUsoGeolocalizacion=false;
                            break;
                    }
                }
            });
        }
        return aceptoUsoGeolocalizacion;
    }
}
