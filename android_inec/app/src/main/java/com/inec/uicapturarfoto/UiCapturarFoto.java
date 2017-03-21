package com.inec.uicapturarfoto;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.inec.ui_listar_incidencia.UiListarIncidencia;
import com.inec.uihomeapp.UiHomeApp;
import com.inec.uiiniciarsesion.UiIniciarSesion;
import com.inec.uiregistrarincidencia.UiRegistrarIncidencia;
import com.inec.uiregistrarincidencia.UiRegistrarIncidenciaImpl;
import com.inec.utils.PreferenciasSession;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import inec.com.R;

public class UiCapturarFoto extends Activity  implements InterCapturarFoto, View.OnClickListener  {
    public static final int CAMERA_REQUEST = 1888;
    public ImageButton btnAceptar;
    public ImageButton btnAtras;
    public ImageButton btnTomarFoto;
    public ImageView imgFoto;
    public static boolean fotoTomada=false;
    public static boolean fotoVisible=false;
    public static boolean ubicacionObtenida=false;
    public static boolean envioRealizado=false;
    public SharedPreferences mPrefSesion;
    public Bitmap bm=null;
    public static String resource;
    public static List<String> listResources= new ArrayList<>();
    public static File resourcesFile=null;
    public static List<File> listFile=new ArrayList<>();
    public static List<String> listUrlFlikr=new ArrayList<>();
    public static List<Double> listLatitud=new ArrayList<>();
    public static List<Double> listLongitud=new ArrayList<>();
    public static Integer contador=0;
    public static List<Bitmap> listBitMap= new ArrayList<>();
    public static List<Object[]> listBitMapCoordenadas= new ArrayList<>();
    public static Activity actCapture;
    int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private static final int REQUEST_SELFIE = 123;
    private static final int REQUEST_ALBUM = 124;
    public static final int PICTURE_REQUEST = 1889;
    private static String[] PERMISSIONS_SELFIE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_capturar_foto);
        initComponents();
        initListener();
        actCapture=this;
    }

    @Override
    public void initComponents(){
        mPrefSesion = getSharedPreferences(PreferenciasSession.NAME_PREFERENCE, Context.MODE_PRIVATE);
        btnAceptar = (ImageButton) findViewById(R.id.btnAceptar);
        btnAtras = (ImageButton) findViewById(R.id.btnQuitar);
        btnTomarFoto = (ImageButton) findViewById(R.id.btnAgregarFoto);
        imgFoto = (ImageView) findViewById(R.id.img_fotografia);
    }

    public void initComponentsGeo(){
    }

    @Override
    public void initListener(){
        btnAceptar.setOnClickListener(this);
        btnAtras.setOnClickListener(this);
        btnTomarFoto.setOnClickListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (!fotoTomada) {
            if (!fotoVisible) {
//                TomarFoto();
                permissionCamera();
            } else {
                VerImagen();
            }
        } else {
            VerImagen();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAceptar:
                envioRealizado=true;
                addImagen();
                fotoTomada=false;
                fotoVisible=true;
                Intent intent = new Intent(this, UiRegistrarIncidenciaImpl.class);
                startActivity(intent);
                return;
            case R.id.btnAgregarFoto:
                TomarFoto();
                UiHomeApp.updateDatosPosicion();
                addImagen();
                return;
            case R.id.btnQuitar:
                TomarFoto();
                return;
            default:
                return;
        }
    }

    @Override
    public void TomarFoto(){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        resource="inec_00"+contador+".jpg";
        File image = getFileImage();
        Uri uriSavedImage = Uri.fromFile(image);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    @Override
    public File getFileImage(){
        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "InecImages");
        if (!imagesFolder.exists()) imagesFolder.mkdirs();
        File image = new File(imagesFolder, resource);
        resourcesFile=image;
        return image;
    }

    public void permissionCamera(){
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                || (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
            requestCameraAndExternalPermission();
        } else {
            try {

                TomarFoto();
            }catch(Exception ex) {
                Toast.makeText(getApplicationContext(), "Habilite permisos manualmente", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void requestCameraAndExternalPermission() {
        ActivityCompat.requestPermissions(this, PERMISSIONS_SELFIE, REQUEST_SELFIE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {
        Toast.makeText(getApplicationContext(), "Habilite todos los permisos", Toast.LENGTH_SHORT).show();
        if (requestCode == REQUEST_SELFIE) {
            if (grantResults.length == 3 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Permisos otorgados", Toast.LENGTH_SHORT).show();
                TomarFoto();
            } else {
                Toast.makeText(getApplicationContext(), "Habilite todos los permisos", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_ALBUM) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
//                llamarGaleria();
            } else {
                Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @Override
    public void VerImagen(){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        ExifInterface exif=null;
        File image=getFileImage();
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
        bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), mat, true);
        imgFoto.setImageBitmap(bm);
        fotoVisible=true;
    }

    @Override
    protected void onDestroy() {
        if(bm!=null) {
            bm.recycle();
            bm=null;
        }
        super.onDestroy();
    }

    @Override
    public void capturaFoto() {

    }

    @Override
    public void addImagen(){
        listFile.add(resourcesFile);
        listResources.add(resource);
        Object[] myObject= new Object[3];
        myObject[0]=bm;
        myObject[1]=UiIniciarSesion.geoPunto.getLatitude();
        myObject[2]=UiIniciarSesion.geoPunto.getLongitude();
        listBitMapCoordenadas.add(myObject);
        listBitMap.add(bm);
        contador++;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        listUrlFlikr=new ArrayList<>();
        listLatitud=new ArrayList<>();
        listLongitud=new ArrayList<>();
        listBitMap= new ArrayList<>();
        listFile=new ArrayList<>();
        contador=0;
        fotoTomada=false;
        fotoVisible=false;
        if(UiHomeApp.listIncidencia ==null || UiHomeApp.listIncidencia.isEmpty()){
            UiListarIncidencia.uiListarActivity.finish();
            finish();
        }else{
            finish();
        }

    }
}
