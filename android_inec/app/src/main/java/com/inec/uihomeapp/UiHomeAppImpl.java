package com.inec.uihomeapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.firebase.crash.FirebaseCrash;
import com.inec.server.ws.gestionIncidencia.GestionIncidencia;
import com.inec.server.ws.gestionIncidencia.model.Incidencia;
import com.inec.server.ws.gestionMantenimiento.GestionMantenimiento;
import com.inec.server.ws.gestionMantenimiento.model.PartidoPolitico;
import com.inec.server.ws.gestionMantenimiento.model.ReturnValue;
import com.inec.server.ws.gestionUsuario.GestionUsuario;
import com.inec.utils.GeoPunto;
import com.inec.utils.PreferenciasSession;

import com.inec.utils.ServiceGPS;
import com.inec.utils.ServiceGeolocalizacion;
import com.inec.utils.UtilGeolocalizacion;

import java.io.IOException;
import java.util.List;

import android.Manifest;
import inec.com.R;

import static com.inec.uiiniciarsesion.UiIniciarSesion.geoPunto;
import static com.inec.uiiniciarsesion.UiIniciarSesion.mPrefSesion;

/**
 * Created by root on 10/10/16.
 */

public class UiHomeAppImpl extends  UiHomeApp implements LocationListener{
    public static AsyncTaskFused asyncTaskFused;
    public static ServiceGeolocalizacion asyncTaskService;

    @Override
    public void onResume(){
        super.onResume();
        loadServiceGeo();
    }

    @Override
    public void loadServiceGeo() {
        asyncTaskService=new ServiceGeolocalizacion(this,this.getApplicationContext(),1000,1);
        asyncTaskService.onConnected(null);
        asyncTaskFused=new AsyncTaskFused();
        asyncTaskFused.execute(this);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    public class AsyncTaskFused extends AsyncTask<Activity, Void, Boolean> {
        private ServiceGPS gps;
        Activity activity;

        @Override
        protected Boolean doInBackground(Activity... params) {
            gps=new ServiceGPS(params[0],params[0].getApplicationContext(),1000,1);
            activity=params[0];
            gps.connect();
            return gps.isConected();
        }

        public Boolean reconnect(){
            if(gps!=null) {
                gps.onConnected(null);
                return gps.getActiveGps();
            }else{
                return false;
            }
        }
        public void disconect(){
            if(gps!=null) {
                gps.disconnect();
            }
        }
        public Double getCurrentLatitude() {
            return gps.getCurrentLatitude();
        }

        public Double getCurrentLongitude() {
            return gps.getCurrentLongitude();
        }

        public LatLng getLatLng() {
            return gps.getLatLng();
        }

        public boolean activarGeolocalizacion(Activity activity,Integer codeUi){
            return gps.activarGeolocalizacion(activity,codeUi);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_ACTIVE_GEO) {
            if (resultCode == RESULT_OK) {
                if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    finish();
                }
                if(!googleApiClient.isConnected()){
                    googleApiClient.connect();
                }else {
                    LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
                }
            }else{
                asyncTaskFused.disconect();
                if(reintentar){
                    reintentar=false;
                }
            }
        }
    }


    @Override
    public void cargarPartidoPolitico() {
        new AsynTaskPartidoPolitico().execute();
    }
    class AsynTaskPartidoPolitico extends AsyncTask<Object, Void, Object> {
        private GestionMantenimiento myApiServiceGestionUsuario = null;
        @Override
        protected Object doInBackground(Object... objects) {
            try {
                if(myApiServiceGestionUsuario == null) {
                    GestionMantenimiento.Builder builder = new GestionMantenimiento.Builder(AndroidHttp.newCompatibleTransport(),
                            new AndroidJsonFactory(), null)
                            .setRootUrl(PreferenciasSession.DEFAULT_ROOT_URL)
                            .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                                @Override
                                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                    abstractGoogleClientRequest.setDisableGZipContent(true);
                                }
                            });
                    myApiServiceGestionUsuario = builder.build();
                }
                ReturnValue returnValue=myApiServiceGestionUsuario.listPartidoPolitico().execute();
                return  returnValue;

            } catch (Exception e) {
                FirebaseCrash.report(e.getCause());
                FirebaseCrash.logcat(e.hashCode(),e.getLocalizedMessage(),e.getMessage());
                return null;
            }
        }
        @Override
        protected void onPostExecute(Object result) {
            ReturnValue rv=(ReturnValue) result;
            List<PartidoPolitico> listPartido = rv.getReturnListPartidoPolitico();
            if(result!=null && listPartido.size()>0){
                listPartidoPolitico=listPartido;
            }
        }
    }

    @Override
    public void cerrarSessionTask(String codeSessionFiscalizador) {
        new AsynTaskCerrarSession().execute(codeSessionFiscalizador);
    }

    class AsynTaskCerrarSession extends AsyncTask<String, Void, Boolean> {
        private GestionUsuario myApiServiceGestionUsuario = null;
        @Override
        protected Boolean doInBackground(String... objects) {
            try {
                if(myApiServiceGestionUsuario == null) {
                    GestionUsuario.Builder builder = new GestionUsuario.Builder(AndroidHttp.newCompatibleTransport(),
                            new AndroidJsonFactory(), null)
                            .setRootUrl(PreferenciasSession.DEFAULT_ROOT_URL)
                            .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                                @Override
                                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                    abstractGoogleClientRequest.setDisableGZipContent(true);
                                }
                            });
                    myApiServiceGestionUsuario = builder.build();
                }
                return (Boolean)myApiServiceGestionUsuario.cerrarSesionFiscalizador(objects[0]).execute().getValueReturn();
            } catch (Exception e) {
                FirebaseCrash.report(e.getCause());
                FirebaseCrash.logcat(e.hashCode(),e.getLocalizedMessage(),e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
        }
    }
}
