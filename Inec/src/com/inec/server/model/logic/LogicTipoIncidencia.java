package com.inec.server.model.logic;

import java.util.List;

import javax.jdo.PersistenceManager;

import com.inec.server.model.bean.TipoIncidencia;
import com.inec.server.model.dao.DaoTipoIncidencia;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class LogicTipoIncidencia {
	private PersistenceManager pm;

	public LogicTipoIncidencia(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoTipoIncidencia dao = new DaoTipoIncidencia(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoTipoIncidencia dao = new DaoTipoIncidencia(this.pm);
		return dao.getBean(id);
	}
	
	public Object getBeanByCode(String codeTipoIncidencia) throws UnknownException {
		DaoTipoIncidencia dao = new DaoTipoIncidencia(this.pm);
		return dao.getBeanByCode(codeTipoIncidencia);
	}

	public List<TipoIncidencia> getListarBean() throws UnknownException {
		DaoTipoIncidencia dao = new DaoTipoIncidencia(this.pm);
		List<TipoIncidencia> lista = dao.getListarBean();
		return lista;
	}
}
