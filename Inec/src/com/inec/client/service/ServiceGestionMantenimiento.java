package com.inec.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.inec.server.model.bean.PosicionFiscalizador;
import com.inec.server.model.bean.ProgramacionNotificacion;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.server.model.bean.Zona;
import com.inec.shared.UnknownException;

@RemoteServiceRelativePath("servicegestionmantenimiento")
public interface ServiceGestionMantenimiento extends RemoteService {

	UsuarioFiscalizador crearUsuario(String dni,			
			String nombres, 
			String apellidos, 
			String correoPersonal,
			String correoInstitucional, 
			String telefono, 
			String estado,
			String codeZona)throws UnknownException;
	UsuarioFiscalizador actualizarUsuario(String dni,			
			String nombres, 
			String apellidos, 
			String correoPersonal,
			String correoInstitucional, 
			String telefono, 
			String estado,
			String codeZona)throws UnknownException;
	
	UsuarioFiscalizador actualizarEstado(String dni,
			String estado)throws UnknownException;
	UsuarioFiscalizador updateUsuarioFiscalizador(UsuarioFiscalizador beanUsuarioFiscalizador) throws UnknownException;
	Boolean removeUsuarioFiscalizador(String correoUsuarioFiscalizador) throws UnknownException;
	List<UsuarioFiscalizador> listUsuarioFiscalizador() throws UnknownException;
	List<UsuarioFiscalizador> buscarUsuarioFiscalizadores(String patron, 
			String codeZona) throws UnknownException;
	Boolean enviarCorreoFiscalizadores(String paramAsunto,String paramContent,String paramDestinatarios) throws UnknownException;
	List<Zona> listZona()throws UnknownException;
	List<PosicionFiscalizador> listarRutasIntervalo(String codeUsuario, Long fechaInicio, Long fechaFin) throws UnknownException;
	ProgramacionNotificacion planificarNotificacion(
			String diaInicio, 
			String diaFin, 
			Integer horaInicio,
			Integer horaFin,
			Integer frecuencia, 
			String tipoFrecuencia) throws UnknownException;
	List<ProgramacionNotificacion> listProgramaciones() throws UnknownException;
	
}
