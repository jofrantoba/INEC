package com.inec.view.uiutil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.cell.client.CheckboxCell;
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
import com.inec.view.uiutil.UiBuscarFiscalizador.ClassBusqueda;

public class UiFiltroFiscalizador {
	public static List<ClassBusqueda> listBuscarFiscalizador = new ArrayList<>();
	List<UsuarioFiscalizador> listFiscalizador;
	 CellTable<ClassBusqueda> table = new CellTable<ClassBusqueda>();
	 public UiFiltroFiscalizador(List<UsuarioFiscalizador> listFiscalizador) {		
		 this.listFiscalizador= listFiscalizador;
	}
	 
	 public Widget loadTableBusqueda(){
		 return onModuleLoad();
	 }
	  public static final ProvidesKey<ClassBusqueda> KEY_PROVIDER = new ProvidesKey<ClassBusqueda>() {
	      @Override
	      public Object getKey(ClassBusqueda item) {
	        return item;
	      }
	    };

	   public Widget onModuleLoad() {
	      setData(listFiscalizador);
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
			
			ClassBusqueda beanClassBusqueda= new ClassBusqueda(contador, beanUsuarioFiscalizador.getNombre(), 
					beanUsuarioFiscalizador.getApellido(), beanUsuarioFiscalizador.getNombreZona(), 
					beanUsuarioFiscalizador.getCorreoCorporativo(), checkBoxItem);
			listClassBusqueda.add(beanClassBusqueda);
		}		
		listBuscarFiscalizador=listClassBusqueda;
	}
}
