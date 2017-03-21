package com.inec.ui_editar_incidencia;

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
import com.inec.server.ws.gestionIncidencia.GestionIncidencia;
import com.inec.server.ws.gestionIncidencia.model.Incidencia;
import com.inec.server.ws.gestionIncidencia.model.ReturnValue;
import com.inec.utils.PreferenciasSession;

import java.io.IOException;
import java.util.List;

import static com.inec.uiiniciarsesion.UiIniciarSesion.mPrefSesion;

/**
 * Created by root on 26/10/16.
 */

public class UiEditarIncidenciaImpl extends UiEditarIncidencia {

    @Override
    public void callEditarIncidencia(String aiCodeIncidencia, String aiCodePartidoPolitico, String aiCodeUsuario, String aiDescripcion, String aiDireccion, String aiTipoIncidencia) {
        super.callEditarIncidencia(aiCodeIncidencia, aiCodePartidoPolitico, aiCodeUsuario, aiDescripcion, aiDireccion, aiTipoIncidencia);
        EditarIncidenciaAsyncTask editarIncidenciaTask= new EditarIncidenciaAsyncTask(this);
        editarIncidenciaTask.execute(aiCodeIncidencia, aiCodePartidoPolitico, aiCodeUsuario, aiDescripcion, aiDireccion, aiTipoIncidencia);
    }

    class EditarIncidenciaAsyncTask extends AsyncTask<String, Void,ReturnValue> {
        private GestionIncidencia myApiServiceIncidencia = null;
        private Context context;
        public EditarIncidenciaAsyncTask(Context context){
            this.context=context;
        }
        private ProgressDialog pgDialog;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pgDialog = new ProgressDialog(UiEditarIncidenciaImpl.this);
            pgDialog.setTitle("Cargando");
            pgDialog.setMessage("Espere..");
            pgDialog.show();
        }

        @Override
        protected ReturnValue doInBackground(String... params) {
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
                return myApiServiceIncidencia.actualizarIncidencia(params[0],params[1],params[2],params[3],params[4],params[5]).execute();
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
                } else if(result.getNameClass().equalsIgnoreCase(Boolean.class.getSimpleName())) {
                    Toast.makeText(context, "Se Actualizo  los Datos Correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
}
