package com.inec.server.ws;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.server.model.process.GestionUsuario;
import com.inec.shared.ReturnValue;
import com.inec.shared.UnknownException;

@Api(name = "gestionUsuario", namespace = @ApiNamespace(ownerDomain = "inec.com", ownerName = "inec.com", packagePath = "server.ws"))
public class WsGestionUsuario {

	@ApiMethod(name = "crearUsuario",path="crearUsuario")
	public ReturnValue crearUsuario(
			@Named("cuDni")String dni,			
			@Named("cuNombres")String nombres, 
			@Named("cuApellidos")String apellidos, 
			@Named("cuCorreoPersonal")String correoPersonal,
			@Named("cuCorreoInstitucional")String correoInstitucional, 
			@Named("cuTelefono")String telefono, 
			@Named("cuEstado")String estado, 
			@Named("cuCodeZona")String codeZona) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(UsuarioFiscalizador.class);
			valorRetorno.setReturnUsuarioFiscalizador(GestionUsuario.crearUsuario(dni, nombres, apellidos, correoPersonal, correoInstitucional,telefono,estado,codeZona));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "actualizarUsuario",path="actualizarUsuario")
	public ReturnValue actualizarUsuario(
			@Named("auDni")String dni,			
			@Named("auNombres")String nombres, 
			@Named("auApellidos")String apellidos, 
			@Named("auCorreoPersonal")String correoPersonal,
			@Named("auCorreoInstitucional")String correoInstitucional, 
			@Named("auTelefono")String telefono, 
			@Named("auEstado")String estado,
			@Named("auCodeZona")String codeZona) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(UsuarioFiscalizador.class);
			valorRetorno.setReturnUsuarioFiscalizador(GestionUsuario.actualizarUsuario(dni, nombres, apellidos, correoPersonal, correoInstitucional, telefono, estado, codeZona));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "actualizarPassword",path="actualizarPassword")
	public ReturnValue actualizarPassword(
			@Named("apDniUsuario")String dniUsuario,			
			@Named("apClaveUsuario")String claveUsuario, 
			@Named("apNuevaClave")String nuevaClave, 
			@Named("apConfirmarClave")String confirmarClave) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(UsuarioFiscalizador.class);
			valorRetorno.setReturnUsuarioFiscalizador(GestionUsuario.actualizarPassword(dniUsuario, claveUsuario, nuevaClave, confirmarClave)
					);
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "loginUsuario",path="loginUsuario")
	public ReturnValue loginUsuario(
			@Named("luCorreoUsuario")String correoUsuario,
			@Named("luClaveUsuario")String claveUsuario,
			@Named("luTokenFirebase")String tokenFirebase) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(UsuarioFiscalizador.class);
			valorRetorno.setReturnUsuarioFiscalizador(GestionUsuario.loginUsuario(correoUsuario, claveUsuario,tokenFirebase));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "cerrarSesionFiscalizador",path="cerrarSesionFiscalizador")
	public ReturnValue cerrarSesionFiscalizador(
			@Named("csfCodeSessionFiscalizador")String codeSesionFiscalizador) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setValueReturn(GestionUsuario.cerrarSesionFiscalizador(codeSesionFiscalizador));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listarUsuarios",path="listarUsuarios")
	public ReturnValue listarUsuarios() {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListUsuarioFiscalizador(GestionUsuario.listarUsuarios());
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "buscarUsuarioFiscalizadores",path="buscarUsuarioFiscalizadores")
	public ReturnValue buscarUsuarioFiscalizadores(
			@Named("bufPatron")String patron, 
			@Nullable @Named("bufCodeZona")String codeZona) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListUsuarioFiscalizador(GestionUsuario.buscarUsuarioFiscalizadores(patron, codeZona));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listarPosicionFiscalizadors",path="listarPosicionFiscalizadors")
	public ReturnValue listarPosicionFiscalizadors(@Named("lpfDniUsuario")String dniUsuario) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListPosicionFiscalizador(GestionUsuario.listarPosicionFiscalizadors(dniUsuario));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listarRutas",path="listarRutas")
	public ReturnValue listarRutas(@Named("lpffDniUsuario")String dniUsuario,@Named("lpffFechaFormat")String fechaFormat) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListPosicionFiscalizador(GestionUsuario.listarPosicionByFechaFormat(dniUsuario, fechaFormat));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listarRutasIntervalo",path="listarRutasIntervalo")
	public ReturnValue listarRutasIntervalo(
			@Named("lriDniUsuario")String codeUsuario, 
			@Named("lriFechaInicio")Long fechaInicio, 
			@Named("lriFechaFin")Long fechaFin) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListPosicionFiscalizador(GestionUsuario.listarPosicionByIntervalo(codeUsuario, fechaInicio, fechaFin));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}	
	
	@ApiMethod(name = "enviarCorreoFiscalizadores",path="enviarCorreoFiscalizadores")
	public ReturnValue enviarCorreoFiscalizadores(
			@Named("ecfAsunto")String paramAsunto,
			@Named("ecfContenido")String paramContentBody,
			@Named("ecfCorreos")String correos) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setValueReturn(GestionUsuario.enviarCorreoFiscalizadores(paramAsunto, paramContentBody, correos));
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "planificarNotificacion",path="planificarNotificacion")
	public ReturnValue planificarNotificacion(
			@Named("pnDiaInicio")String diaInicio, 
			@Named("pnDiaFin")String diaFin, 
			@Named("pnHoraInicio")Integer horaInicio,
			@Named("pnHoraFin")Integer horaFin,
			@Named("pnFrecuencia")Integer frecuencia, 
			@Named("pnTipoFrecuencia")String tipoFrecuencia) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setValueReturn(GestionUsuario.planificarNotificacion(diaInicio, diaFin, horaInicio, horaFin, frecuencia, tipoFrecuencia));
					
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "listarNotificaciones",path="listarNotificaciones")
	public ReturnValue listarNotificaciones() {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(List.class);
			valorRetorno.setReturnListNotificacion(GestionUsuario.listarNotificaciones());
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "guardarPosicion",path="guardarPosicion")
	public ReturnValue guardarPosicion(
			@Named("gpCodeUsuario")String codeUsuario, 
			@Named("gpLatitud")Double latitud,
			@Named("gpLongitud")Double longitud,
			@Named("gpDepartamento")String departamento,
			@Named("gpProvincia")String provincia,
			@Named("gpDistrito")String distrito) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setValueReturn(GestionUsuario.guardarPosicion(codeUsuario, latitud, longitud, departamento, provincia, distrito));
					
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
	
	@ApiMethod(name = "estadoNotificacion",path="estadoNotificacion")
	public ReturnValue estadoNotificacion(
			@Named("enCodeNotificacion")String codeNotificacion) {
		ReturnValue valorRetorno = new ReturnValue();
		try {
			valorRetorno.setNameClass(Boolean.class);
			valorRetorno.setValueReturn(GestionUsuario.estadoNotificacion(codeNotificacion));
					
		} catch (UnknownException e) {
			valorRetorno.setNameClass(UnknownException.class);
			valorRetorno.setValueReturn(e.getLocalizedMessage());
		}
		return valorRetorno;
	}
}
