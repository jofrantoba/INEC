package com.inec.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class PartidoPolitico implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idPartidoPolitico;
	@Persistent
	private String codePartidoPolitico;
	@Persistent
	private String nombrePartido;
	@Persistent
	private String lider;	
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;

	public String getIdPartidoPolitico() {
		return idPartidoPolitico;
	}
	public void setIdPartidoPolitico(String idPartidoPolitico) {
		this.idPartidoPolitico = idPartidoPolitico;
	}
	public String getCodePartidoPolitico() {
		return codePartidoPolitico;
	}
	public void setCodePartidoPolitico(String codePartidoPolitico) {
		this.codePartidoPolitico = codePartidoPolitico;
	}
	public String getNombrePartido() {
		return nombrePartido;
	}
	public void setNombrePartido(String nombrePartido) {
		this.nombrePartido = nombrePartido;
	}
	public String getLider() {
		return lider;
	}
	public void setLider(String lider) {
		this.lider = lider;
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
		result = prime * result + ((codePartidoPolitico == null) ? 0 : codePartidoPolitico.hashCode());
		result = prime * result + ((idPartidoPolitico == null) ? 0 : idPartidoPolitico.hashCode());
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
		PartidoPolitico other = (PartidoPolitico) obj;
		if (codePartidoPolitico == null) {
			if (other.codePartidoPolitico != null)
				return false;
		} else if (!codePartidoPolitico.equals(other.codePartidoPolitico))
			return false;
		if (idPartidoPolitico == null) {
			if (other.idPartidoPolitico != null)
				return false;
		} else if (!idPartidoPolitico.equals(other.idPartidoPolitico))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PartidoPolitico [idPartidoPolitico=" + idPartidoPolitico + ", codePartidoPolitico="
				+ codePartidoPolitico + ", nombrePartido=" + nombrePartido + ", lider=" + lider + ", version=" + version
				+ "]";
	}	
}
