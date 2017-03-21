package com.inec.view.admin.uitoolbaradmin;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.inec.client.resource.MyResource;
import com.inec.client.service.ServiceGestionMantenimiento;
import com.inec.client.service.ServiceGestionMantenimientoAsync;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.view.admin.grid.GridEnviarCorreo;
import com.inec.view.admin.grid.GridPlanificacionNotificacion;
import com.inec.view.admin.grid.GridRutas;
import com.inec.view.admin.uiEnviarCorreo.UiEnviarCorreoImpl;
import com.inec.view.admin.uimain.UiMain;
import com.inec.view.admin.uiusuariofiscalizador.UiUsuarioFiscalizadorImpl;
import com.inec.view.uiutil.ButtonBar;
import com.inec.view.uiutil.TabClose;

import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.client.ui.MaterialTooltip;

public class UiToolbarAdmin extends Composite implements ClickHandler{
	private HorizontalPanel pnlContenedor;
	private MaterialTooltip tipHome;
	private MaterialTooltip tipTurista;
	private MaterialTooltip tipRuta;
	private MaterialTooltip tipMensaje;	
	private ButtonBar linkHome;
	private ButtonBar linkTurista;
	private ButtonBar linkNegocio;
	private ButtonBar linkDestino;
	private ButtonBar linkColonia;
	private ButtonBar linkMiembro;
	
	public UiToolbarAdmin(){
		initComponents();
		initStyle();
		initListener();
		loadFiscalizador();
	}
	
	private void initComponents(){
		pnlContenedor=new HorizontalPanel();
		
		linkHome=new ButtonBar(new Image(MyResource.INSTANCE.getImgFotografo32()));
		
		tipHome=new MaterialTooltip(linkHome,"Fiscalizador");		
		linkTurista=new ButtonBar(new Image(MyResource.INSTANCE.getImgRuta32()));
		tipTurista=new MaterialTooltip(linkTurista,"Monitoreo de rutas");		
		linkNegocio=new ButtonBar(new Image(MyResource.INSTANCE.getImgMensaje32()));
		tipRuta=new MaterialTooltip(linkNegocio,"Enviar Mensaje");
		linkDestino=new ButtonBar(new Image(MyResource.INSTANCE.getImgNotificacion32()));
		tipMensaje=new MaterialTooltip(linkDestino,"Planear Notificaciones");
		linkColonia=new ButtonBar(new Image(MyResource.INSTANCE.getImgNotificacion32()));		
		linkMiembro=new ButtonBar(new Image(MyResource.INSTANCE.getImgRuta32()));		
		pnlContenedor.add(tipHome);
		pnlContenedor.add(tipTurista);
		pnlContenedor.add(tipRuta);
		pnlContenedor.add(linkDestino);	
		this.initWidget(pnlContenedor);
	}
	
	private void initStyle(){
		MyResource.INSTANCE.getStlUiToolBarAdmin().ensureInjected();
		pnlContenedor.getElement().setId("pnlToolBar");
		linkHome.getElement().setId("btnToolHome");
		linkTurista.getElement().setId("btnToolTurista");
		linkNegocio.getElement().setId("btnToolNegocio");
		linkDestino.getElement().setId("btnToolDestino");
		linkColonia.getElement().setId("btnToolColonia");
		linkMiembro.getElement().setId("btnToolMiembro");
	}
	
	private void initListener(){
		linkHome.addClickHandler(this);
		linkTurista.addClickHandler(this);
		linkNegocio.addClickHandler(this);
		linkDestino.addClickHandler(this);
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if(linkHome.equals(event.getSource())){
			showuiUsuarioFiscalizador.execute();
		}else if(linkTurista.equals(event.getSource())){
			showMonitoreo.execute();
		}else if(linkNegocio.equals(event.getSource())){
			showEnvioCorreos.execute();
		}else if(linkDestino.equals(event.getSource())){
			showPlanificacionNotificacion.execute();
		}
		
	}
	
	Command showuiUsuarioFiscalizador= new Command() {

		@Override
		public void execute() {
			convertir();	
			
			if (UiMain.uiUsuarioFiscalizador == null) {                
				UiMain.uiUsuarioFiscalizador = new UiUsuarioFiscalizadorImpl();                
                TabClose tab = new TabClose(UiMain.uiUsuarioFiscalizador, "Fiscalizador");
                UiMain.tabPanel.add(UiMain.uiUsuarioFiscalizador, tab);                
                UiMain.tabs.add(tab);
                UiMain.tabPanel.selectTab(UiMain.uiUsuarioFiscalizador);
                UiMain.tabSelected=tab;
                backGroundColor();
            }else{
                TabClose tab = new TabClose(UiMain.uiUsuarioFiscalizador, "Fiscalizador");
                UiMain.tabPanel.add(UiMain.uiUsuarioFiscalizador, tab);                
                UiMain.tabs.add(tab);
                UiMain.tabPanel.selectTab(UiMain.uiUsuarioFiscalizador);
                UiMain.tabSelected=tab;
                backGroundColor();
            }
			}
		
	};	
	
	Command showPlanificacionNotificacion= new Command() {
		@Override
		public void execute() {			
			convertir();	
			if(UiMain.uiWidgetPlanificacion==null){
				GridPlanificacionNotificacion planificacionNotificacion= new GridPlanificacionNotificacion();
				UiMain.uiWidgetPlanificacion =planificacionNotificacion.showPanelMonitoreo();
			}
			TabClose tab= new TabClose(UiMain.uiWidgetPlanificacion, "Planificacion");
			UiMain.tabPanel.add(UiMain.uiWidgetPlanificacion, tab);  
			UiMain.tabs.add(tab);
			UiMain.tabPanel.selectTab(UiMain.uiWidgetPlanificacion);
			UiMain.tabSelected=tab;
			backGroundColor();
			}		
	};
	
	
	Command showMonitoreo= new Command() {
		@Override
		public void execute() {			
			convertir();	
			if(UiMain.uiWidgetRutas==null){
				GridRutas monitoreoRutas= new GridRutas();
				UiMain.uiWidgetRutas =monitoreoRutas.showPanelMonitoreo();
			}
			TabClose tab= new TabClose(UiMain.uiWidgetRutas, "Monitoreo Rutas");
			UiMain.tabPanel.add(UiMain.uiWidgetRutas, tab);  
			UiMain.tabs.add(tab);
			UiMain.tabPanel.selectTab(UiMain.uiWidgetRutas);
			UiMain.tabSelected=tab;
			backGroundColor();
			}		
	};
	
	Command showEnvioCorreos= new Command() {
		@Override
		public void execute() {
			
			convertir();			
			if(UiMain.uiWidgetCorreo==null){
				GridEnviarCorreo enviarCorreo= new GridEnviarCorreo();
				UiMain.uiWidgetCorreo =enviarCorreo.showPanelEnviarCorreo();
			}
			TabClose tab= new TabClose(UiMain.uiWidgetCorreo, "Enviar Correos");
			UiMain.tabPanel.add(UiMain.uiWidgetCorreo, tab);  
			UiMain.tabs.add(tab);
			UiMain.tabPanel.selectTab(UiMain.uiWidgetCorreo);
			UiMain.tabSelected=tab;
			backGroundColor();
			}		
	};
	
	public static void backGroundColor(){
		for(int i=0;i<UiMain.tabs.size();i++){
			TabClose tab=UiMain.tabs.get(i);
			tab.getElement().getStyle().setBackgroundColor("#FA5858");
		}
		UiMain.tabSelected.getElement().getStyle().setBackgroundColor("#F6CECE");
	}
	
	@SuppressWarnings("unused")
	public static void exitTabDefault(){
		for(int i=0;i<UiMain.tabs.size();i++){
			UiMain.tabSelected=UiMain.tabs.get(i);
			break;
		}
	}
	
	public  List<UsuarioFiscalizador> listUsuarioFiscalizador= new ArrayList<UsuarioFiscalizador>();
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
	
	public void convertir(){
		if(UiEnviarCorreoImpl.listUsuarioFiscalizador==null || UiEnviarCorreoImpl.listUsuarioFiscalizador.isEmpty()){
				UiEnviarCorreoImpl.listUsuarioFiscalizador=listUsuarioFiscalizador;			
		}
	}
	
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);

	
}
