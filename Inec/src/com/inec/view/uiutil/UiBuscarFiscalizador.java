package com.inec.view.uiutil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionModel;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.view.admin.uiEnviarCorreo.UiEnviarCorreo;

public class UiBuscarFiscalizador {
	 public static List<ClassBusqueda> listBuscarFiscalizador = new ArrayList<>();
	 
	 CellTable<ClassBusqueda> table = new CellTable<ClassBusqueda>();
	 public Widget loadTableBusqueda(){
		 return onModuleLoad();
	 }
	  public static final ProvidesKey<ClassBusqueda> KEY_PROVIDER = new ProvidesKey<ClassBusqueda>() {
	      @Override
	      public Object getKey(ClassBusqueda item) {
	        return item;
	      }
	    };
	   public static class ClassBusqueda {
		   public final Integer numero;
			public final String nombre;
			public final String apellido;
			public final String zona;
			public final String correo;
			public final CheckBox uiMarcar;

			public ClassBusqueda(Integer numero, String nombre, String apellido, String zona, String correo,
					CheckBox uiMarcar) {
				super();
				this.numero = numero;
				this.nombre = nombre;
				this.apellido = apellido;
				this.zona = zona;
				this.correo = correo;
				this.uiMarcar = uiMarcar;
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((correo == null) ? 0 : correo.hashCode());
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				ClassBusqueda other = (ClassBusqueda) obj;
				if (correo == null) {
					if (other.correo != null)
						return false;
				} else if (!correo.equals(other.correo))
					return false;
				return true;
			}					
	   }

	   public Widget onModuleLoad() {	      
	      table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
	     
	      TextColumn<ClassBusqueda> numeroColum = 
	    	      new TextColumn<ClassBusqueda>() {
	    	         @Override
	    	         public String getValue(ClassBusqueda object) {
	    	            return object.numero.toString();
	    	         }
	    	      };
	    	      table.addColumn(numeroColum, "Numero");
	    	      
    	      TextColumn<ClassBusqueda> nombreColum = 
    	    	      new TextColumn<ClassBusqueda>() {
    	    	         @Override
    	    	         public String getValue(ClassBusqueda object) {
    	    	            return object.nombre.toString();
    	    	         }
    	    	      };
    	    	      table.addColumn(nombreColum, "Nombre");
    	    	      
	      TextColumn<ClassBusqueda> apellidoColum = 
	    	      new TextColumn<ClassBusqueda>() {
	    	         @Override
	    	         public String getValue(ClassBusqueda object) {
	    	            return object.apellido.toString();
	    	         }
	    	      };
	    	      table.addColumn(apellidoColum, "Apellido");
	    	      
    	      TextColumn<ClassBusqueda> zonaColum = 
    	    	      new TextColumn<ClassBusqueda>() {
    	    	         @Override
    	    	         public String getValue(ClassBusqueda object) {
    	    	            return object.zona.toString();
    	    	         }
    	    	      };
    	    	      table.addColumn(zonaColum, "Zona");
    	    	      
    	      TextColumn<ClassBusqueda> correoColum = 
    	    	      new TextColumn<ClassBusqueda>() {
    	    	         @Override
    	    	         public String getValue(ClassBusqueda object) {
    	    	            return object.correo.toString();
    	    	         }
    	    	      };
    	    	      table.addColumn(correoColum, "Correo");    	    	          	    	      
    	    	      final SelectionModel<ClassBusqueda> selectionModels = new MultiSelectionModel<ClassBusqueda>(
    	  	    	        KEY_PROVIDER);	        	    	          	    	      
    	    	      table.setSelectionModel(selectionModels,
    	  	    	        DefaultSelectionEventManager.<ClassBusqueda> createCheckboxManager());
         Column<ClassBusqueda, Boolean> checkColumn = 
	      new Column<ClassBusqueda, Boolean>(new CheckboxCell(true,false)) 
	      {
	         @Override
	         public Boolean getValue(ClassBusqueda object)
	         {
	        	 Boolean result= selectionModels.isSelected(object);
	        	if(result){
	        		UiEnviarCorreo.listFiscalizadorChecked.add(object);	        		
	        	}else{
	        		if(UiEnviarCorreo.listFiscalizadorChecked.contains(object)){
	        			UiEnviarCorreo.listFiscalizadorChecked.remove(object);
	        		}
	        	}
	        	return result;
	         }   
	      };
	      table.addColumn(checkColumn, "Elegir");    	    	    	    	  	        
	      table.setRowCount(listBuscarFiscalizador.size(), true);
	     
	      table.setRowData(0, listBuscarFiscalizador);

	      VerticalPanel panel = new VerticalPanel();
	      panel.setBorderWidth(1);	    
	      panel.setWidth("400");
	      panel.add(table);	    
	      return panel;
	   }
	
	public void setData(List<UsuarioFiscalizador> listFiscalizador){
		Iterator<UsuarioFiscalizador> listFiscalizadorIterator= listFiscalizador.iterator();
		List<ClassBusqueda> listClassBusqueda= new ArrayList<>();
		Integer contador=0;
		while(listFiscalizadorIterator.hasNext()){
			contador++;
			UsuarioFiscalizador beanUsuarioFiscalizador= listFiscalizadorIterator.next();
			
			CheckBox checkBoxItem = new CheckBox();					
			checkBoxItem.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
				}
			});
			ClassBusqueda beanClassBusqueda= new ClassBusqueda(contador, beanUsuarioFiscalizador.getNombre(), 
					beanUsuarioFiscalizador.getApellido(), beanUsuarioFiscalizador.getNombreZona(), 
					beanUsuarioFiscalizador.getCorreoCorporativo(), checkBoxItem);
			listClassBusqueda.add(beanClassBusqueda);
		}		
		listBuscarFiscalizador=listClassBusqueda;
	}
	
}
