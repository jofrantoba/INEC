package com.inec.view.admin.uiEnviarCorreo;

public interface InterUiEnviarCorreo {
    void showSelectOptionZona();
    void buscarZona();
    void loadZona();
    void loadFiscalizador();
    void loadBusquedaFiscalizador(String patron, String codeZona);
    void insertarDestinatarios();
}
