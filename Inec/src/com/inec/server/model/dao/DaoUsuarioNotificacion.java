package com.inec.server.model.dao;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.inec.server.model.bean.UsuarioNotificacion;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class DaoUsuarioNotificacion {
	private PersistenceManager pm;

	public DaoUsuarioNotificacion(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(UsuarioNotificacion.class, id);
	}

	public boolean mantenimiento(Collection<BeanParametro> parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	@SuppressWarnings("unchecked")
	public Collection<UsuarioNotificacion> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<UsuarioNotificacion> lista = (Collection<UsuarioNotificacion>) query
				.getListaBean(UsuarioNotificacion.class);
		return lista;
	}
}
