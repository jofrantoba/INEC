package com.inec.shared;

import java.io.Serializable;
import java.util.List;

import com.inec.server.model.bean.Incidencia;
import com.inec.server.model.bean.Notificacion;
import com.inec.server.model.bean.PartidoPolitico;
import com.inec.server.model.bean.PosicionFiscalizador;
import com.inec.server.model.bean.TipoIncidencia;
import com.inec.server.model.bean.Ubigeo;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.server.model.bean.Zona;

public class ReturnValue implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nameClass;
	private Object valueReturn;
	private TipoIncidencia returnTipoIncidencia;
	private List<TipoIncidencia> returnListTipoIncidencia;
	private PartidoPolitico returnPartidoPolitico;
	private List<PartidoPolitico> returnListPartidoPolitico;
	private Ubigeo returnUbigeo;
	private List<Ubigeo> returnListUbigeo;
	private Zona returnZona;
	private List<Zona> returnListZona;
	private UsuarioFiscalizador returnUsuarioFiscalizador;
	private Incidencia returnIncidencia;
	private List<Incidencia>returnListIncidencia;
	private List<UsuarioFiscalizador> returnListUsuarioFiscalizador;
	private List<PosicionFiscalizador> returnListPosicionFiscalizador;
	private List<Notificacion> returnListNotificacion;
	
	
	public String getNameClass() {
		return nameClass;
	}
	public void setNameClass(Class<?> ObjectNameClass) {
		this.nameClass = ObjectNameClass.getSimpleName();
	}
	public Object getValueReturn() {
		return valueReturn;
	}
	public void setValueReturn(Object valueReturn) {
		this.valueReturn = valueReturn;
	}
	public TipoIncidencia getReturnTipoIncidencia() {
		return returnTipoIncidencia;
	}
	public void setReturnTipoIncidencia(TipoIncidencia returnTipoIncidencia) {
		this.returnTipoIncidencia = returnTipoIncidencia;
	}
	public List<TipoIncidencia> getReturnListTipoIncidencia() {
		return returnListTipoIncidencia;
	}
	public void setReturnListTipoIncidencia(List<TipoIncidencia> returnListTipoIncidencia) {
		this.returnListTipoIncidencia = returnListTipoIncidencia;
	}
	public PartidoPolitico getReturnPartidoPolitico() {
		return returnPartidoPolitico;
	}
	public void setReturnPartidoPolitico(PartidoPolitico returnPartidoPolitico) {
		this.returnPartidoPolitico = returnPartidoPolitico;
	}
	public List<PartidoPolitico> getReturnListPartidoPolitico() {
		return returnListPartidoPolitico;
	}
	public void setReturnListPartidoPolitico(List<PartidoPolitico> returnListPartidoPolitico) {
		this.returnListPartidoPolitico = returnListPartidoPolitico;
	}
	public Ubigeo getReturnUbigeo() {
		return returnUbigeo;
	}
	public void setReturnUbigeo(Ubigeo returnUbigeo) {
		this.returnUbigeo = returnUbigeo;
	}
	public List<Ubigeo> getReturnListUbigeo() {
		return returnListUbigeo;
	}
	public void setReturnListUbigeo(List<Ubigeo> returnListUbigeo) {
		this.returnListUbigeo = returnListUbigeo;
	}
	public Zona getReturnZona() {
		return returnZona;
	}
	public void setReturnZona(Zona returnZona) {
		this.returnZona = returnZona;
	}
	public List<Zona> getReturnListZona() {
		return returnListZona;
	}
	public void setReturnListZona(List<Zona> returnListZona) {
		this.returnListZona = returnListZona;
	}
	public UsuarioFiscalizador getReturnUsuarioFiscalizador() {
		return returnUsuarioFiscalizador;
	}
	public void setReturnUsuarioFiscalizador(UsuarioFiscalizador returnUsuarioFiscalizador) {
		this.returnUsuarioFiscalizador = returnUsuarioFiscalizador;
	}
	public Incidencia getReturnIncidencia() {
		return returnIncidencia;
	}
	public void setReturnIncidencia(Incidencia returnIncidencia) {
		this.returnIncidencia = returnIncidencia;
	}
	public List<Incidencia> getReturnListIncidencia() {
		return returnListIncidencia;
	}
	public void setReturnListIncidencia(List<Incidencia> returnListIncidencia) {
		this.returnListIncidencia = returnListIncidencia;
	}
	public List<UsuarioFiscalizador> getReturnListUsuarioFiscalizador() {
		return returnListUsuarioFiscalizador;
	}
	public void setReturnListUsuarioFiscalizador(List<UsuarioFiscalizador> returnListUsuarioFiscalizador) {
		this.returnListUsuarioFiscalizador = returnListUsuarioFiscalizador;
	}
	public List<PosicionFiscalizador> getReturnListPosicionFiscalizador() {
		return returnListPosicionFiscalizador;
	}
	public void setReturnListPosicionFiscalizador(List<PosicionFiscalizador> returnListPosicionFiscalizador) {
		this.returnListPosicionFiscalizador = returnListPosicionFiscalizador;
	}
	public List<Notificacion> getReturnListNotificacion() {
		return returnListNotificacion;
	}
	public void setReturnListNotificacion(List<Notificacion> returnListNotificacion) {
		this.returnListNotificacion = returnListNotificacion;
	}		
}
