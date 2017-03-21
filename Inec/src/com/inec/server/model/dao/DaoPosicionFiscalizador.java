package com.inec.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.inec.server.model.bean.PosicionFiscalizador;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class DaoPosicionFiscalizador {
	private PersistenceManager pm;

	public DaoPosicionFiscalizador(PersistenceManager pm) {
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
		return query.getBean(PosicionFiscalizador.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<PosicionFiscalizador> getListarBean() throws UnknownException {
		Querys query = new Querys(this.pm);
		Collection<PosicionFiscalizador> lista = (Collection<PosicionFiscalizador>) query
				.getListaBean(PosicionFiscalizador.class);
		return lista;
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<PosicionFiscalizador> getListarBeanByUsuario(String dniUsuario) throws UnknownException {
		Query query = pm.newQuery(PosicionFiscalizador.class);
		String declareParameters="String paramDniUsuario";		
		String filter= "codeUsuarioFiscalizador==paramDniUsuario";
		String ordering="version desc";
		try{
			List<PosicionFiscalizador> lista=new ArrayList<PosicionFiscalizador>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();
			beanObject.add(dniUsuario);			
			lista.addAll((List<PosicionFiscalizador>)consulta.sendQuery(null, declareParameters, filter, ordering, beanObject, query));
			return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<PosicionFiscalizador> getListarBeanByFechaFormat(String dniUsuario,String fechaFormat) throws UnknownException {
		Query query = pm.newQuery(PosicionFiscalizador.class);
		String declareParameters="String paramDniUsuario,String paramFechaFormat";		
		String filter= "codeUsuarioFiscalizador==paramDniUsuario && fechaFormat==paramFechaFormat";
		String ordering="version desc";
		try{
			List<PosicionFiscalizador> lista=new ArrayList<PosicionFiscalizador>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();
			beanObject.add(dniUsuario);		
			beanObject.add(fechaFormat);
			lista.addAll((List<PosicionFiscalizador>)consulta.sendQuery(null, declareParameters, filter, ordering, beanObject, query));
			return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<PosicionFiscalizador> getListarBeanByFechaFormatInicial(String dniUsuario,Long fechaInicial) throws UnknownException {
		Query query = pm.newQuery(PosicionFiscalizador.class);
		String declareParameters="String paramDniUsuario,Long paramFechaInicial";		
		String filter= "codeUsuarioFiscalizador==paramDniUsuario && version>=paramFechaInicial";
		String ordering="version desc";
		try{
			List<PosicionFiscalizador> lista=new ArrayList<PosicionFiscalizador>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();
			beanObject.add(dniUsuario);		
			beanObject.add(fechaInicial);
			lista.addAll((List<PosicionFiscalizador>)consulta.sendQuery(null, declareParameters, filter, ordering, beanObject, query));
			return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<PosicionFiscalizador> getListarBeanByFechaFormatFinal(String dniUsuario,Long fechaFinal) throws UnknownException {
		Query query = pm.newQuery(PosicionFiscalizador.class);
		String declareParameters="String paramDniUsuario,String paramFechaFinal";		
		String filter= "codeUsuarioFiscalizador==paramDniUsuario && version<=paramFechaFinal";
		String ordering="version desc";
		try{
			List<PosicionFiscalizador> lista=new ArrayList<PosicionFiscalizador>();
			Querys consulta=new Querys();
			List beanObject=new ArrayList();
			beanObject.add(dniUsuario);		
			beanObject.add(fechaFinal);
			lista.addAll((List<PosicionFiscalizador>)consulta.sendQuery(null, declareParameters, filter, ordering, beanObject, query));
			return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}
	}
}
