package com.inec.ui_mis_rutas;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.inec.adapter.AdapterMisRutas;
import com.inec.ui_posicion_actual.UiPosicionActual;
import com.inec.ui_posicion_actual.UiPosicionActualImpl;
import com.inec.ui_reporte.UiReporte;
import com.inec.ui_reporte.UiReporteImpl;
import com.inec.uihomeapp.UiHomeApp;
import com.inec.utils.PreferenciasSession;

import inec.com.R;

import static com.inec.uihomeapp.UiHomeAppImpl.asyncTaskFused;
import static com.inec.uihomeapp.UiHomeAppImpl.asyncTaskService;
import static com.inec.uiiniciarsesion.UiIniciarSesion.mPrefSesion;

/**
 * Created by root on 28/10/16.
 */

public class UiMisRutas extends AppCompatActivity implements View.OnClickListener,InterMisRutas,SwipeRefreshLayout.OnRefreshListener,Toolbar.OnMenuItemClickListener {
    private Toolbar toolbar;
    public static ListView uimr_layout_content;
    public static AdapterMisRutas adapterMisRutas;
    public static SwipeRefreshLayout objSwipeRefreshLayout;
    public static LinearLayout uimr_layout_click;
    private static String[] PERMISSIONS_GPS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private static final int REQUEST_GPS = 123;
    private boolean valueGeo=false;
    public int CODE_ACTIVE_GEO = 1000;
    private static final int DURATION = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_mis_rutas);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        uimr_layout_content = (ListView) findViewById(R.id.uimr_layout_content);
        adapterMisRutas = new AdapterMisRutas(this);
        uimr_layout_content.setAdapter(adapterMisRutas);
        objSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.uimr_swipe_refresh_layout);
        uimr_layout_click = (LinearLayout) findViewById(R.id.uimr_layout_click);
        objSwipeRefreshLayout.setOnRefreshListener(this);
        loadPosicion();
        initListener();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_rutas, menu);
        return true;
    }

    @Override
    public void onRefresh() {
    loadPosicion();
    }

    @Override
    public void loadPosicion() {

    }

    @Override
    public void initListener() {
        uimr_layout_click.setOnClickListener(this);
    }

    @Override
    public void goPosicionActual() {
        Intent intent = new Intent(getApplicationContext(), UiPosicionActualImpl.class);
        startActivity(intent);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mr_posicion:
               goPosicionActual();
                break;
        }
        return true;
    }

    @Override
    public void goReporteRutas() {
        Intent intent = new Intent(getApplicationContext(), UiReporteImpl.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mr_posicion:
//                goPosicionActual();
                if(ActivarGPS()){
                    goPosicionActual();
                }
                break;
            case R.id.uimr_layout_click:
                finish();
                onBackPressed();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mr_posicion:
                UiHomeApp.updateDatosPosicion();
//                goPosicionActual();
//                verificarGps();
                if(ActivarGPS()){
                    goPosicionActual();
                }
                break;
            case R.id.mr_generar_reporte:
                goReporteRutas();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mPrefSesion==null){
            mPrefSesion=getSharedPreferences(PreferenciasSession.NAME_PREFERENCE, Context.MODE_PRIVATE);
        }
        loadPosicion();
    }

    public void verificarGps() {
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                ) {
            requestGeolocalizacionPermission();
        } else {
            try {
                goPosicionActual();
            }catch(Exception ex) {
                Toast.makeText(getApplicationContext(), "Habilite permisos manualmente", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean ActivarGPS() {
        if(asyncTaskFused!=null){
            valueGeo=asyncTaskFused.reconnect();
            if(!valueGeo){
                asyncTaskFused.activarGeolocalizacion(this, CODE_ACTIVE_GEO);
            }
        }
        return valueGeo;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_ACTIVE_GEO) {
            if (resultCode == RESULT_OK) {
                valueGeo=false;
                int contador=0;
                while(!valueGeo){
                    valueGeo=asyncTaskService.onConnected(null);
                    if(valueGeo){
                        break;
                    }
                    valueGeo=asyncTaskFused.reconnect();
                    if(valueGeo){
                        break;
                    }
                    contador=contador+1;
                    if(contador>5){
                        break;
                    }
                }
                goPosicionActual();
            }else{
                valueGeo=false;
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
                goPosicionActual();
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
}
