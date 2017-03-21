package com.inec.ui_reporte;

import android.view.View;

import com.inec.server.ws.gestionUsuario.model.PosicionFiscalizador;

import java.util.List;

/**
 * Created by root on 01/11/16.
 */

public interface InterUiReporte {
    void showDatePickerDialog(View v);
    void uirLayoutBackClick(View v);
    void downloadReport(View v);
    void generarReporte(String fecha, List<PosicionFiscalizador>listaPosicionFiscalizador);
    void loadGenerarReporte(String fechaFormat);
}
