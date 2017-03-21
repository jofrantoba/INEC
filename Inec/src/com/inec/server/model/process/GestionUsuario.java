package com.inec.server.model.process;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.jdo.PersistenceManager;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.modules.ModulesServiceFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.inec.server.model.bean.EnviarCorreo;
import com.inec.server.model.bean.Notificacion;
import com.inec.server.model.bean.PosicionFiscalizador;
import com.inec.server.model.bean.ProgramacionNotificacion;
import com.inec.server.model.bean.SesionFiscalizador;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.server.model.bean.UsuarioNotificacion;
import com.inec.server.model.bean.Zona;
import com.inec.server.model.dao.PMF;
import com.inec.server.model.logic.LogicEnviarCorreo;
import com.inec.server.model.logic.LogicNotificacion;
import com.inec.server.model.logic.LogicPosicionFiscalizador;
import com.inec.server.model.logic.LogicProgramacionNotificacion;
import com.inec.server.model.logic.LogicSesionFiscalizador;
import com.inec.server.model.logic.LogicUsuarioFiscalizador;
import com.inec.server.model.logic.LogicUsuarioNotificacion;
import com.inec.server.model.logic.LogicZona;
import com.inec.shared.AESencrypt;
import com.inec.shared.BeanParametro;
import com.inec.shared.GeneradorClave;
import com.inec.shared.MyMessage;
import com.inec.shared.StringHex;
import com.inec.shared.UnknownException;
import com.jofrantoba.httpfcm.AndroidNotificationPayLoad;
import com.jofrantoba.httpfcm.DataPayLoad;
import com.jofrantoba.httpfcm.EnumContentType;
import com.jofrantoba.httpfcm.FcmConection;
import com.jofrantoba.httpfcm.NotificationMessage;

public class GestionUsuario {

	private final static String INSERTAR = "I";
	private final static String ACTUALIZAR = "A";
	private final static String DESACTIVADO = "D";
	private final static String ACTIVADO = "A";
	private final static String NOVISTO = "N";

	/**
	 * 
	 * @param dni
	 * @param clave
	 * @param nombres
	 * @param apellidos
	 * @param correoPersonal
	 * @param correoInstitucional
	 * @param telefono
	 * @param codeZona
	 * @return
	 * @throws UnknownException
	 */
	public static UsuarioFiscalizador loginUsuario(String dniUsuario, String claveUsuario, String tokenFirebase)
			throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicUsuarioFiscalizador logicUsuarioFiscalizador = new LogicUsuarioFiscalizador(pm);
			UsuarioFiscalizador beanUsuarioFiscalizador = (UsuarioFiscalizador) logicUsuarioFiscalizador
					.getBeanByLogin(dniUsuario, AESencrypt.encrypt(claveUsuario));
			if (beanUsuarioFiscalizador == null) {
				GestionShared.closeConnection(pm, null);
				throw new UnknownException(MyMessage.ERROR_NO_EXISTENCIA);
			}
			if (beanUsuarioFiscalizador.getEstado().equalsIgnoreCase(DESACTIVADO)) {
				throw new UnknownException(MyMessage.CUENTA_NO_ACTIVA);
			}

			LogicSesionFiscalizador logicSesionFiscalizador = new LogicSesionFiscalizador(pm);
			SesionFiscalizador beanSesionFiscalizador = new SesionFiscalizador();
			String codeSesionFiscalizador = StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
			beanSesionFiscalizador.setIdInicioSesion(KeyFactory.keyToString(
					KeyFactory.createKey(SesionFiscalizador.class.getSimpleName(), codeSesionFiscalizador)));
			beanSesionFiscalizador.setCodeInicioSesion(codeSesionFiscalizador);
			beanSesionFiscalizador.setCodeUsuarioFiscalizador(beanUsuarioFiscalizador.getCodeUsuarioFiscalizador());
			beanSesionFiscalizador.setInicio(new SimpleDateFormat("MM/dd/YYYY HH:mm:ss").format(new Date()));
			beanSesionFiscalizador.setFin("");
			beanSesionFiscalizador.setVersion(new java.util.Date().getTime());
			BeanParametro beanParametro = new BeanParametro();
			beanParametro.setBean(beanSesionFiscalizador);
			beanParametro.setTipoOperacion(INSERTAR);
			Boolean rptaSesionFiscalizador = logicSesionFiscalizador.mantenimiento(beanParametro);
			if (!rptaSesionFiscalizador) {
				throw new UnknownException(MyMessage.ERROR_OPERACION);
			}
			beanUsuarioFiscalizador.setTokenFirebase(tokenFirebase);
			beanParametro = new BeanParametro();
			beanParametro.setBean(beanUsuarioFiscalizador);
			beanParametro.setTipoOperacion(ACTUALIZAR);

			Boolean rptaUsuario = logicUsuarioFiscalizador.mantenimiento(beanParametro);
			if (!rptaUsuario) {
				throw new UnknownException(MyMessage.ERROR_OPERACION);
			}
			GestionShared.closeConnection(pm, null);
			beanUsuarioFiscalizador.setOperacion(codeSesionFiscalizador);
			return beanUsuarioFiscalizador;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}

	public static Boolean cerrarSesionFiscalizador(String codeSesionFiscalizador) throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();

			LogicSesionFiscalizador logicSesionFiscalizador = new LogicSesionFiscalizador(pm);
			SesionFiscalizador beanSesionFiscalizador = logicSesionFiscalizador.getBeanByCode(codeSesionFiscalizador);
			if (beanSesionFiscalizador == null) {
				GestionShared.closeConnection(pm, null);
				throw new UnknownException(MyMessage.ERROR_NO_EXISTENCIA);
			}
			beanSesionFiscalizador.setFin(new SimpleDateFormat("MM/dd/YYYY HH:mm:ss").format(new Date()));
			BeanParametro beanParametro = new BeanParametro();
			beanParametro.setBean(beanSesionFiscalizador);
			beanParametro.setTipoOperacion(ACTUALIZAR);
			Boolean rptaSesionFiscalizador = logicSesionFiscalizador.mantenimiento(beanParametro);
			if (!rptaSesionFiscalizador) {
				throw new UnknownException(MyMessage.ERROR_OPERACION);
			}
			return true;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}

	public static UsuarioFiscalizador actualizarPassword(String dniUsuario, String claveUsuario, String nuevaClave,
			String confirmarClave) throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicUsuarioFiscalizador logicUsuarioFiscalizador = new LogicUsuarioFiscalizador(pm);
			UsuarioFiscalizador beanUsuarioFiscalizador = (UsuarioFiscalizador) logicUsuarioFiscalizador
					.getBeanByLogin(dniUsuario, AESencrypt.encrypt(claveUsuario));
			if (beanUsuarioFiscalizador == null) {
				GestionShared.closeConnection(pm, null);
				throw new UnknownException(MyMessage.ERROR_NO_EXISTENCIA);
			}
			if (!nuevaClave.equals(confirmarClave)) {
				throw new UnknownException(MyMessage.CLAVE_NO_COINCIDE);
			}
			beanUsuarioFiscalizador.setClave(AESencrypt.encrypt(nuevaClave));
			BeanParametro beanParametro = new BeanParametro();
			beanParametro.setTipoOperacion(INSERTAR);
			beanParametro.setBean(beanUsuarioFiscalizador);
			Boolean rptaUsuarioFiscalizador = logicUsuarioFiscalizador.mantenimiento(beanParametro);
			if (!rptaUsuarioFiscalizador) {
				throw new UnknownException(MyMessage.ERROR_OPERACION);
			}
			GestionShared.closeConnection(pm, null);
			return beanUsuarioFiscalizador;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}

	public static UsuarioFiscalizador crearUsuario(String dni, String nombres, String apellidos, String correoPersonal,
			String correoInstitucional, String telefono, String estado, String codeZona) throws UnknownException {
		PersistenceManager pm = null;
		try {
			if (dni == null || dni.isEmpty() || nombres == null || nombres.isEmpty() || apellidos == null
					|| apellidos.isEmpty() || correoPersonal == null || correoPersonal.isEmpty() || codeZona == null
					|| codeZona.isEmpty()) {
				throw new UnknownException(MyMessage.PARAMETROS_INVALIDOS);
			}
			pm = PMF.getPMF().getPersistenceManager();
			LogicUsuarioFiscalizador logicUsuarioFiscalizador = new LogicUsuarioFiscalizador(pm);
			LogicZona logicZona = new LogicZona(pm);

			UsuarioFiscalizador beanUsuarioFiscalizador = (UsuarioFiscalizador) logicUsuarioFiscalizador
					.getBeanByDni(dni);
			if (beanUsuarioFiscalizador != null) {
				GestionShared.closeConnection(pm, null);
				throw new UnknownException(MyMessage.ERROR_EXISTENCIA);
			}
			Zona beanZona = logicZona.getBeanByCode(codeZona);
			if (beanZona == null) {
				GestionShared.closeConnection(pm, null);
				throw new UnknownException(MyMessage.ERROR_NO_EXISTENCIA);
			}
			String clave = GeneradorClave.getPassword(nombres, apellidos, dni, 8);
			beanUsuarioFiscalizador = new UsuarioFiscalizador();
			beanUsuarioFiscalizador.setIdUsuarioFiscalizador(
					KeyFactory.keyToString(KeyFactory.createKey(UsuarioFiscalizador.class.getSimpleName(), dni)));
			beanUsuarioFiscalizador.setCodeUsuarioFiscalizador(dni);
			beanUsuarioFiscalizador.setDniFiscalizador(dni);
			beanUsuarioFiscalizador.setClave(AESencrypt.encrypt(clave));
			beanUsuarioFiscalizador.setNombre(nombres);
			beanUsuarioFiscalizador.setApellido(apellidos);
			beanUsuarioFiscalizador.setCorreoPersonal(correoPersonal);
			beanUsuarioFiscalizador.setCorreoCorporativo(correoInstitucional);
			beanUsuarioFiscalizador.setNumero(telefono);
			beanUsuarioFiscalizador.setNombreZona(beanZona.getNombreZona());
			beanUsuarioFiscalizador.setCodeZona(codeZona);
			beanUsuarioFiscalizador.setEstado(estado);
			beanUsuarioFiscalizador.setVersion(new java.util.Date().getTime());

			BeanParametro beanParametro = new BeanParametro();
			beanParametro.setTipoOperacion(INSERTAR);
			beanParametro.setBean(beanUsuarioFiscalizador);
			Boolean rptaUsuarioFiscalizador = logicUsuarioFiscalizador.mantenimiento(beanParametro);
			if (!rptaUsuarioFiscalizador) {
				throw new UnknownException(MyMessage.ERROR_OPERACION);
			}
			enviarNotificacionCreacionCuenta(dni, correoPersonal, clave, nombres, apellidos);
			GestionShared.closeConnection(pm, null);
			return beanUsuarioFiscalizador;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}

	public static UsuarioFiscalizador actualizarUsuario(String dni, String nombres, String apellidos,
			String correoPersonal, String correoInstitucional, String telefono, String estado, String codeZona)
			throws UnknownException {
		PersistenceManager pm = null;
		try {
			if (dni == null || dni.isEmpty() || nombres == null || nombres.isEmpty() || apellidos == null
					|| apellidos.isEmpty() || correoPersonal == null || correoPersonal.isEmpty() || codeZona == null
					|| codeZona.isEmpty()) {
				throw new UnknownException(MyMessage.PARAMETROS_INVALIDOS);
			}
			pm = PMF.getPMF().getPersistenceManager();
			LogicUsuarioFiscalizador logicUsuarioFiscalizador = new LogicUsuarioFiscalizador(pm);
			LogicZona logicZona = new LogicZona(pm);

			UsuarioFiscalizador beanUsuarioFiscalizador = (UsuarioFiscalizador) logicUsuarioFiscalizador
					.getBeanByDni(dni);
			if (beanUsuarioFiscalizador == null) {
				GestionShared.closeConnection(pm, null);
				throw new UnknownException(MyMessage.errorNoExistencia(UsuarioFiscalizador.class));
			}
			Zona beanZona = logicZona.getBeanByCode(codeZona);
			if (beanZona == null) {
				GestionShared.closeConnection(pm, null);
				throw new UnknownException(MyMessage.errorNoExistencia(Zona.class));
			}
			beanUsuarioFiscalizador.setCodeUsuarioFiscalizador(dni);
			beanUsuarioFiscalizador.setDniFiscalizador(dni);
			beanUsuarioFiscalizador.setNombre(nombres);
			beanUsuarioFiscalizador.setApellido(apellidos);
			beanUsuarioFiscalizador.setCorreoPersonal(correoPersonal);
			beanUsuarioFiscalizador.setCorreoCorporativo(correoInstitucional);
			beanUsuarioFiscalizador.setNumero(telefono);
			beanUsuarioFiscalizador.setNombreZona(beanZona.getNombreZona());
			beanUsuarioFiscalizador.setCodeZona(codeZona);
			beanUsuarioFiscalizador.setEstado(estado);
			beanUsuarioFiscalizador.setVersion(new java.util.Date().getTime());

			BeanParametro beanParametro = new BeanParametro();
			beanParametro.setTipoOperacion(ACTUALIZAR);
			beanParametro.setBean(beanUsuarioFiscalizador);
			Boolean rptaUsuarioFiscalizador = logicUsuarioFiscalizador.mantenimiento(beanParametro);
			if (!rptaUsuarioFiscalizador) {
				throw new UnknownException(MyMessage.ERROR_OPERACION);
			}
			GestionShared.closeConnection(pm, null);
			return beanUsuarioFiscalizador;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}

	public static UsuarioFiscalizador actualizarEstado(String dni, String estado) throws UnknownException {
		PersistenceManager pm = null;
		try {
			if (dni == null || dni.isEmpty() || estado == null || estado.isEmpty()) {
				throw new UnknownException(MyMessage.PARAMETROS_INVALIDOS);
			}
			pm = PMF.getPMF().getPersistenceManager();
			LogicUsuarioFiscalizador logicUsuarioFiscalizador = new LogicUsuarioFiscalizador(pm);

			UsuarioFiscalizador beanUsuarioFiscalizador = (UsuarioFiscalizador) logicUsuarioFiscalizador
					.getBeanByDni(dni);
			if (beanUsuarioFiscalizador == null) {
				GestionShared.closeConnection(pm, null);
				throw new UnknownException(MyMessage.ERROR_NO_EXISTENCIA);
			}
			beanUsuarioFiscalizador.setEstado(estado);
			beanUsuarioFiscalizador.setVersion(new java.util.Date().getTime());

			BeanParametro beanParametro = new BeanParametro();
			beanParametro.setTipoOperacion(ACTUALIZAR);
			beanParametro.setBean(beanUsuarioFiscalizador);
			Boolean rptaUsuarioFiscalizador = logicUsuarioFiscalizador.mantenimiento(beanParametro);
			if (!rptaUsuarioFiscalizador) {
				throw new UnknownException(MyMessage.ERROR_OPERACION);
			}
			GestionShared.closeConnection(pm, null);
			return beanUsuarioFiscalizador;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}

	/**
	 * 
	 * @return
	 * @throws UnknownException
	 */
	public static List<UsuarioFiscalizador> listarUsuarios() throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicUsuarioFiscalizador logicUsuarioFiscalizador = new LogicUsuarioFiscalizador(pm);
			return (List<UsuarioFiscalizador>) logicUsuarioFiscalizador.getListarBean();
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}

	public static List<PosicionFiscalizador> listarPosicionFiscalizadors(String codeUsuario) throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicPosicionFiscalizador logicPosicionFiscalizador = new LogicPosicionFiscalizador(pm);
			LogicUsuarioFiscalizador logicUsuarioFiscalizador = new LogicUsuarioFiscalizador(pm);
			UsuarioFiscalizador beanUsuario = (UsuarioFiscalizador) logicUsuarioFiscalizador.getBeanByDni(codeUsuario);
			if (beanUsuario == null) {
				throw new UnknownException(MyMessage.ERROR_NO_EXISTENCIA);
			}
			List<PosicionFiscalizador> listPosicionFiscalizador = (List<PosicionFiscalizador>) logicPosicionFiscalizador
					.getListarBeanByUsuario(codeUsuario);
			return listPosicionFiscalizador;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}

	public static List<UsuarioFiscalizador> buscarUsuarioFiscalizadores(String patron, String codeZona)
			throws UnknownException {

		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicUsuarioFiscalizador logicUsuarioFiscalizador = new LogicUsuarioFiscalizador(pm);
			List<UsuarioFiscalizador> listaUsuarioFiscalizadors = (List<UsuarioFiscalizador>) logicUsuarioFiscalizador
					.getListarByZona(codeZona);
			if (listaUsuarioFiscalizadors.isEmpty()) {
				listaUsuarioFiscalizadors = (List<UsuarioFiscalizador>) logicUsuarioFiscalizador.getListarBean();
				if (listaUsuarioFiscalizadors.isEmpty()) {
					return new ArrayList<>();
				}
			}

			if (patron == null || patron.trim().isEmpty()) {
				patron = ".*.*";
			} else {
				patron = ".*".concat(patron.trim().toUpperCase()).concat(".*");
			}
			patron = ".*".concat(patron.trim().toUpperCase()).concat(".*");
			List<UsuarioFiscalizador> listaFiltro = new ArrayList<UsuarioFiscalizador>();
			for (int i = 0; i < listaUsuarioFiscalizadors.size(); i++) {
				if (listaUsuarioFiscalizadors.get(i).getNombre().toUpperCase().matches(patron)
						|| listaUsuarioFiscalizadors.get(i).getDniFiscalizador().matches(patron)
						|| listaUsuarioFiscalizadors.get(i).getApellido().toUpperCase().matches(patron)) {
					listaFiltro.add(listaUsuarioFiscalizadors.get(i));
					continue;
				}
			}
			return listaFiltro;

		} catch (Exception ex) {
			throw new UnknownException(ex.getLocalizedMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}

	public static List<PosicionFiscalizador> listarPosicionByFechaFormat(String codeUsuario, String fechaFormat)
			throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();

			LogicPosicionFiscalizador logicPosicionFiscalizador = new LogicPosicionFiscalizador(pm);
			LogicUsuarioFiscalizador logicUsuarioFiscalizador = new LogicUsuarioFiscalizador(pm);
			UsuarioFiscalizador beanUsuario = (UsuarioFiscalizador) logicUsuarioFiscalizador.getBeanByDni(codeUsuario);
			if (beanUsuario == null) {
				throw new UnknownException(MyMessage.ERROR_NO_EXISTENCIA);
			}
			List<PosicionFiscalizador> listPosicionFiscalizador = (List<PosicionFiscalizador>) logicPosicionFiscalizador
					.getListarBeanByFechaFormat(codeUsuario, fechaFormat);
			return listPosicionFiscalizador;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}

	@SuppressWarnings({ "deprecation" })
	public static List<PosicionFiscalizador> listarPosicionByIntervalo(String codeUsuario, Long fechaInicio,
			Long fechaFin) throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			String fechaFinal = new SimpleDateFormat("MM/dd/YYYY").format(fechaFin);

			Calendar aa = GregorianCalendar.getInstance();
			aa.setTime(new Date(fechaFinal));
			aa.add(Calendar.DAY_OF_MONTH, +1);
			fechaFin = new Date(new SimpleDateFormat("MM/dd/YYYY").format(aa.getTime())).getTime();
			fechaInicio = new Date(new SimpleDateFormat("MM/dd/YYYY").format(fechaInicio)).getTime();
			LogicPosicionFiscalizador logicPosicionFiscalizador = new LogicPosicionFiscalizador(pm);
			LogicUsuarioFiscalizador logicUsuarioFiscalizador = new LogicUsuarioFiscalizador(pm);
			UsuarioFiscalizador beanUsuario = (UsuarioFiscalizador) logicUsuarioFiscalizador.getBeanByDni(codeUsuario);
			if (beanUsuario == null) {
				throw new UnknownException(MyMessage.ERROR_NO_EXISTENCIA);
			}
			List<PosicionFiscalizador> listPosicionFiscalizadorInicial = (List<PosicionFiscalizador>) logicPosicionFiscalizador
					.getListarBeanByFechaFormatInicial(codeUsuario, fechaInicio);
			List<PosicionFiscalizador> listPosicionFiscalizadorFinal = (List<PosicionFiscalizador>) logicPosicionFiscalizador
					.getListarBeanByFechaFormatFinal(codeUsuario, fechaFin);
			List<PosicionFiscalizador> listPosicionFiscalizadorReturn = new ArrayList<>();
			for (int i = 0; i < listPosicionFiscalizadorInicial.size(); i++) {
				for (int j = 0; j < listPosicionFiscalizadorFinal.size(); j++) {
					if (listPosicionFiscalizadorInicial.get(i).getCodePosicionFiscalizador()
							.equalsIgnoreCase(listPosicionFiscalizadorFinal.get(j).getCodePosicionFiscalizador())) {
						listPosicionFiscalizadorReturn.add(listPosicionFiscalizadorInicial.get(i));
						continue;
					}
				}

			}
			return listPosicionFiscalizadorReturn;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}

	public static Boolean enviarNotificacionCreacionCuenta(String dni, String correoFiscalizador, String clave,
			String nombres, String apellidos)
			throws UnknownException, UnsupportedEncodingException, MessagingException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		String remitente = "ingmanuelcespedes@gmail.com";
		String usuario = "Sr(a). " + nombres + " " + apellidos;
		String URL = "http://www.inec.com";
		String msgBody = "<div id=\"titulo\" style=\"padding:3mm;color:#B40404;font-weight: bold; font-size:10mm;\">INEC</div>";
		msgBody = msgBody + "<div style=\"padding:3mm;font-size: 14px;\">" + usuario + "</div>";
		msgBody = msgBody + "<div style=\"padding:3mm;font-size: 14px;\">" + "Ha sido registrado en el Sistema INEC"
				+ "</div>";
		msgBody = msgBody + "<div style=\"padding:3mm;font-size: 14px;\"><b>Usuario:</b> " + dni + "</div>";
		msgBody = msgBody + "<div style=\"padding:3mm;font-size: 14px;\"><b>Clave:</b> " + clave + "</div>";
		Message msg = new MimeMessage(session);
		Multipart mp = new MimeMultipart();
		MimeBodyPart htmlPart = new MimeBodyPart();
		msg.setFrom(new InternetAddress(remitente.trim().toString(), URL));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(correoFiscalizador, usuario));
		msg.setSubject("Cuenta de Acceso INEC");
		htmlPart.setContent(msgBody, "text/html");
		mp.addBodyPart(htmlPart);
		msg.setContent(mp);
		Transport.send(msg);
		return true;
	}

	public static Boolean enviarCorreoFiscalizadores(String paramAsunto, String paramContentBody, String correos)
			throws UnknownException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		String remitente = "ingmanuelcespedes@gmail.com";
		String URL = "http://www.inec.com";
		String msgBody = "";
		Message msg = new MimeMessage(session);
		msgBody = msgBody + "<div style=\"padding:3mm;font-size: 14px;\">" + paramContentBody + "</div>";
		Multipart mp = new MimeMultipart();
		MimeBodyPart htmlPart = new MimeBodyPart();
		try {
			msg.setFrom(new InternetAddress(remitente.trim().toString(), URL));
		} catch (UnsupportedEncodingException | MessagingException e) {
			throw new UnknownException(e.getMessage());
		}
		String[] recipientList = correos.split(",");
		InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
		int counter = 0;
		for (String recipient : recipientList) {
			try {
				recipientAddress[counter] = new InternetAddress(recipient.trim());
			} catch (AddressException e) {
				throw new UnknownException(e.getMessage());
			}
			counter++;
		}
		try {
			msg.addRecipients(Message.RecipientType.CC, recipientAddress);
			msg.setSubject(paramAsunto);
			htmlPart.setContent(msgBody, "text/html");
			mp.addBodyPart(htmlPart);
			msg.setContent(mp);
			Transport.send(msg);	
			
		} catch (MessagingException e) {
			throw new UnknownException(e.getMessage());
		}
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicEnviarCorreo logicEnviarCorreo= new LogicEnviarCorreo(pm);
									
			EnviarCorreo beanEnviarCorreo= new EnviarCorreo();
			String codeEnviarCorreo = StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
			beanEnviarCorreo.setIdEnviarCorreo(KeyFactory.keyToString(
					KeyFactory.createKey(EnviarCorreo.class.getSimpleName(), codeEnviarCorreo)));
			beanEnviarCorreo.setCodeEnviarCorreo(codeEnviarCorreo);			
			beanEnviarCorreo.setDestinatarios(correos);
			beanEnviarCorreo.setAsunto(paramAsunto);
			beanEnviarCorreo.setContenido(paramContentBody);
			beanEnviarCorreo.setVersion(new java.util.Date().getTime());
			beanEnviarCorreo.setFecha(new SimpleDateFormat("dd/MM/YYYY").format(beanEnviarCorreo.getVersion()));
			BeanParametro beanParametro= new BeanParametro();
			beanParametro.setBean(beanEnviarCorreo);
			beanParametro.setTipoOperacion(INSERTAR);
			Boolean rptaEnviarCorreo= logicEnviarCorreo.mantenimiento(beanParametro);			
			if(!rptaEnviarCorreo){
				throw new UnknownException(MyMessage.ERROR_OPERACION);
			}									
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
		return true;
	}

	
	public static List<EnviarCorreo> listarCorreosEnviados()
			throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();

			LogicEnviarCorreo logicEnviarCorreo= new LogicEnviarCorreo(pm);
			return (List<EnviarCorreo>) logicEnviarCorreo.getListarBean();
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}
	
	private final static String ID_PROGRAMACION = "id_programacion";
	
	public static ProgramacionNotificacion planificarNotificacion(String diaInicio, String diaFin, Integer horaInicio, Integer horaFin,
			Integer frecuencia, String tipoFrecuencia) throws UnknownException {
		PersistenceManager pm = null;
		try {			
			pm = PMF.getPMF().getPersistenceManager();
			LogicProgramacionNotificacion logicProgramacionNotificacion = new LogicProgramacionNotificacion(pm);
			ProgramacionNotificacion beanProgramacionBD= (ProgramacionNotificacion) logicProgramacionNotificacion.getBeanByCode(ID_PROGRAMACION);
			if(beanProgramacionBD!=null){
				ProgramacionNotificacion beanProgramacionHistorico= new ProgramacionNotificacion();
				String codeProgramacionHistorico = StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
				beanProgramacionHistorico.setIdProgramacionNotificacion(KeyFactory.keyToString(
						KeyFactory.createKey(ProgramacionNotificacion.class.getSimpleName(), codeProgramacionHistorico)));
				beanProgramacionHistorico.setCodeProgramacionNotificacion(codeProgramacionHistorico);	
				beanProgramacionHistorico.setDiaInicio(beanProgramacionBD.getDiaInicio());
				beanProgramacionHistorico.setDiaFin(beanProgramacionBD.getDiaFin());
				beanProgramacionHistorico.setHoraInicio(beanProgramacionBD.getHoraInicio());
				beanProgramacionHistorico.setHoraFin(beanProgramacionBD.getHoraFin());
				beanProgramacionHistorico.setFrecuencia(beanProgramacionBD.getFrecuencia());
				beanProgramacionHistorico.setTipoFrecuencia(beanProgramacionBD.getTipoFrecuencia());
				beanProgramacionHistorico.setHoraInicioLabel(beanProgramacionBD.getHoraInicioLabel());
				beanProgramacionHistorico.setHoraFinLabel(beanProgramacionBD.getHoraFinLabel());
				beanProgramacionHistorico.setHoraInicioDinamic(beanProgramacionBD.getHoraInicioDinamic());					
				beanProgramacionHistorico.setHoraInicioDinamicLabel(beanProgramacionBD.getHoraInicioDinamicLabel());			
				beanProgramacionHistorico.setVersion(beanProgramacionBD.getVersion());
				beanProgramacionHistorico.setEstado(DESACTIVADO);
				beanProgramacionHistorico.setFecha(beanProgramacionBD.getFecha());
				beanProgramacionHistorico.setFechaFin(new SimpleDateFormat("dd/MM/YYYY").format(new java.util.Date().getTime()));
				
				BeanParametro beanParametro = new BeanParametro();
				beanParametro.setBean(beanProgramacionHistorico);
				beanParametro.setTipoOperacion(INSERTAR);
				logicProgramacionNotificacion.mantenimiento(beanParametro);
				
			}
			ProgramacionNotificacion beanProgramacionNotificacion = new ProgramacionNotificacion();
			String codeProgramacion = StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
			beanProgramacionNotificacion.setIdProgramacionNotificacion(KeyFactory.keyToString(
					KeyFactory.createKey(ProgramacionNotificacion.class.getSimpleName(), ID_PROGRAMACION)));
			beanProgramacionNotificacion.setCodeProgramacionNotificacion(ID_PROGRAMACION);
			beanProgramacionNotificacion.setDiaInicio(diaInicio);
			beanProgramacionNotificacion.setDiaFin(diaFin);
			beanProgramacionNotificacion.setHoraInicio(horaInicio);
			beanProgramacionNotificacion.setHoraFin(horaFin);
			beanProgramacionNotificacion.setFrecuencia(frecuencia);
			beanProgramacionNotificacion.setTipoFrecuencia(tipoFrecuencia);
					
			beanProgramacionNotificacion.setHoraInicioLabel((horaInicio)+":00");
			beanProgramacionNotificacion.setHoraFinLabel((horaFin)+":00");
			Integer horaInicioDinamic=horaInicio;
			
			Calendar calendario = GregorianCalendar.getInstance();			
			calendario.setTime(new java.util.Date());
			calendario.add(Calendar.DAY_OF_WEEK, 1);
			
			Calendar myCalendar = GregorianCalendar.getInstance();			
			Integer horaActual= GeneradorClave.cambiarHora(myCalendar.get(Calendar.HOUR_OF_DAY));
			
			while(horaInicioDinamic<horaActual && (horaInicioDinamic+frecuencia<=23)){
				horaInicioDinamic=horaInicioDinamic+frecuencia;
			}					      
			beanProgramacionNotificacion.setHoraInicioDinamic(horaInicioDinamic);					
			beanProgramacionNotificacion.setHoraInicioDinamicLabel(horaInicioDinamic+":00");			
			beanProgramacionNotificacion.setVersion(new java.util.Date().getTime());
			beanProgramacionNotificacion.setEstado(ACTIVADO);
			beanProgramacionNotificacion.setFecha(new SimpleDateFormat("dd/MM/YYYY").format(beanProgramacionNotificacion.getVersion()));
			
			BeanParametro beanParametro = new BeanParametro();
			beanParametro.setBean(beanProgramacionNotificacion);
			beanParametro.setTipoOperacion(INSERTAR);

			logicProgramacionNotificacion.mantenimiento(beanParametro);

			Queue queue = QueueFactory.getQueue("notification-queue");
			queue.add(TaskOptions.Builder.withUrl("/gaejcronjob").param("codeProgramacionNotificacion", codeProgramacion)
							.header("Host", ModulesServiceFactory.getModulesService().getVersionHostname(null, null)));
			return beanProgramacionNotificacion;

		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}

	public static List<ProgramacionNotificacion> listProgramaciones() throws UnknownException {
		PersistenceManager pm = null;
		try {			
			pm = PMF.getPMF().getPersistenceManager();
			LogicProgramacionNotificacion logicProgramacionNotificacion = new LogicProgramacionNotificacion(pm);
			return (List<ProgramacionNotificacion>) logicProgramacionNotificacion.getListarBean();
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}
	
	public static Boolean prepareCronTask() throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicProgramacionNotificacion logicProgramacionNotificacion = new LogicProgramacionNotificacion(pm);
			LogicNotificacion logicNotificacion= new LogicNotificacion(pm);
			ProgramacionNotificacion beanProgramacionNotificacion = (ProgramacionNotificacion) logicProgramacionNotificacion
					.getBeanByCode(ID_PROGRAMACION);
			if (beanProgramacionNotificacion == null) {
				throw new UnknownException(MyMessage.ERROR_NO_EXISTENCIA);
			}	
						
			Integer diaInicioIndice= -1;
			Integer diaFinIndice=-1;
			Integer diaActualIndice=-1;
			String diaInicio= beanProgramacionNotificacion.getDiaInicio();
			String diaFin=beanProgramacionNotificacion.getDiaFin();				
			Integer horaFinPlanificacion= beanProgramacionNotificacion.getHoraFin();
			Integer horaInicioPlanificacion= beanProgramacionNotificacion.getHoraInicio();
			Integer timePlanificacion= beanProgramacionNotificacion.getHoraInicioDinamic();			
			Integer frecuencia= beanProgramacionNotificacion.getFrecuencia();
			Calendar myCalendar = GregorianCalendar.getInstance();
			diaActualIndice=  myCalendar.get(Calendar.DAY_OF_WEEK);
			if(diaActualIndice==1){
				diaActualIndice=8;
			}
			Integer horaActual= GeneradorClave.cambiarHora(myCalendar.get(Calendar.HOUR_OF_DAY));
			diaInicioIndice= GeneradorClave.returnIndice(diaInicio);
			diaFinIndice=GeneradorClave.returnIndice(diaFin);
			if(diaInicioIndice<= diaFinIndice){
				if(diaActualIndice>= diaInicioIndice && diaActualIndice<= diaFinIndice){
						if(timePlanificacion<=23){
						if(horaActual==timePlanificacion){
							Notificacion beanNotificacion= new Notificacion();
							String codeNotificacion = StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
							beanNotificacion.setIdNotificacion(KeyFactory.keyToString(
									KeyFactory.createKey(Notificacion.class.getSimpleName(), codeNotificacion)));				
							beanNotificacion.setCodeNotificacion(codeNotificacion);
							beanNotificacion.setCodeProgramacionNotificacion(beanProgramacionNotificacion.getCodeProgramacionNotificacion());
							Date fechaNotificacion=new java.util.Date();
							beanNotificacion.setVersion(fechaNotificacion.getTime());
							beanNotificacion.setFechaNotificacionFiltro(new SimpleDateFormat("dd/MM/YYYY").format(fechaNotificacion.getTime()));
							beanNotificacion.setFechaNotificacion(fechaNotificacion);
							beanNotificacion.setEstado(NOVISTO);
							BeanParametro beanParametro = new BeanParametro();
							beanParametro.setBean(beanNotificacion);
							beanParametro.setTipoOperacion(INSERTAR);							
							logicNotificacion.mantenimiento(beanParametro);
								
							Integer result= (timePlanificacion+frecuencia);
							if(result>=horaFinPlanificacion){
								beanProgramacionNotificacion.setHoraInicioDinamic(horaInicioPlanificacion);	
								beanProgramacionNotificacion.setHoraInicioDinamicLabel(horaInicioPlanificacion+":00");
							}else{
								beanProgramacionNotificacion.setHoraInicioDinamic(result);	
								beanProgramacionNotificacion.setHoraInicioDinamicLabel(result+":00");
							}																														
							beanParametro= new BeanParametro();
							beanParametro.setBean(beanProgramacionNotificacion);
							beanParametro.setTipoOperacion(ACTUALIZAR);
							logicProgramacionNotificacion.mantenimiento(beanParametro);
							
							cronNotificarUsuarios(beanNotificacion);
						}
					}					
				}
			}				
			return true;
		} catch (Exception ex) {
			throw new UnknownException(ex.getLocalizedMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}

	private final static String FIREBASE_SERVER_KEY = "AIzaSyBzME-2go_HHWV_QhcJFG6vNVddFYIpYzI";
	


	public static Boolean cronNotificarUsuarios(Notificacion beanNotificacion) throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicUsuarioNotificacion logicUsuarioNotificacion = new LogicUsuarioNotificacion(pm);			
			StringBuilder msjNotificacion = new StringBuilder();
			msjNotificacion.append("Registre su Ubicacion");
			LogicUsuarioFiscalizador logicUsuarioFiscalizador = new LogicUsuarioFiscalizador(pm);
			List<UsuarioFiscalizador> listUsuarioFiscalizador = (List<UsuarioFiscalizador>) logicUsuarioFiscalizador
					.getListarBean();
			Iterator<UsuarioFiscalizador> listQueueUsuarioIterator = listUsuarioFiscalizador.iterator();

			NotificationMessage not = new NotificationMessage();//
			not.setOptionRestrictedPackageName("com.inec");
			not.setOptionPriority(10);
			AndroidNotificationPayLoad payLoad = new AndroidNotificationPayLoad();
			payLoad.setSound("default");
//			payLoad.setClickAction("myaction");
			DataPayLoad dataPayLoad = new DataPayLoad();
			dataPayLoad.add("idDefault", "valueDefault");			
			List<UsuarioNotificacion> listUsuarioNotificacion = new ArrayList<UsuarioNotificacion>();//
			while (listQueueUsuarioIterator.hasNext()) {
				UsuarioFiscalizador beanUsuarioFiscalizador = listQueueUsuarioIterator.next();
				payLoad.setTitle(msjNotificacion.toString());
				payLoad.setBody("");
				not.setPayLoadData(dataPayLoad.buildPayLoad());
				not.setPayLoadNotification(payLoad.buildPayLoadAndroid());
				not.setTargetTo(beanUsuarioFiscalizador.getTokenFirebase());
				FcmConection cnx = new FcmConection(FIREBASE_SERVER_KEY, EnumContentType.JSON.getValue());
				cnx.sendNotificationHttp(not);

				UsuarioNotificacion beanUsuarioNotificacion = new UsuarioNotificacion();
				String codeUsuarioNotificacion = StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
				beanUsuarioNotificacion.setIdUsuarioNotificacion(KeyFactory.keyToString(
									KeyFactory.createKey(UsuarioNotificacion.class.getSimpleName(), codeUsuarioNotificacion)));
				beanUsuarioNotificacion.setCodeUsuarioNotificacion(codeUsuarioNotificacion);
				beanUsuarioNotificacion.setCodeNotificacion(beanNotificacion.getCodeNotificacion());
				beanUsuarioNotificacion
						.setCodeUsuarioFiscalizador(beanUsuarioFiscalizador.getCodeUsuarioFiscalizador());
				beanUsuarioNotificacion.setEstado("ENVIADO");
				beanUsuarioNotificacion.setFechaRecepcion(new java.util.Date());
				beanUsuarioNotificacion.setVersion(new java.util.Date().getTime());
				listUsuarioNotificacion.add(beanUsuarioNotificacion);
			}
			if (listUsuarioNotificacion.size() > 0) {
				Collection<BeanParametro> listParametros = new ArrayList<BeanParametro>();
				BeanParametro parametro = new BeanParametro();
				parametro.setBean(listUsuarioNotificacion);
				parametro.setTipoOperacion(INSERTAR);
				listParametros.add(parametro);
				Boolean rptaNotificacionTurista = logicUsuarioNotificacion.mantenimiento(listParametros);
				pm.close();
				return rptaNotificacionTurista;
			}
			return true;
		} catch (Exception ex) {
			throw new UnknownException(ex.getLocalizedMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws UnknownException
	 */
	public static List<Notificacion> listarNotificaciones() throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicNotificacion logicNotificacion = new LogicNotificacion(pm);
			Date fechaNotificacion=new java.util.Date();
			return (List<Notificacion>) logicNotificacion.getListarBean(new SimpleDateFormat("dd/MM/YYYY").format(fechaNotificacion.getTime()));
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}
	
	public static UsuarioFiscalizador verUsuario(String dniUsuario) throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicUsuarioFiscalizador logicUsuarioFiscalizador = new LogicUsuarioFiscalizador(pm);			
			return (UsuarioFiscalizador) logicUsuarioFiscalizador.getBeanByDni(dniUsuario);					
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}
	
	
	public static Boolean guardarPosicion(
			String codeUsuario, 
			Double latitud,
			Double longitud,
			String departamento,
			String provincia,
			String distrito
			) throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			Long version= new java.util.Date().getTime();
			BeanParametro beanParametro= new BeanParametro();
			PosicionFiscalizador beanPosicionFiscalizador= new PosicionFiscalizador();
			LogicPosicionFiscalizador logicPosicionFiscalizador= new LogicPosicionFiscalizador(pm);
			
			String codePosicionFiscalizador= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
			beanPosicionFiscalizador.setIdPosicionFiscalizador(KeyFactory.keyToString(
					KeyFactory.createKey(PosicionFiscalizador.class.getSimpleName(), codePosicionFiscalizador)));			
			beanPosicionFiscalizador.setCodePosicionFiscalizador(codePosicionFiscalizador);
			beanPosicionFiscalizador.setVersion(version);
			beanPosicionFiscalizador.setCodeUsuarioFiscalizador(codeUsuario);
			beanPosicionFiscalizador.setLatitud(latitud);
			beanPosicionFiscalizador.setLongitud(longitud);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
			beanPosicionFiscalizador.setFechaFormat(sdf.format(new java.util.Date().getTime()));
			beanPosicionFiscalizador.setDepartamento(departamento);
			beanPosicionFiscalizador.setProvincia(provincia);
			beanPosicionFiscalizador.setDistrito(distrito);
			beanParametro= new BeanParametro();
			beanParametro.setBean(beanPosicionFiscalizador);
			beanParametro.setTipoOperacion(INSERTAR);
			Boolean rptaPosicionFiscalizador= logicPosicionFiscalizador.mantenimiento(beanParametro);
			if(!rptaPosicionFiscalizador){
				throw new UnknownException(MyMessage.ERROR_OPERACION);
			}
			return true;
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}
	
	public static Boolean estadoNotificacion(String codeNotificacion) throws UnknownException {
		PersistenceManager pm = null;
		try {
			pm = PMF.getPMF().getPersistenceManager();
			LogicNotificacion logicNotificacion = new LogicNotificacion(pm);
			Notificacion beanNotificacion= (Notificacion) logicNotificacion.getBeanByCode(codeNotificacion);
			if (beanNotificacion == null) {
				throw new UnknownException(MyMessage.ERROR_NO_EXISTENCIA);
			}
			beanNotificacion.setEstado(DESACTIVADO);
			BeanParametro beanParametro= new BeanParametro();
			beanParametro.setBean(beanNotificacion);
			beanParametro.setTipoOperacion(ACTUALIZAR);
			
			Boolean rptaNotificacion= logicNotificacion.mantenimiento(beanParametro);
			return rptaNotificacion;			
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			GestionShared.closeConnection(pm, null);
		}
	}
}
