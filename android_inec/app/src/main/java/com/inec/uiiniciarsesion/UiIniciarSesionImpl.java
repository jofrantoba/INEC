package com.inec.uiiniciarsesion;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.iid.FirebaseInstanceId;
import com.inec.server.ws.gestionUsuario.GestionUsuario;
import com.inec.server.ws.gestionUsuario.model.ReturnValue;
import com.inec.server.ws.gestionUsuario.model.UsuarioFiscalizador;
import com.inec.uihomeapp.UiHomeAppImpl;
import com.inec.utils.PreferenciasSession;
import java.io.IOException;
import inec.com.R;

/**
 * Created by root on 10/09/16.
 */
public class UiIniciarSesionImpl extends UiIniciarSesion {

    @Override
    public void iniciarSesion() {
        if(!isRunTask) {
            pbLoading.setVisibility(View.VISIBLE);
            GetLoginAsyncTask getOauthAsyncTask = new GetLoginAsyncTask(this);
            getOauthAsyncTask.execute(txtClave.getText().toString(), txtEmail.getText().toString());
        }
    }

    class GetLoginAsyncTask extends AsyncTask<String, Void,ReturnValue> {
        private GestionUsuario myApiServiceGestionUsuario = null;
        private Context context;
        public GetLoginAsyncTask(Context context){
            this.context=context;
        }

        @Override
        protected ReturnValue doInBackground(String ... params) {
            isRunTask=true;
            Bundle bundle=new Bundle();
            String usuario=params[1];
            String clave=params[0];
            try {
                if(myApiServiceGestionUsuario == null) {  // Only do this once
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
                String tokenFirebase = FirebaseInstanceId.getInstance().getToken();
                usuario_session=usuario;
                ReturnValue values=myApiServiceGestionUsuario.loginUsuario(clave,usuario,tokenFirebase).execute();
                return  values;
            } catch (Exception e) {
                FirebaseCrash.report(e.getCause());
                FirebaseCrash.logcat(e.hashCode(),e.getLocalizedMessage(),e.getMessage());
                bundle.putString("exception",e.getMessage());
                bundle.putBoolean("result",false);
            }
            return null;
        }

        @Override
        protected void onPostExecute(ReturnValue result) {
            pbLoading.setVisibility(View.GONE);
            if(result!=null){
                if(result.getNameClass().equalsIgnoreCase(UsuarioFiscalizador.class.getSimpleName())){
                    Toast.makeText(context, R.string.msg_welcome_Inec, Toast.LENGTH_LONG).show();
                    UsuarioFiscalizador beanUsuarioFiscalizador= result.getReturnUsuarioFiscalizador();
                    Intent intent = new Intent(context,UiHomeAppImpl.class);
                    intent.putExtra("usuariosession",usuario_session);
                    intent.putExtra("codesesionfiscalizador",beanUsuarioFiscalizador.getOperacion());
                    intent.putExtra("nombrecompleto",beanUsuarioFiscalizador.getNombre()+" "+beanUsuarioFiscalizador.getApellido());
                    intent.putExtra("dniusuario",beanUsuarioFiscalizador.getDniFiscalizador());
                    intent.putExtra("autenticado",true);
                    startActivity(intent);
                }else{
                    Toast.makeText(context, R.string.err_datos_incorrectos, Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(context, R.string.err_datos_incorrectos, Toast.LENGTH_LONG).show();
            }
            isRunTask=false;
        }
    }
}
