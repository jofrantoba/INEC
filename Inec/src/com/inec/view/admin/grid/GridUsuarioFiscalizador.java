package com.inec.view.admin.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.view.client.SingleSelectionModel;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.view.uiutil.FilteredListDataProvider;
import com.inec.view.uiutil.IFilter;

public class GridUsuarioFiscalizador extends DataGrid<UsuarioFiscalizador> {

	private List<UsuarioFiscalizador> data = new ArrayList<UsuarioFiscalizador>();
	private final SingleSelectionModel<UsuarioFiscalizador> selectionModel = new SingleSelectionModel<UsuarioFiscalizador>();
	private FilteredListDataProvider<UsuarioFiscalizador> dataProvider = new FilteredListDataProvider<UsuarioFiscalizador>(
			new IFilter<UsuarioFiscalizador>() {

				@Override
				public boolean isValid(UsuarioFiscalizador value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getNombre()
								.toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}

			});

	private SimplePager pager;

	public GridUsuarioFiscalizador() {
		initComponents();
		initStyle();
	}

	private void initComponents() {
		this.setWidth("100%");
		this.setHeight("90%");
		initColumns();
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		dataProvider.setList(data);
		dataProvider.addDataDisplay(this);
		this.setVisible(true);
		this.setSelectionModel(selectionModel);
		SimplePager.Resources pagerResources = GWT
				.create(SimplePager.Resources.class);
		pager = new SimplePager(SimplePager.TextLocation.CENTER,
				pagerResources, false, 0, true);
		pager.setDisplay(this);
		pager.setVisible(true);
	}
		

	private void initStyle() {
		// MyResource.INSTANCE.getStlGridData().ensureInjected();
		// this.addStyleName(MyResource.INSTANCE.getStlGridData().stlGridData());
		this.getColumn(0).setHorizontalAlignment(
				HasHorizontalAlignment.ALIGN_LEFT);
		this.getColumn(1).setHorizontalAlignment(
				HasHorizontalAlignment.ALIGN_LEFT);
		this.getColumn(2).setHorizontalAlignment(
				HasHorizontalAlignment.ALIGN_LEFT);
		this.getColumn(3).setHorizontalAlignment(
				HasHorizontalAlignment.ALIGN_LEFT);
		this.getColumn(4).setHorizontalAlignment(
				HasHorizontalAlignment.ALIGN_LEFT);
		this.getColumn(5).setHorizontalAlignment(
				HasHorizontalAlignment.ALIGN_LEFT);
		this.getColumn(6).setHorizontalAlignment(
				HasHorizontalAlignment.ALIGN_LEFT);
		this.getColumn(7).setHorizontalAlignment(
				HasHorizontalAlignment.ALIGN_LEFT);
	}

	private void initColumns() {		
		this.addColumn(nombre, "NOMBRES");
		this.addColumn(apellido, "APELLIDOS");
		this.addColumn(dni, "DNI");
		this.addColumn(telefono, "TELEFONO");
		this.addColumn(correo_personal, "CORREO PERS.");
		this.addColumn(correo_corporativo, "CORREO CORP.");
		this.addColumn(numero, "NUMERO");
		this.addColumn(estado, "ESTADO");		
		
	}

	public Column<UsuarioFiscalizador, String> code = new Column<UsuarioFiscalizador, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioFiscalizador object) {
			return object.getCodeUsuarioFiscalizador();
		}

	};

	public Column<UsuarioFiscalizador, String> nombre = new Column<UsuarioFiscalizador, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioFiscalizador object) {
			return object.getNombre();
		}

	};
	
	public Column<UsuarioFiscalizador, String> apellido = new Column<UsuarioFiscalizador, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioFiscalizador object) {
			return object.getApellido();
		}

	};
	
	public Column<UsuarioFiscalizador, String> dni = new Column<UsuarioFiscalizador, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioFiscalizador object) {
			return object.getDniFiscalizador();
		}

	};
	
	public Column<UsuarioFiscalizador, String> telefono = new Column<UsuarioFiscalizador, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioFiscalizador object) {
			return object.getNumero();
		}

	};
	
	public Column<UsuarioFiscalizador, String> correo_corporativo = new Column<UsuarioFiscalizador, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioFiscalizador object) {
			return object.getCorreoCorporativo();
		}

	};
	
	
	public Column<UsuarioFiscalizador, String> estado = new Column<UsuarioFiscalizador, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioFiscalizador object) {
			return object.getEstado();
		}

	};
	
	public Column<UsuarioFiscalizador, String> correo_personal = new Column<UsuarioFiscalizador, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioFiscalizador object) {
			return object.getCorreoPersonal();
		}

	};
	
	public Column<UsuarioFiscalizador, String> numero = new Column<UsuarioFiscalizador, String>(
			new TextCell()) {

		@Override
		public String getValue(UsuarioFiscalizador object) {
			return object.getNumero();
		}

	};

	public void setData(List<UsuarioFiscalizador> data) {
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0, data);
		this.setPageSize(data.size());
		dataProvider.setList(data);
		dataProvider.refresh();
	}
	
	public void refreshGrid(){
    	this.selectionModel.clear();
    	this.setRowCount(this.data.size(), true);
        this.setRowData(0, this.data);
        this.setPageSize(this.data.size());
        dataProvider.setList(this.data);
        dataProvider.refresh();
    }

	public List<UsuarioFiscalizador> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<UsuarioFiscalizador> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<UsuarioFiscalizador> getDataProvider() {
		return dataProvider;
	}
	
}

