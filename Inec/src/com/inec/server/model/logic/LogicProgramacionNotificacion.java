package com.inec.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.inec.server.model.bean.ProgramacionNotificacion;
import com.inec.server.model.dao.DaoProgramacionNotificacion;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class LogicProgramacionNotificacion {
	private PersistenceManager pm;

	public LogicProgramacionNotificacion(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoProgramacionNotificacion dao = new DaoProgramacionNotificacion(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoProgramacionNotificacion dao = new DaoProgramacionNotificacion(this.pm);
		return dao.getBean(id);
	}

	public Object getBeanByCode(String codeProgramacion) throws UnknownException {
		DaoProgramacionNotificacion dao = new DaoProgramacionNotificacion(this.pm);
		return dao.getBeanByCode(codeProgramacion);
	}
	public Collection<ProgramacionNotificacion> getListarBean() throws UnknownException {
		DaoProgramacionNotificacion dao = new DaoProgramacionNotificacion(this.pm);
		Collection<ProgramacionNotificacion> lista = dao.getListarBean();
		return lista;
	}
}
