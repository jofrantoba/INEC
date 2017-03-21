package com.inec.uiregistrarincidencia;

import android.view.View;

/**
 * Created by root on 10/10/16.
 */

public interface InterRegistrarIncidencia {

    void loadNacionalidad();
    void onRadioButtonClicked(View view);
    void initComponents();
    boolean isValidData();
    void goRegistrarIncidencia(
            java.lang.String riCodePartidoPolitico, java.lang.String riDepartamento, java.lang.String riDescripcion, java.lang.String riDireccion, java.lang.String riDistrito, java.lang.String riDniUsuario, java.lang.Long riFechaCelular, java.lang.Double riLatitud, java.util.List<java.lang.String> riListUrlFotografia, java.lang.Double riLongitud, java.lang.String riProvincia, java.lang.String riTipoIncidencia
        );
}
