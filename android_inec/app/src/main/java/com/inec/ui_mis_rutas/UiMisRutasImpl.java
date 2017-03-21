package com.inec.ui_mis_rutas;

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
import com.inec.uihomeapp.UiHomeApp;
import com.inec.utils.PreferenciasSession;

import java.io.IOException;
import java.util.List;

import static com.inec.uiiniciarsesion.UiIniciarSesion.mPrefSesion;

/**
 * Created by root on 28/10/16.
 */

public class UiMisRutasImpl extends UiMisRutas {

    @Override
    public void loadPosicion() {
        LoadPosicionAsyncTask getMuroTuristaAsyncTask=new LoadPosicionAsyncTask(this.getApplicationContext());
        getMuroTuristaAsyncTask.execute();
    }
    class LoadPosicionAsyncTask extends AsyncTask<Bundle, Void,ReturnValue> {
        private GestionUsuario myApiServicePosicionFiscalizador = null;
        private Context context;
        public LoadPosicionAsyncTask(Context context){
            this.context=context;
        }
        private ProgressDialog pgDialog;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            objSwipeRefreshLayout.setRefreshing(true);
            pgDialog = new ProgressDialog(UiMisRutasImpl.this);
            pgDialog.setTitle("Cargando");
            pgDialog.setMessage("Espere..");
            pgDialog.setCancelable(false);
            pgDialog.show();
        }

        @Override
        protected ReturnValue doInBackground(Bundle... args) {
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
                return myApiServicePosicionFiscalizador.listarPosicionFiscalizadors(mPrefSesion.getString("dniusuario","")).execute();
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
                    List<PosicionFiscalizador> lista=result.getReturnListPosicionFiscalizador();
                    if(lista!=null && lista.size()>0){
                        adapterMisRutas.setData(lista);
                        adapterMisRutas.notifyDataSetChanged();
                    }
                }
            }
            objSwipeRefreshLayout.setRefreshing(false);
        }
    }
    
}
