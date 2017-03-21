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
import com.inec.server.model.bean.Zona;
import com.inec.view.uiutil.FilteredListDataProvider;
import com.inec.view.uiutil.IFilter;

public class GridZona extends DataGrid<Zona>{
	private List<Zona> data = new ArrayList<Zona>();
	private final SingleSelectionModel<Zona> selectionModel = new SingleSelectionModel<Zona>();
	private FilteredListDataProvider<Zona> dataProvider = new FilteredListDataProvider<Zona>(
			new IFilter<Zona>() {

				@Override
				public boolean isValid(Zona value, String filter) {
					if (filter == null || value == null) {
						return true;
					} else {
						String values = value.getNombreZona()
								.toLowerCase();
						return values.contains(filter.toLowerCase());
					}
				}
			});

	private SimplePager pager;

	public GridZona() {
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
		this.getColumn(0).setHorizontalAlignment(
				HasHorizontalAlignment.ALIGN_LEFT);
	}

	private void initColumns() {		
		this.addColumn(nombre, "NOMBRES");
	}

	public Column<Zona, String> code = new Column<Zona, String>(
			new TextCell()) {

		@Override
		public String getValue(Zona object) {
			return object.getCodeZona();
		}

	};

	public Column<Zona, String> nombre = new Column<Zona, String>(
			new TextCell()) {

		@Override
		public String getValue(Zona object) {
			return object.getNombreZona();
		}

	};	

	public void setData(List<Zona> data) {
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

	public List<Zona> getData() {
		return data;
	}

	public SimplePager getPager() {
		return pager;
	}

	@Override
	public SingleSelectionModel<Zona> getSelectionModel() {
		return selectionModel;
	}

	public FilteredListDataProvider<Zona> getDataProvider() {
		return dataProvider;
	}
}
