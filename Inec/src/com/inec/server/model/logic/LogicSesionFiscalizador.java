package com.inec.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.inec.server.model.bean.SesionFiscalizador;
import com.inec.server.model.dao.DaoSesionFiscalizador;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class LogicSesionFiscalizador {
	private PersistenceManager pm;

	public LogicSesionFiscalizador(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoSesionFiscalizador dao = new DaoSesionFiscalizador(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoSesionFiscalizador dao = new DaoSesionFiscalizador(this.pm);
		return dao.getBean(id);
	}
	
	public SesionFiscalizador getBeanByCode(String codeSesionFiscalizador) throws UnknownException {
		DaoSesionFiscalizador dao = new DaoSesionFiscalizador(this.pm);
		return dao.getBeanByCode(codeSesionFiscalizador);
	}

	public Collection<SesionFiscalizador> getListarBean() throws UnknownException {
		DaoSesionFiscalizador dao = new DaoSesionFiscalizador(this.pm);
		Collection<SesionFiscalizador> lista = dao.getListarBean();
		return lista;
	}
}
