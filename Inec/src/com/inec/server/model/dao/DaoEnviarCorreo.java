package com.inec.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.inec.server.model.bean.EnviarCorreo;
import com.inec.server.model.bean.ProgramacionNotificacion;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class DaoEnviarCorreo {
	private PersistenceManager pm;

	public DaoEnviarCorreo(PersistenceManager pm) {
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
		return query.getBean(EnviarCorreo.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<EnviarCorreo> getListarBean() throws UnknownException {
		Query query = pm.newQuery(EnviarCorreo.class);
		String ordering="version desc";
		try{
			List<EnviarCorreo> lista=new ArrayList<EnviarCorreo>();
			Querys consulta=new Querys();	
			lista.addAll((List<EnviarCorreo>)consulta.sendQuery(null, null, null, ordering, null, query));
			return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}	
}
