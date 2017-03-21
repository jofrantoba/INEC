package com.inec.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.inec.server.model.bean.PartidoPolitico;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class DaoPartidoPolitico {
	private PersistenceManager pm;

	public DaoPartidoPolitico(PersistenceManager pm) {
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
		return query.getBean(PartidoPolitico.class, id);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PartidoPolitico getBeanByCode(String codePartidoPolitico) throws UnknownException {
		Query query = pm.newQuery(PartidoPolitico.class);				
		String declareParameters="String paramCodePartidoPolitico";
		String filter="codePartidoPolitico==paramCodePartidoPolitico";
		try{
		List<PartidoPolitico> lista=new ArrayList<PartidoPolitico>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(codePartidoPolitico);
		lista.addAll((List<PartidoPolitico>)consulta.sendQuery(1,declareParameters , filter, null, beanObject, query));
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
	
	@SuppressWarnings("unchecked")
	public Collection<PartidoPolitico> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<PartidoPolitico> lista = (Collection<PartidoPolitico>) query
				.getListaBean(PartidoPolitico.class);
		return lista;
	}	
}
