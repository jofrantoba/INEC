package com.inec.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.inec.server.model.bean.Notificacion;
import com.inec.server.model.dao.DaoNotificacion;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class LogicNotificacion {
	private PersistenceManager pm;

	public LogicNotificacion(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoNotificacion dao = new DaoNotificacion(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoNotificacion dao = new DaoNotificacion(this.pm);
		return dao.getBean(id);
	}
	
	public Object getBeanByCode(String codeNotificacion) throws UnknownException {
		DaoNotificacion dao = new DaoNotificacion(this.pm);
		return dao.getBeanByCode(codeNotificacion);
	}

	public Collection<Notificacion> getListarBean(String fecha) throws UnknownException {
		DaoNotificacion dao = new DaoNotificacion(this.pm);
		Collection<Notificacion> lista = dao.getListarBean(fecha);
		return lista;
	}
}
