package com.inec.ui_listar_incidencia;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.firebase.crash.FirebaseCrash;
import com.inec.server.ws.gestionIncidencia.GestionIncidencia;
import com.inec.server.ws.gestionIncidencia.model.Incidencia;
import com.inec.server.ws.gestionIncidencia.model.ReturnValue;
import com.inec.uicapturarfoto.UiCapturarFotoImpl;
import com.inec.uihomeapp.UiHomeApp;
import com.inec.uiregistrarincidencia.UiRegistrarIncidenciaImpl;
import com.inec.utils.PreferenciasSession;
import com.inec.utils.ServiceGPS;
import com.inec.utils.ServiceGeolocalizacion;

import java.io.IOException;
import java.util.List;

import static com.inec.uihomeapp.UiHomeApp.googleApiClient;
import static com.inec.uihomeapp.UiHomeApp.locationRequest;
import static com.inec.uihomeapp.UiHomeAppImpl.asyncTaskFused;
import static com.inec.uihomeapp.UiHomeAppImpl.asyncTaskService;
import static com.inec.uiiniciarsesion.UiIniciarSesion.mPrefSesion;
import static com.inec.uiiniciarsesion.UiIniciarSesion.usuario_session;

/**
 * Created by root on 21/10/16.
 */

public class UiListarIncidenciaImpl extends UiListarIncidencia implements LocationListener {

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void loadIncidencia() {
            LoadIncidenciaAsyncTask getMuroTuristaAsyncTask=new LoadIncidenciaAsyncTask(this.getApplicationContext());
            getMuroTuristaAsyncTask.execute();
    }
    class LoadIncidenciaAsyncTask extends AsyncTask<Bundle, Void,ReturnValue> {
        private GestionIncidencia myApiServiceIncidencia = null;
        private Context context;
        public LoadIncidenciaAsyncTask(Context context){
            this.context=context;
        }
        private ProgressDialog pgDialog;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            objSwipeRefreshLayout.setRefreshing(true);
            pgDialog = new ProgressDialog(UiListarIncidenciaImpl.this);
            pgDialog.setTitle("Cargando");
            pgDialog.setMessage("Espere..");
            pgDialog.setCancelable(false);
            pgDialog.show();
        }

        @Override
        protected ReturnValue doInBackground(Bundle... args) {
            try {
                if(myApiServiceIncidencia == null) {  // Only do this once
                    GestionIncidencia.Builder builder = new GestionIncidencia.Builder(AndroidHttp.newCompatibleTransport(),
                            new AndroidJsonFactory(), null)
                            .setRootUrl(PreferenciasSession.DEFAULT_ROOT_URL)
                            .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                                @Override
                                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                    abstractGoogleClientRequest.setDisableGZipContent(true);
                                }
                            });
                    myApiServiceIncidencia = builder.build();
                }
                return myApiServiceIncidencia.listarIncidencias(mPrefSesion.getString("dniusuario","")).execute();
            } catch (Exception e) {
                FirebaseCrash.report(e.getCause());
                FirebaseCrash.logcat(e.hashCode(),e.getLocalizedMessage(),e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(ReturnValue result) {
            pgDialog.dismiss();
            if(result!=null) {
                if (result.getNameClass().equalsIgnoreCase("UnknownException")) {
                    String exceptionMessage = result.getValueReturn().toString();
                    Toast.makeText(context, exceptionMessage, Toast.LENGTH_SHORT).show();
                } else if(result.getNameClass().equalsIgnoreCase(List.class.getSimpleName())) {
                    List<Incidencia> lista=result.getReturnListIncidencia();
                    UiHomeApp.listIncidencia=lista;
                    galeriaDefecto();
                    if(lista!=null && lista.size()>0){
                        adapterListarIncidencia.setData(lista);
                        adapterListarIncidencia.notifyDataSetChanged();
                    }
                }
            }
            objSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
