package com.inec.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.inec.server.model.bean.SesionFiscalizador;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class DaoSesionFiscalizador {
	private PersistenceManager pm;

	public DaoSesionFiscalizador(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(SesionFiscalizador.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<SesionFiscalizador> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<SesionFiscalizador> lista = (Collection<SesionFiscalizador>) query
				.getListaBean(SesionFiscalizador.class);
		return lista;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SesionFiscalizador getBeanByCode(String codeSesionFiscalizador) throws UnknownException {
		Query query = pm.newQuery(SesionFiscalizador.class);				
		String declareParameters="String paramCodeSesionFiscalizador";
		String filter="codeInicioSesion==paramCodeSesionFiscalizador";
		try{
		List<SesionFiscalizador> lista=new ArrayList<SesionFiscalizador>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(codeSesionFiscalizador);
		lista.addAll((List<SesionFiscalizador>)consulta.sendQuery(1,declareParameters , filter, null, beanObject, query));
		if(!lista.isEmpty()){
			return lista.get(0);
		}
		return null;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}	
	}
}
