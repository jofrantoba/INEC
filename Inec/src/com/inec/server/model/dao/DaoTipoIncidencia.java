package com.inec.server.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.inec.server.model.bean.TipoIncidencia;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class DaoTipoIncidencia {
	private PersistenceManager pm;

	public DaoTipoIncidencia(PersistenceManager pm) {
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
		return query.getBean(TipoIncidencia.class, id);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TipoIncidencia getBeanByCode(String codeTipoIncidencia) throws UnknownException {
		Query query = pm.newQuery(TipoIncidencia.class);				
		String declareParameters="String paramCodeTipoIncidencia";
		String filter="codeTipoIncidencia==paramCodeTipoIncidencia";
		try{
		List<TipoIncidencia> lista=new ArrayList<TipoIncidencia>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(codeTipoIncidencia);
		lista.addAll((List<TipoIncidencia>)consulta.sendQuery(1,declareParameters , filter, null, beanObject, query));
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
	public List<TipoIncidencia> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		List<TipoIncidencia> lista = (List<TipoIncidencia>) query
				.getListaBean(TipoIncidencia.class);
		return lista;
	}	
}
