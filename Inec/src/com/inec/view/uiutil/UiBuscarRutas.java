package com.inec.view.uiutil;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.view.admin.grid.GridRutas;

public class UiBuscarRutas {
	

	 CellTable<UsuarioFiscalizador> table = new CellTable<UsuarioFiscalizador>();
	 public static List<UsuarioFiscalizador> listBuscarFiscalizadorRutas = new ArrayList<>();
	 
		public void setData(List<UsuarioFiscalizador> listFiscalizador){	
			listBuscarFiscalizadorRutas=listFiscalizador;
		}
	   public Widget onModuleLoad() {
		      
		      TextColumn<UsuarioFiscalizador> dniColum = 
		    	      new TextColumn<UsuarioFiscalizador>() {
		    	         @Override
		    	         public String getValue(UsuarioFiscalizador object) {
		    	            return object.getDniFiscalizador();
		    	         }
		    	      };
		    	      table.addColumn(dniColum, "Dni");
		    	      
	    	      TextColumn<UsuarioFiscalizador> nombreColum = 
	    	    	      new TextColumn<UsuarioFiscalizador>() {
	    	    	         @Override
	    	    	         public String getValue(UsuarioFiscalizador object) {
	    	    	            return object.getNombre().toString();
	    	    	         }
	    	    	      };
	    	    	      table.addColumn(nombreColum, "Nombre");
	    	    	      
		      TextColumn<UsuarioFiscalizador> apellidoColum = 
		    	      new TextColumn<UsuarioFiscalizador>() {
		    	         @Override
		    	         public String getValue(UsuarioFiscalizador object) {
		    	            return object.getApellido().toString();
		    	         }
		    	      };
		    	      table.addColumn(apellidoColum, "Apellido");
		    	      
	    	      TextColumn<UsuarioFiscalizador> zonaColum = 
	    	    	      new TextColumn<UsuarioFiscalizador>() {
	    	    	         @Override
	    	    	         public String getValue(UsuarioFiscalizador object) {
	    	    	            return object.getNombreZona().toString();
	    	    	         }
	    	    	      };
	    	    	      table.addColumn(zonaColum, "Zona");	    	    	        	    	    	  

	    	    	      final SingleSelectionModel<UsuarioFiscalizador> selectionModel 
	    	    	      = new SingleSelectionModel<UsuarioFiscalizador>();
	    	    	     
	    	    	      selectionModel.addSelectionChangeHandler(
	    	    	      new SelectionChangeEvent.Handler() {
	    	    	         public void onSelectionChange(SelectionChangeEvent event) {
	    	    	            UsuarioFiscalizador selected = selectionModel.getSelectedObject();
	    	    	            if (selected != null) {
	    	    	               GridRutas.beanUserFiscalizador=selected;
	    	    	            }
	    	    	         }
	    	    	      });
	    	    	      table.setSelectionModel(selectionModel);
		      table.setRowCount(listBuscarFiscalizadorRutas.size(), true);
		     
		      table.setRowData(0, listBuscarFiscalizadorRutas);

		      VerticalPanel panel = new VerticalPanel();
		      panel.setBorderWidth(1);	    
		      panel.setWidth("400");
		      panel.add(table);	    
		      return panel;
		   }
}
