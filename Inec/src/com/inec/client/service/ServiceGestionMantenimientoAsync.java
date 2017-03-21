package com.inec.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.inec.server.model.bean.PosicionFiscalizador;
import com.inec.server.model.bean.ProgramacionNotificacion;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.server.model.bean.Zona;

public interface ServiceGestionMantenimientoAsync {
	void updateUsuarioFiscalizador(UsuarioFiscalizador beanUsuarioFiscalizador, AsyncCallback<UsuarioFiscalizador> callback);
	void removeUsuarioFiscalizador(String correoUsuarioFiscalizador, AsyncCallback<Boolean> callback);
	void listUsuarioFiscalizador(AsyncCallback<List<UsuarioFiscalizador>> callback);
	void crearUsuario(String dni, String nombres, String apellidos, String correoPersonal, String correoInstitucional,
			String telefono, String estado, String codeZona, AsyncCallback<UsuarioFiscalizador> callback);
	void actualizarEstado(String dni, String estado, AsyncCallback<UsuarioFiscalizador> callback);
	void actualizarUsuario(String dni, String nombres, String apellidos, String correoPersonal,
			String correoInstitucional, String telefono, String estado, String codeZona,
			AsyncCallback<UsuarioFiscalizador> callback);
	void listZona(AsyncCallback<List<Zona>> callback);
	void buscarUsuarioFiscalizadores(String patron, String codeZona, AsyncCallback<List<UsuarioFiscalizador>> callback);
	void enviarCorreoFiscalizadores(String paramAsunto, String paramContent, String paramDestinatarios,
			AsyncCallback<Boolean> callback);
	void listarRutasIntervalo(String codeUsuario, Long fechaInicio, Long fechaFin,
			AsyncCallback<List<PosicionFiscalizador>> callback);
	void planificarNotificacion(String diaInicio, String diaFin, Integer horaInicio, Integer horaFin,
			Integer frecuencia, String tipoFrecuencia, AsyncCallback<ProgramacionNotificacion> callback);
	void listProgramaciones(AsyncCallback<List<ProgramacionNotificacion>> callback);
	
}
