package com.inec.ui_notificacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.inec.adapter.AdapterMisRutas;
import com.inec.adapter.AdapterNotificaciones;
import com.inec.server.ws.gestionUsuario.model.Notificacion;
import com.inec.ui_posicion_actual.UiPosicionActualImpl;

import inec.com.R;

import static com.inec.uihomeapp.UiHomeApp.updateDatosPosicion;

/**
 * Created by root on 11/11/16.
 */

public class UiNotificacion extends AppCompatActivity implements View.OnClickListener,InterNotificacion,SwipeRefreshLayout.OnRefreshListener {
    private Toolbar toolbar;
    public static ListView uimr_layout_content;
    public static AdapterNotificaciones adapterNotificaciones;
    public static SwipeRefreshLayout objSwipeRefreshLayout;
    public static LinearLayout uimr_layout_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_panel_notificacion);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        uimr_layout_content = (ListView) findViewById(R.id.uipn_layout_content);
        adapterNotificaciones = new AdapterNotificaciones(this);
        uimr_layout_content.setAdapter(adapterNotificaciones);
        objSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.uipn_swipe_refresh_layout);
        uimr_layout_click = (LinearLayout) findViewById(R.id.uimr_layout_click);
        objSwipeRefreshLayout.setOnRefreshListener(this);
        initListener();
    }

    @Override
    public void loadNotificacion() {

    }


    @Override
    protected void onResume() {
        super.onResume();
        loadNotificacion();
    }

    @Override
    public void initListener() {
        uimr_layout_click.setOnClickListener(this);
        updateDatosPosicion();
        uimr_layout_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Notificacion beanNotificacion= (Notificacion) parent.getItemAtPosition(position);
                estadoNotificacion(beanNotificacion.getCodeNotificacion());
            }
        });
    }

    @Override
    public void estadoNotificacion(String codeNotificacion) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uimr_layout_click:
                finish();
                onBackPressed();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {

    }
}
