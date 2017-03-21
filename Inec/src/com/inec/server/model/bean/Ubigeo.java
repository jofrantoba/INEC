package com.inec.server.model.bean;

import java.io.Serializable;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Ubigeo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idUbigeo;
	@Persistent
	private String codeUbigeo;
	@Persistent
	private String departamento;
	@Persistent
	private String provincia;
	@Persistent
	private String distrito;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	public String getIdUbigeo() {
		return idUbigeo;
	}
	public void setIdUbigeo(String idUbigeo) {
		this.idUbigeo = idUbigeo;
	}
	public String getCodeUbigeo() {
		return codeUbigeo;
	}
	public void setCodeUbigeo(String codeUbigeo) {
		this.codeUbigeo = codeUbigeo;
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
		result = prime * result + ((codeUbigeo == null) ? 0 : codeUbigeo.hashCode());
		result = prime * result + ((idUbigeo == null) ? 0 : idUbigeo.hashCode());
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
		Ubigeo other = (Ubigeo) obj;
		if (codeUbigeo == null) {
			if (other.codeUbigeo != null)
				return false;
		} else if (!codeUbigeo.equals(other.codeUbigeo))
			return false;
		if (idUbigeo == null) {
			if (other.idUbigeo != null)
				return false;
		} else if (!idUbigeo.equals(other.idUbigeo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Ubigeo [idUbigeo=" + idUbigeo + ", codeUbigeo=" + codeUbigeo + ", departamento=" + departamento
				+ ", provincia=" + provincia + ", distrito=" + distrito + ", version=" + version + "]";
	}
}
