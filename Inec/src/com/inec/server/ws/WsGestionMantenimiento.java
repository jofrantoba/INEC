package com.inec.server.ws;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.inec.server.model.bean.PartidoPolitico;
import com.inec.server.model.bean.TipoIncidencia;
import com.inec.server.model.bean.Ubigeo;
import com.inec.server.model.bean.Zona;
import com.inec.server.model.process.GestionMantenimiento;
import com.inec.shared.ReturnValue;
import com.inec.shared.UnknownException;

@Api(name = "gestionMantenimiento", namespace = @ApiNamespace(ownerDomain = "inec.com", ownerName = "inec.com", packagePath = "server.ws"))
public class WsGestionMantenimiento {
	
	@ApiMethod(name = "insertarTipoIncidencia",path="insertarTipoIncidencia")
	public ReturnValue insertarTipoIncidencia(
			@Named("itiCodeTipoIncidencia")String codeTipoIncidencia,
			@Nullable @Named("itiDescripcion")String descripcion) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(TipoIncidencia.class);
			valorRetorno.setReturnTipoIncidencia(GestionMantenimiento.insertarTipoIncidencia(codeTipoIncidencia, descripcion));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listTipoIncidencia",path="listTipoIncidencia")
	public ReturnValue listTipoIncidencia() {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListTipoIncidencia(GestionMantenimiento.listTipoIncidencia());
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "insertarPartidoPolitico",path="insertarPartidoPolitico")
	public ReturnValue insertarPartidoPolitico(
			@Named("ippCodePartidoPolitico")String codePartidoPolitico,
			@Named("ippNombrePartidoPolitico")String nombrePartidoPolitico,
			@Named("ippLider")String lider) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(PartidoPolitico.class);
			valorRetorno.setReturnPartidoPolitico(GestionMantenimiento.insertarPartidoPolitico(codePartidoPolitico, nombrePartidoPolitico, lider));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listPartidoPolitico",path="listPartidoPolitico")
	public ReturnValue listPartidoPolitico() {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListPartidoPolitico(GestionMantenimiento.listPartidoPolitico());
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "insertarUbigeo",path="insertarUbigeo")
	public ReturnValue insertarUbigeo(
			@Named("iuCodeUbigeo")String codeUbigeo,
			@Named("iuDepartamento")String departamento,
			@Nullable @Named("iuProvincia")String provincia,
			@Nullable @Named("iuDistrito")String distrito) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Ubigeo.class);
			valorRetorno.setReturnUbigeo(GestionMantenimiento.insertarUbigeo(codeUbigeo, departamento, provincia, distrito));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listUbigeo",path="listUbigeo")
	public ReturnValue listUbigeo() {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListUbigeo(GestionMantenimiento.listUbigeo());
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}	
	
	@ApiMethod(name = "insertarZona",path="insertarZona")
	public ReturnValue insertarZona(			
			@Named("izNombreZona")String nombreZona,
			@Named("izDepartamento")String departamento,
			@Named("izProvincia")String provincia,
			@Named("izDistrito")String distrito) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Zona.class);
			valorRetorno.setReturnZona(GestionMantenimiento.insertarZona(nombreZona, departamento, provincia, distrito));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listZona",path="listZona")
	public ReturnValue listZona() {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListZona(GestionMantenimiento.listZona());
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}	
	
}
