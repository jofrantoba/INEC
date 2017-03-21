package com.inec.view.uiutil;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.inec.client.resource.MyResource;

public class UiScreenSesion extends BorderLayout {

    public static final int TITULO = 0;
    public static final int MENU = 1;
    public static final int BAR = 2;
    public static final int TAB = 3;
    public static final int ESTADO = 4;
    private FlowPanel pnlHeader;
    private FlowPanel pnlTitulo;
    private FlowPanel pnlMenu;
    private FlowPanel pnlBar;
    private FlowPanel pnlTab;
    private FlowPanel pnlEstado;

    public UiScreenSesion() {
        initComponents();
        initStyle();
    }

    private void initComponents() {
        pnlHeader = new FlowPanel();
        pnlTitulo = new FlowPanel();
        pnlMenu = new FlowPanel();
        pnlBar = new FlowPanel();
        pnlHeader.add(pnlTitulo);
        pnlHeader.add(pnlMenu);
        pnlHeader.add(pnlBar);
        pnlTab = new FlowPanel();
        pnlEstado = new FlowPanel();
        this.add(BorderLayout.NORTE, pnlHeader);
        this.add(BorderLayout.CENTRO, pnlTab);
        this.add(BorderLayout.SUR, pnlEstado);
    }

    private void initStyle() {
        pnlHeader.getElement().setId("pnlHeader");
        pnlTitulo.getElement().setId("pnlTitulo");
        pnlEstado.getElement().setId("pnlEstado");
        pnlMenu.getElement().setId("pnlMenu");
        MyResource.INSTANCE.getStlUiScreenSesion().ensureInjected();
    }

    public void setComponent(int ubicacion, Widget component) {
        switch (ubicacion) {
            case 0:
                pnlTitulo.add(component);
                break;
            case 1:
                pnlMenu.add(component);
                break;
            case 2:
                pnlBar.add(component);
                break;
            case 3:
                pnlTab.add(component);
                break;
            case 4:
                pnlEstado.add(component);
                break;
        }
    }

}
