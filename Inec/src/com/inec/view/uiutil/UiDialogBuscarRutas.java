package com.inec.view.uiutil;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.inec.client.resource.MyResource;
import com.inec.client.resource.cssresource.UiMantenimientoCss;
import com.inec.view.admin.grid.GridRutas;

import gwt.material.design.client.constants.ButtonType;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCardContent;
import gwt.material.design.client.ui.MaterialCardTitle;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialToast;

public class UiDialogBuscarRutas extends DialogBox implements ClickHandler {
    public final static String MODOINSERTAR = "INSERTAR";
    public final static String MODOUPDATE = "ACTUALIZAR";
    public final static String MODODELETE = "ELIMINAR";    
    public final static String MODODETALLE = "DETALLE";    
	protected ScrollPanel pnlScroll;
	protected MaterialCardTitle CardContTitulo;
	protected FlowPanel pnlContenedor;		
	private MaterialRow pnlFila;	
	private MaterialColumn pnlColumn;
	private MaterialCard pnlCard;		
	protected MaterialCardContent pnlCardContent;
	private MaterialCardContent pnlCardContentCabecera;
	private MaterialCardContent pnlCardContentBoton;
	protected MaterialButton btnOperacion;		
	protected MaterialIcon btnCerrar;
	public FlexTable flexTable; 
	protected String modo;
	private FlexCellFormatter cellFormatter;
				
	public UiDialogBuscarRutas(){		
		initComponents();	
		initListener();
		initStyle();
	}	
	private void initComponents(){
		pnlContenedor=new FlowPanel();		
		flexTable=new FlexTable();	
		cellFormatter = flexTable.getFlexCellFormatter();
		pnlFila=new MaterialRow();
		pnlColumn=new MaterialColumn();
		pnlCard=new MaterialCard();	
		pnlCardContent=new MaterialCardContent();
		pnlCardContentBoton=new MaterialCardContent();
		pnlCardContentCabecera=new MaterialCardContent();
		btnOperacion=new MaterialButton();		
		btnCerrar=new MaterialIcon(IconType.CLOSE);
		btnCerrar.setCircle(true);
		btnCerrar.setWaves(WavesType.LIGHT);
		btnCerrar.setType(ButtonType.FLOATING);		
		pnlScroll = new ScrollPanel();
		CardContTitulo=new MaterialCardTitle();			
		btnOperacion.setText("Insertar");		
		pnlCardContentCabecera.add(CardContTitulo);
		pnlCardContentCabecera.add(btnCerrar);		
		pnlScroll.add(flexTable); 		
		pnlCardContent.add(pnlScroll);
		pnlCardContentBoton.add(btnOperacion);
		pnlCard.add(pnlCardContentCabecera);		
		pnlCard.add(pnlCardContent);
		pnlCard.add(pnlCardContentBoton);
		pnlColumn.add(pnlCard);
		pnlFila.add(pnlColumn);
		pnlContenedor.add(pnlFila);	
		this.add(pnlContenedor);	
		 int left = Window.getClientWidth()/ 2;
         int top = Window.getClientHeight()/ 2;
         this.setPopupPosition(left, top);
	}
	
	private void initStyle(){
		UiMantenimientoCss myResource=MyResource.INSTANCE.getStlUiMantenimiento();
		myResource.ensureInjected();
		flexTable.getElement().setId("flexTable");
		btnOperacion.getElement().setId("kngBtnOperacion");		
		btnCerrar.getElement().setId("btnCerrar");
		pnlCardContentCabecera.getElement().setId("pnlCardContentCabecera");
		pnlCardContent.getElement().setId("pnlCardContent");
		this.getElement().setClassName("father");
		pnlContenedor.addStyleName(MyResource.INSTANCE.getStlUiMantenimiento().pnlContenedor());		
		pnlScroll.getElement().setId("KngPnlscroll");
	}
	
	public void addWidget(int fila,int col,Widget componente){
		flexTable.setWidget(fila,col,componente);
	}
	
	public void addWidget(int fila,int col,Widget componente,int colSpan){
		flexTable.setWidget(fila,col,componente);
		cellFormatter.setColSpan(fila, col, colSpan);
	}
	private void initListener(){
		btnOperacion.addClickHandler(this);
		btnCerrar.addClickHandler(this);
	}
	public void CerrarForm(){
		this.hide();
	}

	public void processInsertar() {        
    }

    public void processActualizar() {	
    }

    public void processEliminar() {	
    }   
    
    public void processDetalle() {	
    }
	
	public void onClick(ClickEvent event) {
		if(event.getSource().equals(btnCerrar)){			
			CerrarForm();
		}else if(event.getSource().equals(btnOperacion)){
			if(GridRutas.beanUserFiscalizador==null){
				MaterialToast.fireToast("Seleccionar un Usuario");
			}else{
				GridRutas.gridRutas.insertarUser();
				GridRutas.beanUserFiscalizadorAux=GridRutas.beanUserFiscalizador;
				GridRutas.beanUserFiscalizador=null;
				CerrarForm();
			}			
		}
	}

	
}
