package com.inec.ui_listar_incidencia;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.location.LocationServices;
import com.inec.adapter.AdapterListarIncidencia;
import com.inec.server.ws.gestionIncidencia.model.Incidencia;
import com.inec.server.ws.gestionMantenimiento.model.PartidoPolitico;
import com.inec.ui_reporte.UiReporte;
import com.inec.ui_show_galeria.UiShowGaleria;
import com.inec.ui_show_galeria.UiShowGaleriaImpl;
import com.inec.uicapturarfoto.UiCapturarFotoImpl;
import com.inec.uihomeapp.UiHomeApp;
import com.inec.utils.AppController;
import com.inec.utils.ExpandAndCollapseViewUtil;
import com.inec.utils.FeedImageView;
import com.inec.utils.FeedImageViewGallery;
import com.inec.utils.PreferenciasSession;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import in.srain.cube.views.GridViewWithHeaderAndFooter;
import inec.com.R;

import static com.inec.uihomeapp.UiHomeApp.listIncidencia;
import static com.inec.uihomeapp.UiHomeAppImpl.asyncTaskFused;
import static com.inec.uihomeapp.UiHomeAppImpl.asyncTaskService;
import static com.inec.uiiniciarsesion.UiIniciarSesion.mPrefSesion;

/**
 * Created by root on 21/10/16.
 */

public class UiListarIncidencia extends Activity implements InterListarIncidencia, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, AdapterView.OnItemSelectedListener {
    public static SwipeRefreshLayout objSwipeRefreshLayout;
    public static AdapterListarIncidencia adapterListarIncidencia;
    public static GridViewWithHeaderAndFooter gridView;
    public static LinearLayout layout;
    public static ImageView btnRight;
    public static ImageView btnLeft;
    public static int posicion;
    public static String urlFoto;
    public static List<String> listUrlFoto;
    public static FeedImageViewGallery imgFotografia;
    public static Incidencia beanIncidencia;
    public static Button btnOpenIncidencia;
    public static TextView txtDescripcionGaleria;
    public static TextView txtDisplayInfoTipI;
    public static TextView txtDisplayInfoDireccion;
    public static TextView txtDisplayInfoDescripcion;
    public static TextView txtDisplayInfoCoordenadas;
    public static TextView txtDisplayInfoFecha;
    public static TextView txtCantidadGaleria;
    public static Button btnToggleMore;
    public static ViewGroup layoutToggleGroup;
    public static Boolean openGaleria=false;
    public static Boolean closeEditarIncidencia=false;
    public static Activity uiListarActivity;

    public static boolean activoGps = false;
    public int CODE_ACTIVE_GEO = 1000;
    private static final int DURATION = 250;
    private boolean valueGeo=false;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    public static LinearLayout uili_layout_click;


    public interface CurrentView {
        int OPTIONS_LAYOUT = 1;
        int READ_LAYOUT = 2;
    }

    /**
     * FrameLayout child views. We will manage our UI to one layout
     * Hide/Show these views as per requirement
     */
    public static LinearLayout optionsLayout;
    public static LinearLayout readLayout;
    public static int currentView;


    MenuItem closeOption;

    private static int currentPage = 0;

   static  ImageView pdfView;
    static Button next;
    static Button previous;

    private  static  PdfRenderer.Page mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_listar_incidencia);
        gridView = (GridViewWithHeaderAndFooter) findViewById(R.id.gridview);
        adapterListarIncidencia = new AdapterListarIncidencia(this);
        gridView.setAdapter(adapterListarIncidencia);
        objSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        objSwipeRefreshLayout.setOnRefreshListener(this);
        layout = (LinearLayout) findViewById(R.id.layout_click);
        uili_layout_click = (LinearLayout) findViewById(R.id.uili_layout_click);
        imgFotografia = (FeedImageViewGallery) findViewById(R.id.img_galeria);
        btnRight = (ImageView) findViewById(R.id.btn_rigth);
        btnLeft = (ImageView) findViewById(R.id.btn_left);
        btnOpenIncidencia = (Button) findViewById(R.id.ui_open_incidencia);
        txtDescripcionGaleria = (TextView) findViewById(R.id.ui_descripcion_galeria);
        txtCantidadGaleria = (TextView) findViewById(R.id.ui_cantidad_galeria);
        btnToggleMore = (Button) findViewById(R.id.ui_mas);
        layoutToggleGroup = (ViewGroup) findViewById(R.id.ui_content_toggle);
        txtDisplayInfoTipI = (TextView) findViewById(R.id.ui_display_info_tipoI);
        txtDisplayInfoDireccion = (TextView) findViewById(R.id.ui_display_info_direccion);
        txtDisplayInfoDescripcion = (TextView) findViewById(R.id.ui_display_info_descripcion);
        txtDisplayInfoCoordenadas = (TextView) findViewById(R.id.ui_display_info_coordenadas);
        txtDisplayInfoFecha = (TextView) findViewById(R.id.ui_display_info_fecha);
        optionsLayout = (LinearLayout) findViewById(R.id.content);
        readLayout = (LinearLayout) findViewById(R.id.read_layout);
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(this);
        previous = (Button) findViewById(R.id.previous);
        previous.setOnClickListener(this);
        pdfView = (ImageView) findViewById(R.id.pdfView);
        currentView = CurrentView.OPTIONS_LAYOUT;
        initListener();
        uiListarActivity=this;
    }

    @Override
    public void onRefresh() {
        loadIncidencia();
    }

    @Override
    public void loadIncidencia() {

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
            }else{
                valueGeo=false;
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
    public void initListener() {
        btnRight.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        gridView.setOnItemSelectedListener(this);
        btnOpenIncidencia.setOnClickListener(this);
        uili_layout_click.setOnClickListener(this);
        imgFotografia.setOnClickListener(this);
    }

    @Override
    public void galeriaDefecto() {
        if (UiHomeApp.listIncidencia != null) {
            if (!UiHomeApp.listIncidencia.isEmpty()) {
                beanIncidencia = UiHomeApp.listIncidencia.get(0);
                posicion = 0;
                listUrlFoto = beanIncidencia.getListUrlFoto();
                txtCantidadGaleria.setText("(" + (posicion + 1) + "/" + listUrlFoto.size() + ")");
                txtDescripcionGaleria.setText(beanIncidencia.getNombrePartidoPolitico() + ": " + new SimpleDateFormat().format(beanIncidencia.getVersion()));
                txtDisplayInfoTipI.setText(beanIncidencia.getCodeTipoIncidencia());
                txtDisplayInfoDireccion.setText(beanIncidencia.getDireccion());
                txtDisplayInfoDescripcion.setText(beanIncidencia.getDescripcion());
                txtDisplayInfoCoordenadas.setText(beanIncidencia.getListLatitud().get(posicion)+","+beanIncidencia.getListLongitud().get(posicion));
                txtDisplayInfoFecha.setText(new SimpleDateFormat().format(beanIncidencia.getVersion()));
                cargarGaleria();
                btnLeft.setVisibility(View.GONE);
                if (listUrlFoto.size() == 1) {
                    btnRight.setVisibility(View.GONE);
                } else {
                    btnRight.setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(this, R.string.msg_not_incidencia, Toast.LENGTH_LONG).show();
                if(ActivarGPS()){
                    goCaptureFoto();
                }
            }
        } else {
            Toast.makeText(this, R.string.msg_not_incidencia, Toast.LENGTH_LONG).show();
            if(ActivarGPS()){
                goCaptureFoto();
            }
        }
    }

    @Override
    public void galeriaByIncidencia() {

    }

    @Override
    public void loadServiceGeo() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left:
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
                txtCantidadGaleria.setText("(" + (posicion + 1) + "/" + listUrlFoto.size() + ")");
                cargarGaleria();
                break;
            case R.id.btn_rigth:
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
                txtCantidadGaleria.setText("(" + (posicion + 1) + "/" + listUrlFoto.size() + ")");
                cargarGaleria();
                break;
            case R.id.ui_open_incidencia:
                if(ActivarGPS()){
                    goCaptureFoto();
                }
                break;
            case R.id.uili_layout_click:
                finish();
                onBackPressed();
                break;
            case R.id.img_galeria:
                goOpenGaleria();
                break;
            case R.id.next:
                currentPage++;
                showPage(currentPage);
                break;
            case R.id.previous:
                currentPage--;
                showPage(currentPage);
                break;
            default:
                break;

        }
    }


    @Override
    public void goOpenGaleria() {
        openGaleria=true;
        Intent intent = new Intent(this, UiShowGaleriaImpl.class);
        intent.putExtra("indexlista",posicion);
        startActivity(intent);
    }

    @Override
    public void goCaptureFoto() {
        UiCapturarFotoImpl.ubicacionObtenida = false;
        UiCapturarFotoImpl.envioRealizado = false;
        UiCapturarFotoImpl.fotoTomada = false;
        UiCapturarFotoImpl.fotoVisible = false;
        UiHomeApp.updateDatosPosicion();
        Intent intent = new Intent(this, UiCapturarFotoImpl.class);
        startActivity(intent);
    }

    @Override
    public void cargarGaleria() {
        if (posicion > -1) {
            urlFoto = listUrlFoto.get(posicion);
            if (!urlFoto.equals("")) {
                imgFotografia.setImageUrl(urlFoto, imageLoader);
                txtDisplayInfoCoordenadas.setText(beanIncidencia.getListLatitud().get(posicion)+","+beanIncidencia.getListLongitud().get(posicion));
                imgFotografia.setVisibility(View.VISIBLE);
            } else {
                imgFotografia.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.equals(gridView)) {
            beanIncidencia=(Incidencia) parent.getSelectedItem();
            listUrlFoto=beanIncidencia.getListUrlFoto();
            Toast.makeText(this,"",Toast.LENGTH_LONG);
            cargarGaleria();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void toggleDetails(View view) {
        if (layoutToggleGroup.getVisibility() == View.GONE) {
            ExpandAndCollapseViewUtil.expand(layoutToggleGroup, DURATION);
            btnToggleMore.setBackgroundResource(R.drawable.ic_mas);
            rotate(-180.0f);
        } else {
            ExpandAndCollapseViewUtil.collapse(layoutToggleGroup, DURATION);
            btnToggleMore.setBackgroundResource(R.drawable.ic_menos);
            rotate(180.0f);
        }
    }

    private void rotate(float angle) {
        Animation animation = new RotateAnimation(0.0f, angle, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(DURATION);
        btnToggleMore.startAnimation(animation);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(openGaleria==false){
            if(closeEditarIncidencia==false){
                loadIncidencia();
            }else{
                closeEditarIncidencia=false;
            }

        }else{
            openGaleria=false;
        }
        if(mPrefSesion==null){
            mPrefSesion=getSharedPreferences(PreferenciasSession.NAME_PREFERENCE, Context.MODE_PRIVATE);
        }
    }


    public  void uirLayoutBackClickClose(View v) {
        int id = v.getId();
        if (id == R.id.uir_layout_click2) {
            if (currentView == CurrentView.OPTIONS_LAYOUT) {
                updateView(CurrentView.READ_LAYOUT);
            } else if (currentView == CurrentView.READ_LAYOUT) {
                updateView(CurrentView.OPTIONS_LAYOUT);
            }
        }
    }

    private static void showPage(int index) {
//        if (mPdfRenderer == null || mPdfRenderer.getPageCount() <= index
//                || index < 0) {
//
//            return;
//        }
//        try {
//            if (mCurrentPage != null) {
//                mCurrentPage.close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        next.setVisibility(View.VISIBLE);
//        previous.setVisibility(View.VISIBLE);
//        if(mPdfRenderer.getPageCount() <= index+1){
//            next.setVisibility(View.INVISIBLE);
//        }
//        if(index ==0){
//            previous.setVisibility(View.INVISIBLE);
//        }
//
//        mCurrentPage = mPdfRenderer.openPage(index);
//        Bitmap bitmap = Bitmap.createBitmap(mCurrentPage.getWidth(),
//                mCurrentPage.getHeight(), Bitmap.Config.ARGB_8888);
//        mCurrentPage.render(bitmap, null, null,
//                PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
//        pdfView.setImageBitmap(bitmap);
    }


    public static void updateView(int updateView) {
        switch (updateView) {
            case CurrentView.OPTIONS_LAYOUT:
                currentView = CurrentView.OPTIONS_LAYOUT;
                optionsLayout.setVisibility(View.VISIBLE);
                readLayout.setVisibility(View.INVISIBLE);
                break;
            case CurrentView.READ_LAYOUT:
                currentView = CurrentView.READ_LAYOUT;
                showPage(currentPage);
                optionsLayout.setVisibility(View.INVISIBLE);
                readLayout.setVisibility(View.VISIBLE);
                break;
        }
    }


    public static ParcelFileDescriptor mFileDescriptor;
    public static PdfRenderer mPdfRenderer;

}
