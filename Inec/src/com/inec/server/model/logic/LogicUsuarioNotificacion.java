package com.inec.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.inec.server.model.bean.UsuarioNotificacion;
import com.inec.server.model.dao.DaoUsuarioNotificacion;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class LogicUsuarioNotificacion {
	private PersistenceManager pm;

	public LogicUsuarioNotificacion(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoUsuarioNotificacion dao = new DaoUsuarioNotificacion(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoUsuarioNotificacion dao = new DaoUsuarioNotificacion(this.pm);
		return dao.getBean(id);
	}
	
	public boolean mantenimiento(Collection<BeanParametro> parametro)
			throws UnknownException {
		DaoUsuarioNotificacion dao = new DaoUsuarioNotificacion(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Collection<UsuarioNotificacion> getListarBean() throws UnknownException {
		DaoUsuarioNotificacion dao = new DaoUsuarioNotificacion(this.pm);
		Collection<UsuarioNotificacion> lista = dao.getListarBean();
		return lista;
	}
}
