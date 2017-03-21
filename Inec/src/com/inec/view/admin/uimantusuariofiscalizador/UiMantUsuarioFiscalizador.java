package com.inec.view.admin.uimantusuariofiscalizador;

import java.util.Iterator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.server.model.bean.Zona;
import com.inec.shared.FieldVerifier;
import com.inec.view.admin.grid.GridEstado;
import com.inec.view.admin.grid.GridZona;
import com.inec.view.admin.uiusuariofiscalizador.UiUsuarioFiscalizadorImpl;
import com.inec.view.uiutil.UiMantenimiento;
import com.inec.view.uiutil.UiSelectOption;

import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class UiMantUsuarioFiscalizador extends UiMantenimiento implements InterMantUsuarioFiscalizador{
	protected UiUsuarioFiscalizadorImpl uiPadre;		
	private MaterialLabel lblNombres;
	protected MaterialTextBox txtNombres;
	private MaterialLabel lblApellidos;
	protected MaterialTextBox txtApellidos;
	private MaterialLabel lblDni;
	protected MaterialTextBox txtDni;
	private MaterialLabel lblTelefono;
	protected MaterialTextBox txtTelefono;
	private MaterialLabel lblCorreoPersonal;
	protected MaterialTextBox txtCorreoPersonal;
	private MaterialLabel lblCorreoCorporativo;
	protected MaterialTextBox txtCorreoCorporativo;
	protected UsuarioFiscalizador beanUsuarioFiscalizador;
	private MaterialLabel lblEstado;
	protected UiSelectOption lstEstado;
	protected GridEstado gridEstado;	
	private MaterialLabel lblZona;
	protected UiSelectOption lstZona;
	protected GridZona gridZona;
	protected Zona beanZona ;
	protected MaterialTextBox txtGuardarCodeZona;
	protected UsuarioFiscalizador bean;

	
	public UiMantUsuarioFiscalizador(UiUsuarioFiscalizadorImpl uiPadre,Boolean a){
		this.uiPadre=uiPadre;		
	}
	public UiMantUsuarioFiscalizador(UiUsuarioFiscalizadorImpl uiPadre){
		this.uiPadre=uiPadre;
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
	private void initComponents(){
		this.CardContTitulo.setText("Usuario Fiscalizador");
		lblNombres=new MaterialLabel("NOMBRES");
		txtNombres=new MaterialTextBox();	
		lblApellidos=new MaterialLabel("APELLIDOS");
		txtApellidos=new MaterialTextBox();	
		lblDni=new MaterialLabel("DNI");
		txtDni=new MaterialTextBox();
		txtDni.setType(InputType.NUMBER);
		lblTelefono=new MaterialLabel("TELEFONO");
		txtTelefono=new MaterialTextBox();	
		txtTelefono.setType(InputType.NUMBER);
		lblCorreoPersonal=new MaterialLabel("CORREO PERSONAL");
		txtCorreoPersonal=new MaterialTextBox();	
		lblCorreoCorporativo=new MaterialLabel("CORREO CORPORATIVO");
		txtCorreoCorporativo=new MaterialTextBox();		
		txtGuardarCodeZona=new MaterialTextBox();
		lblZona=new MaterialLabel("ZONA");
		gridZona=new GridZona();
		lstZona=new UiSelectOption(gridZona);
		this.addWidget(1, 0, lblNombres);
		this.addWidget(1, 1, txtNombres);
		this.addWidget(1, 2, lblApellidos);
		this.addWidget(1, 3, txtApellidos);
		this.addWidget(2, 0, lblDni);
		this.addWidget(2, 1, txtDni);
		this.addWidget(2, 2, lblTelefono);
		this.addWidget(2, 3, txtTelefono);
		this.addWidget(3, 0, lblCorreoPersonal);
		this.addWidget(3, 1, txtCorreoPersonal);
		this.addWidget(3, 2, lblCorreoCorporativo);
		this.addWidget(3, 3, txtCorreoCorporativo);	
		this.addWidget(4, 0, lblZona);
		this.addWidget(4, 1, lstZona);
		this.addWidget(4, 2, txtGuardarCodeZona);
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.center();
	}

	@Override
	public void loadFields() {		
		if (this.modo.equals(UiMantenimiento.MODOUPDATE)) {
			txtGuardarCodeZona.setVisible(false);
			txtNombres.setEnabled(true);			
            txtNombres.setFocus(true);
        	txtApellidos.setVisible(true);
        	txtApellidos.setEnabled(true);            
        	txtDni.setVisible(true);
        	txtDni.setEnabled(true);
        	txtTelefono.setVisible(true);
        	txtTelefono.setEnabled(true);
        	txtCorreoPersonal.setVisible(true);
        	txtCorreoPersonal.setEnabled(true);
        	txtCorreoCorporativo.setVisible(true);
        	txtCorreoCorporativo.setEnabled(true);        	
			lstZona.txtBuscar.setEnabled(true);
            lstZona.btnCombo.setEnabled(true);
            beanZona=null;
            gridZona.getSelectionModel().clear();
            txtDni.setText(this.bean.getDniFiscalizador());
            txtNombres.setText(this.bean.getNombre());
			txtApellidos.setText(this.bean.getApellido());
			txtCorreoPersonal.setText(this.bean.getCorreoPersonal());
	        txtCorreoCorporativo.setText(this.bean.getCorreoCorporativo());            
            txtTelefono.setText(this.bean.getNumero());           
            lstZona.txtBuscar.setEnabled(true);
            lstZona.btnCombo.setEnabled(true);
            lstZona.txtInput.setText(bean.getNombreZona());
            txtGuardarCodeZona.setText(bean.getCodeZona());          
            this.btnOperacion.setEnabled(true);
        } else if (this.modo.equals(UiMantenimiento.MODODELETE)) {        	
        	processCambiarEstado();
        	
        } else if (this.modo.equals(UiMantenimiento.MODODETALLE)) {
        	lstEstado.txtInput.setText(bean.getEstado());
			lstEstado.txtInput.setEnabled(false);
			lstEstado.btnCombo.setEnabled(false);
			lstEstado.setVisible(true);
			lblEstado.setVisible(true);
			txtNombres.setEnabled(false); 
			txtGuardarCodeZona.setVisible(false);
			txtNombres.setVisible(true);        	
			txtApellidos.setEnabled(false);
			txtApellidos.setVisible(true);
        	txtDni.setEnabled(false);
        	txtDni.setVisible(true);
        	txtTelefono.setEnabled(false);
        	txtTelefono.setVisible(true);
        	txtCorreoPersonal.setVisible(true);
        	txtCorreoPersonal.setEnabled(false);
        	txtCorreoCorporativo.setVisible(true);
        	txtCorreoCorporativo.setEnabled(false);
        	txtApellidos.setText(this.bean.getApellido());
        	txtNombres.setText(this.bean.getNombre());
            txtDni.setText(this.bean.getDniFiscalizador());
            txtTelefono.setText(this.bean.getNumero());
            txtCorreoPersonal.setText(this.bean.getCorreoPersonal());
            txtCorreoCorporativo.setText(this.bean.getCorreoCorporativo());
            this.btnOperacion.setEnabled(false);
        } else {            	
			txtNombres.setEnabled(true);
            txtNombres.setFocus(true);
        	txtApellidos.setVisible(true);
        	txtApellidos.setEnabled(true);            
        	txtDni.setVisible(true);
        	txtDni.setEnabled(true);
        	txtTelefono.setEnabled(true);
        	txtCorreoPersonal.setVisible(true);
        	txtCorreoPersonal.setEnabled(true);
        	txtCorreoCorporativo.setVisible(true);
        	txtCorreoCorporativo.setEnabled(true);
        	txtGuardarCodeZona.setVisible(false);
        	lstZona.txtBuscar.setEnabled(true);
            lstZona.btnCombo.setEnabled(true);
            beanZona=null;
            lstZona.txtInput.setText(null);
            lstZona.txtBuscar.setText("");
            gridZona.getSelectionModel().clear();
            cleanForm();
            this.btnOperacion.setEnabled(true);     		
        }
		clearError();
	}

	@Override
	public void cleanForm() {
		
		txtApellidos.clear();
		txtNombres.clear();
		txtDni.clear();
		txtTelefono.clear();
		txtCorreoPersonal.clear();
		txtCorreoCorporativo.clear();		        
	}

	@Override
	public void goToUiUsuarioFiscalizador() {
		
	}

	@Override
	public boolean isValidData() {
		
		if (this.modo.equals(UiMantenimiento.MODOINSERTAR)){
			if(FieldVerifier.isEmpty(txtNombres.getText())){
				txtNombres.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtApellidos.getText())){
				txtApellidos.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtDni.getText())){
				txtDni.setError("Campo obligatorio");
				return false;
			}else if((txtDni.getText()).length()!=8){
				txtDni.setError("Debe tener 8 Caracteres");
				return false;
			}else if(FieldVerifier.isEmpty(txtTelefono.getText())){
				txtTelefono.setError("Campo obligatorio");
				return false;
			}else if(FieldVerifier.isEmpty(txtCorreoPersonal.getText())){
				txtCorreoPersonal.setError("Campo obligatorio");
				return false;			
			}else if(FieldVerifier.isEmpty(txtCorreoCorporativo.getText())){
				txtCorreoCorporativo.setError("Campo obligatorio");
				return false;
			}else if(isExistInGrid()){
				MaterialToast.fireToast("ID existe");
				return false;
			}else{
				clearError();
				return true;
			}
		}else if(this.modo.equals(UiMantenimiento.MODOUPDATE)){

			return true;
		}else{
			return true;
		}
	}

	public void setBean(UsuarioFiscalizador bean) {
		this.bean = bean;
	}

	@Override
	public void updateDataGrid(UsuarioFiscalizador bean) {
		
		
	}

	@Override
	public void clearError() {
		
		txtApellidos.clearErrorOrSuccess();
		txtNombres.clearErrorOrSuccess();
		txtDni.clearErrorOrSuccess();
		txtTelefono.clearErrorOrSuccess();
		txtCorreoPersonal.clearErrorOrSuccess();
		txtCorreoCorporativo.clearErrorOrSuccess();
	}

	@Override
	public boolean isExistInGrid() {
		
		Iterator<UsuarioFiscalizador> iterador=uiPadre.getGrid().getData().iterator();
		while(iterador.hasNext()){
			UsuarioFiscalizador bean=iterador.next();
			if(bean.getCodeUsuarioFiscalizador().equalsIgnoreCase(txtDni.getText())){
				return true;
			}
		}
		return false;
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

	@Override
	public void loadZona() {
		
		
	}
	@Override
	public void processCambiarEstado() {
		
		
	}
}
