package com.inec.view.admin.grid;

import java.util.Iterator;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.inec.client.service.ServiceGestionMantenimiento;
import com.inec.client.service.ServiceGestionMantenimientoAsync;
import com.inec.view.admin.uiEnviarCorreo.UiEnviarCorreoImpl;
import com.inec.view.uiutil.UiBuscarFiscalizador.ClassBusqueda;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextArea;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;

public class GridEnviarCorreo {

	public static GridEnviarCorreo enviarCorreoInstance;

	public GridEnviarCorreo() {
		initComponents();
		initStyle();
		enviarCorreoInstance=this;
	}

	public GridEnviarCorreo(Boolean a){
		
	}
	private void initComponents() {

	}

	public Widget resultWidget() {
		final FormPanel form = new FormPanel();
		form.setAction("/myFormHandler");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(10);
		form.setWidget(panel);
		final TextBox tb = new TextBox();

		tb.setName("textBoxFormElement");
		panel.add(tb);
		ListBox lb = new ListBox();
		lb.setName("listBoxFormElement");
		lb.addItem("item1", "item1");
		lb.addItem("item2", "item2");
		lb.addItem("item3", "item3");

		panel.add(lb);

		FileUpload upload = new FileUpload();
		upload.setName("uploadFormElement");
		panel.add(upload);

		panel.add(new Button("Submit", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				form.submit();
			}
		}));

		form.addSubmitHandler(new FormPanel.SubmitHandler() {
			@Override
			public void onSubmit(SubmitEvent event) {
				if (tb.getText().length() == 0) {
					Window.alert("The text box must not be empty");
					event.cancel();
				}
			}
		});

		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				Window.alert(event.getResults());
			}
		});

		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.add(form);
		// Add the widgets to the root panel.
		return decoratorPanel;

	}

	FlexTable myFlexTable;
	MaterialTextBox txtAsunto;
	MaterialLabel labelDestinatarios;
	MaterialTextBox txtDestinatarios;
	MaterialButton btnOpenBuscarDestinatarios;
	MaterialLabel labelAsunto;
	MaterialLabel labelContenido;
	MaterialTextArea txtContenido;
	MaterialButton btnEnviarCorreo;

	public void initComponent() {
		myFlexTable = new FlexTable();
		txtAsunto = new MaterialTextBox();
		labelDestinatarios = new MaterialLabel();
		txtDestinatarios = new MaterialTextBox();
		btnOpenBuscarDestinatarios = new MaterialButton();
		labelAsunto = new MaterialLabel();
		labelContenido = new MaterialLabel();
		txtContenido = new MaterialTextArea();
		btnEnviarCorreo = new MaterialButton();
		labelDestinatarios.setText("Destinatarios:");
		btnOpenBuscarDestinatarios.setText("Buscar Destinatarios");
		labelAsunto.setText("Asunto:");
		labelContenido.setText("Mensaje:");
		btnEnviarCorreo.setText("Enviar Correo");

		myFlexTable.setWidget(0, 0, labelDestinatarios);
		myFlexTable.setWidget(0, 1, txtDestinatarios);
		myFlexTable.setWidget(0, 2, btnOpenBuscarDestinatarios);

		myFlexTable.setWidget(1, 0, labelAsunto);
		myFlexTable.setWidget(1, 1, txtAsunto);

		myFlexTable.setWidget(2, 0, labelContenido);
		myFlexTable.setWidget(2, 1, txtContenido);

		myFlexTable.setWidget(2, 2, btnEnviarCorreo);
	}

	public void insertarDestinatarios() {
		String correos="";
		Iterator<ClassBusqueda> iterator= UiEnviarCorreoImpl.listFiscalizadorChecked.iterator();
		while(iterator.hasNext()){
			correos+= iterator.next().correo+",";
		}
		
		txtDestinatarios.setText(correos.substring(0, correos.length()-1));
		txtAsunto.setFocus(true);
		
	}
	private final ServiceGestionMantenimientoAsync SERVICE = GWT.create(ServiceGestionMantenimiento.class);
	
	public void addListener() {
		btnOpenBuscarDestinatarios.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				openBuscarDestinatarios();
				
			}
		});

		btnEnviarCorreo.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				String paramAsunto= txtAsunto.getText().toString();
				String paramContent=txtContenido.getText().toString();
				String paramDestinatarios= txtDestinatarios.getText().toString();
				//is valid data
				SERVICE.enviarCorreoFiscalizadores(paramAsunto, paramContent, paramDestinatarios,new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {					
						MaterialToast.fireToast(caught.getMessage());
					}

					@Override
					public void onSuccess(Boolean result) {
						MaterialToast.fireToast("Envio correcto");	
						clear();
					}
					
				});
			}
		});
	}
UiEnviarCorreoImpl uiEnviar;
	public void openBuscarDestinatarios(){
		uiEnviar= new UiEnviarCorreoImpl();
		uiEnviar.show();
	}
	
	public  void clear(){
		this.txtAsunto.setText("");
		this.txtDestinatarios.setText("");
		this.txtContenido.setText("");
	}
	
	public Widget showPanelEnviarCorreo() {
		initComponent();
		addListener();
		return myFlexTable;
	}

	public Widget flexTableResult() {
		final FlexTable flexTable = new FlexTable();
		FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
		flexTable.addStyleName("flexTable");
		flexTable.setWidth("32em");
		flexTable.setCellSpacing(5);
		flexTable.setCellPadding(3);

		// Add some text
		cellFormatter.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		flexTable.setHTML(0, 0, "This is a FlexTable that supports" + " <b>colspans</b> and <b>rowspans</b>."
				+ " You can use it to format your page" + " or as a special purpose table.");
		cellFormatter.setColSpan(0, 0, 2);

		// Add a button that will add more rows to the table
		Button addRowButton = new Button("Add a Row");
		addRowButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addRow(flexTable);
			}
		});

		addRowButton.addStyleName("fixedWidthButton");

		// Add a button that will add more rows to the table
		Button removeRowButton = new Button("Remove a Row");
		removeRowButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				removeRow(flexTable);
			}
		});

		removeRowButton.addStyleName("fixedWidthButton");

		VerticalPanel buttonPanel = new VerticalPanel();
		buttonPanel.setStyleName("flexTable-buttonPanel");
		buttonPanel.add(addRowButton);
		buttonPanel.add(removeRowButton);
		flexTable.setWidget(0, 1, buttonPanel);
		cellFormatter.setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);

		// Add two rows to start
		addRow(flexTable);
		addRow(flexTable);

		// Add the widgets to the root panel.
		return flexTable;
	}

	private void addRow(FlexTable flexTable) {
		int numRows = flexTable.getRowCount();
		flexTable.setWidget(numRows, 0, new Image("http://www.tutorialspoint.com/images/gwt-mini.png"));
		flexTable.setWidget(numRows, 1, new Image("http://www.tutorialspoint.com/images/gwt-mini.png"));
		flexTable.getFlexCellFormatter().setRowSpan(0, 1, numRows + 1);
	}

	/**
	 * Remove a row from the flex table.
	 */
	private void removeRow(FlexTable flexTable) {
		int numRows = flexTable.getRowCount();
		if (numRows > 1) {
			flexTable.removeRow(numRows - 1);
			flexTable.getFlexCellFormatter().setRowSpan(0, 1, numRows - 1);
		}
	}

	public Widget onInitializeWidget() {
		// Make a new check box, and select it by default.
		Label labelAsunto = new Label("Asunto");
		Label labelContent = new Label("Contenido");
		TextBox textBox1 = new TextBox();

		CheckBox checkBox1 = new CheckBox("Check Me!");
		CheckBox checkBox2 = new CheckBox("Check Me!");

		Button redButton = new Button("Enviar Correo");
		redButton.setWidth("100px");
		redButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("Red Button clicked!");
			}
		});
		TextArea textArea1 = new TextArea();
		// set width as 10 characters
		textArea1.setCharacterWidth(20);

		// set height as 5 lines
		textArea1.setVisibleLines(20);

		// add text to text area
		// set check box as selected
		checkBox1.setValue(true);

		// disable a checkbox
		checkBox2.setEnabled(false);

		checkBox1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				CheckBox checkBox = (CheckBox) event.getSource();
				Window.alert("CheckBox is " + (checkBox.getValue() ? "" : "not") + " checked");
			}
		});

		// Add checkboxes to the root panel.
		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.setSize("1000px", "400px");

		HorizontalPanel panelHorizontal = new HorizontalPanel();
		panelHorizontal.add(labelContent);
		panelHorizontal.add(textArea1);
		VerticalPanel panel = new VerticalPanel();

		panel.add(panelHorizontal);
		panel.add(labelAsunto);
		panel.add(textBox1);
		panel.add(labelContent);
		panel.add(textArea1);
		panel.add(redButton);
		// scrollPanel.add(panelHorizontal);
		scrollPanel.add(panel);
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.add(scrollPanel);
		return decoratorPanel;
	}

	private void initStyle() {

	}
}
