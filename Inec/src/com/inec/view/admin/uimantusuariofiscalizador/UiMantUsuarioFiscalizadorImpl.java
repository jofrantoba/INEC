package com.inec.view.admin.uimantusuariofiscalizador;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.inec.client.service.ServiceGestionMantenimiento;
import com.inec.client.service.ServiceGestionMantenimientoAsync;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.server.model.bean.Zona;
import com.inec.view.admin.uiusuariofiscalizador.UiUsuarioFiscalizadorImpl;
import com.inec.view.uiutil.UiMantenimiento;

import gwt.material.design.client.ui.MaterialToast;

public class UiMantUsuarioFiscalizadorImpl extends UiMantUsuarioFiscalizador {	
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public UiMantUsuarioFiscalizadorImpl(UiUsuarioFiscalizadorImpl uiPadre){
		super(uiPadre);
	}
	
	public UiMantUsuarioFiscalizadorImpl(UiUsuarioFiscalizadorImpl uiPadre,Boolean a){
		super(uiPadre,a);
	}

	@Override
	public void processInsertar() {		
		if (isValidData()) {		
			String dni= txtDni.getText().trim().toUpperCase(); 
			String nombres=txtNombres.getText().trim().toUpperCase();; 
			String apellidos=txtApellidos.getText().trim().toUpperCase(); 
			String correoPersonal=txtCorreoPersonal.getText().trim().toUpperCase(); 
			String correoInstitucional=txtCorreoCorporativo.getText().trim().toUpperCase();				
			String telefono=txtTelefono.getText().trim().toUpperCase(); 
			String estado="A"; 
			String codeZona=beanZona.getCodeZona();
			
			SERVICE.crearUsuario(dni, nombres, apellidos, correoPersonal, correoInstitucional, telefono, estado, codeZona, new AsyncCallback<UsuarioFiscalizador>(){
				@Override
				public void onFailure(Throwable caught) {					
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(UsuarioFiscalizador result) {							
					MaterialToast.fireToast("Insertado correctamente");
					MaterialToast.fireToast(result.toString());
					updateDataGrid(result);
					cleanForm();
				}
			});					
		}
	}

	@Override
	public void processActualizar() {
		if (isValidData()) {
			String dni= txtDni.getText().trim().toUpperCase(); 
			String nombres=txtNombres.getText().trim().toUpperCase();; 
			String apellidos=txtApellidos.getText().trim().toUpperCase(); 
			String correoPersonal=txtCorreoPersonal.getText().trim().toUpperCase(); 
			String correoInstitucional=txtCorreoCorporativo.getText().trim().toUpperCase();				
			String telefono=txtTelefono.getText().trim().toUpperCase(); 
			String estado="A"; 
			String codeZona=txtGuardarCodeZona.getText();
			if(beanZona!=null){
				 codeZona=beanZona.getCodeZona();
			}		
			SERVICE.actualizarUsuario(dni, nombres, apellidos, correoPersonal, correoInstitucional, telefono, estado, codeZona, new AsyncCallback<UsuarioFiscalizador>() {

				@Override
				public void onFailure(Throwable caught) {					
					MaterialToast.fireToast(caught.getMessage());
				}

				@Override
				public void onSuccess(UsuarioFiscalizador result) {
					MaterialToast.fireToast("Actualizado correctamente");
					updateDataGrid(result);
					goToUiUsuarioFiscalizador();
				}
				
			});
		}
	}
	@Override
	public void processCambiarEstado() {		
		processEliminar();
	}
	@Override
	public void processEliminar() {	
		if (isValidData()) {
			String estado="A";
			String dni=null;
			if(bean!=null){
				dni=bean.getDniFiscalizador();
				if(bean.getEstado().equals("A")){
					estado="D";
				}
			}			
			SERVICE.actualizarEstado(dni, estado, new AsyncCallback<UsuarioFiscalizador>() {
				@Override
				public void onFailure(Throwable caught) {				
				}
				@Override
				public void onSuccess(UsuarioFiscalizador result) {			
					if(result.getEstado().equalsIgnoreCase("A")){
						MaterialToast.fireToast("Se Activó la Cuenta");
					}else{
						MaterialToast.fireToast("Se Descativó la Cuenta");
					}					
					updateDataGrid(result);
					goToUiUsuarioFiscalizador();
					
				}
			});
		}
	}

	@Override
	public void processDetalle() {		
	}
	
	@Override
	public void goToUiUsuarioFiscalizador() {
		cleanForm();
		this.hide();
	}
	
	@Override
	public void updateDataGrid(UsuarioFiscalizador bean) {
		if(this.modo.equalsIgnoreCase(UiMantenimiento.MODOINSERTAR)){
			uiPadre.getGrid().getData().add(bean);
			uiPadre.getGrid().refreshGrid();
		}else if(this.modo.equalsIgnoreCase(UiMantenimiento.MODOUPDATE)){
			uiPadre.getGrid().getData().set(uiPadre.getGrid().getData().indexOf(this.bean), bean);
			uiPadre.getGrid().refreshGrid();
		}else if(this.modo.equalsIgnoreCase(UiMantenimiento.MODODELETE)){			
			uiPadre.getGrid().getData().set(uiPadre.getGrid().getData().indexOf(this.bean), bean);
			uiPadre.getGrid().refreshGrid();
		}
		
	}
	
	@Override
	public void loadZona() {		
				SERVICE.listZona(new AsyncCallback<List<Zona>>() {

					@Override
					public void onFailure(Throwable caught) {						
						MaterialToast.fireToast(caught.getMessage());
					}

					@Override
					public void onSuccess(List<Zona> result) {						
						gridZona.setData(result);						
					}
					
				});
	}

}
