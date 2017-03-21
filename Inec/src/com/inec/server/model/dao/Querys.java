package com.inec.server.model.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.inec.shared.BeanParametro;
import com.inec.shared.UnknownException;

public class Querys {

	private PersistenceManager pm;
	private static final Logger LOG = Logger.getLogger(Querys.class.getName());

	public Querys() {
	}

	public Querys(PersistenceManager cnx) {
		pm = cnx;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean mantenimiento(Collection<BeanParametro> parametros) throws UnknownException {
		try {
			Iterator<BeanParametro> iterador = parametros.iterator();
			BeanParametro parametro;
			Collection objectsIU = new ArrayList();
			while (iterador.hasNext()) {
				parametro = iterador.next();
				if (parametro.getTipoOperacion().equalsIgnoreCase("I")
						|| parametro.getTipoOperacion().equalsIgnoreCase("A")) {
					objectsIU.addAll((Collection)parametro.getBean());
				} else {
					throw new UnknownException("NO se ha definido Operacion");
				}
			}
			if (objectsIU.size() > 0) {
				pm.makePersistentAll(objectsIU);
			}	
			return true;
		} catch (Exception ex) {
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}
	}

	public boolean mantenimiento(BeanParametro parametro) throws UnknownException {
		try {
			if (parametro.getTipoOperacion().equalsIgnoreCase("I")) {
				pm.makePersistent(parametro.getBean());
				return true;
			} else if (parametro.getTipoOperacion().equalsIgnoreCase("A")) {
				pm.makePersistent(parametro.getBean());
				return true;
			} else if (parametro.getTipoOperacion().equalsIgnoreCase("E")) {
				Object bean = pm.getObjectById(parametro.getBean().getClass(), parametro.getId());
				pm.deletePersistent(bean);
				return true;
			} else {
				throw new UnknownException("NO se ha definido Operacion");
			}
		} catch (Exception ex) {
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}		
	}	
	
	public Object mantenimientoReturn(BeanParametro parametro) throws UnknownException {
		try {
			if (parametro.getTipoOperacion().equalsIgnoreCase("I")) {
				return pm.makePersistent(parametro.getBean());				
			} else if (parametro.getTipoOperacion().equalsIgnoreCase("A")) {
				return pm.makePersistent(parametro.getBean());				
			} else if (parametro.getTipoOperacion().equalsIgnoreCase("E")) {
				Object bean = pm.getObjectById(parametro.getBean().getClass(), parametro.getId());
				pm.deletePersistent(bean);
				return true;
			} else {
				throw new UnknownException("NO se ha definido Operacion");
			}
		} catch (Exception ex) {
			LOG.warning("Metodo: mantenimientoReturn: "+ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}
	}

	public Object getBean(Class<?> nomClase, Long id) throws UnknownException {
		try {
			Object bean = this.pm.getObjectById(nomClase, id);
			return bean;
		} catch (Exception ex) {
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}
	}

	public Object getBean(Class<?> nomClase, String id) throws UnknownException {
		Object bean = null;
		try {
			bean = this.pm.getObjectById(nomClase, id);
			return bean;
		} catch (Exception ex) {
			LOG.warning(ex.getMessage());
			throw new UnknownException(ex.getMessage());
		}
		
	}

	public Object getBean(Class<?> nomClase, Key id) throws UnknownException {
		try {
			Object bean = this.pm.getObjectById(nomClase, id);
			return bean;
		} catch (Exception ex) {
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection<?> getListaBean(Class<?> nomClase) throws UnknownException {
		javax.jdo.Query query = pm.newQuery(nomClase);
		try {
			ArrayList lista = new ArrayList();			
			lista.addAll((List) query.execute(true));
			return lista;
		} catch (Exception ex) {
			LOG.warning(ex.getMessage());
			LOG.info(ex.getLocalizedMessage());
			throw new UnknownException(ex.getMessage());
		} finally {
			query.closeAll();
		}
	}

	public List<Entity> getListBean(Class<?> nomClase) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query(nomClase.getSimpleName());
		PreparedQuery pq = datastore.prepare(q);
		return pq.asList(FetchOptions.Builder.withDefaults());
	}
	
	@SuppressWarnings("unchecked")
	public Object sendQuery(Integer limiteMostrar,String declareParameters,String filter,String ordering,Object beanObject,javax.jdo.Query query){					
		Object objResult=null;
		if(declareParameters!=null){
			query.declareParameters(declareParameters);
		}		
		if(filter!=null){
			query.setFilter(filter);
		}
		if(ordering!=null){
			query.setOrdering(ordering);
		}
		rangeQuery(limiteMostrar, query);
		if(beanObject!=null){
			if(beanObject instanceof List<?>){				
				List<Object> listaBeans=(ArrayList<Object>)beanObject;
				objResult=query.executeWithArray(listaBeans.toArray());		
			}else{
				objResult=query.execute(beanObject);
			}
		}else{
			objResult=query.execute();
		}		
		return objResult;					
	}
	
	@SuppressWarnings("unchecked")
	public Object sendQueryResult(Integer limiteMostrar,String declareParameters,String filter,String ordering,String result,Object beanObject,javax.jdo.Query query){					
		Object objResult=null;
		if(declareParameters!=null){
			query.declareParameters(declareParameters);
		}		
		if(filter!=null){
			query.setFilter(filter);
		}
		if(ordering!=null){
			query.setOrdering(ordering);
		}
		if(result!=null){
			query.setResult(result);
		}
		rangeQuery(limiteMostrar, query);
		if(beanObject!=null){
			if(beanObject instanceof List<?>){				
				List<Object> listaBeans=(ArrayList<Object>)beanObject;
				objResult=query.executeWithArray(listaBeans.toArray());		
			}else{
				objResult=query.execute(beanObject);
			}
		}else{
			objResult=query.execute();
		}		
		return objResult;					
	}
	
	private void rangeQuery(Integer limiteMostrar,javax.jdo.Query query){
		if(limiteMostrar!=null){
			query.setRange(0,limiteMostrar);
		}
	}
	
}
