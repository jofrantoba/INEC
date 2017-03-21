package com.inec.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.inec.server.model.bean.ProgramacionNotificacion;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class DaoProgramacionNotificacion {
	private PersistenceManager pm;

	public DaoProgramacionNotificacion(PersistenceManager pm) {
		this.pm = pm;
	}

	public boolean mantenimiento(BeanParametro parametro)
			throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.mantenimiento(parametro);
	}

	public Object getBean(String id) throws UnknownException {
		Querys query = new Querys(this.pm);
		return query.getBean(ProgramacionNotificacion.class, id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ProgramacionNotificacion getBeanByCode(String codeProgramacion) throws UnknownException {
		Query query = pm.newQuery(ProgramacionNotificacion.class);				
		String declareParameters="String paramCodeProgramacionNotificacion";
		String filter="codeProgramacionNotificacion==paramCodeProgramacionNotificacion";
		try{
		List<ProgramacionNotificacion> lista=new ArrayList<ProgramacionNotificacion>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(codeProgramacion);
		lista.addAll((List<ProgramacionNotificacion>)consulta.sendQuery(1,declareParameters , filter, null, beanObject, query));
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
	public Collection<ProgramacionNotificacion> getListarBean() throws UnknownException {
		Query query = pm.newQuery(ProgramacionNotificacion.class);
		String ordering="version desc";
		try{
			List<ProgramacionNotificacion> lista=new ArrayList<ProgramacionNotificacion>();
			Querys consulta=new Querys();	
			lista.addAll((List<ProgramacionNotificacion>)consulta.sendQuery(null, null, null, ordering, null, query));
			return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
}
