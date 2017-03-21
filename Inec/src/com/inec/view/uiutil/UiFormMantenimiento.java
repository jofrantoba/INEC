package com.inec.view.uiutil;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.inec.client.resource.MyResource;

import gwt.material.design.client.ui.MaterialButton;

public class UiFormMantenimiento extends Composite implements ClickHandler {

    public static final int BOTONOPER0 = 0;
    public static final int BOTONOPER1 = 1;
    public static final int BOTONOPER2 = 2;
    public static final int BOTONOPER3 = 3;
    public static final int BOTONOPER4 = 4;
    protected MaterialButton btnOper1;
    protected MaterialButton btnOper2;
    protected MaterialButton btnOper3;
    private FlowPanel pnlContenedor;
    private FlowPanel pnlBotones;
    private FlowPanel pnlBusqueda;
    private FlowPanel pnlTabla;
    private FlowPanel pnlUnion;

    public UiFormMantenimiento() {
        initComponents();
        Estilo();
        initWidget(pnlContenedor);
    }

    private void initComponents() {

        pnlContenedor = new FlowPanel();
        pnlBotones = new FlowPanel();
        pnlBusqueda = new FlowPanel();
        pnlBusqueda.setHeight("100px");
        pnlTabla = new FlowPanel();
        pnlUnion = new FlowPanel();

        btnOper1 = new MaterialButton();
        btnOper1.setText("Agregar");
        btnOper2 = new MaterialButton();
        btnOper2.setText("Modificar");
        btnOper3 = new MaterialButton();
        btnOper3.setText("Estado");
        pnlBotones.add(btnOper1);
        pnlBotones.add(btnOper2);
        pnlBotones.add(btnOper3);
        pnlUnion.add(pnlBusqueda);
        pnlUnion.add(pnlTabla);

        pnlContenedor.add(pnlBotones);
        pnlContenedor.add(pnlUnion);
        btnOper1.addClickHandler(this);
        btnOper2.addClickHandler(this);
        btnOper3.addClickHandler(this);         
    }

    private void Estilo() {
        pnlBotones.getElement().setId("pnlBotones");
        pnlBusqueda.getElement().setId("pnlBusqueda");
        pnlTabla.getElement().setId("pnlTabla");
        pnlContenedor.getElement().setId("pnlForm");
        pnlUnion.getElement().setId("pnlUnion");
        MyResource.INSTANCE.getStlUiFormMantenimiento().ensureInjected();
    }

    public FlowPanel getPnlTabla() {
        return pnlTabla;
    }

    public FlowPanel getPnlBusqueda() {
        return pnlBusqueda;
    }

    public FlowPanel getPnlBotones() {
        return pnlBotones;
    }

    public FlowPanel getPnlUnion() {
        return pnlUnion;
    }
    
    

    @Override
    public void onClick(ClickEvent event) {
        
        if (event.getSource().equals(btnOper1)) {
            showUIOper1();
        } else if (event.getSource().equals(btnOper2)) {
            showUIOper2();
        } else if (event.getSource().equals(btnOper3)) {
            showUIOper3();
        } 
    }

    public void addComponent(Widget Component) {
        pnlBotones.add(Component);
    }

    public void setRenderizar(int component, String titulo, String toolTip) {
        switch (component) {
            case 1:
                btnOper1.setTitle(toolTip);
                btnOper1.setText(titulo);
                break;
            case 2:
                btnOper2.setTitle(toolTip);
                btnOper2.setText(titulo);
                break;
            case 3:
                btnOper3.setTitle(toolTip);
                btnOper3.setText(titulo);
                break;
        }

    }

    public void setVisible(int component, boolean value) {
        switch (component) {
            case 1:
                btnOper1.setVisible(value);
                break;
            case 2:
                btnOper2.setVisible(value);
                break;
            case 3:
                btnOper3.setVisible(value);
                break;
        }

    }

    public void showUIOper1() {

    }

    public void showUIOper2() {

    }

    public void showUIOper3() {

    }

    public void showUIOper4() {
        
    }

    public void showUIOper0() {        
        loadTable();
    }

    public void loadTable() {

    }

}
