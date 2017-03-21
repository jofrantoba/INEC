package com.inec.view.admin.uiEnviarCorreo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.inec.client.service.ServiceGestionMantenimiento;
import com.inec.client.service.ServiceGestionMantenimientoAsync;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.server.model.bean.Zona;
import com.inec.view.admin.grid.GridEnviarCorreo;
import com.inec.view.admin.grid.GridZona;
import com.inec.view.admin.uiusuariofiscalizador.UiUsuarioFiscalizadorImpl;
import com.inec.view.uiutil.UiBuscarFiscalizador;
import com.inec.view.uiutil.UiBuscarFiscalizador.ClassBusqueda;
import com.inec.view.uiutil.UiDialogBuscar;
import com.inec.view.uiutil.UiFiltroFiscalizador;
import com.inec.view.uiutil.UiSelectOption;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class UiEnviarCorreo extends UiDialogBuscar  implements InterUiEnviarCorreo{
	protected GridEnviarCorreo grid;
	protected UiUsuarioFiscalizadorImpl uiPadre;
	private MaterialLabel lblDni;
	public MaterialTextBox txtDni;
	protected MaterialTextBox txtNombresApellidos;
	protected MaterialButton btnBuscar;

	private MaterialLabel lblZona;
	protected UiSelectOption lstZona;
	protected GridZona gridZona;
	protected Zona beanZona;	
	protected VerticalPanel verticalPanel;
	public static List<UsuarioFiscalizador> listUsuarioFiscalizador= new ArrayList<UsuarioFiscalizador>();
	public UiBuscarFiscalizador uiBuscar;
	public static Set<ClassBusqueda> listFiscalizadorChecked= new HashSet<>();

	public UiEnviarCorreo() {
		initComponents();
		initListener();
	}

	private void initComponents() {
		this.CardContTitulo.setText("Buscar Fiscalizador");
		lblDni = new MaterialLabel("Busqueda");
		txtDni = new MaterialTextBox();
		txtDni.setPlaceholder("Ingrese dni , nombres o apellidos");		
		txtNombresApellidos = new MaterialTextBox();				
		lblZona = new MaterialLabel("ZONA");
		gridZona = new GridZona();
		lstZona = new UiSelectOption(gridZona);
		btnBuscar= new MaterialButton();
		btnBuscar.setText("Buscar");
		verticalPanel= new VerticalPanel();
		uiBuscar= new UiBuscarFiscalizador();
		uiBuscar.setData(listUsuarioFiscalizador);
		verticalPanel=(VerticalPanel) uiBuscar.onModuleLoad();
		
		this.addWidget(1, 0, lblDni);
		this.addWidget(1, 1, txtDni,2);
		this.addWidget(1, 4, lblZona);
		this.addWidget(1, 5, lstZona);	
		this.addWidget(1, 6, btnBuscar);	
		this.addWidget(2, 0, verticalPanel,6);	
		
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
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

	@Override
	public void buscarZona() {
		gridZona.getDataProvider().setFilter(lstZona.txtBuscar.getText());
		gridZona.getDataProvider().refresh();
	}
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
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
	
	
	@Override
	public void loadFiscalizador() {
	}
	@Override
	public void loadBusquedaFiscalizador(String patron, String codeZona) {
		 SERVICE.buscarUsuarioFiscalizadores(patron, codeZona, new AsyncCallback<List<UsuarioFiscalizador>>() {
			@Override
			public void onFailure(Throwable caught) {						
				MaterialToast.fireToast(caught.getMessage());
			}
			@Override
			public void onSuccess(List<UsuarioFiscalizador> result) {							
				UiFiltroFiscalizador filtro= new UiFiltroFiscalizador(result);				
				verticalPanel=(VerticalPanel) filtro.onModuleLoad();
				addWidget(2, 0, verticalPanel,6);	
			}
			
		});
	}
	
	@SuppressWarnings("unused")
	@Override
	public void insertarDestinatarios() {
		String correos="";
		Iterator<ClassBusqueda> iterator= listFiscalizadorChecked.iterator();
		while(iterator.hasNext()){
			correos+= iterator.next().correo+",";
		}
		
	}
}
