package com.inec.uihomeapp;

/**
 * Created by root on 10/10/16.
 */

public interface InterHomeApp {
     void cargarPartidoPolitico();
     void loadServiceGeo();
     void goCaptureFoto();
     void goNotification();
     void goListarIncidencia();
     void goMisRutas();
     void cerrarSession();
     void goCambiarPassword();
     void cerrarSessionTask(String codeSessionFiscalizador);

}
