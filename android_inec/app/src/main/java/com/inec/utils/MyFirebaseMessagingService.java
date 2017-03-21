package com.inec.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.inec.server.ws.gestionUsuario.GestionUsuario;
import com.inec.server.ws.gestionUsuario.model.ReturnValue;
import com.inec.ui_posicion_actual.UiPosicionActualImpl;
import com.inec.ui_reporte.UiReporteImpl;
import com.inec.uihomeapp.UiHomeApp;
import com.inec.uihomeapp.UiHomeAppImpl;

import java.io.IOException;
import java.text.SimpleDateFormat;

import inec.com.R;

import static com.inec.uiiniciarsesion.UiIniciarSesion.geoPunto;
/**
 * Created by root on 03/08/16.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    private NotificationManager notificationManager;
    private NotificationCompat.Builder notificationBuilder;
    private Intent intent=null;
    public SharedPreferences mPrefSesion;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mPrefSesion = getSharedPreferences(PreferenciasSession.NAME_PREFERENCE, Context.MODE_PRIVATE);

//        String gpCodeUsuario=mPrefSesion.getString("dniusuario","");
//        String gpDepartamento=geoPunto.getNombreRegion();
//        String gpDistrito=geoPunto.getNombreLocalidad();
//        Double gpLatitud=geoPunto.getLatitude();
//        Double gpLongitud=geoPunto.getLongitude();
//        String gpProvincia=geoPunto.getNombreProvincia();
//        guardarPosicion(gpCodeUsuario, gpDepartamento, gpDistrito, gpLatitud, gpLongitud, gpProvincia);

        Log.v("HH", new SimpleDateFormat().format(new java.util.Date()));
//        Intent intent = new Intent(getApplicationContext(), UiPosicionActualImpl.class);
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2222, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        notificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_inec)
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent)
//                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
//                .setLights(Color.BLUE,3000,3000);
//
//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        manager.notify(0,notificationBuilder.build());
       }

    public void guardarPosicion(String gpCodeUsuario, String gpDepartamento, String gpDistrito, Double gpLatitud, Double gpLongitud, String gpProvincia) {
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