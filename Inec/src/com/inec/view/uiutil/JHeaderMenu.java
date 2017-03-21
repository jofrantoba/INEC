package com.inec.view.uiutil;

import com.google.gwt.user.client.ui.Widget;
import com.inec.client.resource.MyResource;
import com.inec.client.resource.cssresource.JHeaderMenuCss;

import gwt.material.design.client.constants.NavBarType;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.ui.MaterialNavBar;
import gwt.material.design.client.ui.MaterialNavBrand;
import gwt.material.design.client.ui.MaterialNavSection;

public class JHeaderMenu extends MaterialNavBar{
	private MaterialNavBrand navBrand;
	private MaterialNavSection navSection;
	
	public JHeaderMenu(){
		initComponents();
		initStyle();
	}
	
	private void initComponents(){
		this.setType(NavBarType.FIXED);
		navBrand=new MaterialNavBrand();
		navBrand.setPosition(Position.LEFT);
		navSection=new MaterialNavSection();
		navSection.setPosition(Position.RIGHT);		
		this.add(navSection);
		this.add(navBrand);
	}
	
	private void initStyle(){
		JHeaderMenuCss myResource=MyResource.INSTANCE.getStlJHeaderMenu();
		myResource.ensureInjected();
		this.getElement().setId("kngNavBar");
		//navBrand.getElement().setId("kngNavBrand");
	}
	
	public void setTitle(String title){
		navBrand.setText(title);
	}
	
	public void addWidgetToNavSection(Widget w){
		navSection.add(w);
	}
	
	
		
}
