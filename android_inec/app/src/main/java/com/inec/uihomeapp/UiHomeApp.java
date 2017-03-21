package com.inec.uihomeapp;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.googlecode.flickrjandroid.Flickr;
import com.inec.server.ws.gestionIncidencia.model.Incidencia;
import com.inec.server.ws.gestionMantenimiento.model.PartidoPolitico;
import com.inec.ui_cambiar_password.UiCambiarPasswordImpl;
import com.inec.ui_listar_incidencia.UiListarIncidencia;
import com.inec.ui_listar_incidencia.UiListarIncidenciaImpl;
import com.inec.ui_mis_rutas.UiMisRutasImpl;
import com.inec.ui_notificacion.UiNotificacionImpl;
import com.inec.uicapturarfoto.UiCapturarFotoImpl;
import com.inec.uiiniciarsesion.UiIniciarSesionImpl;
import com.inec.utils.BadgeDrawable;
import com.inec.utils.GeoPunto;
import com.inec.utils.PreferenciasSession;
import com.inec.utils.ServiceGPS;
import com.inec.utils.ServiceGeolocalizacion;
import com.inec.utils.UtilGeolocalizacion;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import inec.com.R;

import static com.inec.uiiniciarsesion.UiIniciarSesion.geoPunto;
import static com.inec.uiiniciarsesion.UiIniciarSesion.mPrefSesion;

public class UiHomeApp extends AppCompatActivity implements View.OnClickListener ,Toolbar.OnMenuItemClickListener,InterHomeApp,LocationListener, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener{
    public static Double mLatitud=0.0;
    public static Double mLongitud=0.0;
    public Button layoutRegistrarIncidencia;
    public static Button goUiMisRutas;
    public ScrollView svPrincipal;
    public static Boolean llamarNuevamente;
    public static String nombrePaisActual;
    public static String nombreRegionActual;
    public static String nombreLocalidadActual;
    public static String nombreDistritoActual;
    public static boolean reintentar=false;
    public TextView txtUsuario;
    public String txtIdentificacion;
    public static GoogleApiClient googleApiClient;
    public static LocationRequest locationRequest;
    public int CODE_ACTIVE_GEO = 1000;
    public static List<PartidoPolitico> listPartidoPolitico= new ArrayList<>();
    public static List<Incidencia> listIncidencia=new ArrayList<>();
    public static ServiceGeolocalizacion asyncTaskService;
    public static AsyncTaskFused asyncTaskFused;
    public static Boolean enviadoDeGPS = false;
    public static Activity homeActivity;
    private Toolbar toolbar;
    public MenuItem subMenuNotificacion;
    private static String[] PERMISSIONS_GPS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private static final int REQUEST_GPS = 123;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_home_app);
        homeActivity=this;
        initComponents();
        initListener();
        llamarNuevamente = false;
    }

    public void initComponents() {

        layoutRegistrarIncidencia = (Button) findViewById(R.id.ui_registrar_incidencia);
        goUiMisRutas = (Button) findViewById(R.id.ui_mis_rutas);
        svPrincipal = (ScrollView) findViewById(R.id.svPrincipal);
        txtUsuario= (TextView)findViewById(R.id.txt_usuario_session);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);


        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        googleApiClient.connect();
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        cargarPartidoPolitico();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mPrefSesion==null){
            mPrefSesion=getSharedPreferences(PreferenciasSession.NAME_PREFERENCE, Context.MODE_PRIVATE);
        }
        if(!mPrefSesion.getBoolean("autenticado",false)){
            PreferenciasSession.almacenarValor(mPrefSesion,"usuariosession",getIntent().getExtras().getString("usuariosession",""));
            PreferenciasSession.almacenarValor(mPrefSesion,"codesesionfiscalizador",getIntent().getExtras().getString("codesesionfiscalizador",""));
            PreferenciasSession.almacenarValor(mPrefSesion,"nombrecompleto",getIntent().getExtras().getString("nombrecompleto",""));
            PreferenciasSession.almacenarValor(mPrefSesion,"dniusuario",getIntent().getExtras().getString("dniusuario",""));
            PreferenciasSession.almacenarValor(mPrefSesion,"cantidadnotificaciones","1");
            PreferenciasSession.almacenarValorBoolean(mPrefSesion,"autenticado",getIntent().getExtras().getBoolean("autenticado"));
        }
        txtIdentificacion=mPrefSesion.getString("nombrecompleto","")+" - "+mPrefSesion.getString("dniusuario","");
        txtUsuario.setText(txtIdentificacion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        subMenuNotificacion = menu.findItem(R.id.mh_notificacion);
        setCantidadNotificaciones();
        return true;
    }

    public void setCantidadNotificaciones(){
        if(subMenuNotificacion!=null && mPrefSesion !=null) {
            LayerDrawable icon = (LayerDrawable) subMenuNotificacion.getIcon();
        }
    }

    @Override
    public void loadServiceGeo() {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ui_cerrar_session:
                cerrarSession();
                break;
            case R.id.ui_cambiar_pass:
                goCambiarPassword();
                break;
            case R.id.mh_notificacion:
                goNotification();
                break;
        }
        return true;
    }

    public void cerrarSession(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Desea cerrar sesión?");
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.textButtonAceptar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String codeSession=mPrefSesion.getString("codesesionfiscalizador","");
                cerrarSessionTask(codeSession);
                PreferenciasSession.remove(mPrefSesion);
                listIncidencia=null;
                listPartidoPolitico=null;
                Intent intent = new Intent(getApplicationContext(), UiIniciarSesionImpl.class);
                startActivity(intent);
                finish();

            }
        });
        builder.setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void cerrarSessionTask(String codeSessionFiscalizador) {

    }

    @Override
    public void goNotification() {
        Intent intent = new Intent(getApplicationContext(), UiNotificacionImpl.class);
        startActivity(intent);
    }

    @Override
    public void goCambiarPassword() {
        Intent intent = new Intent(getApplicationContext(), UiCambiarPasswordImpl.class);
        startActivity(intent);
    }

    public class AsyncTaskFused extends AsyncTask<Activity, Void, Boolean> {
        private ServiceGPS gps;

        @Override
        protected Boolean doInBackground(Activity... params) {
            gps=new ServiceGPS(params[0],params[0].getApplicationContext(),5,1);
            gps.connect();
            return gps.isConected();
        }

        public Boolean reconnect(){
            if(gps!=null) {
                gps.onConnected(null);
                return gps.getActiveGps();
            }else{
                return false;
            }
        }

        public Boolean activeGps(){
            if(gps!=null) {
                return gps.getActiveGps();
            }else{
                return false;
            }
        }

        public void disconect(){
            if(gps!=null) {
                gps.disconnect();
            }
        }

        public Double getCurrentLatitude() {
            return gps.getCurrentLatitude();
        }

        public Double getCurrentLongitude() {
            return gps.getCurrentLongitude();
        }

        public LatLng getLatLng() {
            return gps.getLatLng();
        }

        public boolean activarGeolocalizacion(Activity activity,Integer codeUi){
            if(gps!=null){
                return gps.activarGeolocalizacion(activity,codeUi);
            }else{
                return false;
            }
        }

    }

    public static GeoPunto returnGeoPunto() {
        GeoPunto geo=ServiceGeolocalizacion.currentGeoPunto;
        if(geo!=null){
            geo= UtilGeolocalizacion.convertGeoPointToGeoNames(geo,homeActivity);
        }
        if(geo==null){
            geo= ServiceGPS.currentGeoPunto;
            if(geo!=null){
                geo=UtilGeolocalizacion.convertGeoPointToGeoNames(geo,homeActivity);
            }
        }
        return geo;
    }

    public static void updateDatosPosicion(){
        GeoPunto geo = returnGeoPunto();
        if (geo != null) {
            enviadoDeGPS= true;
            mLatitud=geo.getLatitude();
            mLongitud=geo.getLongitude();
            nombrePaisActual= geo.getNombrePais();
            nombreRegionActual=geo.getNombreRegion();
            nombreLocalidadActual=geo.getNombreLocalidad();
            nombreDistritoActual=geo.getNombreProvincia();
            if(mLatitud!=null && mLongitud!=null &&
                    nombrePaisActual!=null &&  !nombrePaisActual.isEmpty() &&
                    nombreRegionActual!=null &&  !nombreRegionActual.isEmpty() &&
                    nombreLocalidadActual!=null &&  !nombreLocalidadActual.isEmpty()) {
                PreferenciasSession.almacenarValor(mPrefSesion, "LATD", mLatitud.toString());
                PreferenciasSession.almacenarValor(mPrefSesion, "LONGD", mLongitud.toString());
                PreferenciasSession.almacenarValor(mPrefSesion, "PAISD", nombrePaisActual.toString());
                PreferenciasSession.almacenarValor(mPrefSesion, "REGIOND", nombreRegionActual.toString());
                PreferenciasSession.almacenarValor(mPrefSesion, "LOCALIDADD", nombreLocalidadActual.toString());
                PreferenciasSession.almacenarValor(mPrefSesion, "PROVINCIA", nombreDistritoActual.toString());
            }
        } else {
            boolean valueServiceGeolocalizacion=false;
            if(ServiceGeolocalizacion.currentLatitude!=null &&
                    ServiceGeolocalizacion.currentLongitude!=null &&
                    ServiceGeolocalizacion.currentPais!=null){
                enviadoDeGPS = true;
                mLatitud = ServiceGeolocalizacion.currentLatitude;
                mLongitud = ServiceGeolocalizacion.currentLongitude;
                nombrePaisActual = ServiceGeolocalizacion.currentPais;
                nombreRegionActual = ServiceGeolocalizacion.currentRegion;
                nombreLocalidadActual = ServiceGeolocalizacion.currentLocalidad;
                nombreDistritoActual=ServiceGeolocalizacion.currentProvincia;
                if(mLatitud!=null && mLongitud!=null &&
                        nombrePaisActual!=null &&  !nombrePaisActual.isEmpty() &&
                        nombreRegionActual!=null &&  !nombreRegionActual.isEmpty() &&
                        nombreLocalidadActual!=null &&  !nombreLocalidadActual.isEmpty() &&
                        nombreDistritoActual!=null &&  !nombreDistritoActual.isEmpty()) {
                    PreferenciasSession.almacenarValor(mPrefSesion, "LATD", mLatitud.toString());
                    PreferenciasSession.almacenarValor(mPrefSesion, "LONGD", mLongitud.toString());
                    PreferenciasSession.almacenarValor(mPrefSesion, "PAISD", nombrePaisActual.toString());
                    PreferenciasSession.almacenarValor(mPrefSesion, "REGIOND", nombreRegionActual.toString());
                    PreferenciasSession.almacenarValor(mPrefSesion, "LOCALIDADD", nombreLocalidadActual.toString());
                    PreferenciasSession.almacenarValor(mPrefSesion, "PROVINCIA", nombreDistritoActual.toString());
                }
                valueServiceGeolocalizacion=true;
            }else if(com.inec.utils.ServiceGPS.currentLatitude!=null &&
                    com.inec.utils.ServiceGPS.currentLongitude!=null &&
                    com.inec.utils.ServiceGPS.currentPais!=null && !valueServiceGeolocalizacion){
                enviadoDeGPS = true;
                mLatitud = com.inec.utils.ServiceGPS.currentLatitude;
                mLongitud = com.inec.utils.ServiceGPS.currentLongitude;
                nombrePaisActual = com.inec.utils.ServiceGPS.currentPais;
                nombreRegionActual = com.inec.utils.ServiceGPS.currentRegion;
                nombreLocalidadActual = com.inec.utils.ServiceGPS.currentLocalidad;
                nombreDistritoActual=com.inec.utils.ServiceGPS.currentProvincia;
                if(mLatitud!=null && mLongitud!=null &&
                        nombrePaisActual!=null &&  !nombrePaisActual.isEmpty() &&
                        nombreRegionActual!=null &&  !nombreRegionActual.isEmpty() &&
                        nombreLocalidadActual!=null &&  !nombreLocalidadActual.isEmpty()) {
                    PreferenciasSession.almacenarValor(mPrefSesion, "LATD", mLatitud.toString());
                    PreferenciasSession.almacenarValor(mPrefSesion, "LONGD", mLongitud.toString());
                    PreferenciasSession.almacenarValor(mPrefSesion, "PAISD", nombrePaisActual.toString());
                    PreferenciasSession.almacenarValor(mPrefSesion, "REGIOND", nombreRegionActual.toString());
                    PreferenciasSession.almacenarValor(mPrefSesion, "LOCALIDADD", nombreLocalidadActual.toString());
                    PreferenciasSession.almacenarValor(mPrefSesion, "PROVINCIA", nombreDistritoActual.toString());
                }
            }else {
                enviadoDeGPS = true;
                mLatitud = Double.parseDouble(mPrefSesion.getString("LATD", "-6.790910"));
                mLongitud = Double.parseDouble(mPrefSesion.getString("LONGD", "-79.834149"));
                nombrePaisActual = mPrefSesion.getString("PAISD", "PERU");
                nombreRegionActual = mPrefSesion.getString("REGIOND", "CHICLAYO");
                nombreLocalidadActual = mPrefSesion.getString("LOCALIDADD", "LA VICTORIA");
                nombreDistritoActual = mPrefSesion.getString("PROVINCIA", "CHICLAYO");
                ;
            }
        }
        geoPunto= new GeoPunto();
        geoPunto.setNombreProvincia(mPrefSesion.getString("PROVINCIA", "CHICLAYO"));
        geoPunto.setLatitude(Double.parseDouble(mPrefSesion.getString("LATD", "-6.790910")));
        geoPunto.setLongitude(Double.parseDouble(mPrefSesion.getString("LONGD", "-79.834149")));
        geoPunto.setNombreLocalidad(mPrefSesion.getString("LOCALIDADD", "LA VICTORIA"));
        geoPunto.setNombreRegion(mPrefSesion.getString("REGIOND", "LAMBAYEQUE"));
        geoPunto.setNombrePais(mPrefSesion.getString("PAISD", "PERU"));
    }

    @Override
    public void onResume(){
        super.onResume();
        asyncTaskService=new ServiceGeolocalizacion(this,this.getApplicationContext(),5,1);
        asyncTaskService.onConnected(null);
        asyncTaskFused=new AsyncTaskFused();
        asyncTaskFused.execute(this);
        updateDatosPosicion();
    }


    public void initListener() {
        layoutRegistrarIncidencia.setOnClickListener(this);
        goUiMisRutas.setOnClickListener(this);
        toolbar.setOnMenuItemClickListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ui_registrar_incidencia:
//                goListarIncidencia();
                verificarGps();
                break;
            case R.id.ui_mis_rutas:
                goMisRutas();
                break;
            default:
                break;
        }
    }



    public void verificarGps() {
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                ) {
            requestGeolocalizacionPermission();
        } else {
            try {
                goListarIncidencia();
            }catch(Exception ex) {
                Toast.makeText(getApplicationContext(), "Habilite permisos manualmente", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {
        if (requestCode == REQUEST_GPS) {
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    ) {
                Toast.makeText(getApplicationContext(), "Permisos otorgados", Toast.LENGTH_SHORT).show();
                goListarIncidencia();
            } else {
                Toast.makeText(getApplicationContext(), "Habilite todos los permisos", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void requestGeolocalizacionPermission() {
        ActivityCompat.requestPermissions(this, PERMISSIONS_GPS, REQUEST_GPS);
    }


    @Override
    public void goCaptureFoto() {
        UiCapturarFotoImpl.ubicacionObtenida = false;
        UiCapturarFotoImpl.envioRealizado = false;
        UiCapturarFotoImpl.fotoTomada = false;
        UiCapturarFotoImpl.fotoVisible = false;
        updateDatosPosicion();
        Intent intent = new Intent(this, UiCapturarFotoImpl.class);
        startActivity(intent);
    }

    @Override
    public void goListarIncidencia() {
        Intent intent= new Intent(this, UiListarIncidenciaImpl.class);
        startActivity(intent);
    }

    public String cleanStringUpper(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return texto.toUpperCase();
    }

    @Override
    public void cargarPartidoPolitico() {
    }

    @Override
    public void goMisRutas() {
        Intent intent= new Intent(this, UiMisRutasImpl.class);
        startActivity(intent);
    }
}
