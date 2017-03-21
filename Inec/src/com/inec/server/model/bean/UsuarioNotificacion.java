package com.inec.server.model.bean;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class UsuarioNotificacion implements Serializable{
	private static final long serialVersionUID = 1L;

	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idUsuarioNotificacion;
	@Persistent
	private String codeUsuarioNotificacion;	
	@Persistent
	private String codeNotificacion;
	@Persistent
	private String codeUsuarioFiscalizador;
	@Persistent
	private Date fechaRecepcion;	
	@Persistent
	private String estado;	
	@Persistent
	private Long version;
	public String getIdUsuarioNotificacion() {
		return idUsuarioNotificacion;
	}
	public void setIdUsuarioNotificacion(String idUsuarioNotificacion) {
		this.idUsuarioNotificacion = idUsuarioNotificacion;
	}
	public String getCodeUsuarioNotificacion() {
		return codeUsuarioNotificacion;
	}
	public void setCodeUsuarioNotificacion(String codeUsuarioNotificacion) {
		this.codeUsuarioNotificacion = codeUsuarioNotificacion;
	}
	public String getCodeNotificacion() {
		return codeNotificacion;
	}
	public void setCodeNotificacion(String codeNotificacion) {
		this.codeNotificacion = codeNotificacion;
	}
	public String getCodeUsuarioFiscalizador() {
		return codeUsuarioFiscalizador;
	}
	public void setCodeUsuarioFiscalizador(String codeUsuarioFiscalizador) {
		this.codeUsuarioFiscalizador = codeUsuarioFiscalizador;
	}
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}
	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}	
}
