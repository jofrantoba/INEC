package com.inec.uiiniciarsesion;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.inec.ui_posicion_actual.UiPosicionActualImpl;
import com.inec.uihomeapp.UiHomeApp;
import com.inec.uihomeapp.UiHomeAppImpl;
import com.inec.utils.FieldVerifier;
import com.inec.utils.GeoPunto;
import com.inec.utils.PreferenciasSession;

import inec.com.R;

import static com.inec.uihomeapp.UiHomeApp.updateDatosPosicion;


public class UiIniciarSesion extends Activity implements InterIniciarSesion,View.OnClickListener {
    public static final String TAG=UiIniciarSesion.class.getSimpleName();
    public static Button btnLogin;
    public static EditText txtEmail;
    public static EditText txtClave;
    public static Boolean isRunTask=false;
    protected static ProgressBar pbLoading;
    public static View view;
    public static String usuario_session;
    public static SharedPreferences mPrefSesion;


    public static GeoPunto geoPunto= null;

    public UiIniciarSesion() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrefSesion = getSharedPreferences(PreferenciasSession.NAME_PREFERENCE, Context.MODE_PRIVATE);
        if (getIntent().getExtras() != null) {
            updateDatosPosicion();
            if(mPrefSesion.getBoolean("autenticado",false)){
                Intent intent = new Intent(this, UiPosicionActualImpl.class);
                startActivity(intent);
                this.finish();
            }else{
                setContentView(R.layout.ui_inicio_sesion);
                initComponents();
                initListener();
            }
        }else{
            if(mPrefSesion.getBoolean("autenticado",false)){
                Intent intent = new Intent(this,UiHomeAppImpl.class);
                startActivity(intent);
                this.finish();
            }else{
                setContentView(R.layout.ui_inicio_sesion);
                initComponents();
                initListener();
            }
        }
    }

    @Override
    public void initComponents() {
        btnLogin=(Button) findViewById(R.id.aceptarButton);
        txtEmail=(EditText) findViewById(R.id.txtCorreo);
        txtClave = (EditText) findViewById(R.id.txtClave);
        pbLoading = (ProgressBar)findViewById(R.id.pbLoading);
        pbLoading.getIndeterminateDrawable().setColorFilter(Color.YELLOW,android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    private void initListener(){
        btnLogin.setOnClickListener(this);
    }

    @Override
    public boolean isValidData() {
        if(FieldVerifier.isEmpty(txtEmail.getText())){
            Toast.makeText(this,R.string.errCorreoValido,Toast.LENGTH_SHORT).show();
            return false;
        }
        if(FieldVerifier.isEmpty(txtClave.getText())){
            Toast.makeText(this,R.string.errIngreseClave,Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void iniciarSesion() {
    }

    @Override
    public void onClick(View view) {
        if(view.equals(btnLogin)) {
            if (isValidData()) {
                iniciarSesion();
            }
        }
    }
}
