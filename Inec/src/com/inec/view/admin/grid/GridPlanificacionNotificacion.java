package com.inec.view.admin.grid;



import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.inec.client.service.ServiceGestionMantenimiento;
import com.inec.client.service.ServiceGestionMantenimientoAsync;
import com.inec.server.model.bean.ProgramacionNotificacion;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.view.uiutil.UiBuscarRutas;
import com.inec.view.uiutil.UiTablePlanificacion;

import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialDatePicker;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class GridPlanificacionNotificacion extends FlexTable {
	FlexTable myFlexTable;	
	MaterialTextBox txtFrecuencia;
	MaterialButton btnCrear;
	MaterialButton btnCancelar;
	MaterialLabel labelDiaInicio;
	MaterialLabel labelDiaFin;
	MaterialLabel labelHoraInicio;
	MaterialLabel labelHoraFin;
	 MaterialDatePicker datePickerLeft;
	 MaterialDatePicker datePickerRight;
	 MaterialLabel captureFechaIzquierda;
	 MaterialLabel captureFechaDerecha;
	 MaterialButton btnListar;
	 MaterialButton btnExportar;
	 UiBuscarRutas uiBuscarRutas;
	 MaterialLabel labelCapturarCodigo;	 	
	 MaterialLabel labelFrecuencia;	 	
	 ListBox listaInicio;
	 ListBox listaFin;
	 ListBox listaFrecuencia;
	 
	 MaterialTextBox txtHoraInicio;
	 MaterialTextBox txtHoraFin;
	 
	 
	 protected VerticalPanel verticalPanel;
	 public static UsuarioFiscalizador beanUserFiscalizador;
	 public static GridPlanificacionNotificacion gridPlanificacionNotificacion;
	 
	 public GridPlanificacionNotificacion() {		
		 gridPlanificacionNotificacion=this;
	}
	public void initComponents() {
		myFlexTable = new FlexTable();				
		labelDiaInicio = new MaterialLabel();		
		labelDiaFin = new MaterialLabel();		
		labelHoraInicio = new MaterialLabel();		
		labelHoraFin = new MaterialLabel();	
		
		txtHoraInicio= new MaterialTextBox();
		txtHoraFin= new MaterialTextBox();
		txtFrecuencia= new MaterialTextBox();
		
		txtHoraInicio.setType(InputType.NUMBER);
		txtHoraFin.setType(InputType.NUMBER);
		txtFrecuencia.setType(InputType.NUMBER);
		
		labelFrecuencia= new MaterialLabel();
		btnCrear= new MaterialButton();
		btnCancelar= new MaterialButton();
		btnCrear.setText("Crear");
		btnCancelar.setText("Cancelar");
		
		listaInicio= new ListBox();
		listaInicio.addItem("Lunes");
		listaInicio.addItem("Martes");
		listaInicio.addItem("Miercoles");
		listaInicio.addItem("Jueves");
		listaInicio.addItem("Viernes");
		listaInicio.addItem("Sabado");
		listaInicio.addItem("Domingo");
		listaInicio.setVisible(true);
		
		listaFin= new ListBox();
		listaFin.addItem("Lunes");
		listaFin.addItem("Martes");
		listaFin.addItem("Miercoles");
		listaFin.addItem("Jueves");
		listaFin.addItem("Viernes");
		listaFin.addItem("Sabado");
		listaFin.addItem("Domingo");
		listaFin.setVisibleItemCount(1);
		listaFin.setVisible(true);
		
		listaFrecuencia= new ListBox();
		listaFrecuencia.addItem("Minutos");
		listaFrecuencia.addItem("Horas");
		listaFrecuencia.setVisibleItemCount(1);
		listaFrecuencia.setVisible(true);
				
		labelDiaInicio.setText("Dia Inicio");
		labelDiaFin.setText("Dia Fin");
		labelDiaInicio.setWidth("100px");		
		labelDiaFin.setWidth("100px");
		labelHoraFin.setText("Hora Fin:");
		labelHoraInicio.setText("Hora Inicio:");
		labelFrecuencia.setText("Frecuencia:");
		
		listaInicio.setWidth("150px");
		listaFin.setWidth("150px");
		listaFrecuencia.setWidth("150px");
		
		this.setWidget(0, 0, new MaterialLabel());
		this.setWidget(0, 1, labelDiaInicio);
		this.setWidget(0, 2, listaInicio);
		this.setWidget(0, 3, labelDiaFin);
		this.setWidget(0, 4, listaFin);
		this.setWidget(0, 5, labelHoraInicio);
		this.setWidget(0, 6, txtHoraInicio);
		this.setWidget(0, 7, new MaterialLabel());		
		this.setWidget(1, 0, new MaterialLabel());		
		this.setWidget(1, 1, labelHoraFin);
		this.setWidget(1, 2, txtHoraFin);
		this.setWidget(1, 3, labelFrecuencia);
		this.setWidget(1, 4, txtFrecuencia);
		this.setWidget(1, 5, btnCrear);	
		this.setWidget(1, 7, new MaterialLabel());
		FlexCellFormatter a = new FlexCellFormatter();
		a.setColSpan(2, 0, 8);
		this.setCellFormatter(a);
		this.setCellPadding(0);
		this.setCellSpacing(0);
		
		SERVICE.listProgramaciones(new AsyncCallback<List<ProgramacionNotificacion>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<ProgramacionNotificacion> result) {
				// TODO Auto-generated method stub
				UiTablePlanificacion uitable= new UiTablePlanificacion();
				uitable.setData(result);
				verticalPanel=(VerticalPanel) uitable.onModuleLoad();
				setWidget(2, 0, verticalPanel);
			}
		});
	}
	
	public Widget showPanelMonitoreo() {
		initComponents();
		addListener();
		return this;
	}
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public void addListener() {
		btnCrear.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				String diaInicio = listaInicio.getSelectedValue();				
				String diaFin= listaFin.getSelectedValue();
				Integer horaInicio= Integer.parseInt(txtHoraInicio.getText().toString()); 
				Integer horaFin= Integer.parseInt(txtHoraFin.getText().toString());
				Integer frecuencia= Integer.parseInt(txtFrecuencia.getText().toString()); 
				String tipoFrecuencia= "HORAS";
				
				SERVICE.planificarNotificacion(diaInicio, diaFin, horaInicio, horaFin, frecuencia, tipoFrecuencia, new AsyncCallback<ProgramacionNotificacion>(){

					@Override
					public void onFailure(Throwable caught) {
						MaterialToast.fireToast(caught.getMessage());
						
					}

					@Override
					public void onSuccess(ProgramacionNotificacion result) {
						MaterialToast.fireToast("Se agrego una planificacion");	
						SERVICE.listProgramaciones(new AsyncCallback<List<ProgramacionNotificacion>>() {
							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub								
							}

							@Override
							public void onSuccess(List<ProgramacionNotificacion> result) {
								// TODO Auto-generated method stub
								UiTablePlanificacion uitable= new UiTablePlanificacion();
								uitable.setData(result);
								verticalPanel=(VerticalPanel) uitable.onModuleLoad();
								setWidget(2, 0, verticalPanel);
							}
						});
						
						
					}					
				});

			}
		});
	}
}
