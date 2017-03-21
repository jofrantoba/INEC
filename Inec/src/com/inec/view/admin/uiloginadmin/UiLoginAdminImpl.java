package com.inec.view.admin.uiloginadmin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;

import gwt.material.design.client.ui.MaterialToast;

public class UiLoginAdminImpl extends UiLoginAdmin{
	
	private static final String URLBASE=GWT.getHostPageBaseURL();
	
	@Override
	public void loginAdmin() {
		String correo=txtCorreo.getText().toString();
		String clave=txtClave.getText().toString();		
		if(correo.equals("admin") && clave.equals("admin")){			
			goToSession();	
		}else{
			MaterialToast.fireToast("Datos Incorrectos");
		}
	}
	
	@SuppressWarnings("unused")
	@Override
	public void goToSession(){
		String url=URLBASE;
		url=url+"inecservlet.html";
		MaterialToast.fireToast(url);
		RequestBuilder builder=new RequestBuilder(RequestBuilder.GET,url);
		try{
			Request request=builder.sendRequest(null,new RequestCallback(){
				@Override
				public void onResponseReceived(Request request, Response response) {
					
					if(response.getStatusCode()==200){
						Window.Location.assign(response.getText());
					}else{
						MaterialToast.fireToast(response.getStatusText());
					}
				}
				@Override
				public void onError(Request request, Throwable ex) {
					
					MaterialToast.fireToast(ex.getLocalizedMessage());
				}});			
		}catch(RequestException ex){
			MaterialToast.fireToast(ex.getLocalizedMessage());
		}
	}	
}
