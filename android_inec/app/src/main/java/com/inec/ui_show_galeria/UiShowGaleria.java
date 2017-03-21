package com.inec.ui_show_galeria;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.inec.adapter.AdapterPartido;
import com.inec.server.ws.gestionMantenimiento.model.PartidoPolitico;
import com.inec.uihomeapp.UiHomeApp;
import com.inec.utils.AppController;
import com.inec.utils.FeedImageCircle;
import com.inec.utils.FeedImageViewGallery;

import java.text.SimpleDateFormat;

import inec.com.R;

import static com.inec.ui_listar_incidencia.UiListarIncidencia.listUrlFoto;
import static com.inec.ui_listar_incidencia.UiListarIncidencia.openGaleria;
import static com.inec.uihomeapp.UiHomeApp.listPartidoPolitico;

/**
 * Created by root on 31/10/16.
 */

public class UiShowGaleria  extends Activity implements InterShowGaleria,View.OnClickListener{

    public static NetworkImageView uisg_galeria;
    public static ImageView btnRight;
    public static ImageView btnLeft;
    public static String urlFoto;
    public static Integer posicion;
    public static Button uisg_close_galeria;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_show_galeria);
        initComponents();
        initListener();
    }

    @Override
    public void initComponents(){
        uisg_galeria=(NetworkImageView)findViewById(R.id.uisg_galeria);
        btnRight = (ImageView) findViewById(R.id.uisg_flecha_rigth);
        btnLeft = (ImageView) findViewById(R.id.uisg_flecha_left);
        uisg_close_galeria = (Button) findViewById(R.id.uisg_close_galeria);
        posicion=(getIntent().getExtras().getInt("indexlista"));
        galeriaDefecto(posicion);
    }

    @Override
    public void initListener() {
        btnRight.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        uisg_close_galeria.setOnClickListener(this);
    }

    @Override
    public void galeriaDefecto(Integer posicion) {
        cargarGaleria();
        if (posicion == 0) {
            btnLeft.setVisibility(View.GONE);
        } else {
            btnLeft.setVisibility(View.VISIBLE);
        }
        if (posicion == (listUrlFoto.size() - 1)) {
            btnRight.setVisibility(View.GONE);
        } else {
            btnRight.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uisg_flecha_left:
                if (posicion > 0) {
                    posicion = posicion - 1;
                    if (posicion == 0) {
                        btnLeft.setVisibility(View.GONE);
                    } else {
                        btnLeft.setVisibility(View.VISIBLE);
                    }

                    if (posicion == (listUrlFoto.size() - 1)) {
                        btnRight.setVisibility(View.GONE);
                    } else {
                        btnRight.setVisibility(View.VISIBLE);
                    }
                }
                cargarGaleria();
                break;
            case R.id.uisg_close_galeria:
                openGaleria=true;
                onBackPressed();
                finish();
                break;
            case R.id.uisg_flecha_rigth:
                if (posicion < listUrlFoto.size() - 1) {
                    posicion = posicion + 1;
                    if (posicion == (listUrlFoto.size() - 1)) {
                        btnRight.setVisibility(View.GONE);
                    } else {
                        btnRight.setVisibility(View.VISIBLE);
                    }
                    if (posicion == 0) {
                        btnLeft.setVisibility(View.GONE);
                    } else {
                        btnLeft.setVisibility(View.VISIBLE);
                    }
                }
                cargarGaleria();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        openGaleria=true;
        finish();
    }

    @Override
    public void cargarGaleria() {
        if (posicion > -1) {
            urlFoto = listUrlFoto.get(posicion);
            if (!urlFoto.equals("")) {
                uisg_galeria.setImageUrl(urlFoto, imageLoader);
                uisg_galeria.setVisibility(View.VISIBLE);
            } else {
                uisg_galeria.setVisibility(View.GONE);
            }
        }
    }
}
