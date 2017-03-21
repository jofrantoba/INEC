package com.inec.client.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.inec.client.resource.cssresource.BorderLayoutCss;
import com.inec.client.resource.cssresource.JHeaderMenuCss;
import com.inec.client.resource.cssresource.TabCloseCss;
import com.inec.client.resource.cssresource.UiFormMantenimientoCss;
import com.inec.client.resource.cssresource.UiHomeNegocioCss;
import com.inec.client.resource.cssresource.UiLoginAdminCss;
import com.inec.client.resource.cssresource.UiMantenimientoCss;
import com.inec.client.resource.cssresource.UiSelectOptionCss;
import com.inec.client.resource.cssresource.UiToolBarAdminCss;

public interface MyResource extends ClientBundle {
	public static final MyResource INSTANCE = GWT.create(MyResource.class);
	
	@Source("style/uitoolbaradmin.gss")
	UiToolBarAdminCss getStlUiToolBarAdmin();
	
	@Source("style/uiloginadmin.gss")
	UiLoginAdminCss getStlUiLoginAdmin();
	
	@Source("style/uihomenegocio.gss")
	UiHomeNegocioCss getStlUiHomeNegocio();
	
	@Source("style/borderlayout.gss")
	BorderLayoutCss getStlBorderLayout();
	
	@Source("style/uiscreensesion.gss")
	BorderLayoutCss getStlUiScreenSesion();
	
	@Source("style/jheadermenu.gss")
	JHeaderMenuCss getStlJHeaderMenu();
	
	@Source("style/tabclose.gss")
	TabCloseCss getStlTabClose();
	
	@Source("image/ic_home.png")
	ImageResource getImgHome48();
	
	@Source("image/ic_adduser.png")
	ImageResource getImgTurista48();
	
	@Source("image/ic_transparent.png")
	ImageResource getImgNegocio48();
	
	@Source("image/ic_transparent.png")
	ImageResource getImgDestino48();
	
	@Source("image/ic_transparent.png")
	ImageResource getImgColonia48();
	
	@Source("image/ic_transparent.png")
	ImageResource getImgMiembro48();
	
	@Source("image/cerrar16.png")
	ImageResource getImgCerrar16();
	
	@Source("image/abajo32.png")
	ImageResource getImgAbajo32();
	
	@Source("image/chat48.png")
	ImageResource getImgChat48();
	
	@Source("image/megafono48.png")
	ImageResource getImgMegafono48();
	
	@Source("image/noticia48.png")
	ImageResource getImgNoticia48();
	
	@Source("image/oferta48.png")
	ImageResource getImgOferta48();
	
	@Source("image/tarjeta48.png")
	ImageResource getImgTarjeta48();
	
	@Source("image/asistencia48.png")
	ImageResource getImgAsistencia48();
	
	@Source("image/fotografo32.png")
	ImageResource getImgFotografo32();
	
	@Source("image/mensaje32.png")
	ImageResource getImgMensaje32();
	
	@Source("image/notificacion32.png")
	ImageResource getImgNotificacion32();
	
	@Source("image/ruta32.png")
	ImageResource getImgRuta32();
	
	@Source("style/uiformmantenimiento.gss")
	UiFormMantenimientoCss getStlUiFormMantenimiento();
	
	@Source("style/uimantenimiento.gss")
	UiMantenimientoCss getStlUiMantenimiento();
	
	@Source("style/uiselectoption.gss")
	UiSelectOptionCss getStlUiSelectOption();
}
