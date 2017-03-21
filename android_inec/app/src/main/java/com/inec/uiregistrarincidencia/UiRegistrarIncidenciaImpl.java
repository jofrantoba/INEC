package com.inec.uiregistrarincidencia;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.util.DateTime;
import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.RequestContext;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import com.googlecode.flickrjandroid.people.User;
import com.googlecode.flickrjandroid.uploader.UploadMetaData;
import com.inec.server.ws.gestionIncidencia.GestionIncidencia;
import com.inec.server.ws.gestionIncidencia.model.Incidencia;
import com.inec.server.ws.gestionIncidencia.model.ReturnValue;
import com.inec.uicapturarfoto.UiCapturarFoto;
import com.inec.uicapturarfoto.UiCapturarFotoImpl;
import com.inec.utils.PreferenciasSession;

import org.json.JSONException;
import org.xml.sax.SAXException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import inec.com.R;

import static com.inec.uicapturarfoto.UiCapturarFoto.contador;
import static com.inec.uicapturarfoto.UiCapturarFoto.fotoTomada;
import static com.inec.uicapturarfoto.UiCapturarFoto.fotoVisible;
import static com.inec.uicapturarfoto.UiCapturarFoto.listBitMap;
import static com.inec.uicapturarfoto.UiCapturarFoto.listBitMapCoordenadas;
import static com.inec.uicapturarfoto.UiCapturarFoto.listFile;
import static com.inec.uicapturarfoto.UiCapturarFoto.listLatitud;
import static com.inec.uicapturarfoto.UiCapturarFoto.listLongitud;
import static com.inec.uicapturarfoto.UiCapturarFoto.listResources;
import static com.inec.uicapturarfoto.UiCapturarFoto.listUrlFlikr;
import static com.inec.uiiniciarsesion.UiIniciarSesion.mPrefSesion;

/**
 * Created by root on 09/10/16.
 */

public class UiRegistrarIncidenciaImpl extends  UiRegistrarIncidencia {


    @Override
    public void goRegistrarIncidencia(String riCodePartidoPolitico, String riDepartamento, String riDescripcion, String riDireccion, String riDistrito, String riDniUsuario, Long riFechaCelular, Double riLatitud, List<String> riListUrlFotografia, Double riLongitud, String riProvincia, String riTipoIncidencia) {
        new GoRegistrarIncidenciaFotografica().execute(
                 riCodePartidoPolitico,  riDepartamento,  riDescripcion,  riDireccion,
                 riDistrito,  riDniUsuario,  riFechaCelular,  riLatitud,null,
                 riLongitud,  riProvincia,  riTipoIncidencia
        );
    }

    class GoRegistrarIncidenciaFotografica extends AsyncTask<Object, Void, Object> {
        private GestionIncidencia myApiService = null;
        private AsyncTask mTask;
        private File image;
        private ProgressDialog pgDialog;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mTask = this;
            pgDialog = new ProgressDialog(UiRegistrarIncidenciaImpl.this);
            pgDialog.setTitle("Guardando");
            pgDialog.setMessage("Registrando Actividad");
            pgDialog.show();
        }
        @Override
        protected Object doInBackground(Object... params) {
            Flickr flickr = new Flickr(PreferenciasSession.FLICKR_API_KEY, PreferenciasSession.FLICKR_API_SECRET);
            OAuth auth = new OAuth();
            User user = new User();
            user.setId(PreferenciasSession.FLICKR_USER_ID);
            user.setUsername(PreferenciasSession.FLICKR_USER_NAME);
            auth.setToken(new OAuthToken(PreferenciasSession.FLICKR_OAUTH_TOKEN, PreferenciasSession.FLICKR_OAUTH_SECRET));
            RequestContext.getRequestContext().setOAuth(auth);

            if(myApiService == null) {  // Only do this once
                GestionIncidencia.Builder builder = new GestionIncidencia.Builder(AndroidHttp.newCompatibleTransport(),
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
            try {
                int cont=0;
                for (int i = 0 ; i<listBitMapCoordenadas.size();i++){
                    cont=cont+1;
                    Log.e("contado"," "+cont);
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    Bitmap bitMap= (Bitmap)listBitMapCoordenadas.get(i)[0];
                    if(bitMap==null){
                        mTask.cancel(true);
                    }
                    bitMap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    resultado = flickr.getUploader().upload("Imagen", out.toByteArray(), new UploadMetaData());
                    String url=flickr.getPhotosInterface().getInfo(resultado, null).getMedium800Url();
                    List<String> myList= new ArrayList<>();
                    myList.add(url);
                    myList.add(listBitMapCoordenadas.get(i)[1].toString());
                    myList.add(listBitMapCoordenadas.get(i)[2].toString());
                    listUrlFlikr.add(url);
                    listLatitud.add(Double.parseDouble(listBitMapCoordenadas.get(i)[1].toString()));
                    listLongitud.add(Double.parseDouble(listBitMapCoordenadas.get(i)[2].toString()));
                    out.flush();
                    out.close();
                    bitMap.recycle();
                }

                ReturnValue valueIncidencia=myApiService.registrarIncidenciaTransaction(
                        params[0].toString(),
                        params[1].toString(),
                        params[2].toString(),
                        params[3].toString(),
                        params[4].toString(),
                        params[5].toString(),
                        Long.parseLong(params[6].toString()),
                        Double.parseDouble(params[7].toString()),
                        Double.parseDouble(params[9].toString()),
                        params[10].toString(),
                        params[11].toString(),listUrlFlikr,listLatitud,listLongitud
                        ).execute();
                return valueIncidencia;
            } catch (FlickrException e) {
                return  e.getMessage();
            } catch (IOException e) {
                return  e.getMessage();
            } catch (SAXException e) {
                return  e.getMessage();
            } catch (JSONException e) {
                return  e.getMessage();
            }
        }
        @Override
        protected void onPostExecute(Object result) {
            pgDialog.dismiss();
            if(result instanceof ReturnValue){
                ReturnValue rv = (ReturnValue) result;
                if(rv.getNameClass().equalsIgnoreCase(Incidencia.class.getSimpleName())) {
                    Toast.makeText(UiRegistrarIncidenciaImpl.this, R.string.msg_registro_correcto,Toast.LENGTH_LONG).show();
                    listUrlFlikr=new ArrayList<>();
                    listLatitud=new ArrayList<>();
                    listLongitud=new ArrayList<>();
                    listBitMap= new ArrayList<>();
                    listBitMapCoordenadas=new ArrayList<>();
                    listFile=new ArrayList<>();
                    contador=0;
                    fotoVisible=false;
                    fotoTomada=false;
                }else{
                    Toast.makeText(UiRegistrarIncidenciaImpl.this,rv.getValueReturn().toString(),Toast.LENGTH_LONG).show();
                }
            }
            UiCapturarFoto.actCapture.finish();
            finish();
        }
    }
}