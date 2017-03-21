package com.inec.server.model.bean;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Notificacion implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idNotificacion;
	@Persistent
	private String codeNotificacion;	
	@Persistent
	private String codeProgramacionNotificacion;
	@Persistent
	private Date fechaNotificacion;
	@Persistent
	private String fechaNotificacionFiltro;
	@Persistent
	private String estado;
	@Persistent
	private Long version;
	public String getIdNotificacion() {
		return idNotificacion;
	}
	public void setIdNotificacion(String idNotificacion) {
		this.idNotificacion = idNotificacion;
	}
	public String getCodeNotificacion() {
		return codeNotificacion;
	}
	public void setCodeNotificacion(String codeNotificacion) {
		this.codeNotificacion = codeNotificacion;
	}
	public String getFechaNotificacionFiltro() {
		return fechaNotificacionFiltro;
	}
	public void setFechaNotificacionFiltro(String fechaNotificacionFiltro) {
		this.fechaNotificacionFiltro = fechaNotificacionFiltro;
	}
	public String getCodeProgramacionNotificacion() {
		return codeProgramacionNotificacion;
	}	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public void setCodeProgramacionNotificacion(String codeProgramacionNotificacion) {
		this.codeProgramacionNotificacion = codeProgramacionNotificacion;
	}
	public Date getFechaNotificacion() {
		return fechaNotificacion;
	}
	public void setFechaNotificacion(Date fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}			
}
