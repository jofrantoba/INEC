package com.inec.view.uiutil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.inec.server.model.bean.PosicionFiscalizador;

public class UiBuscarRutasIntervalo {
	
	 DateTimeFormat fmt = DateTimeFormat.getFormat("h:mm a");
	 CellTable<PosicionFiscalizador> table = new CellTable<PosicionFiscalizador>();
	 public static List<PosicionFiscalizador> listBuscarFiscalizadorRutas = new ArrayList<>();
	 
		public void setData(List<PosicionFiscalizador> listFiscalizador){	
			listBuscarFiscalizadorRutas=listFiscalizador;
		}
	   public Widget onModuleLoad() {

		      TextColumn<PosicionFiscalizador> latitudColum = 
		    	      new TextColumn<PosicionFiscalizador>() {
		    	         @Override
		    	         public String getValue(PosicionFiscalizador object) {
		    	            return object.getLatitud().toString();
		    	         }
		    	      };
		    	      table.addColumn(latitudColum, "Latitud");
		    	      
	    	      TextColumn<PosicionFiscalizador> longitudColum = 
	    	    	      new TextColumn<PosicionFiscalizador>() {
	    	    	         @Override
	    	    	         public String getValue(PosicionFiscalizador object) {
	    	    	            return object.getLatitud().toString();
	    	    	         }
	    	    	      };
	    	    	      table.addColumn(longitudColum, "Longitud");
	    	    	     
		      TextColumn<PosicionFiscalizador> horaColumn = 
		    	      new TextColumn<PosicionFiscalizador>() {
		    	         @Override
		    	         public String getValue(PosicionFiscalizador object) {
		    	            return String.valueOf(fmt.format(new Date(object.getVersion())));
		    	         }
		    	      };
		    	      table.addColumn(horaColumn, "Hora");
		    	      
	    	      TextColumn<PosicionFiscalizador> ubigeoColumn = 
	    	    	      new TextColumn<PosicionFiscalizador>() {
	    	    	         @Override
	    	    	         public String getValue(PosicionFiscalizador object) {
	    	    	            return object.getDepartamento()+"-"+ object.getProvincia()+"-"+object.getDistrito();
	    	    	         }
	    	    	      };
	    	    	      table.addColumn(ubigeoColumn, "Ubigeo");	    	    	        	    	    	  

		      table.setRowCount(listBuscarFiscalizadorRutas.size(), true);
		     
		      table.setRowData(0, listBuscarFiscalizadorRutas);		     

		      VerticalPanel panel = new VerticalPanel();
		      panel.setBorderWidth(1);	    
		      panel.setWidth("400");
		      panel.add(table);	    
		      return panel;
		   }
}
