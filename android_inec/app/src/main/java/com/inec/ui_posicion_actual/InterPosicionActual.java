package com.inec.ui_posicion_actual;

/**
 * Created by root on 29/10/16.
 */

public interface InterPosicionActual {

    void initComponents();
    void loadData();
    void cargarGoogleMaps();
    void initListener();
    void guardarPosicion(java.lang.String gpCodeUsuario, java.lang.String gpDepartamento, java.lang.String gpDistrito, java.lang.Double gpLatitud, java.lang.Double gpLongitud, java.lang.String gpProvincia);
}
