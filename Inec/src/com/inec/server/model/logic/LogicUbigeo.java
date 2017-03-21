package com.inec.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.inec.server.model.bean.Ubigeo;
import com.inec.server.model.dao.DaoUbigeo;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class LogicUbigeo {
	private PersistenceManager pm;

	public LogicUbigeo(PersistenceManager pm) {
		this.pm = pm;
	}
	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoUbigeo dao = new DaoUbigeo(this.pm);
		return dao.mantenimiento(parametro);
	}
	public Object getBean(String id) throws UnknownException {
		DaoUbigeo dao = new DaoUbigeo(this.pm);
		return dao.getBean(id);
	}	
	public Object getBeanByUbigeo(String departamento,String provincia,String distrito) throws UnknownException {
		DaoUbigeo dao = new DaoUbigeo(this.pm);
		return dao.getBeanByUbigeo(departamento, provincia, distrito);
	}
	public Collection<Ubigeo> getListarBean() throws UnknownException {
		DaoUbigeo dao = new DaoUbigeo(this.pm);
		Collection<Ubigeo> lista = dao.getListarBean();
		return lista;
	}
}
