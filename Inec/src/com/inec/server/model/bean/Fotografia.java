package com.inec.server.model.bean;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Fotografia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idFotografia;
	@Persistent
	private String codeFotografia;
	@Persistent
	private Date fechaCapturaCelular;
	@Persistent
	private Date fechaCaptura;
	@Persistent
	private Double longitud;
	@Persistent
	private Double latitud;
	@Persistent
	private String codeIncidencia;
	@Persistent
	private String urlFotografia;
	@Persistent
	private String codeUsuarioFiscalizador;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	public String getIdFotografia() {
		return idFotografia;
	}
	public void setIdFotografia(String idFotografia) {
		this.idFotografia = idFotografia;
	}
	public String getCodeFotografia() {
		return codeFotografia;
	}
	public void setCodeFotografia(String codeFotografia) {
		this.codeFotografia = codeFotografia;
	}
	public Date getFechaCapturaCelular() {
		return fechaCapturaCelular;
	}
	public void setFechaCapturaCelular(Date fechaCapturaCelular) {
		this.fechaCapturaCelular = fechaCapturaCelular;
	}
	public Date getFechaCaptura() {
		return fechaCaptura;
	}
	public void setFechaCaptura(Date fechaCaptura) {
		this.fechaCaptura = fechaCaptura;
	}
	public Double getLongitud() {
		return longitud;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	public Double getLatitud() {
		return latitud;
	}
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	public String getUrlFotografia() {
		return urlFotografia;
	}
	public void setUrlFotografia(String urlFotografia) {
		this.urlFotografia = urlFotografia;
	}
	public String getCodeIncidencia() {
		return codeIncidencia;
	}
	public void setCodeIncidencia(String codeIncidencia) {
		this.codeIncidencia = codeIncidencia;
	}
	public String getCodeUsuarioFiscalizador() {
		return codeUsuarioFiscalizador;
	}
	public void setCodeUsuarioFiscalizador(String codeUsuarioFiscalizador) {
		this.codeUsuarioFiscalizador = codeUsuarioFiscalizador;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeFotografia == null) ? 0 : codeFotografia.hashCode());
		result = prime * result + ((idFotografia == null) ? 0 : idFotografia.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fotografia other = (Fotografia) obj;
		if (codeFotografia == null) {
			if (other.codeFotografia != null)
				return false;
		} else if (!codeFotografia.equals(other.codeFotografia))
			return false;
		if (idFotografia == null) {
			if (other.idFotografia != null)
				return false;
		} else if (!idFotografia.equals(other.idFotografia))
			return false;
		return true;
	}	
}
