package com.inec.uiregistrarincidencia;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.inec.adapter.AdapterPartido;
import com.inec.server.ws.gestionMantenimiento.model.PartidoPolitico;
import com.inec.uicapturarfoto.UiCapturarFoto;
import com.inec.uiiniciarsesion.UiIniciarSesion;
import com.inec.utils.FieldVerifier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import inec.com.R;

import static com.inec.uicapturarfoto.UiCapturarFoto.contador;
import static com.inec.uicapturarfoto.UiCapturarFoto.fotoTomada;
import static com.inec.uicapturarfoto.UiCapturarFoto.fotoVisible;
import static com.inec.uicapturarfoto.UiCapturarFoto.listBitMap;
import static com.inec.uicapturarfoto.UiCapturarFoto.listFile;
import static com.inec.uicapturarfoto.UiCapturarFoto.listUrlFlikr;
import static com.inec.uihomeapp.UiHomeApp.listPartidoPolitico;
import static com.inec.uiiniciarsesion.UiIniciarSesion.geoPunto;
import static com.inec.uiiniciarsesion.UiIniciarSesion.mPrefSesion;

/**
 * Created by root on 09/10/16.
 */

public class UiRegistrarIncidencia extends Activity implements InterRegistrarIncidencia,AdapterView.OnItemSelectedListener,View.OnClickListener {
    public ImageView vistaPrevia;
    public Bitmap bm=null;
    public static AdapterPartido adapterPartidoPolitico;
    public static Spinner combobox_partido;
    public static PartidoPolitico beanPartido;
    public static String txtRadio="PUBLICIDAD";
    public static TextView txtDepartamento;
    public static TextView txtProvincia;
    public static TextView txtDistrito;
    public static EditText txtDireccion;
    public static EditText txtDescripcion;
    public static Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_registrar_incidencia);
        initComponents();
    }

    @Override
    public void initComponents(){
        vistaPrevia = (ImageView) findViewById(R.id.vista_previa);
        combobox_partido = (Spinner) findViewById(R.id.combobox_partido);
        adapterPartidoPolitico=new AdapterPartido(this.getApplicationContext());
        combobox_partido.setAdapter(adapterPartidoPolitico);
        combobox_partido.setOnItemSelectedListener(this);
        txtDepartamento=(TextView)findViewById(R.id.cont_departamento);
        txtDireccion=(EditText)findViewById(R.id.txt_direccion);
        txtDescripcion=(EditText)findViewById(R.id.txt_descripcion);
        txtProvincia=(TextView)findViewById(R.id.cont_provincia);
        txtDistrito=(TextView)findViewById(R.id.cont_distrito);
        btnGuardar=(Button)findViewById(R.id.btn_registrar_incidencia);
        btnGuardar.setOnClickListener(this);
        loadNacionalidad();
        if(geoPunto!=null){
            if(geoPunto.getNombreRegion()!=null){
                txtDepartamento.setText(geoPunto.getNombreRegion());
            }
            if(geoPunto.getNombreProvincia()!=null){
                txtProvincia.setText(geoPunto.getNombreProvincia());
            }
            if(geoPunto.getNombreLocalidad()!=null){
                txtDistrito.setText(geoPunto.getNombreLocalidad());
            }
        }
    }

    @Override
    public void goRegistrarIncidencia(String riCodePartidoPolitico, String riDepartamento, String riDescripcion, String riDireccion, String riDistrito, String riDniUsuario, Long riFechaCelular, Double riLatitud, List<String> riListUrlFotografia, Double riLongitud, String riProvincia, String riTipoIncidencia) {

    }


    @Override
    public void loadNacionalidad() {
        if(listPartidoPolitico!=null) {
            adapterPartidoPolitico.setData(listPartidoPolitico);
            adapterPartidoPolitico.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(listFile!=null){
            if(!listFile.isEmpty()){
                verImagen(listFile.get(contador-1));
            }
        }
    }

    public void verImagen(File image){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        ExifInterface exif=null;
        long length = image.length();
        length = length/1024;
        if(length>2000) {
            options.inSampleSize = 6;
        }else if(length>1000) {
            options.inSampleSize = 4;
        }else if(length>700) {
            options.inSampleSize = 3;
        }else if(length>250) {
            options.inSampleSize = 2;
        }
        bm=BitmapFactory.decodeFile(image.getPath(),options);
        int orientation=0;
        int angle=0;

        try {
            exif = new ExifInterface(image.getPath());
            orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch(orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                angle= 90;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                angle= 180;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                angle=270;
                break;
        }
        Matrix mat = new Matrix();
        mat.postRotate(angle);
        Display display = getWindowManager().getDefaultDisplay();
        bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), mat, true);
        bm=Bitmap.createScaledBitmap(bm,display.getWidth(),vistaPrevia.getMaxHeight(),true);
        vistaPrevia.setImageBitmap(bm);
    }


    @Override
    public boolean isValidData() {
        if(FieldVerifier.isEmpty(txtDescripcion.getText())){
            Toast.makeText(this.getApplicationContext(),"Ingrese Descripcion",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(FieldVerifier.isEmpty(txtDireccion.getText())){
            Toast.makeText(this.getApplicationContext(),"Ingrese Direccion",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_registrar_incidencia){
            if(isValidData()){
                String paramCodePartidoPolitico= beanPartido.getCodePartidoPolitico();
                String paramDniUsuario=mPrefSesion.getString("usuariosession","");
                String paramDepartamento=geoPunto.getNombreRegion();
                String paramDescripcion=txtDescripcion.getText().toString();
                String paramDireccion=txtDireccion.getText().toString();
                String paramDistrito= geoPunto.getNombreLocalidad();
                Long paramFechaCelular=new java.util.Date().getTime();
                Double paramLatitud= geoPunto.getLatitude();
                Double paramLongitud=geoPunto.getLongitude();
                String paramProvincia= geoPunto.getNombreProvincia();
                String paramCodeTipoIncidencia=txtRadio;
                goRegistrarIncidencia(paramCodePartidoPolitico,
                        paramDepartamento,
                        paramDescripcion,
                        paramDireccion,
                        paramDistrito,
                        paramDniUsuario,
                        paramFechaCelular,
                        paramLatitud,
                        null,
                        paramLongitud,
                        paramProvincia,
                        paramCodeTipoIncidencia);
            }

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
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_propaganda:
                if (checked) {
                   txtRadio="PROPAGANDA";
                }
                break;
            case R.id.radio_publicidad:
                if (checked) {
                    txtRadio="PUBLICIDAD";
                }
                break;
            case R.id.radio_otros:
                if (checked) {
                    txtRadio="OTROS";
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        listUrlFlikr=new ArrayList<>();
        listBitMap= new ArrayList<>();
        listFile=new ArrayList<>();
        contador=0;
        fotoTomada=false;
        fotoVisible=false;
        UiCapturarFoto.actCapture.finish();
        finish();
    }
}


