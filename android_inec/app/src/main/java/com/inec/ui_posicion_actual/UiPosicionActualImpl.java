package com.inec.ui_posicion_actual;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.firebase.crash.FirebaseCrash;
import com.inec.server.ws.gestionUsuario.GestionUsuario;
import com.inec.server.ws.gestionUsuario.model.PosicionFiscalizador;
import com.inec.server.ws.gestionUsuario.model.ReturnValue;
import com.inec.ui_mis_rutas.UiMisRutasImpl;
import com.inec.utils.PreferenciasSession;

import java.io.IOException;
import java.util.List;

import static com.inec.uiiniciarsesion.UiIniciarSesion.mPrefSesion;

/**
 * Created by root on 30/11/16.
 */

public class UiPosicionActualImpl extends UiPosicionActual {

    @Override
    public void guardarPosicion(String gpCodeUsuario, String gpDepartamento, String gpDistrito, Double gpLatitud, Double gpLongitud, String gpProvincia) {
        super.guardarPosicion(gpCodeUsuario, gpDepartamento, gpDistrito, gpLatitud, gpLongitud, gpProvincia);
        LoadPosicionAsyncTask getMuroTuristaAsyncTask=new LoadPosicionAsyncTask(this.getApplicationContext());
        getMuroTuristaAsyncTask.execute(gpCodeUsuario, gpDepartamento, gpDistrito, gpLatitud, gpLongitud, gpProvincia);
    }

    class LoadPosicionAsyncTask extends AsyncTask<Object, Void,ReturnValue> {
        private GestionUsuario myApiServicePosicionFiscalizador = null;
        private Context context;
        public LoadPosicionAsyncTask(Context context){
            this.context=context;
        }
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected ReturnValue doInBackground(Object... args) {
            try {
                if(myApiServicePosicionFiscalizador == null) {  // Only do this once
                    GestionUsuario.Builder builder = new GestionUsuario.Builder(AndroidHttp.newCompatibleTransport(),
                            new AndroidJsonFactory(), null)
                            .setRootUrl(PreferenciasSession.DEFAULT_ROOT_URL)
                            .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                                @Override
                                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                    abstractGoogleClientRequest.setDisableGZipContent(true);
                                }
                            });
                    myApiServicePosicionFiscalizador = builder.build();
                }

                String gpCodeUsuario=String.valueOf(args[0]);
                String gpDepartamento=String.valueOf(args[1]);
                String gpDistrito=String.valueOf(args[2]);
                Double gpLatitud=Double.parseDouble(String.valueOf(args[3]));
                Double gpLongitud=Double.parseDouble(String.valueOf(args[4]));
                String gpProvincia=String.valueOf(args[5]);
                return myApiServicePosicionFiscalizador.guardarPosicion(
                        gpCodeUsuario, gpDepartamento, gpDistrito, gpLatitud, gpLongitud, gpProvincia
                ).execute();
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
                }
            }
        }
    }
}
