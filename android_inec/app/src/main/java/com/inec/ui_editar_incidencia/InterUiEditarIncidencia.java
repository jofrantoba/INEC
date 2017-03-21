package com.inec.ui_editar_incidencia;

import android.view.View;

/**
 * Created by root on 26/10/16.
 */

public interface InterUiEditarIncidencia {
    void initComponents();
    void onRadioButtonClicked(View view);
    void callEditarIncidencia(java.lang.String aiCodeIncidencia, java.lang.String aiCodePartidoPolitico, java.lang.String aiCodeUsuario, java.lang.String aiDescripcion, java.lang.String aiDireccion, java.lang.String aiTipoIncidencia);
}
