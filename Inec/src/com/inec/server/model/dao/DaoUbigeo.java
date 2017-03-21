package com.inec.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.inec.server.model.bean.Ubigeo;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class DaoUbigeo {
	private PersistenceManager pm;

	public DaoUbigeo(PersistenceManager pm) {
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
		return query.getBean(Ubigeo.class, id);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Ubigeo getBeanByUbigeo(String departamento,String provincia,String distrito) throws UnknownException {
		Query query = pm.newQuery(Ubigeo.class);				
		String declareParameters="String paramDepartamento, String paramProvincia,String paramDistrito";
		String filter="departamento==paramDepartamento && provincia==paramProvincia && "
				+ "distrito==paramDistrito";
		try{
		List<Ubigeo> lista=new ArrayList<Ubigeo>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(departamento);
		beanObject.add(provincia);
		beanObject.add(distrito);
		lista.addAll((List<Ubigeo>)consulta.sendQuery(1,declareParameters , filter, null, beanObject, query));
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
	public Collection<Ubigeo> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Ubigeo> lista = (Collection<Ubigeo>) query
				.getListaBean(Ubigeo.class);
		return lista;
	}	
}
