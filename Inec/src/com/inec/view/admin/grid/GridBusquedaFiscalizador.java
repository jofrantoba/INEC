package com.inec.view.admin.grid;

import com.google.gwt.user.cellview.client.DataGrid;
import com.inec.view.uiutil.UiBuscarFiscalizador;

public class GridBusquedaFiscalizador  extends DataGrid<UiBuscarFiscalizador>{
//	private List<UiBuscarFiscalizador> data = new ArrayList<UiBuscarFiscalizador>();
//	private final SingleSelectionModel<UiBuscarFiscalizador> selectionModel = new SingleSelectionModel<UiBuscarFiscalizador>();
//	private FilteredListDataProvider<UiBuscarFiscalizador> dataProvider = new FilteredListDataProvider<UiBuscarFiscalizador>(
//			new IFilter<UiBuscarFiscalizador>() {
//
//				@Override
//				public boolean isValid(UiBuscarFiscalizador value, String filter) {
//					if (filter == null || value == null) {
//						return true;
//					} else {
//						String values = value.
//								.toLowerCase();
//						return values.contains(filter.toLowerCase());
//					}
//				}
//
//			});
//
//	private SimplePager pager;
//
//	public GridUiBuscarFiscalizador() {
//		initComponents();
//		initStyle();
//	}
//
//	private void initComponents() {
//		this.setWidth("100%");
//		this.setHeight("90%");
//		initColumns();
//		this.setRowCount(data.size(), true);
//		this.setRowData(0, data);
//		dataProvider.setList(data);
//		dataProvider.addDataDisplay(this);
//		this.setVisible(true);
//		this.setSelectionModel(selectionModel);
//		SimplePager.Resources pagerResources = GWT
//				.create(SimplePager.Resources.class);
//		pager = new SimplePager(SimplePager.TextLocation.CENTER,
//				pagerResources, false, 0, true);
//		pager.setDisplay(this);
//		pager.setVisible(true);
//	}
//		
//
//	private void initStyle() {
//		// MyResource.INSTANCE.getStlGridData().ensureInjected();
//		// this.addStyleName(MyResource.INSTANCE.getStlGridData().stlGridData());
//		this.getColumn(0).setHorizontalAlignment(
//				HasHorizontalAlignment.ALIGN_LEFT);
//		this.getColumn(1).setHorizontalAlignment(
//				HasHorizontalAlignment.ALIGN_LEFT);
//		this.getColumn(2).setHorizontalAlignment(
//				HasHorizontalAlignment.ALIGN_LEFT);
//		this.getColumn(3).setHorizontalAlignment(
//				HasHorizontalAlignment.ALIGN_LEFT);
//		this.getColumn(4).setHorizontalAlignment(
//				HasHorizontalAlignment.ALIGN_LEFT);
//		this.getColumn(5).setHorizontalAlignment(
//				HasHorizontalAlignment.ALIGN_LEFT);
//		this.getColumn(6).setHorizontalAlignment(
//				HasHorizontalAlignment.ALIGN_LEFT);
//		this.getColumn(7).setHorizontalAlignment(
//				HasHorizontalAlignment.ALIGN_LEFT);
//	}
//
//	private void initColumns() {		
//		this.addColumn(nombre, "NOMBRES");
//		this.addColumn(apellido, "APELLIDOS");
//		this.addColumn(dni, "DNI");
//		this.addColumn(telefono, "TELEFONO");
//		this.addColumn(correo_personal, "CORREO PERSONAL");
//		this.addColumn(correo_corporativo, "CORREO CORPORATIVO");
//		this.addColumn(numero, "NUMERO");
//		this.addColumn(estado, "ESTADO");		
//		
//	}
//
//	public Column<UiBuscarFiscalizador, String> code = new Column<UiBuscarFiscalizador, String>(
//			new TextCell()) {
//
//		@Override
//		public String getValue(UiBuscarFiscalizador object) {
//			return object.getCodeUiBuscarFiscalizador();
//		}
//
//	};
//
//	public Column<UiBuscarFiscalizador, String> nombre = new Column<UiBuscarFiscalizador, String>(
//			new TextCell()) {
//
//		@Override
//		public String getValue(UiBuscarFiscalizador object) {
//			return object.getNombre();
//		}
//
//	};
//	
//	public Column<UiBuscarFiscalizador, String> apellido = new Column<UiBuscarFiscalizador, String>(
//			new TextCell()) {
//
//		@Override
//		public String getValue(UiBuscarFiscalizador object) {
//			return object.getApellido();
//		}
//
//	};
//	
//	public Column<UiBuscarFiscalizador, String> dni = new Column<UiBuscarFiscalizador, String>(
//			new TextCell()) {
//
//		@Override
//		public String getValue(UiBuscarFiscalizador object) {
//			return object.getDniFiscalizador();
//		}
//
//	};
//	
//	public Column<UiBuscarFiscalizador, String> telefono = new Column<UiBuscarFiscalizador, String>(
//			new TextCell()) {
//
//		@Override
//		public String getValue(UiBuscarFiscalizador object) {
//			return object.getNumero();
//		}
//
//	};
//	
//	public Column<UiBuscarFiscalizador, String> correo_corporativo = new Column<UiBuscarFiscalizador, String>(
//			new TextCell()) {
//
//		@Override
//		public String getValue(UiBuscarFiscalizador object) {
//			return object.getCorreoCorporativo();
//		}
//
//	};
//	
//	
//	public Column<UiBuscarFiscalizador, String> estado = new Column<UiBuscarFiscalizador, String>(
//			new TextCell()) {
//
//		@Override
//		public String getValue(UiBuscarFiscalizador object) {
//			return object.getEstado();
//		}
//
//	};
//	
//	public Column<UiBuscarFiscalizador, String> correo_personal = new Column<UiBuscarFiscalizador, String>(
//			new TextCell()) {
//
//		@Override
//		public String getValue(UiBuscarFiscalizador object) {
//			return object.getCorreoPersonal();
//		}
//
//	};
//	
//	public Column<UiBuscarFiscalizador, String> numero = new Column<UiBuscarFiscalizador, String>(
//			new TextCell()) {
//
//		@Override
//		public String getValue(UiBuscarFiscalizador object) {
//			return object.getNumero();
//		}
//
//	};
//
//	public void setData(List<UiBuscarFiscalizador> data) {
//		this.data = data;
//		this.setRowCount(data.size(), true);
//		this.setRowData(0, data);
//		this.setPageSize(data.size());
//		dataProvider.setList(data);
//		dataProvider.refresh();
//	}
//	
//	public void refreshGrid(){
//    	this.selectionModel.clear();
//    	this.setRowCount(this.data.size(), true);
//        this.setRowData(0, this.data);
//        this.setPageSize(this.data.size());
//        dataProvider.setList(this.data);
//        dataProvider.refresh();
//    }
//
//	public List<UiBuscarFiscalizador> getData() {
//		return data;
//	}
//
//	public SimplePager getPager() {
//		return pager;
//	}
//
//	@Override
//	public SingleSelectionModel<UiBuscarFiscalizador> getSelectionModel() {
//		return selectionModel;
//	}
//
//	public FilteredListDataProvider<UiBuscarFiscalizador> getDataProvider() {
//		return dataProvider;
//	}
	
}
