package com.inec.view.admin.uimenubaradmin;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.inec.client.service.ServiceGestionMantenimiento;
import com.inec.client.service.ServiceGestionMantenimientoAsync;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.view.admin.grid.GridEnviarCorreo;
import com.inec.view.admin.grid.GridPlanificacionNotificacion;
import com.inec.view.admin.grid.GridRutas;
import com.inec.view.admin.uiEnviarCorreo.UiEnviarCorreoImpl;
import com.inec.view.admin.uimain.UiMain;
import com.inec.view.admin.uiusuariofiscalizador.UiUsuarioFiscalizador;
import com.inec.view.admin.uiusuariofiscalizador.UiUsuarioFiscalizadorImpl;
import com.inec.view.uiutil.TabClose;

import gwt.material.design.client.ui.MaterialToast;

public class UiMenuBar extends Composite{	
	private MenuBar menuRoot;
	private MenuBar mbMantenimiento;
	private MenuBar mbSistema;
	public static UiUsuarioFiscalizador uiUsuarioFiscalizador;	
	private MenuItem miUsuarioAdmin;	
	private MenuItem monitoreoRutas;	
	private MenuItem enviarCorreos;		
	private MenuItem planeacionNotificacion;		
	
	public UiMenuBar(){
		initComponents();
		initStyle();
	}
	
	private void initComponents(){
		loadFiscalizador();
		menuRoot=new MenuBar();
        menuRoot.setAnimationEnabled(true);
        menuRoot.setAutoOpen(true);
        initMenuMain();
        initMenuMantenimientos();
        initiMenuAdministracion();
        initWidget(menuRoot);
	}
	
	private void initMenuMain(){
		mbMantenimiento=new MenuBar(true);
		menuRoot.addItem("Operaciones", mbMantenimiento);		
	}
	
	private void initMenuMantenimientos(){
		mbSistema=new MenuBar(true);
		mbMantenimiento.addItem("Sistema",mbSistema);
	}	
	
	private void initiMenuAdministracion(){
		miUsuarioAdmin= new MenuItem("Fiscalizador",showuiUsuarioFiscalizador);
		mbSistema.addItem(miUsuarioAdmin);
		monitoreoRutas=new MenuItem("Monitoreo de Rutas",showMonitoreo);
		mbSistema.addItem(monitoreoRutas);
		enviarCorreos=new MenuItem("Enviar Correos ",showEnvioCorreos);
		mbSistema.addItem(enviarCorreos);	
		planeacionNotificacion=new MenuItem("Planear Notificacion ",showPlanificacionNotificacion);
		mbSistema.addItem(planeacionNotificacion);	
		
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
	private void initStyle(){
		
	}
	
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
