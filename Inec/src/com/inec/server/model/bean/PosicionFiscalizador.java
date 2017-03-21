package com.inec.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class PosicionFiscalizador implements Serializable{
	private static final long serialVersionUID = 1L;

	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idPosicionFiscalizador;
	@Persistent
	private String codePosicionFiscalizador;
	@Persistent
	private String codeUsuarioFiscalizador;
	@Persistent
	private Double longitud;
	@Persistent
	private Double latitud;
	@Persistent
	private String departamento;
	@Persistent
	private String provincia;
	@Persistent
	private String distrito;
	@Persistent
	private String fechaFormat;
	@Persistent
	private Long version;
	public String getIdPosicionFiscalizador() {
		return idPosicionFiscalizador;
	}
	public void setIdPosicionFiscalizador(String idPosicionFiscalizador) {
		this.idPosicionFiscalizador = idPosicionFiscalizador;
	}
	public String getCodePosicionFiscalizador() {
		return codePosicionFiscalizador;
	}
	public void setCodePosicionFiscalizador(String codePosicionFiscalizador) {
		this.codePosicionFiscalizador = codePosicionFiscalizador;
	}
	public String getCodeUsuarioFiscalizador() {
		return codeUsuarioFiscalizador;
	}
	public String getFechaFormat() {
		return fechaFormat;
	}
	public void setFechaFormat(String fechaFormat) {
		this.fechaFormat = fechaFormat;
	}
	public void setCodeUsuarioFiscalizador(String codeUsuarioFiscalizador) {
		this.codeUsuarioFiscalizador = codeUsuarioFiscalizador;
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
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codePosicionFiscalizador == null) ? 0 : codePosicionFiscalizador.hashCode());
		result = prime * result + ((idPosicionFiscalizador == null) ? 0 : idPosicionFiscalizador.hashCode());
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
		PosicionFiscalizador other = (PosicionFiscalizador) obj;
		if (codePosicionFiscalizador == null) {
			if (other.codePosicionFiscalizador != null)
				return false;
		} else if (!codePosicionFiscalizador.equals(other.codePosicionFiscalizador))
			return false;
		if (idPosicionFiscalizador == null) {
			if (other.idPosicionFiscalizador != null)
				return false;
		} else if (!idPosicionFiscalizador.equals(other.idPosicionFiscalizador))
			return false;
		return true;
	}
}
