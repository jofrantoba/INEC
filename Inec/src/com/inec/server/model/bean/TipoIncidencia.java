package com.inec.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class TipoIncidencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idTipoIncidencia;
	@Persistent
	private String codeTipoIncidencia;
	@Persistent
	private String descripcion;	
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	
	public String getIdTipoIncidencia() {
		return idTipoIncidencia;
	}
	public void setIdTipoIncidencia(String idTipoIncidencia) {
		this.idTipoIncidencia = idTipoIncidencia;
	}
	public String getCodeTipoIncidencia() {
		return codeTipoIncidencia;
	}
	public void setCodeTipoIncidencia(String codeTipoIncidencia) {
		this.codeTipoIncidencia = codeTipoIncidencia;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		result = prime * result + ((codeTipoIncidencia == null) ? 0 : codeTipoIncidencia.hashCode());
		result = prime * result + ((idTipoIncidencia == null) ? 0 : idTipoIncidencia.hashCode());
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
		TipoIncidencia other = (TipoIncidencia) obj;
		if (codeTipoIncidencia == null) {
			if (other.codeTipoIncidencia != null)
				return false;
		} else if (!codeTipoIncidencia.equals(other.codeTipoIncidencia))
			return false;
		if (idTipoIncidencia == null) {
			if (other.idTipoIncidencia != null)
				return false;
		} else if (!idTipoIncidencia.equals(other.idTipoIncidencia))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TipoIncidencia [idTipoIncidencia=" + idTipoIncidencia + ", codeTipoIncidencia=" + codeTipoIncidencia
				+ ", descripcion=" + descripcion + ", version=" + version + "]";
	}	
}
