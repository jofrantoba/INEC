package com.inec.ui_editar_incidencia;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.inec.adapter.AdapterPartido;
import com.inec.server.ws.gestionMantenimiento.model.PartidoPolitico;
import com.inec.utils.PreferenciasSession;

import inec.com.R;

import static com.inec.ui_listar_incidencia.UiListarIncidencia.closeEditarIncidencia;
import static com.inec.uihomeapp.UiHomeApp.listPartidoPolitico;
import static com.inec.uiiniciarsesion.UiIniciarSesion.geoPunto;
import static com.inec.uiiniciarsesion.UiIniciarSesion.mPrefSesion;

/**
 * Created by root on 26/10/16.
 */

public class UiEditarIncidencia extends Activity implements View.OnClickListener,InterUiEditarIncidencia,AdapterView.OnItemSelectedListener {
    public static AdapterPartido adapterPartidoPolitico;
    public static Spinner combobox_partido;
    public static String txtRadio="PUBLICIDAD";
    public static EditText edit_txtDireccion;
    public static EditText edit_txtDescripcion;
    public static Button btnGuardar;
    public static PartidoPolitico beanPartido;
    public static RadioButton btnRadioPublicidad;
    public static RadioButton btnRadioPropaganda;
    public static RadioButton btnRadioOtros;
    public static final String PUBLICIDAD="PUBLICIDAD";
    public static final String PROPAGANDA="PROPAGANDA";
    public static Button uiei_cerrar_editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_editar_incidencia);
        initComponents();
    }

    @Override
    public void initComponents(){
        combobox_partido = (Spinner) findViewById(R.id.edit_combobox_partido);
        adapterPartidoPolitico=new AdapterPartido(this.getApplicationContext());
        combobox_partido.setAdapter(adapterPartidoPolitico);
        combobox_partido.setOnItemSelectedListener(this);
        adapterPartidoPolitico.setData(listPartidoPolitico);
        adapterPartidoPolitico.notifyDataSetChanged();
        edit_txtDescripcion=(EditText)findViewById(R.id.edit_txt_descripcion);
        edit_txtDireccion=(EditText)findViewById(R.id.edit_txt_direccion);
        btnGuardar=(Button)findViewById(R.id.btn_actualizar_incidencia);
        btnGuardar.setOnClickListener(this);
        btnRadioPropaganda=(RadioButton) findViewById(R.id.edit_radio_propaganda);
        btnRadioOtros=(RadioButton) findViewById(R.id.edit_radio_otros);
        btnRadioPublicidad=(RadioButton) findViewById(R.id.edit_radio_publicidad);
        uiei_cerrar_editar=(Button)findViewById(R.id.uiei_cerrar_editar);
        uiei_cerrar_editar.setOnClickListener(this);
        String tipoIncidencia=getIntent().getExtras().getString("bundle_tipoincidencia").toString();
        if(tipoIncidencia.equalsIgnoreCase(PUBLICIDAD)){
            btnRadioPublicidad.setChecked(true);
        }else{
            if(tipoIncidencia.equalsIgnoreCase(PROPAGANDA)){
                btnRadioPropaganda.setChecked(true);
            }else{
                btnRadioOtros.setChecked(true);
            }

        }
        edit_txtDescripcion.setText(getIntent().getExtras().getString("bundle_descripcion").toString());
        edit_txtDireccion.setText(getIntent().getExtras().getString("bundle_direccion").toString());
    }
    @Override
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.edit_radio_propaganda:
                if (checked) {
                    txtRadio="PROPAGANDA";
                }
                break;
            case R.id.edit_radio_publicidad:
                if (checked) {
                    txtRadio="PUBLICIDAD";
                }
                break;
            case R.id.edit_radio_otros:
                if (checked) {
                    txtRadio="OTROS";
                }
                break;
        }
    }

    @Override
    public void callEditarIncidencia(String aiCodeIncidencia, String aiCodePartidoPolitico,
                                     String aiCodeUsuario, String aiDescripcion, String aiDireccion,
                                     String aiTipoIncidencia) {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_actualizar_incidencia:
                String codeIncidencia=getIntent().getExtras().getString("bundle_codeincidencia").toString();
                String codePartidoPolitico=beanPartido.getCodePartidoPolitico();;
                String codeUsuario=mPrefSesion.getString("usuariosession","");
                String descripcion=edit_txtDescripcion.getText().toString();
                String direccion=edit_txtDireccion.getText().toString();
                String tipoIncidencia=txtRadio;
                callEditarIncidencia(codeIncidencia,codePartidoPolitico,codeUsuario,descripcion,direccion,tipoIncidencia);
                break;
            case R.id.uiei_cerrar_editar:
                closeEditarIncidencia=true;
                onBackPressed();
                finish();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.equals(combobox_partido)) {
            beanPartido=(PartidoPolitico)parent.getSelectedItem();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed() {
        closeEditarIncidencia=true;
        finish();
    }
}
