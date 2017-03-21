package com.inec.server.model.bean;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class ProgramacionNotificacion implements Serializable{
	private static final long serialVersionUID = 1L;

	@PrimaryKey	
	@Persistent
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String idProgramacionNotificacion;
	@Persistent
	private String codeProgramacionNotificacion;
	@Persistent
	private String diaInicio;
	@Persistent
	private String diaFin;//proporcionado por gwt
	@Persistent
	private Integer horaInicio;
	@Persistent
	private String horaInicioLabel;
	@Persistent
	private Integer horaInicioDinamic;
	@Persistent
	private String horaInicioDinamicLabel;
	@Persistent
	private Integer horaFin;	
	@Persistent
	private String horaFinLabel;
	@Persistent
	private String fecha;	
	@Persistent
	private String fechaFin;	
	@Persistent
	private Integer frecuencia;	
	@Persistent
	private String estado;	
	@Persistent
	private String tipoFrecuencia;	
	@Persistent
	private Long version;
	public String getIdProgramacionNotificacion() {
		return idProgramacionNotificacion;
	}
	public void setIdProgramacionNotificacion(String idProgramacionNotificacion) {
		this.idProgramacionNotificacion = idProgramacionNotificacion;
	}
	public String getCodeProgramacionNotificacion() {
		return codeProgramacionNotificacion;
	}
	public void setCodeProgramacionNotificacion(String codeProgramacionNotificacion) {
		this.codeProgramacionNotificacion = codeProgramacionNotificacion;
	}
	public String getDiaInicio() {
		return diaInicio;
	}
	public void setDiaInicio(String diaInicio) {
		this.diaInicio = diaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getHoraInicioLabel() {
		return horaInicioLabel;
	}
	public void setHoraInicioLabel(String horaInicioLabel) {
		this.horaInicioLabel = horaInicioLabel;
	}
	public String getHoraInicioDinamicLabel() {
		return horaInicioDinamicLabel;
	}
	public void setHoraInicioDinamicLabel(String horaInicioDinamicLabel) {
		this.horaInicioDinamicLabel = horaInicioDinamicLabel;
	}
	public String getHoraFinLabel() {
		return horaFinLabel;
	}
	public void setHoraFinLabel(String horaFinLabel) {
		this.horaFinLabel = horaFinLabel;
	}
	public String getDiaFin() {
		return diaFin;
	}
	public void setDiaFin(String diaFin) {
		this.diaFin = diaFin;
	}	
	public Integer getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Integer horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Integer getHoraInicioDinamic() {
		return horaInicioDinamic;
	}
	public void setHoraInicioDinamic(Integer horaInicioDinamic) {
		this.horaInicioDinamic = horaInicioDinamic;
	}
	public Integer getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Integer horaFin) {
		this.horaFin = horaFin;
	}	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}	
	public String getTipoFrecuencia() {
		return tipoFrecuencia;
	}
	public void setTipoFrecuencia(String tipoFrecuencia) {
		this.tipoFrecuencia = tipoFrecuencia;
	}
	public Integer getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(Integer frecuencia) {
		this.frecuencia = frecuencia;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}	
}
