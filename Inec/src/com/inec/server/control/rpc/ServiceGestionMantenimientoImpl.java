package com.inec.server.control.rpc;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.inec.client.service.ServiceGestionMantenimiento;
import com.inec.server.model.bean.PosicionFiscalizador;
import com.inec.server.model.bean.ProgramacionNotificacion;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.server.model.bean.Zona;
import com.inec.server.model.process.GestionMantenimiento;
import com.inec.server.model.process.GestionUsuario;
import com.inec.shared.UnknownException;

public class ServiceGestionMantenimientoImpl extends RemoteServiceServlet implements ServiceGestionMantenimiento{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9071049936109262193L;
	static final String CADENA="";

	@Override
	public UsuarioFiscalizador updateUsuarioFiscalizador(UsuarioFiscalizador beanUsuarioFiscalizador) throws UnknownException {
		UsuarioFiscalizador bean=new UsuarioFiscalizador();
		
	  return bean;
	}

	@Override
	public Boolean removeUsuarioFiscalizador(String correoUsuarioFiscalizador) throws UnknownException {		
	return true;
	}
	@Override
	public List<UsuarioFiscalizador> listUsuarioFiscalizador()
	  throws UnknownException {
	
		return (List<UsuarioFiscalizador>) GestionUsuario.listarUsuarios();
	}

	@Override
	public UsuarioFiscalizador crearUsuario(String dni, String nombres, String apellidos, String correoPersonal,
			String correoInstitucional, String telefono, String estado, String codeZona) throws UnknownException {
		
		UsuarioFiscalizador beanUsuarioFiscalizador= GestionUsuario.crearUsuario(dni, nombres, apellidos, correoPersonal, correoInstitucional, telefono, estado, codeZona);
			return beanUsuarioFiscalizador;	
	}

	@Override
	public UsuarioFiscalizador actualizarUsuario(String dni, String nombres, String apellidos, String correoPersonal,
			String correoInstitucional, String telefono, String estado, String codeZona) throws UnknownException {
		
		UsuarioFiscalizador beanUsuarioFiscalizador= GestionUsuario.actualizarUsuario(dni, nombres, apellidos, correoPersonal, correoInstitucional, telefono, estado, codeZona);
		return beanUsuarioFiscalizador;
	}

	@Override
	public UsuarioFiscalizador actualizarEstado(String dni, String estado) throws UnknownException {
		
		UsuarioFiscalizador beanUsuarioFiscalizador=GestionUsuario.actualizarEstado(dni, estado);
		return  beanUsuarioFiscalizador;
	}

	@Override
	public List<Zona> listZona() throws UnknownException {
		
		return GestionMantenimiento.listZona();
	}

	@Override
	public List<UsuarioFiscalizador> buscarUsuarioFiscalizadores(String patron, String codeZona)
			throws UnknownException {
		
		return GestionUsuario.buscarUsuarioFiscalizadores(patron, codeZona);
	}

	@Override
	public Boolean enviarCorreoFiscalizadores(String paramAsunto, String paramContent, String paramDestinatarios) throws UnknownException {
		
		return GestionUsuario.enviarCorreoFiscalizadores(paramAsunto, paramContent, paramDestinatarios);
	}

	@Override
	public List<PosicionFiscalizador> listarRutasIntervalo(String codeUsuario, Long fechaInicio, Long fechaFin) throws UnknownException {
		
		return GestionUsuario.listarPosicionByIntervalo(codeUsuario, fechaInicio, fechaFin);
	}

	@Override
	public ProgramacionNotificacion planificarNotificacion(String diaInicio, String diaFin, Integer horaInicio, Integer horaFin,
			Integer frecuencia, String tipoFrecuencia) throws UnknownException {		
		return GestionUsuario.planificarNotificacion(diaInicio, diaFin, horaInicio, horaFin, frecuencia, tipoFrecuencia);
	}

	@Override
	public List<ProgramacionNotificacion> listProgramaciones() throws UnknownException {
		return GestionUsuario.listProgramaciones();
	}	
	
}
