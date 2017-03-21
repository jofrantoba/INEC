package com.inec.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class DaoUsuarioFiscalizador {
	private PersistenceManager pm;

	public DaoUsuarioFiscalizador(PersistenceManager pm) {
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
		return query.getBean(UsuarioFiscalizador.class, id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UsuarioFiscalizador getBeanByDni(String dniFiscalizador) throws UnknownException {
		Query query = pm.newQuery(UsuarioFiscalizador.class);				
		String declareParameters="String paramDniUsuario";
		String filter="dniFiscalizador==paramDniUsuario";
		try{
		List<UsuarioFiscalizador> lista=new ArrayList<UsuarioFiscalizador>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(dniFiscalizador);
		lista.addAll((List<UsuarioFiscalizador>)consulta.sendQuery(1,declareParameters , filter, null, beanObject, query));
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UsuarioFiscalizador getBeanByEmail(String email) throws UnknownException {
		Query query = pm.newQuery(UsuarioFiscalizador.class);				
		String declareParameters="String paramCorreo";
		String filter="correoPersonal==paramCorreo";
		try{
		List<UsuarioFiscalizador> lista=new ArrayList<UsuarioFiscalizador>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(email);
		lista.addAll((List<UsuarioFiscalizador>)consulta.sendQuery(1,declareParameters , filter, null, beanObject, query));
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UsuarioFiscalizador getBeanByLogin(String dniUsuario,String claveUsuario) throws UnknownException {
		Query query = pm.newQuery(UsuarioFiscalizador.class);				
		String declareParameters="String paramDniFiscalizador, String paramClaveUsuario";
		String filter="dniFiscalizador==paramDniFiscalizador && clave== paramClaveUsuario";
		try{
		List<UsuarioFiscalizador> lista=new ArrayList<UsuarioFiscalizador>();
		Querys consulta=new Querys();
		List beanObject=new ArrayList();
		beanObject.add(dniUsuario);
		beanObject.add(claveUsuario);
		lista.addAll((List<UsuarioFiscalizador>)consulta.sendQuery(1,declareParameters , filter, null, beanObject, query));
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
	public List<UsuarioFiscalizador> getListarBean() throws UnknownException {
		Query query = pm.newQuery(UsuarioFiscalizador.class);				
		try{
		List<UsuarioFiscalizador> lista=new ArrayList<UsuarioFiscalizador>();
		Querys consulta=new Querys();
		lista.addAll((List<UsuarioFiscalizador>)consulta.sendQuery(null,null , null, null, null, query));
		return lista;
		}catch(Exception ex){			
			throw new UnknownException(ex.getMessage());
		}finally{
			query.closeAll();
		}	
	}	
	
	@SuppressWarnings("unchecked")
	public Collection<UsuarioFiscalizador> getListarByZona(String codeZona) throws UnknownException {
		Query query = pm.newQuery(UsuarioFiscalizador.class);
		try {
			String declareParameters = "";
			String filter = "";
			List<Object> listaParameters=new ArrayList<Object>();
			if(codeZona!=null){
				declareParameters += "String paramCodeZona";
				filter+="codeZona==paramCodeZona";				
				listaParameters.add(codeZona);
			}			
			query.declareParameters(declareParameters);
			query.setFilter(filter);			
			List<UsuarioFiscalizador> listUsuarioFiscalizadors = new ArrayList<UsuarioFiscalizador>();
			Object object = null;			
			object = query.executeWithArray(listaParameters.toArray());
			listUsuarioFiscalizadors.addAll((List<UsuarioFiscalizador>) object);
			return listUsuarioFiscalizadors;			
		} catch (Exception ex) {
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}		
	}
}
