package com.inec.server.model.dao;

import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.inec.server.model.bean.Fotografia;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class DaoFotografia {
	private PersistenceManager pm;

	public DaoFotografia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}
	public boolean mantenimiento(List<BeanParametro> listParametros)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(listParametros);
	}
	
	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Fotografia.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Fotografia> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Fotografia> lista = (Collection<Fotografia>) query
				.getListaBean(Fotografia.class);
		return lista;
	}	
}
