package com.inec.view.admin.uimantusuariofiscalizador;

import com.inec.server.model.bean.UsuarioFiscalizador;

public interface InterMantUsuarioFiscalizador {
	void loadFields();
    void cleanForm();
    void goToUiUsuarioFiscalizador();
    boolean isValidData();
    void updateDataGrid(UsuarioFiscalizador bean);
    void clearError();
    boolean isExistInGrid();    
    void showSelectOptionZona();
    void buscarZona();
    void loadZona();
    void processCambiarEstado();
}
