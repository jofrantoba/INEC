package com.inec.ui_reporte;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.firebase.crash.FirebaseCrash;
import com.inec.server.ws.gestionUsuario.model.PosicionFiscalizador;
import com.inec.server.ws.gestionUsuario.model.ReturnValue;
import com.inec.server.ws.gestionUsuario.GestionUsuario;
import com.inec.utils.PreferenciasSession;

import java.io.File;
import java.io.IOException;
import java.util.List;

import inec.com.R;

import static com.inec.uiiniciarsesion.UiIniciarSesion.mPrefSesion;

/**
 * Created by root on 04/11/16.
 */

public class UiReporteImpl  extends  UiReporte{

    @Override
    public void generarReporte(String fecha, List<PosicionFiscalizador> listaPosicionFiscalizador) {
        super.generarReporte(fecha, listaPosicionFiscalizador);
    }

    @Override
    public void openRenderer(String filePath) {
        super.openRenderer(filePath);
    }

    @Override
    public void loadGenerarReporte(String fechaFormat) {
        RutasReportTask reportTask=new RutasReportTask(this.getApplicationContext());
        reportTask.execute(fechaFormat);
    }

    class RutasReportTask extends AsyncTask<String, Void,ReturnValue> {
        private GestionUsuario myApiServiceUsuario = null;
        private Context context;
        public RutasReportTask(Context context){
            this.context=context;
        }
        private ProgressDialog pgDialog;
        private String fechaFormat;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pgDialog = new ProgressDialog(UiReporteImpl.this);
            pgDialog.setMessage("Generando Reporte, por favor espere...");
            pgDialog.show();
        }

        @Override
        protected ReturnValue doInBackground(String... args) {
            try {
                if(myApiServiceUsuario == null) {  // Only do this once
                    GestionUsuario.Builder builder = new GestionUsuario.Builder(AndroidHttp.newCompatibleTransport(),
                            new AndroidJsonFactory(), null)
                            .setRootUrl(PreferenciasSession.DEFAULT_ROOT_URL)
                            .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                                @Override
                                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                    abstractGoogleClientRequest.setDisableGZipContent(true);
                                }
                            });
                    myApiServiceUsuario = builder.build();
                    fechaFormat=args[0];
                }
                return myApiServiceUsuario.listarRutas(mPrefSesion.getString("dniusuario",""),fechaFormat).execute();
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
                    List<PosicionFiscalizador> listPosicionFiscalizador=result.getReturnListPosicionFiscalizador();
                    if(listPosicionFiscalizador==null){
                        Toast.makeText(context, "No hay rutas para esta fecha", Toast.LENGTH_SHORT).show();
                    }else{
                        if(listPosicionFiscalizador.isEmpty()){
                            Toast.makeText(context, "No hay rutas para esta fecha", Toast.LENGTH_SHORT).show();
                        }else{
                            generarReporte(fechaFormat,listPosicionFiscalizador);
                            if(UiReporte.PATH!=null){
                                Intent intent = new Intent();
                                intent.setAction(android.content.Intent.ACTION_VIEW);
                                File file = new File(UiReporte.PATH);
                                intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                                PendingIntent pIntent = PendingIntent.getActivity(UiReporteImpl.this, 0, intent, 0);
                                Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                Notification noti = new NotificationCompat.Builder(UiReporteImpl.this)
                                        .setContentTitle("Abrir Reporte de Ruta Diaria")
                                        .setSmallIcon(R.mipmap.ic_inec)
                                        .setContentText("INEC")
                                        .setSound(defaultSoundUri)
                                        .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                                        .setContentIntent(pIntent).build();

                                noti.flags |= Notification.FLAG_AUTO_CANCEL;
                                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                notificationManager.notify(0, noti);
                            }
                        }
                    }
                }
            }
        }
    }
}
