package com.inec.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
public class Zona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idZona;
	@Persistent
	private String codeZona;
	@Persistent	
	private String nombreZona;
	@Persistent
	private String codeUbigeo;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	public String getIdZona() {
		return idZona;
	}
	public void setIdZona(String idZona) {
		this.idZona = idZona;
	}
	public String getCodeZona() {
		return codeZona;
	}
	public void setCodeZona(String codeZona) {
		this.codeZona = codeZona;
	}
	public String getNombreZona() {
		return nombreZona;
	}
	public void setNombreZona(String nombreZona) {
		this.nombreZona = nombreZona;
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
	
	public String getCodeUbigeo() {
		return codeUbigeo;
	}
	public void setCodeUbigeo(String codeUbigeo) {
		this.codeUbigeo = codeUbigeo;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeZona == null) ? 0 : codeZona.hashCode());
		result = prime * result + ((idZona == null) ? 0 : idZona.hashCode());
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
		Zona other = (Zona) obj;
		if (codeZona == null) {
			if (other.codeZona != null)
				return false;
		} else if (!codeZona.equals(other.codeZona))
			return false;
		if (idZona == null) {
			if (other.idZona != null)
				return false;
		} else if (!idZona.equals(other.idZona))
			return false;
		return true;
	}		
}
