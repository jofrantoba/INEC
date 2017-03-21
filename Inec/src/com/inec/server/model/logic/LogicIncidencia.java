package com.inec.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.inec.server.model.bean.Incidencia;
import com.inec.server.model.dao.DaoIncidencia;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class LogicIncidencia {
	private PersistenceManager pm;

	public LogicIncidencia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoIncidencia dao = new DaoIncidencia(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoIncidencia dao = new DaoIncidencia(this.pm);
		return dao.getBean(id);
	}
	public Object getBeanByCode(String codeIncidencia)  throws UnknownException {
		DaoIncidencia dao = new DaoIncidencia(this.pm);
		return dao.getBeanByCode(codeIncidencia);
	}

	public Collection<Incidencia> getListarBean() throws UnknownException {
		DaoIncidencia dao = new DaoIncidencia(this.pm);
		Collection<Incidencia> lista = dao.getListarBean();
		return lista;
	}
	public Collection<Incidencia> getListarBeanByUsuario(String dniUsuario)throws UnknownException {
		DaoIncidencia dao = new DaoIncidencia(this.pm);
		Collection<Incidencia> lista = dao.getListarBeanByUsuario(dniUsuario);
		return lista;
	}
}
