package com.inec.view.uiutil;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inec.client.resource.MyResource;

import gwt.material.design.client.ui.MaterialTextBox;

public class UiSelectOption extends FlexTable{
    public MaterialTextBox txtInput; 
    public TextBox txtBuscar;
    public PushButton btnCombo;
    public Image imgCombo;
    @SuppressWarnings("rawtypes")
	public DataGrid grid;
    public PopupPanel simplePopup;
    
    @SuppressWarnings("rawtypes")
	public UiSelectOption(DataGrid grid){
    	this.grid=grid;
        initComponents();        
        initStyle();
    }
    
    private void initComponents(){
    	txtBuscar=new TextBox();
    	txtBuscar.getElement().setPropertyString("placeholder", "buscar aqui");             
        txtInput=new MaterialTextBox();
        imgCombo=new Image(MyResource.INSTANCE.getImgAbajo32());
        btnCombo=new PushButton(imgCombo); 
        simplePopup = new PopupPanel(true);
        VerticalPanel flow=new VerticalPanel();
        flow.add(txtBuscar);
        flow.add(grid);
        
        flow.setHeight("300px");
        simplePopup.setWidget(flow);
        this.setWidget(0, 0, txtInput);
        this.setWidget(0, 1, btnCombo);   
        this.setCellPadding(0);
        this.setCellSpacing(0);        
    }    
    
    private void initStyle(){    
    	txtInput.getElement().setId("txtInput");  
    	btnCombo.getElement().setId("btnCombo");
    	imgCombo.getElement().setId("imgCombo");
    	simplePopup.getElement().setId("simplePopup");
    	txtBuscar.getElement().setId("txtBuscar");
    	MyResource.INSTANCE.getStlUiSelectOption().ensureInjected();
    	this.getElement().setClassName(MyResource.INSTANCE.getStlUiSelectOption().pnlControl());
    	btnCombo.getElement().getStyle().setBorderStyle(BorderStyle.NONE);
    	imgCombo.setWidth("24px");
    	imgCombo.setHeight("24px");
    }
    
    
    public void showPopup(){            
    	int left = txtInput.getAbsoluteLeft();
        int top = txtInput.getAbsoluteTop();
        simplePopup.setPopupPosition(left, top);
            simplePopup.show();
            grid.setFocus(true);
    }
    
    public void hidePopup(){        
            simplePopup.hide();
    }

    
}