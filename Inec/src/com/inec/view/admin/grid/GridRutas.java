package com.inec.view.admin.grid;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.inec.client.service.ServiceGestionMantenimiento;
import com.inec.client.service.ServiceGestionMantenimientoAsync;
import com.inec.server.model.bean.PosicionFiscalizador;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.view.admin.uiEnviarCorreo.UiEnviarCorreo;
import com.inec.view.uiutil.UiBuscarRutas;
import com.inec.view.uiutil.UiBuscarRutasIntervalo;
import com.inec.view.uiutil.UiDialogFiscalizadorRutas;

import gwt.material.design.client.constants.Orientation;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialDatePicker;
import gwt.material.design.client.ui.MaterialDatePicker.MaterialDatePickerType;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class GridRutas extends FlexTable {
	
	private static final String URLBASE=GWT.getHostPageBaseURL();
	private static String URLBASEPDF=GWT.getHostPageBaseURL();
	
	FlexTable myFlexTable;	
	MaterialTextBox txtUsuario;
	MaterialButton btnOpenBuscarUsuario;
	MaterialLabel labelUsuario;
	MaterialLabel labelPickerLeft;
	MaterialLabel labelPickerRight;
	 MaterialDatePicker datePickerLeft;
	 MaterialDatePicker datePickerRight;
	 MaterialLabel captureFechaIzquierda;
	 MaterialLabel captureFechaDerecha;
	 MaterialButton btnListar;
	 MaterialButton btnExportar;
	 UiBuscarRutas uiBuscarRutas;
	 MaterialLabel labelCapturarCodigo;
	 protected VerticalPanel verticalPanel;
	 public static UsuarioFiscalizador beanUserFiscalizador;
	 public static UsuarioFiscalizador beanUserFiscalizadorAux;
	 public static GridRutas gridRutas;
	 
	 public Long fechainicial= null;
		public Long fechafinal=null;
	 
	 public GridRutas() {		
		 gridRutas=this;
	}
	public void initComponents() {
		myFlexTable = new FlexTable();
		txtUsuario = new MaterialTextBox();
		txtUsuario.setEnabled(false);
		btnOpenBuscarUsuario = new MaterialButton();
		labelUsuario = new MaterialLabel();		
		btnOpenBuscarUsuario.setText("Buscar Usuario");
		labelPickerLeft= new MaterialLabel();
		labelPickerLeft.setText("Desde:");
		labelPickerRight= new MaterialLabel();
		labelPickerRight.setText("Hasta:");
		labelCapturarCodigo= new MaterialLabel();
		labelUsuario.setText("Usuario:");
		datePickerLeft= new MaterialDatePicker();
		datePickerLeft.setCircle(true);
		datePickerLeft.setPixelSize(100, 40);		
		datePickerLeft.setOrientation(Orientation.PORTRAIT);
		datePickerRight= new MaterialDatePicker();
		datePickerRight.setCircle(true);
		datePickerRight.setPixelSize(100, 40);
		btnListar = new MaterialButton();		
		btnListar.setText("Listar");
		btnExportar = new MaterialButton();	
		captureFechaIzquierda= new MaterialLabel();
		captureFechaDerecha=new MaterialLabel();
		btnExportar.setText("Exportar");
		txtUsuario.setWidth("300px");
		uiBuscarRutas= new UiBuscarRutas();
		uiBuscarRutas.setData(UiEnviarCorreo.listUsuarioFiscalizador);		
		verticalPanel=(VerticalPanel) uiBuscarRutas.onModuleLoad();
		datePickerLeft.setSelectionType(MaterialDatePickerType.DAY);		
		datePickerLeft.setWidth("130px");		
		datePickerRight.setWidth("130px");		
		this.setWidget(0, 0, labelUsuario);
		this.setWidget(0, 1, txtUsuario);
		this.setWidget(0, 2, btnOpenBuscarUsuario);
		this.setWidget(0, 3, labelPickerLeft);
		this.setWidget(0,4, datePickerLeft);
		this.setWidget(0, 5, labelPickerRight);		
		this.setWidget(0, 6, datePickerRight);
		this.setWidget(0, 7, btnListar);
		this.setWidget(0, 8, btnExportar);	
		FlexCellFormatter a = new FlexCellFormatter();
		a.setColSpan(1, 0, 8);
		this.setCellFormatter(a);
		
		
	}
	public Widget showPanelMonitoreo() {
		initComponents();
		addListener();
		return this;
	}
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public void addListener() {
		btnOpenBuscarUsuario.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				new UiDialogFiscalizadorRutas().show();
				
			}
		});
		
				
		btnListar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				String codigo= labelCapturarCodigo.getText().toString();
				fechainicial= datePickerLeft.getDate().getTime();
				fechafinal=datePickerRight.getDate().getTime();
//				MaterialToast.fireToast(datePickerLeft.getDate().getTime()+"");
				captureFechaIzquierda.setText(datePickerLeft.getDate().toString());
				SERVICE.listarRutasIntervalo(codigo, fechainicial, fechafinal, new AsyncCallback<List<PosicionFiscalizador>>() {

					@Override
					public void onFailure(Throwable caught) {						
						
					}

					@Override
					public void onSuccess(List<PosicionFiscalizador> result) {						
						UiBuscarRutasIntervalo uib= new UiBuscarRutasIntervalo();
						uib.setData(result);
						verticalPanel=(VerticalPanel) uib.onModuleLoad();
						setWidget(1, 0, verticalPanel);
					}
				});
				
			}
		});
		
		btnExportar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String url=URLBASE;
				URLBASEPDF=url+"inecservlet2.html";
//				String urlprueba=new UrlBuilder()										
//				        .setHost(URL.encode(URLBASEPDF))
//				        .setParameter("hola", "hola munfo")
//				        .buildString();
//				MaterialToast.fireToast(urlprueba);
				 Window.open(URL.encode(URLBASEPDF)+"?codeusuario="+beanUserFiscalizadorAux.getDniFiscalizador()
				 +"&fechainicial="+fechainicial.toString()+"&fechafinal="+fechafinal.toString()
				 , "_blank", "width=800,height=600");
			
				MaterialToast.fireToast(url);
				String requestData="hola=holas";
				final RequestBuilder builder=new RequestBuilder(RequestBuilder.GET,URLBASEPDF);
//				
//				try{
//					builder.sendRequest(requestData,new RequestCallback(){
//						@Override
//						public void onResponseReceived(Request request, Response response) {	
//							
//							if(response.getStatusCode()==200){
////								String hola=Window.Location.createUrlBuilder().setParameter("hola", "hola mundo dev").buildString();
////								MaterialToast.fireToast(hola);
////								Window.Location.assign(URLBASEPDF);
////								
////								Window.Location.replace(URL.encode(URLBASEPDF)+"?codeusuario="+beanUserFiscalizador.getDniFiscalizador());								
////								String props="fullscreen=no,modal=yes,addressbar=no,toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes,status=no ,height=" + (800) + "," + "width=" + (550);
////								com.google.gwt.user.client.Window.open(URLBASEPDF+"?hola=holasddfdf", "_blank", props);
////								FormPanel fm= new FormPanel("_blank");
////								fm.setAction(URLBASEPDF);
////								
////								fm.setMethod(FormPanel.METHOD_POST);
////								fm.submit();
//								String urlprueba=new UrlBuilder()										
//								        .setHost(URL.encode(URLBASEPDF))
//								        .setParameter("hola", "hola munfo")
//								        .buildString();
//								MaterialToast.fireToast(urlprueba);
//								 Window.open(URL.encode(URLBASEPDF)+"?codeusuario="+beanUserFiscalizador.getDniFiscalizador(), "_blank", "width=800,height=600");
//							
//							}else{
//								MaterialToast.fireToast(response.getStatusText());
//							}
//						}
//						@Override
//						public void onError(Request request, Throwable ex) {							
//							MaterialToast.fireToast(ex.getLocalizedMessage());
//						}});			
//				}catch(RequestException ex){
//					MaterialToast.fireToast(ex.getLocalizedMessage());
//				}				
			}
		});
	}
	
	public void insertarUser() {	
		txtUsuario.setText(beanUserFiscalizador.getNombre()+ " "+beanUserFiscalizador.getApellido());	
		labelCapturarCodigo.setText(beanUserFiscalizador.getDniFiscalizador());
	}
}
