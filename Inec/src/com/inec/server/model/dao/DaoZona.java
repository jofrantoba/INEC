package com.inec.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.inec.server.model.bean.Zona;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class DaoZona {
	private PersistenceManager pm;

	public DaoZona(PersistenceManager pm) {
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
		return query.getBean(Zona.class, id);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Zona getBeanByDni(String codeZona) throws UnknownException {
		Query query = pm.newQuery(Zona.class);				
		String declareParameters="String paramCodeZona";
		String filter="codeZona==paramCodeZona";
		try{
		List<Zona> lista=new ArrayList<Zona>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(codeZona);
		lista.addAll((List<Zona>)consulta.sendQuery(1,declareParameters , filter, null, beanObject, query));
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
	public Collection<Zona> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Zona> lista = (Collection<Zona>) query
				.getListaBean(Zona.class);
		return lista;
	}	
}
