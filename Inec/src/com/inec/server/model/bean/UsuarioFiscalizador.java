package com.inec.server.model.bean;
import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
public class UsuarioFiscalizador implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idUsuarioFiscalizador;
	@Persistent
	private String codeUsuarioFiscalizador;	
	@Persistent
	private String dniFiscalizador;	
	@Persistent
	private String clave;
	@Persistent
	private String nombre;
	@Persistent
	private String apellido;
	@Persistent
	private String numero;
	@Persistent
	private String correoPersonal;
	@Persistent
	private String correoCorporativo;
	@Persistent
	private String estado;
	@Persistent
	private String codeZona;
	@Persistent
	private String nombreZona;
	@Persistent
	private String tokenFirebase;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	public String getIdUsuarioFiscalizador() {
		return idUsuarioFiscalizador;
	}
	public void setIdUsuarioFiscalizador(String idUsuarioFiscalizador) {
		this.idUsuarioFiscalizador = idUsuarioFiscalizador;
	}
	public String getCodeUsuarioFiscalizador() {
		return codeUsuarioFiscalizador;
	}
	public void setCodeUsuarioFiscalizador(String codeUsuarioFiscalizador) {
		this.codeUsuarioFiscalizador = codeUsuarioFiscalizador;
	}
	public String getDniFiscalizador() {
		return dniFiscalizador;
	}
	public void setDniFiscalizador(String dniFiscalizador) {
		this.dniFiscalizador = dniFiscalizador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTokenFirebase() {
		return tokenFirebase;
	}
	public void setTokenFirebase(String tokenFirebase) {
		this.tokenFirebase = tokenFirebase;
	}
	public String getNombreZona() {
		return nombreZona;
	}
	public void setNombreZona(String nombreZona) {
		this.nombreZona = nombreZona;
	}
	public String getCorreoPersonal() {
		return correoPersonal;
	}	
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public void setCorreoPersonal(String correoPersonal) {
		this.correoPersonal = correoPersonal;
	}
	public String getCorreoCorporativo() {
		return correoCorporativo;
	}
	public void setCorreoCorporativo(String correoCorporativo) {
		this.correoCorporativo = correoCorporativo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCodeZona() {
		return codeZona;
	}
	public void setCodeZona(String codeZona) {
		this.codeZona = codeZona;
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
		result = prime * result + ((codeUsuarioFiscalizador == null) ? 0 : codeUsuarioFiscalizador.hashCode());
		result = prime * result + ((dniFiscalizador == null) ? 0 : dniFiscalizador.hashCode());
		result = prime * result + ((idUsuarioFiscalizador == null) ? 0 : idUsuarioFiscalizador.hashCode());
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
		UsuarioFiscalizador other = (UsuarioFiscalizador) obj;
		if (codeUsuarioFiscalizador == null) {
			if (other.codeUsuarioFiscalizador != null)
				return false;
		} else if (!codeUsuarioFiscalizador.equals(other.codeUsuarioFiscalizador))
			return false;
		if (dniFiscalizador == null) {
			if (other.dniFiscalizador != null)
				return false;
		} else if (!dniFiscalizador.equals(other.dniFiscalizador))
			return false;
		if (idUsuarioFiscalizador == null) {
			if (other.idUsuarioFiscalizador != null)
				return false;
		} else if (!idUsuarioFiscalizador.equals(other.idUsuarioFiscalizador))
			return false;
		return true;
	}		
}
