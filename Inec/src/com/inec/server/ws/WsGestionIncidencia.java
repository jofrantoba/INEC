package com.inec.server.ws;

import java.util.List;

import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.inec.server.model.bean.Incidencia;
import com.inec.server.model.process.GestionIncidencia;
import com.inec.shared.ReturnValue;
import com.inec.shared.UnknownException;

@Api(name = "gestionIncidencia", namespace = @ApiNamespace(ownerDomain = "inec.com", ownerName = "inec.com", packagePath = "server.ws"))
public class WsGestionIncidencia {
	
	@ApiMethod(name = "registrarIncidenciaTransaction",path="registrarIncidenciaTransaction")
	public ReturnValue registrarIncidenciaTransaction(
			@Named("riDniUsuario")String codeUsuario,				
			@Named("riCodePartidoPolitico")String codePartidoPolitico,			
			@Named("riDepartamento")String departamento,
			@Named("riProvincia")String provincia,
			@Named("riDistrito")String distrito,
			@Named("riDireccion")String direccion,				
			@Named("riTipoIncidencia")String tipoIncidencia,
			@Named("riDescripcion")String descripcion,
			@Named("riFechaCelular")Long fechaCelular,
			@Named("riLatitud")Double latitud,
			@Named("riLongitud")Double longitud,
			@Named("wListUrl")List<String> listUrl,
			@Named("zListLatitud")List<Double> listLatitud,
			@Named("zListLongitud")List<Double>listLongitud) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Incidencia.class);
			valorRetorno.setReturnIncidencia(GestionIncidencia.registrarIncidenciaTransaction(codeUsuario, codePartidoPolitico, departamento, provincia, distrito, direccion, tipoIncidencia, descripcion, fechaCelular, latitud, longitud,listUrl,listLatitud,listLongitud));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "actualizarIncidencia",path="actualizarIncidencia")
	public ReturnValue actualizarIncidencia(
			@Named("aiCodeIncidencia")String codeIncidencia,
			@Named("aiCodeUsuario")String codeUsuario,				
			@Named("aiCodePartidoPolitico")String codePartidoPolitico,			
			@Named("aiDireccion")String direccion,				
			@Named("aiTipoIncidencia")String tipoIncidencia,
			@Named("aiDescripcion")String descripcion) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setValueReturn(GestionIncidencia.actualizarIncidencia(codeIncidencia, codeUsuario, codePartidoPolitico, direccion, tipoIncidencia, descripcion));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listarIncidencias",path="listarIncidencias")
	public ReturnValue listarIncidencias(
			@Named("liDniUsuario")String codeUsuario) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListIncidencia(GestionIncidencia.listarIncidencias(codeUsuario));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
}
