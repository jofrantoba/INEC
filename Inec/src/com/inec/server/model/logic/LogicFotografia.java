package com.inec.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.inec.server.model.bean.Fotografia;
import com.inec.server.model.dao.DaoFotografia;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class LogicFotografia {
	private PersistenceManager pm;

	public LogicFotografia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoFotografia dao = new DaoFotografia(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoFotografia dao = new DaoFotografia(this.pm);
		return dao.getBean(id);
	}

	public Collection<Fotografia> getListarBean() throws UnknownException {
		DaoFotografia dao = new DaoFotografia(this.pm);
		Collection<Fotografia> lista = dao.getListarBean();
		return lista;
	}
}
