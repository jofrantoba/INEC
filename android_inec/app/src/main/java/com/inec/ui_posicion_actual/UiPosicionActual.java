package com.inec.ui_posicion_actual;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;
import com.google.maps.android.ui.IconGenerator;
import com.inec.ui_cambiar_password.UiCambiarPasswordImpl;
import com.inec.uihomeapp.UiHomeApp;
import com.inec.uihomeapp.UiHomeAppImpl;
import com.inec.uiiniciarsesion.UiIniciarSesion;

import java.text.SimpleDateFormat;

import inec.com.R;

import static com.inec.uiiniciarsesion.UiIniciarSesion.geoPunto;
import static com.inec.uiiniciarsesion.UiIniciarSesion.mPrefSesion;

/**
 * Created by root on 29/10/16.
 */

public class UiPosicionActual extends AppCompatActivity implements InterPosicionActual, OnMapReadyCallback, View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private GoogleMap mpGoogle;
    private Marker miMarker;
    private SupportMapFragment mapFragment;
    public static TextView uipa_display_latitud;
    public static TextView uipa_display_longitud;
    public static TextView uipa_display_departamento;
    public static TextView uipa_display_provincia;
    public static TextView uipa_display_distrito;
    public static TextView uipa_display_fecha;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    public static LinearLayout uipa_layout_onclick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        setContentView(R.layout.ui_posicion_actual);
        initComponents();
        loadData();
        initListener();

    }

    @Override
    public void initComponents() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.uipa_content_map);
        uipa_display_latitud = (TextView) findViewById(R.id.uipa_display_latitud);
        uipa_display_longitud = (TextView) findViewById(R.id.uipa_display_longitud);
        uipa_display_departamento = (TextView) findViewById(R.id.uipa_display_departamento);
        uipa_display_provincia = (TextView) findViewById(R.id.uipa_display_provincia);
        uipa_display_distrito = (TextView) findViewById(R.id.uipa_display_distrito);
        uipa_display_fecha = (TextView) findViewById(R.id.uipa_display_fecha);
        uipa_layout_onclick = (LinearLayout) findViewById(R.id.uipa_layout_onclick);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        googleApiClient.connect();

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationRequest.setInterval(5 * 1000);
        locationRequest.setFastestInterval(1 * 1000);
    }

    @Override
    public void loadData() {
        uipa_display_latitud.setText(geoPunto.getLatitude().toString());
        uipa_display_longitud.setText(geoPunto.getLongitude().toString());
        uipa_display_departamento.setText(geoPunto.getNombreRegion());
        uipa_display_provincia.setText(geoPunto.getNombreProvincia());
        uipa_display_distrito.setText(geoPunto.getNombreLocalidad());
        uipa_display_fecha.setText(new SimpleDateFormat().format(new java.util.Date().getTime()));
        String gpCodeUsuario=mPrefSesion.getString("dniusuario","");
        String gpDepartamento=geoPunto.getNombreRegion();
        String gpDistrito=geoPunto.getNombreLocalidad();
        Double gpLatitud=geoPunto.getLatitude();
        Double gpLongitud=geoPunto.getLongitude();
        String gpProvincia=geoPunto.getNombreProvincia();
        guardarPosicion(gpCodeUsuario, gpDepartamento, gpDistrito, gpLatitud, gpLongitud, gpProvincia);
    }

    @Override
    public void initListener() {
        uipa_layout_onclick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uipa_layout_onclick:
                finish();
                if(UiHomeApp.homeActivity==null){
                    Intent intent = new Intent(getApplicationContext(), UiHomeAppImpl.class);
                    startActivity(intent);
                }else{
                    onBackPressed();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mapFragment.getMapAsync(this);
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
    public void onMapReady(GoogleMap googleMap) {
        mpGoogle = googleMap;
        cargarGoogleMaps();
    }

    @Override
    public void cargarGoogleMaps() {
        Double mLatitud = geoPunto.getLatitude();
        Double mLongitud=geoPunto.getLongitude();
        if (mLatitud != null && mLongitud != null) {
            LatLng miPosicion = new LatLng(mLatitud, mLongitud);
            if (miMarker == null) {
                miMarker = mpGoogle.addMarker(new MarkerOptions().position(miPosicion));
            } else {
                miMarker.remove();
                miMarker = mpGoogle.addMarker(new MarkerOptions().position(miPosicion));
            }
            mpGoogle.moveCamera(CameraUpdateFactory.newLatLngZoom(miPosicion,14));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
    }

    @Override
    public void guardarPosicion(String gpCodeUsuario, String gpDepartamento, String gpDistrito, Double gpLatitud, Double gpLongitud, String gpProvincia) {

    }
}