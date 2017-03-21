package com.inec.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(detachable="true")
public class EnviarCorreo implements Serializable{
	private static final long serialVersionUID = 1L;

	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idEnviarCorreo;
	@Persistent
	private String codeEnviarCorreo;
	@Persistent	
	private String destinatarios;
	@Persistent
	private String asunto;
	@Persistent
	private String contenido;
	@Persistent
	private String fecha;
	@Persistent
	private Long version;
	public String getIdEnviarCorreo() {
		return idEnviarCorreo;
	}
	public void setIdEnviarCorreo(String idEnviarCorreo) {
		this.idEnviarCorreo = idEnviarCorreo;
	}
	public String getCodeEnviarCorreo() {
		return codeEnviarCorreo;
	}
	public void setCodeEnviarCorreo(String codeEnviarCorreo) {
		this.codeEnviarCorreo = codeEnviarCorreo;
	}
	public String getDestinatarios() {
		return destinatarios;
	}
	public void setDestinatarios(String destinatarios) {
		this.destinatarios = destinatarios;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}			
}
