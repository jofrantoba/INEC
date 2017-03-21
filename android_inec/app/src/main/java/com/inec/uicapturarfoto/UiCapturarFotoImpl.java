package com.inec.uicapturarfoto;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.media.ExifInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.inec.uihomeapp.UiHomeAppImpl;
import com.inec.utils.ColocarImagen;
import com.inec.utils.ServiceGPS;
import com.inec.utils.ServiceGeolocalizacion;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static com.inec.uihomeapp.UiHomeApp.googleApiClient;
import static com.inec.uihomeapp.UiHomeApp.locationRequest;

/**
 * Created by root on 10/10/16.
 */

public class UiCapturarFotoImpl extends UiCapturarFoto implements LocationListener, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener{

//    public static boolean fotoTomada=false;
//    public static boolean fotoVisible=false;
//    public static boolean ubicacionObtenida=false;
//    public static boolean envioRealizado=false;
    private int CODE_ACTIVE_GEO = 1000;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_ACTIVE_GEO) {
            if (resultCode == RESULT_OK) {
                if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    finish();
                }
                if(!googleApiClient.isConnected()) {
                    googleApiClient.connect();
                }else{
                    LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, (com.google.android.gms.location.LocationListener) this);
                }
            }else{
                finish();
            }
        }
        if (requestCode == CAMERA_REQUEST) {
            if(resultCode == this.RESULT_OK) {
                fotoTomada=true;
                VerImagen();
            }
            else{
                if(!fotoTomada && !fotoVisible){
                    onBackPressed();
                }
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
