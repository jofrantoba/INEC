package com.inec.server.model.process;

import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.KeyFactory;
import com.inec.server.model.bean.PartidoPolitico;
import com.inec.server.model.bean.TipoIncidencia;
import com.inec.server.model.bean.Ubigeo;
import com.inec.server.model.bean.Zona;
import com.inec.server.model.dao.PMF;
import com.inec.server.model.logic.LogicPartidoPolitico;
import com.inec.server.model.logic.LogicTipoIncidencia;
import com.inec.server.model.logic.LogicUbigeo;
import com.inec.server.model.logic.LogicZona;
import com.inec.shared.BeanParametro;
import com.inec.shared.MyMessage;
import com.inec.shared.StringHex;
import com.inec.shared.UnknownException;

public class GestionMantenimiento {
	private final static String INSERTAR="I";

	/**
	 * TipoIncidencia
	 * @param codeTipoIncidencia
	 * @param descripcion
	 * @return
	 * @throws UnknownException
	 */
	public static TipoIncidencia insertarTipoIncidencia(
			String codeTipoIncidencia,
			String descripcion)throws UnknownException{
		PersistenceManager pm=null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			LogicTipoIncidencia logicTipoIncidencia=new LogicTipoIncidencia(pm);
			TipoIncidencia beanTipoIncidencia= new TipoIncidencia();
			beanTipoIncidencia.setIdTipoIncidencia(KeyFactory.keyToString(
									KeyFactory.createKey(TipoIncidencia.class.getSimpleName(), codeTipoIncidencia)));
			beanTipoIncidencia.setCodeTipoIncidencia(codeTipoIncidencia);
			beanTipoIncidencia.setDescripcion(descripcion);
			beanTipoIncidencia.setVersion(new java.util.Date().getTime());
			BeanParametro beanParametro=new BeanParametro();
			beanParametro.setBean(beanTipoIncidencia);
			beanParametro.setTipoOperacion(INSERTAR);;
			Boolean rptaTipoIncidencia=logicTipoIncidencia.mantenimiento(beanParametro);
			if(!rptaTipoIncidencia){
				throw new UnknownException(MyMessage.ERROR_OPERACION);
			}
			return beanTipoIncidencia;
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	/**
	 * 
	 * @return
	 * @throws UnknownException
	 */
	public static List<TipoIncidencia> listTipoIncidencia()throws UnknownException{
		PersistenceManager pm=null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			LogicTipoIncidencia logicTipoIncidencia=new LogicTipoIncidencia(pm);
			return logicTipoIncidencia.getListarBean();
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	
	/**
	 * 
	 * @param codePartidoPolitico
	 * @param nombrePartidoPolitico
	 * @param lider
	 * @return
	 * @throws UnknownException
	 */
	public static PartidoPolitico insertarPartidoPolitico(
			String codePartidoPolitico,
			String nombrePartidoPolitico,
			String lider)throws UnknownException{
		PersistenceManager pm=null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			LogicPartidoPolitico logicPartidoPolitico=new LogicPartidoPolitico(pm);
			PartidoPolitico beanPartidoPolitico= new PartidoPolitico();
			beanPartidoPolitico.setIdPartidoPolitico(KeyFactory.keyToString(
									KeyFactory.createKey(PartidoPolitico.class.getSimpleName(), codePartidoPolitico)));
			beanPartidoPolitico.setCodePartidoPolitico(codePartidoPolitico);
			beanPartidoPolitico.setNombrePartido(nombrePartidoPolitico);
			beanPartidoPolitico.setLider(lider);
			beanPartidoPolitico.setVersion(new java.util.Date().getTime());
			BeanParametro beanParametro=new BeanParametro();
			beanParametro.setBean(beanPartidoPolitico);
			beanParametro.setTipoOperacion(INSERTAR);;
			Boolean rptaPartidoPolitico=logicPartidoPolitico.mantenimiento(beanParametro);
			if(!rptaPartidoPolitico){
				throw new UnknownException(MyMessage.ERROR_OPERACION);
			}
			return beanPartidoPolitico;
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws UnknownException
	 */
	public static List<PartidoPolitico> listPartidoPolitico()throws UnknownException{
		PersistenceManager pm=null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			LogicPartidoPolitico logicPartidoPolitico=new LogicPartidoPolitico(pm);
			return (List<PartidoPolitico>)logicPartidoPolitico.getListarBean();
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	
	
	public static Ubigeo insertarUbigeo(
			String codeUbigeo,
			String departamento,
			String provincia,
			String distrito)throws UnknownException{
		PersistenceManager pm=null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			LogicUbigeo logicUbigeo=new LogicUbigeo(pm);
			Ubigeo beanUbigeo= new Ubigeo();
			beanUbigeo.setIdUbigeo(KeyFactory.keyToString(
									KeyFactory.createKey(Ubigeo.class.getSimpleName(), codeUbigeo)));
			beanUbigeo.setCodeUbigeo(codeUbigeo);
			beanUbigeo.setDepartamento(departamento);
			beanUbigeo.setProvincia(provincia);
			beanUbigeo.setDistrito(distrito);
			beanUbigeo.setVersion(new java.util.Date().getTime());
			BeanParametro beanParametro=new BeanParametro();
			beanParametro.setBean(beanUbigeo);
			beanParametro.setTipoOperacion(INSERTAR);;
			Boolean rptaUbigeo=logicUbigeo.mantenimiento(beanParametro);
			if(!rptaUbigeo){
				throw new UnknownException(MyMessage.ERROR_OPERACION);
			}
			return beanUbigeo;
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	/**
	 * 
	 * @return
	 * @throws UnknownException
	 */
	public static List<Ubigeo> listUbigeo()throws UnknownException{
		PersistenceManager pm=null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			LogicUbigeo logicUbigeo=new LogicUbigeo(pm);
			return (List<Ubigeo>) logicUbigeo.getListarBean();
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
		
	public static Zona insertarZona(			
			String nombreZona,
			String departamento,
			String provincia,
			String distrito)throws UnknownException{
		PersistenceManager pm=null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			LogicZona logicZona=new LogicZona(pm);
			LogicUbigeo logicUbigeo= new LogicUbigeo(pm);
			
			Ubigeo beanUbigeo= (Ubigeo) logicUbigeo.getBeanByUbigeo(departamento, provincia, distrito);
			if(beanUbigeo==null){
				throw new UnknownException(MyMessage.ERROR_NO_EXISTENCIA);
			}
			Zona beanZona= new Zona();
			String codeZona= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
			beanZona.setIdZona(KeyFactory.keyToString(
									KeyFactory.createKey(Zona.class.getSimpleName(), codeZona)));
			beanZona.setCodeZona(codeZona);
			beanZona.setNombreZona(nombreZona);
			beanZona.setCodeUbigeo(beanUbigeo.getCodeUbigeo());
			beanZona.setVersion(new java.util.Date().getTime());
			BeanParametro beanParametro=new BeanParametro();
			beanParametro.setBean(beanZona);
			beanParametro.setTipoOperacion(INSERTAR);;
			Boolean rptaZona=logicZona.mantenimiento(beanParametro);
			if(!rptaZona){
				throw new UnknownException(MyMessage.ERROR_OPERACION);
			}
			return beanZona;
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws UnknownException
	 */
	public static List<Zona> listZona()throws UnknownException{
		PersistenceManager pm=null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			LogicZona logicZona=new LogicZona(pm);
			return (List<Zona>)logicZona.getListarBean();
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
}
