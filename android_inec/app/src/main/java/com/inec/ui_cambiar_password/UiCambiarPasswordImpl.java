package com.inec.ui_cambiar_password;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.firebase.crash.FirebaseCrash;
import com.inec.server.ws.gestionUsuario.GestionUsuario;
import com.inec.server.ws.gestionUsuario.model.ReturnValue;
import com.inec.server.ws.gestionUsuario.model.UsuarioFiscalizador;
import com.inec.utils.PreferenciasSession;
import java.io.File;
import java.io.IOException;


/**
 * Created by root on 28/10/16.
 */

public class UiCambiarPasswordImpl  extends  UiCambiarPassword{

    @Override
    public void goCambiarPassword(String apClaveUsuario, String apConfirmarClave, String apDniUsuario, String apNuevaClave) {
        GoActualizarClave goActualizarClave= new GoActualizarClave();
        goActualizarClave.execute(apClaveUsuario,apConfirmarClave,apDniUsuario,apNuevaClave);
    }

    class GoActualizarClave extends AsyncTask<String, Void, Object> {
        private GestionUsuario myApiService = null;
        private AsyncTask mTask;
        private File image;
        private ProgressDialog pgDialog;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mTask = this;
            pgDialog = new ProgressDialog(UiCambiarPasswordImpl.this);
            pgDialog.setTitle("Espere ..");
            pgDialog.setMessage("Actualizando Informacion");
            pgDialog.show();
        }
        @Override
        protected Object doInBackground(String... params) {
            if(myApiService == null) {  // Only do this once
                GestionUsuario.Builder builder = new GestionUsuario.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl(PreferenciasSession.DEFAULT_ROOT_URL)
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                myApiService = builder.build();
            }
            String resultado = null;
            ReturnValue valueUsuario= null;
            try {
                valueUsuario = myApiService.actualizarPassword(
                        params[0],params[1],params[2],params[3]
                ).execute();
            } catch (IOException e) {
                FirebaseCrash.report(e.getCause());
                FirebaseCrash.logcat(e.hashCode(),e.getLocalizedMessage(),e.getMessage());
            }
            return valueUsuario;

        }
        @Override
        protected void onPostExecute(Object result) {
            pgDialog.dismiss();
            if(result instanceof ReturnValue){
                ReturnValue rv = (ReturnValue) result;
                if(rv.getNameClass().equalsIgnoreCase(UsuarioFiscalizador.class.getSimpleName())) {
                    Toast.makeText(UiCambiarPasswordImpl.this,"Se Actualizaron sus datos",Toast.LENGTH_LONG).show();
                    clearData();
                    goIniciarSesion();
                }else{
                    Toast.makeText(UiCambiarPasswordImpl.this,rv.getValueReturn().toString(),Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
