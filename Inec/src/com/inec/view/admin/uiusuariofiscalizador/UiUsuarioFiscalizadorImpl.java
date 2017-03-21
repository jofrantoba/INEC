package com.inec.view.admin.uiusuariofiscalizador;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.inec.client.service.ServiceGestionMantenimiento;
import com.inec.client.service.ServiceGestionMantenimientoAsync;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.view.admin.uimantusuariofiscalizador.UiMantUsuarioFiscalizador;
import com.inec.view.admin.uimantusuariofiscalizador.UiMantUsuarioFiscalizadorImpl;
import com.inec.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiUsuarioFiscalizadorImpl extends UiUsuarioFiscalizador {
	private UiMantUsuarioFiscalizador ui;
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);

	public UiUsuarioFiscalizadorImpl() {
		loadTable();
	}

	@Override
	public void loadTable() {
		SERVICE.listUsuarioFiscalizador(new AsyncCallback<List<UsuarioFiscalizador>>() {

			@Override
			public void onFailure(Throwable caught) {
				
				MaterialToast.fireToast(caught.getMessage());
			}

			@Override
			public void onSuccess(List<UsuarioFiscalizador> result) {
				
				grid.getSelectionModel().clear();
				grid.setData(result);
			}
		});
	}

	@Override
	public void goToUiMantUsuarioFiscalizadorInsertar() {
		
		if (ui == null) {
			ui = new UiMantUsuarioFiscalizadorImpl(this);
			ui.setModo(UiMantenimiento.MODOINSERTAR);
			ui.loadFields();
			ui.show();
		} else {
			ui = new UiMantUsuarioFiscalizadorImpl(this);
			ui.setModo(UiMantenimiento.MODOINSERTAR);
			ui.loadFields();
			ui.show();
		}
	}

	@Override
	public void goToUiMantUsuarioFiscalizadorActualizar() {
		
		UsuarioFiscalizador bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			if (ui == null) {
				ui = new UiMantUsuarioFiscalizadorImpl(this);
				ui.setModo(UiMantenimiento.MODOUPDATE);
				ui.setBean(bean);
				ui.loadFields();
				ui.show();
			} else {
				ui = new UiMantUsuarioFiscalizadorImpl(this);
				ui.setModo(UiMantenimiento.MODOUPDATE);
				ui.setBean(bean);
				ui.loadFields();
				ui.show();
			}
		} else {
			MaterialToast.fireToast("Seleccione una fila del grid");
		}
	}

	@Override
	public void goToUiMantUsuarioFiscalizadorEliminar() {		
		
		UsuarioFiscalizador bean = grid.getSelectionModel().getSelectedObject();
		if (bean != null) {
			if (ui == null) {
				ui = new UiMantUsuarioFiscalizadorImpl(this,true);
				ui.setModo(UiMantenimiento.MODODELETE);
				ui.setBean(bean);
				ui.loadFields();
//				ui.show();
			} else {
				ui = new UiMantUsuarioFiscalizadorImpl(this,true);
				ui.setModo(UiMantenimiento.MODODELETE);
				ui.setBean(bean);
				ui.loadFields();
//				ui.show();
			}
		} else {
			MaterialToast.fireToast("Seleccione una fila del grid");
		}		
	}

	@Override
	public void goToUiMantUsuarioFiscalizadorDetalle() {
		
		
		UsuarioFiscalizador bean = grid.getSelectionModel().getSelectedObject();		
		if (bean != null) {
			if (ui == null) {
				ui = new UiMantUsuarioFiscalizadorImpl(this);
				ui.setModo(UiMantenimiento.MODODETALLE);
				ui.setBean(bean);
				ui.loadFields();
				ui.show();
			} else {
				ui.setModo(UiMantenimiento.MODODETALLE);
				ui.setBean(bean);
				ui.loadFields();
				ui.show();
			}
		} else {
			MaterialToast.fireToast("Seleccione una fila del grid");
		}
	}
	
	
}
