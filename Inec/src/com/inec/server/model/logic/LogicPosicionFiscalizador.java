package com.inec.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.inec.server.model.bean.PosicionFiscalizador;
import com.inec.server.model.dao.DaoPosicionFiscalizador;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class LogicPosicionFiscalizador {
	private PersistenceManager pm;

	public LogicPosicionFiscalizador(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoPosicionFiscalizador dao = new DaoPosicionFiscalizador(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoPosicionFiscalizador dao = new DaoPosicionFiscalizador(this.pm);
		return dao.getBean(id);
	}

	public Collection<PosicionFiscalizador> getListarBean() throws UnknownException {
		DaoPosicionFiscalizador dao = new DaoPosicionFiscalizador(this.pm);
		Collection<PosicionFiscalizador> lista = dao.getListarBean();
		return lista;
	}
	
	public Collection<PosicionFiscalizador> getListarBeanByUsuario(String dniUsuario)throws UnknownException {
		DaoPosicionFiscalizador dao = new DaoPosicionFiscalizador(this.pm);
		Collection<PosicionFiscalizador> lista = dao.getListarBeanByUsuario(dniUsuario);
		return lista;
	}
	public Collection<PosicionFiscalizador> getListarBeanByFechaFormat(String dniUsuario,String fechaFormat)throws UnknownException {
		DaoPosicionFiscalizador dao = new DaoPosicionFiscalizador(this.pm);
		Collection<PosicionFiscalizador> lista = dao.getListarBeanByFechaFormat(dniUsuario, fechaFormat);
		return lista;
	}
	
	public Collection<PosicionFiscalizador> getListarBeanByFechaFormatInicial(String dniUsuario,Long fechaInicial)throws UnknownException {
		DaoPosicionFiscalizador dao = new DaoPosicionFiscalizador(this.pm);
		Collection<PosicionFiscalizador> lista = dao.getListarBeanByFechaFormatInicial(dniUsuario, fechaInicial);
		return lista;
	}
	
	public Collection<PosicionFiscalizador> getListarBeanByFechaFormatFinal(String dniUsuario,Long fechaFinal)throws UnknownException {
		DaoPosicionFiscalizador dao = new DaoPosicionFiscalizador(this.pm);
		Collection<PosicionFiscalizador> lista = dao.getListarBeanByFechaFormatFinal(dniUsuario, fechaFinal);
		return lista;
	}
}
