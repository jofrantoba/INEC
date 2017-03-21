package com.inec.view.uiutil;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public class ButtonBar extends PushButton{
   
    public ButtonBar(Image icon){
        super(icon);
        initSityle();
    }
    
    private void initSityle(){
        this.getElement().getStyle().setTextAlign(Style.TextAlign.CENTER);
    }    
}
