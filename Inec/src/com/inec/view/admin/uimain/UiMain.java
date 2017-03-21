package com.inec.view.admin.uimain;
import java.util.ArrayList;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.inec.view.admin.grid.GridEnviarCorreo;
import com.inec.view.admin.grid.GridRutas;
import com.inec.view.admin.uimenubaradmin.UiMenuBar;
import com.inec.view.admin.uitoolbaradmin.UiToolbarAdmin;
import com.inec.view.admin.uiusuariofiscalizador.UiUsuarioFiscalizador;
import com.inec.view.uiutil.JHeaderMenu;
import com.inec.view.uiutil.TabClose;
import com.inec.view.uiutil.UiScreenSesion;

import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialLink;

public class UiMain extends UiScreenSesion{
	private JHeaderMenu header;
	private MaterialLink linkUser;
	private MaterialLink linkSettings;
	private MaterialLink linkCloseSesion;
	private UiMenuBar menuBar;
	private UiToolbarAdmin toolBar;
	public static UiUsuarioFiscalizador uiUsuarioFiscalizador;
	public static GridEnviarCorreo uiEnviarCorreo;
	public static GridRutas uiMonitoreoRutas;	
	public static Widget uiWidgetCorreo;
	public static Widget uiWidgetRutas;
	public static Widget uiWidgetPlanificacion;
	public static TabLayoutPanel tabPanel;
	public static ArrayList<TabClose> tabs = new ArrayList<TabClose>();
	public static TabClose tabSelected;	

	
	public UiMain(){
		initComponents();
		initListener();
		initStyle();
		reCalcularWindows();
	}
	
	private void initComponents(){
		header=new JHeaderMenu();
		header.setTitle("SISTEMA DE CONTROL DE FISCALIZADORES JNE - INEC");
		linkUser=new MaterialLink();
		linkUser.setIconType(IconType.ACCOUNT_CIRCLE);
		linkUser.setText("administrador@jne.pe");
		linkUser.setWaves(WavesType.LIGHT);
		linkUser.setTooltip("Ver Perfil");
		linkSettings=new MaterialLink();
		linkSettings.setIconType(IconType.SETTINGS);
		linkSettings.setWaves(WavesType.LIGHT);
		linkSettings.setTooltip("Configuracion");
		linkCloseSesion=new MaterialLink();
		linkCloseSesion.setIconType(IconType.CLOSE);
		linkCloseSesion.setWaves(WavesType.LIGHT);
		linkCloseSesion.setTooltip("Cerrar Sesion");
		header.addWidgetToNavSection(linkUser);
		//header.addWidgetToNavSection(linkSettings);
		header.addWidgetToNavSection(linkCloseSesion);
		menuBar=new UiMenuBar();
		toolBar=new UiToolbarAdmin();
        tabPanel = new TabLayoutPanel(4, Unit.EM);
        tabPanel.setAnimationDuration(1000);
        this.setComponent(UiScreenSesion.TITULO, header);
        //this.setComponent(UiScreenSesion.MENU, menuBar);
        this.setComponent(UiScreenSesion.BAR, toolBar);
        this.setComponent(UiScreenSesion.TAB, tabPanel); 
	}
	
	private void reCalcularWindows() {
        int alto = Window.getClientHeight() - 200;
        int ancho = Window.getClientWidth();
        tabPanel.setWidth(ancho + "px");
        tabPanel.setHeight(alto + "px");      
    }
	
	private void initListener(){
		
	}
	
	private void initStyle(){
		
	}
}
