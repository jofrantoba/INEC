package com.inec.view.admin.uiusuariofiscalizador;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.inec.client.service.ServiceGestionMantenimiento;
import com.inec.client.service.ServiceGestionMantenimientoAsync;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.server.model.bean.Zona;
import com.inec.view.admin.grid.GridUsuarioFiscalizador;
import com.inec.view.admin.grid.GridZona;
import com.inec.view.uiutil.UiFormMantenimiento;
import com.inec.view.uiutil.UiSelectOption;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class UiUsuarioFiscalizador extends UiFormMantenimiento implements InterUiUsuarioFiscalizador {
protected GridUsuarioFiscalizador grid;
	FlexTable flex;
	private MaterialLabel lblDni;
	public MaterialTextBox txtDni;
	protected MaterialButton btnBuscar;
	private MaterialLabel lblZona;
	protected UiSelectOption lstZona;
	protected GridZona gridZona;
	protected Zona beanZona;
	public UiUsuarioFiscalizador(){
		initComponents();
		initListener();
	}
	ClickHandler clickKandler=new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
						
			if(event.getSource().equals(lstZona.btnCombo)){
				showSelectOptionZona();
			}
		}
	};
	
	private void initListener(){
		lstZona.btnCombo.addClickHandler(clickKandler);
		lstZona.txtInput.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
				showSelectOptionZona();
			}
			
		});
		lstZona.txtInput.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				
				showSelectOptionZona();
			}

		
		});
		lstZona.txtBuscar.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				
				buscarZona();
			}

		
		});
		gridZona.getSelectionModel().addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				
				beanZona=gridZona.getSelectionModel().getSelectedObject();
				if(beanZona!=null){
					lstZona.txtInput.setText(beanZona.getNombreZona());
					lstZona.simplePopup.hide();
				}
			}});
	}

	public void buscarZona() {
		
		gridZona.getDataProvider().setFilter(lstZona.txtBuscar.getText());
		gridZona.getDataProvider().refresh();
	}
	
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
	
	public void showSelectOptionZona(){
		loadZona();
		lstZona.txtInput.clear();
		lstZona.txtInput.clearErrorOrSuccess();
		lstZona.grid.setHeight("150px");
		lstZona.grid.setWidth("217px");
		lstZona.simplePopup.setWidth("217px");
		lstZona.simplePopup.setHeight("220px");
		lstZona.showPopup();
	}
	private void initComponents(){
		flex= new FlexTable();
		lblDni = new MaterialLabel("Busqueda");
		txtDni = new MaterialTextBox();
		txtDni.setPlaceholder("Ingrese dni , nombres o apellidos");
		btnBuscar= new MaterialButton();
		btnBuscar.setText("Buscar");
		lblZona = new MaterialLabel("ZONA");
		gridZona = new GridZona();
		lstZona = new UiSelectOption(gridZona);
		grid=new GridUsuarioFiscalizador();
        grid.setMinimumTableWidth(1024, Unit.PX);
        
        flex.setWidget(0, 0, lblDni);
        flex.setWidget(0, 1, txtDni);
        flex.setWidget(0, 2, lblZona);
		flex.setWidget(0, 3, lstZona);	
        flex.setWidget(0, 4, btnBuscar);	
        flex.setWidget(1, 0, new MaterialLabel());
        flex.setWidget(1, 1, new MaterialLabel());
        flex.setWidget(1, 2, new MaterialLabel());
		
        this.getPnlBusqueda().add(flex);
        this.getPnlTabla().add(grid);
        this.getPnlTabla().add(grid.getPager()); 
        
    	btnBuscar.addClickHandler(new ClickHandler() {
    		
    		@Override
    		public void onClick(ClickEvent event) {
    			String patron= txtDni.getText().toString();
    			String codeZona=null;
				if(beanZona!=null){
					codeZona=beanZona.getCodeZona();
				}				
				loadBusquedaFiscalizador(patron, codeZona);
    		}
    	});
	}
	
	@Override
	public void showUIOper1() {
		goToUiMantUsuarioFiscalizadorInsertar();
	}

	@Override
	public void showUIOper2() {
		goToUiMantUsuarioFiscalizadorActualizar();
	}

	@Override
	public void showUIOper3() {
		goToUiMantUsuarioFiscalizadorEliminar();
	}

	@Override
	public void showUIOper4() {
		goToUiMantUsuarioFiscalizadorDetalle();
	}

	@Override
	public void goToUiMantUsuarioFiscalizadorInsertar() {
		
		
	}

	@Override
	public void goToUiMantUsuarioFiscalizadorActualizar() {
		
		
	}

	@Override
	public void goToUiMantUsuarioFiscalizadorEliminar() {
		
		
	}

	@Override
	public void goToUiMantUsuarioFiscalizadorDetalle() {
		
		
	}

	public GridUsuarioFiscalizador getGrid() {
		return grid;
	}
	
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	
	public void loadBusquedaFiscalizador(String patron, String codeZona) {
		
		 SERVICE.buscarUsuarioFiscalizadores(patron, codeZona, new AsyncCallback<List<UsuarioFiscalizador>>() {

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
	
}
