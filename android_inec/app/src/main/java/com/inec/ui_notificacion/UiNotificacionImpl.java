package com.inec.ui_notificacion;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.firebase.crash.FirebaseCrash;
import com.inec.server.ws.gestionUsuario.GestionUsuario;
import com.inec.server.ws.gestionUsuario.model.Notificacion;
import com.inec.server.ws.gestionUsuario.model.ReturnValue;
import com.inec.ui_mis_rutas.UiMisRutasImpl;
import com.inec.ui_posicion_actual.UiPosicionActualImpl;
import com.inec.utils.PreferenciasSession;

import java.io.IOException;
import java.util.List;

import static com.inec.uiiniciarsesion.UiIniciarSesion.mPrefSesion;

/**
 * Created by root on 11/11/16.
 */

public class UiNotificacionImpl extends UiNotificacion{
    @Override
    public void loadNotificacion() {
        LoadNotificationTask getMuroTuristaAsyncTask=new LoadNotificationTask(this.getApplicationContext());
        getMuroTuristaAsyncTask.execute();
    }
    class LoadNotificationTask extends AsyncTask<Bundle, Void,ReturnValue> {
        private GestionUsuario myApiServiceNotificacion = null;
        private Context context;
        public LoadNotificationTask(Context context){
            this.context=context;
        }
        private ProgressDialog pgDialog;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            objSwipeRefreshLayout.setRefreshing(true);
            pgDialog = new ProgressDialog(UiNotificacionImpl.this);
            pgDialog.setTitle("Cargando");
            pgDialog.setMessage("Espere..");
            pgDialog.setCancelable(false);
            pgDialog.show();
        }

        @Override
        protected ReturnValue doInBackground(Bundle... args) {
            try {
                if(myApiServiceNotificacion == null) {  // Only do this once
                    GestionUsuario.Builder builder = new GestionUsuario.Builder(AndroidHttp.newCompatibleTransport(),
                            new AndroidJsonFactory(), null)
                            .setRootUrl(PreferenciasSession.DEFAULT_ROOT_URL)
                            .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                                @Override
                                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                    abstractGoogleClientRequest.setDisableGZipContent(true);
                                }
                            });
                    myApiServiceNotificacion = builder.build();
                }
                return myApiServiceNotificacion.listarNotificaciones().execute();
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
                    List<Notificacion> lista=result.getReturnListNotificacion();
                    if(lista!=null && lista.size()>0){
                        adapterNotificaciones.setData(lista);
                        adapterNotificaciones.notifyDataSetChanged();
                    }
                }
            }
            objSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void estadoNotificacion(String codeNotificacion) {
        super.estadoNotificacion(codeNotificacion);
        LoadEstadoNotificacion getMuroTuristaAsyncTask=new LoadEstadoNotificacion(this.getApplicationContext());
        getMuroTuristaAsyncTask.execute(codeNotificacion);
    }

    class LoadEstadoNotificacion extends AsyncTask<String, Void,ReturnValue> {
        private GestionUsuario myApiServiceNotificacion = null;
        private Context context;
        public LoadEstadoNotificacion(Context context){
            this.context=context;
        }
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected ReturnValue doInBackground(String... args) {
            try {
                if(myApiServiceNotificacion == null) {  // Only do this once
                    GestionUsuario.Builder builder = new GestionUsuario.Builder(AndroidHttp.newCompatibleTransport(),
                            new AndroidJsonFactory(), null)
                            .setRootUrl(PreferenciasSession.DEFAULT_ROOT_URL)
                            .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                                @Override
                                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                    abstractGoogleClientRequest.setDisableGZipContent(true);
                                }
                            });
                    myApiServiceNotificacion = builder.build();
                }
                return myApiServiceNotificacion.estadoNotificacion(args[0]).execute();
            } catch (Exception e) {
                FirebaseCrash.report(e.getCause());
                FirebaseCrash.logcat(e.hashCode(),e.getLocalizedMessage(),e.getMessage());
                return null;
            }
        }
        @Override
        protected void onPostExecute(ReturnValue result) {
            if(result!=null) {
                if (result.getNameClass().equalsIgnoreCase("UnknownException")) {
                    String exceptionMessage = result.getValueReturn().toString();
                    Toast.makeText(context, exceptionMessage, Toast.LENGTH_SHORT).show();
                } else if(result.getNameClass().equalsIgnoreCase(Boolean.class.getSimpleName())) {
                    Intent intent = new Intent(context, UiPosicionActualImpl.class);
                    startActivity(intent);
                }
            }
        }
    }
}
