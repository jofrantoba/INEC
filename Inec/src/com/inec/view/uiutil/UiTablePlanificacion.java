package com.inec.view.uiutil;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.inec.server.model.bean.ProgramacionNotificacion;

public class UiTablePlanificacion {

	 CellTable<ProgramacionNotificacion> table = new CellTable<ProgramacionNotificacion>();
	 public static List<ProgramacionNotificacion> listaProgramaciones = new ArrayList<>();
	 
		public void setData(List<ProgramacionNotificacion> listFiscalizador){	
			listaProgramaciones=listFiscalizador;
		}
	   public Widget onModuleLoad() {
		      
		      TextColumn<ProgramacionNotificacion> diaInicioColum = 
		    	      new TextColumn<ProgramacionNotificacion>() {
		    	         @Override
		    	         public String getValue(ProgramacionNotificacion object) {
		    	            return object.getDiaInicio();
		    	         }
		    	      };
    	      table.addColumn(diaInicioColum, "Dia Inicio");
		    	      
    	      TextColumn<ProgramacionNotificacion> diaFinColum = 
    	    	      new TextColumn<ProgramacionNotificacion>() {
    	    	         @Override
    	    	         public String getValue(ProgramacionNotificacion object) {
    	    	            return object.getDiaFin();
    	    	         }
    	    	      };
    	      table.addColumn(diaFinColum, "Dia Fin");
	    	    	      
		      TextColumn<ProgramacionNotificacion> horaInicioColum = 
		    	      new TextColumn<ProgramacionNotificacion>() {
		    	         @Override
		    	         public String getValue(ProgramacionNotificacion object) {
		    	            return object.getHoraInicioLabel();
		    	         }
		    	      };
    	      table.addColumn(horaInicioColum, "Hora Inicio");
		    	      
		    	      
	      TextColumn<ProgramacionNotificacion> horaFinColum = 
	    	      new TextColumn<ProgramacionNotificacion>() {
	    	         @Override
	    	         public String getValue(ProgramacionNotificacion object) {
	    	            return object.getHoraFinLabel();
	    	         }
	    	      };
	      table.addColumn(horaFinColum, "Hora Fin");	 
	    	    	      
	      TextColumn<ProgramacionNotificacion> frecuenciaColum = 
	    	      new TextColumn<ProgramacionNotificacion>() {
	    	         @Override
	    	         public String getValue(ProgramacionNotificacion object) {
	    	            return object.getFrecuencia().toString();
	    	         }
	    	      };
	      table.addColumn(frecuenciaColum, "Frecuencia");
	    	    	    	      
	      TextColumn<ProgramacionNotificacion> fechaCreacionColumn = 
	    	      new TextColumn<ProgramacionNotificacion>() {
	    	         @Override
	    	         public String getValue(ProgramacionNotificacion object) {
	    	            return object.getFecha();
	    	         }
	    	      };
	      table.addColumn(fechaCreacionColumn, "Fecha Creacion");
	      TextColumn<ProgramacionNotificacion> fechaFinColumn = 
	    	      new TextColumn<ProgramacionNotificacion>() {
	    	         @Override
	    	         public String getValue(ProgramacionNotificacion object) {
	    	            return object.getFechaFin();
	    	         }
	    	      };
	      table.addColumn(fechaFinColumn, "Fecha Fin");
	    	    	    	    	      
    	      TextColumn<ProgramacionNotificacion> estadoColumn = 
    	    	      new TextColumn<ProgramacionNotificacion>() {
    	    	         @Override
    	    	         public String getValue(ProgramacionNotificacion object) {
    	    	            return object.getEstado();
    	    	         }
    	    	      };
    	    table.addColumn(estadoColumn, "Estado");

		      table.setRowCount(listaProgramaciones.size(), true);		     
		      table.setRowData(0, listaProgramaciones);

		      VerticalPanel panel = new VerticalPanel();
		      panel.setBorderWidth(1);	    
		      panel.setWidth("400");
		      panel.add(table);	    
		      return panel;
		   }
}
