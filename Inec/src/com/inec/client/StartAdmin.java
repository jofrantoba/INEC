package com.inec.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.inec.view.admin.uiloginadmin.UiLoginAdmin;
import com.inec.view.admin.uiloginadmin.UiLoginAdminImpl;

public class StartAdmin implements EntryPoint {
	
	public void onModuleLoad() {
		UiLoginAdmin ui=new UiLoginAdminImpl();
		RootPanel.get().add(ui);		
		}	
}	
