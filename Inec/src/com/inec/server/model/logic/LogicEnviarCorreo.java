package com.inec.server.model.logic;

import java.util.Collection;
import javax.jdo.PersistenceManager;
import com.inec.server.model.bean.EnviarCorreo;
import com.inec.server.model.dao.DaoEnviarCorreo;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class LogicEnviarCorreo {
	private PersistenceManager pm;

	public LogicEnviarCorreo(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoEnviarCorreo dao = new DaoEnviarCorreo(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoEnviarCorreo dao = new DaoEnviarCorreo(this.pm);
		return dao.getBean(id);
	}
	
	public Collection<EnviarCorreo> getListarBean() throws UnknownException {
		DaoEnviarCorreo dao = new DaoEnviarCorreo(this.pm);
		Collection<EnviarCorreo> lista = dao.getListarBean();
		return lista;
	}
}
