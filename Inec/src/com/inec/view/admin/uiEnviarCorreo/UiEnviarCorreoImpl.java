package com.inec.view.admin.uiEnviarCorreo;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.inec.client.service.ServiceGestionMantenimiento;
import com.inec.client.service.ServiceGestionMantenimientoAsync;
import com.inec.server.model.bean.UsuarioFiscalizador;

import gwt.material.design.client.ui.MaterialToast;

public class UiEnviarCorreoImpl extends UiEnviarCorreo {

	
	public UiEnviarCorreoImpl() {	
	}

	@Override
	public void loadFiscalizador() {
		SERVICE.listUsuarioFiscalizador(new AsyncCallback<List<UsuarioFiscalizador>>() {
			@Override
			public void onFailure(Throwable caught) {
				MaterialToast.fireToast(caught.getMessage());
			}

			@Override
			public void onSuccess(List<UsuarioFiscalizador> result) {							
				listUsuarioFiscalizador=result;			
			}
		});
	}
	
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	
}
