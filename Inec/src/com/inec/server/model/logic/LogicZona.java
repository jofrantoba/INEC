package com.inec.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.inec.server.model.bean.Zona;
import com.inec.server.model.dao.DaoZona;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class LogicZona {
	private PersistenceManager pm;

	public LogicZona(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoZona dao = new DaoZona(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoZona dao = new DaoZona(this.pm);
		return dao.getBean(id);
	}
	
	public Zona getBeanByCode(String codeZona) throws UnknownException {
		DaoZona dao = new DaoZona(this.pm);
		return dao.getBeanByDni(codeZona);
	}
	
	public Collection<Zona> getListarBean() throws UnknownException {
		DaoZona dao = new DaoZona(this.pm);
		Collection<Zona> lista = dao.getListarBean();
		return lista;
	}
}
