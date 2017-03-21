package com.inec.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.inec.server.model.bean.Notificacion;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class DaoNotificacion {
	private PersistenceManager pm;

	public DaoNotificacion(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(Notificacion.class, id);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public Collection<Notificacion> getListarBean(String fecha) throws UnknownException {
		Query query = pm.newQuery(Notificacion.class);
		String declareParameters="String paramFecha,String paramEstado";
		String filter="fechaNotificacionFiltro==paramFecha && estado==paramEstado";
		String ordering="version desc";
		try{
			List<Notificacion> lista=new ArrayList<Notificacion>();
			Querys consulta=new Querys();	
			List beanObject=new ArrayList();
			beanObject.add(fecha);
			beanObject.add("N");
			lista.addAll((List<Notificacion>)consulta.sendQuery(null, declareParameters, filter, ordering, beanObject, query));
			return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Notificacion getBeanByCode(String codeNotificacion) throws UnknownException {
		Query query = pm.newQuery(Notificacion.class);				
		String declareParameters="String paramCodeNotificacion";
		String filter="codeNotificacion==paramCodeNotificacion";
		try{
		List<Notificacion> lista=new ArrayList<Notificacion>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(codeNotificacion);
		lista.addAll((List<Notificacion>)consulta.sendQuery(1,declareParameters , filter, null, beanObject, query));
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
