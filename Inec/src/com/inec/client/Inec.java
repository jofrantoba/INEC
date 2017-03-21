package com.inec.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.inec.view.admin.uimain.UiMain;

public class Inec implements EntryPoint {
	@Override
	public void onModuleLoad() {		
		UiMain ui=new UiMain();
		RootPanel.get().add(ui);
	}
}
