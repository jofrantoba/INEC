package com.inec.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class SesionFiscalizador implements Serializable{
	private static final long serialVersionUID = 1L;

	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idInicioSesion;
	@Persistent
	private String codeInicioSesion;
	@Persistent
	private String codeUsuarioFiscalizador;
	@Persistent
	private String inicio;//proporcionado por gwt
	@Persistent
	private String fin;	
	@Persistent
	private Long version;
	public String getIdInicioSesion() {
		return idInicioSesion;
	}
	public void setIdInicioSesion(String idInicioSesion) {
		this.idInicioSesion = idInicioSesion;
	}
	public String getCodeInicioSesion() {
		return codeInicioSesion;
	}
	public void setCodeInicioSesion(String codeInicioSesion) {
		this.codeInicioSesion = codeInicioSesion;
	}
	public String getCodeUsuarioFiscalizador() {
		return codeUsuarioFiscalizador;
	}
	public void setCodeUsuarioFiscalizador(String codeUsuarioFiscalizador) {
		this.codeUsuarioFiscalizador = codeUsuarioFiscalizador;
	}
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}			
}
