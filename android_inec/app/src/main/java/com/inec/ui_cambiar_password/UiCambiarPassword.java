package com.inec.ui_cambiar_password;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.inec.adapter.AdapterMisRutas;
import com.inec.uihomeapp.UiHomeApp;
import com.inec.uiiniciarsesion.UiIniciarSesionImpl;
import com.inec.utils.FieldVerifier;
import com.inec.utils.PreferenciasSession;

import inec.com.R;

import static com.inec.uihomeapp.UiHomeApp.listIncidencia;
import static com.inec.uihomeapp.UiHomeApp.listPartidoPolitico;
import static com.inec.uiiniciarsesion.UiIniciarSesion.geoPunto;
import static com.inec.uiiniciarsesion.UiIniciarSesion.mPrefSesion;

/**
 * Created by root on 28/10/16.
 */

public class UiCambiarPassword extends Activity implements InterCambiarPassword,View.OnClickListener {
    public static EditText uicp_txt_clave;
    public static EditText uicp_txt_nueva_clave;
    public static EditText uicp_txt_confirmar_clave;
    public static Button uicp_button_cambiar_clave;
    public static LinearLayout uicp_layout_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_cambiar_password);
        uicp_txt_clave=(EditText)findViewById(R.id.uicp_txt_clave);
        uicp_txt_nueva_clave=(EditText)findViewById(R.id.uicp_txt_nueva_clave);
        uicp_txt_confirmar_clave=(EditText)findViewById(R.id.uicp_txt_confirmar_clave);
        uicp_button_cambiar_clave=(Button)findViewById(R.id.uicp_button_cambiar_clave);
        uicp_layout_click=(LinearLayout) findViewById(R.id.uicp_layout_click);
        initListener();
    }

    @Override
    public boolean isValidData() {
        if(FieldVerifier.isEmpty(uicp_txt_clave.getText())){
            Toast.makeText(this.getApplicationContext(),"Ingrese Clave",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(FieldVerifier.isEmpty(uicp_txt_nueva_clave.getText())){
            Toast.makeText(this.getApplicationContext(),"Ingrese Nueva Clave",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(FieldVerifier.isEmpty(uicp_txt_confirmar_clave.getText())){
            Toast.makeText(this.getApplicationContext(),"Ingrese Confirmar Clave",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void initListener() {
        uicp_button_cambiar_clave.setOnClickListener(this);
        uicp_layout_click.setOnClickListener(this);
    }

    @Override
    public void goCambiarPassword(String apClaveUsuario, String apConfirmarClave, String apDniUsuario, String apNuevaClave) {

    }

    @Override
    public void clearData() {
        uicp_button_cambiar_clave.setText("");
        uicp_txt_clave.setText("");
        uicp_txt_confirmar_clave.setText("");
        uicp_txt_nueva_clave.setText("");
    }

    @Override
    public void goIniciarSesion() {
        PreferenciasSession.remove(mPrefSesion);
        listIncidencia=null;
        listPartidoPolitico=null;
        Intent intent = new Intent(getApplicationContext(), UiIniciarSesionImpl.class);
        startActivity(intent);
        UiHomeApp.homeActivity.finish();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uicp_button_cambiar_clave:
                if(isValidData()){
                    String paramClave=uicp_txt_clave.getText().toString();
                    String paramDniUsuario=mPrefSesion.getString("usuariosession","");
                    String paramNuevaClave=uicp_txt_nueva_clave.getText().toString();
                    String paramConfirmarClave=uicp_txt_confirmar_clave.getText().toString();
                    goCambiarPassword(paramClave,paramConfirmarClave,paramDniUsuario,paramNuevaClave);
                }
                break;
            case R.id.uicp_layout_click:
                finish();
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
