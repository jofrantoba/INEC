package com.inec.ui_cambiar_password;

/**
 * Created by root on 28/10/16.
 */

public interface InterCambiarPassword {

    boolean isValidData();
    void initListener();
    void goCambiarPassword(
            java.lang.String apClaveUsuario,
            java.lang.String apConfirmarClave,
            java.lang.String apDniUsuario,
            java.lang.String apNuevaClave);
    void clearData();
    void goIniciarSesion();
}
