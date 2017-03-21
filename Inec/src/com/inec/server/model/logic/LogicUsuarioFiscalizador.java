package com.inec.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.server.model.dao.DaoUsuarioFiscalizador;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class LogicUsuarioFiscalizador {
	private PersistenceManager pm;

	public LogicUsuarioFiscalizador(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		DaoUsuarioFiscalizador dao = new DaoUsuarioFiscalizador(this.pm);
		return dao.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		DaoUsuarioFiscalizador dao = new DaoUsuarioFiscalizador(this.pm);
		return dao.getBean(id);
	}
	
	public Object getBeanByDni(String dniUsuario) throws UnknownException {
		DaoUsuarioFiscalizador dao = new DaoUsuarioFiscalizador(this.pm);
		return dao.getBeanByDni(dniUsuario);
	}
	public Object getBeanByEmail(String correousuario) throws UnknownException {
		DaoUsuarioFiscalizador dao = new DaoUsuarioFiscalizador(this.pm);
		return dao.getBeanByEmail(correousuario);
	}

	public Object getBeanByLogin(String correoUsuario,String claveUsuario) throws UnknownException {
		DaoUsuarioFiscalizador dao = new DaoUsuarioFiscalizador(this.pm);
		return dao.getBeanByLogin(correoUsuario, claveUsuario);
	}
	public Collection<UsuarioFiscalizador> getListarBean() throws UnknownException {
		DaoUsuarioFiscalizador dao = new DaoUsuarioFiscalizador(this.pm);
		Collection<UsuarioFiscalizador> lista = dao.getListarBean();
		return lista;
	}
	public Collection<UsuarioFiscalizador> getListarByZona(String codeZona) throws UnknownException {
		DaoUsuarioFiscalizador dao = new DaoUsuarioFiscalizador(this.pm);
		Collection<UsuarioFiscalizador> lista = dao.getListarByZona(codeZona);
		return lista;
	}
}
