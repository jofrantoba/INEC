package com.inec.server.model.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class Incidencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idIncidencia;
	@Persistent
	private String codeIncidencia;
	@Persistent
	private Date fechaCelular;
	@Persistent
	private Date fechaServidor;
	@Persistent
	private String direccion;	
	@Persistent
	private String codeUsuarioFiscalizador;
	@Persistent
	private String codeTipoIncidencia;
	@Persistent
	private String codeZona;
	@Persistent
	@Unowned
	private Ubigeo beanUbigeo;
	@Persistent
	private String codeUbigeo;
	@Persistent
	private String codePartidoPolitico;
	@Persistent
	private String nombrePartidoPolitico;
	@Persistent
	private String descripcion;
	@Persistent
	private Double longitud;
	@Persistent
	private Double latitud;
	@Persistent
	private Long version;
	@NotPersistent
	private String operacion;
	@Persistent
	private List<String>listUrlFoto;
	@Persistent
	private List<Double>listLatitud;
	@Persistent
	private List<Double>listLongitud;
	
	public String getIdIncidencia() {
		return idIncidencia;
	}
	public void setIdIncidencia(String idIncidencia) {
		this.idIncidencia = idIncidencia;
	}
	public String getCodeIncidencia() {
		return codeIncidencia;
	}
	public void setCodeIncidencia(String codeIncidencia) {
		this.codeIncidencia = codeIncidencia;
	}
	public Date getFechaCelular() {
		return fechaCelular;
	}
	public void setFechaCelular(Date fechaCelular) {
		this.fechaCelular = fechaCelular;
	}
	public Date getFechaServidor() {
		return fechaServidor;
	}
	public void setFechaServidor(Date fechaServidor) {
		this.fechaServidor = fechaServidor;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<String> getListUrlFoto() {
		return listUrlFoto;
	}
	public void setListUrlFoto(List<String> listUrlFoto) {
		this.listUrlFoto = listUrlFoto;
	}
	public List<Double> getListLatitud() {
		return listLatitud;
	}
	public void setListLatitud(List<Double> listLatitud) {
		this.listLatitud = listLatitud;
	}
	public List<Double> getListLongitud() {
		return listLongitud;
	}
	public void setListLongitud(List<Double> listLongitud) {
		this.listLongitud = listLongitud;
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
	public String getCodeUsuarioFiscalizador() {
		return codeUsuarioFiscalizador;
	}
	public void setCodeUsuarioFiscalizador(String codeUsuarioFiscalizador) {
		this.codeUsuarioFiscalizador = codeUsuarioFiscalizador;
	}
	public String getNombrePartidoPolitico() {
		return nombrePartidoPolitico;
	}
	public void setNombrePartidoPolitico(String nombrePartidoPolitico) {
		this.nombrePartidoPolitico = nombrePartidoPolitico;
	}
	public String getCodeTipoIncidencia() {
		return codeTipoIncidencia;
	}
	public void setCodeTipoIncidencia(String codeTipoIncidencia) {
		this.codeTipoIncidencia = codeTipoIncidencia;
	}
	public String getCodeZona() {
		return codeZona;
	}
	public void setCodeZona(String codeZona) {
		this.codeZona = codeZona;
	}
	public Ubigeo getBeanUbigeo() {
		return beanUbigeo;
	}
	public void setBeanUbigeo(Ubigeo beanUbigeo) {
		this.beanUbigeo = beanUbigeo;
	}
	public String getCodeUbigeo() {
		return codeUbigeo;
	}
	public void setCodeUbigeo(String codeUbigeo) {
		this.codeUbigeo = codeUbigeo;
	}
	public String getCodePartidoPolitico() {
		return codePartidoPolitico;
	}
	public void setCodePartidoPolitico(String codePartidoPolitico) {
		this.codePartidoPolitico = codePartidoPolitico;
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
		result = prime * result + ((codeIncidencia == null) ? 0 : codeIncidencia.hashCode());
		result = prime * result + ((idIncidencia == null) ? 0 : idIncidencia.hashCode());
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
		Incidencia other = (Incidencia) obj;
		if (codeIncidencia == null) {
			if (other.codeIncidencia != null)
				return false;
		} else if (!codeIncidencia.equals(other.codeIncidencia))
			return false;
		if (idIncidencia == null) {
			if (other.idIncidencia != null)
				return false;
		} else if (!idIncidencia.equals(other.idIncidencia))
			return false;
		return true;
	}		
}
