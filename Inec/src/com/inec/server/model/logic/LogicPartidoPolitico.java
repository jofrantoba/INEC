package com.inec.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.inec.server.model.bean.PartidoPolitico;
import com.inec.server.model.dao.DaoPartidoPolitico;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class LogicPartidoPolitico {
	private PersistenceManager pm;

	public LogicPartidoPolitico(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoPartidoPolitico dao = new DaoPartidoPolitico(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoPartidoPolitico dao = new DaoPartidoPolitico(this.pm);
		return dao.getBean(id);
	}
	
	public Object getBeanByCode(String codePartidoPolitico) throws UnknownException {
		DaoPartidoPolitico dao = new DaoPartidoPolitico(this.pm);
		return dao.getBeanByCode(codePartidoPolitico);
	}

	public Collection<PartidoPolitico> getListarBean() throws UnknownException {
		DaoPartidoPolitico dao = new DaoPartidoPolitico(this.pm);
		Collection<PartidoPolitico> lista = dao.getListarBean();
		return lista;
	}
}
