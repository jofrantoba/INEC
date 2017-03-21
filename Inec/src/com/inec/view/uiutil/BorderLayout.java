package com.inec.view.uiutil;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.inec.client.resource.MyResource;

public class BorderLayout extends Composite{
    public static final int NORTE=0;
    public static final int OESTE=1;
    public static final int CENTRO=2;
    public static final int ESTE=3;        
    public static final int SUR=4;    
    protected FlowPanel pnlContenedor;
    protected FlowPanel pnlNorte;
    protected FlowPanel pnlSur;
    protected FlowPanel pnlCenter;
    protected FlowPanel pnlOeste;
    protected FlowPanel pnlCentro;
    protected FlowPanel pnlEste;
    
    public BorderLayout(){
        initComponents();
        initStyle();
    }
    
    private void initComponents(){
        pnlContenedor=new FlowPanel();
        pnlNorte=new FlowPanel();
        pnlSur=new FlowPanel();
        pnlCenter=new FlowPanel();
        pnlOeste=new FlowPanel();
        pnlCentro=new FlowPanel();
        pnlEste=new FlowPanel();
        pnlContenedor.add(pnlNorte);
        pnlContenedor.add(pnlCenter);
        pnlContenedor.add(pnlSur);
        pnlCenter.add(pnlOeste);
        pnlCenter.add(pnlCentro);
        pnlCenter.add(pnlEste);
        this.initWidget(pnlContenedor);        
    }
    
    private void initStyle(){
        pnlContenedor.getElement().setId("pnlContenedor");
        pnlNorte.getElement().setId("pnlNorte");
        pnlSur.getElement().setId("pnlSur");
        pnlCenter.getElement().setId("pnlCenter");
        pnlOeste.getElement().setId("pnlOeste");
        pnlCentro.getElement().setId("pnlCentro");
        pnlEste.getElement().setId("pnlEste");        
        MyResource.INSTANCE.getStlBorderLayout().ensureInjected();        
    }
    
    public void add(int ubicacion,Widget component){
        switch(ubicacion){
            case 0:
                pnlNorte.add(component);
                break;
            case 1:
                pnlOeste.add(component);
                break;
            case 2:
                pnlCentro.add(component);
                break;
            case 3:
                pnlEste.add(component);
                break;
            case 4:
                pnlSur.add(component);
                break;                         
        }
    }     

    public FlowPanel getPnlContenedor() {
        return pnlContenedor;
    }

    public FlowPanel getPnlNorte() {
        return pnlNorte;
    }

    public FlowPanel getPnlSur() {
        return pnlSur;
    }

    public FlowPanel getPnlCenter() {
        return pnlCenter;
    }

    public FlowPanel getPnlOeste() {
        return pnlOeste;
    }

    public FlowPanel getPnlCentro() {
        return pnlCentro;
    }
    
    public FlowPanel getPnlEste() {
        return pnlEste;
    }
    
     
}
