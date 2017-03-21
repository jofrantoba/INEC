package com.inec.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.inec.server.model.bean.Incidencia;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class DaoIncidencia {
	private PersistenceManager pm;

	public DaoIncidencia(PersistenceManager pm) {
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
		return query.getBean(Incidencia.class, id);
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Incidencia getBeanByCode(String codeIncidencia) throws UnknownException {
		Query query = pm.newQuery(Incidencia.class);				
		String declareParameters="String paramCodeIncidencia";
		String filter="codeIncidencia==paramCodeIncidencia";
		try{
		List<Incidencia> lista=new ArrayList<Incidencia>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(codeIncidencia);
		lista.addAll((List<Incidencia>)consulta.sendQuery(1,declareParameters , filter, null, beanObject, query));
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
	public Collection<Incidencia> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<Incidencia> lista = (Collection<Incidencia>) query
				.getListaBean(Incidencia.class);
		return lista;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<Incidencia> getListarBeanByUsuario(String dniUsuario) throws UnknownException {
		Query query = pm.newQuery(Incidencia.class);
		String declareParameters="String paramDniUsuario";		
		String filter= "codeUsuarioFiscalizador==paramDniUsuario";
		String ordering="version desc";
		try{
			List<Incidencia> lista=new ArrayList<Incidencia>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();
			beanObject.add(dniUsuario);			
			lista.addAll((List<Incidencia>)consulta.sendQuery(null, declareParameters, filter, ordering, beanObject, query));
			return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
}
