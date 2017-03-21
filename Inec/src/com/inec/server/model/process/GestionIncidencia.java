package com.inec.server.model.process;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.KeyFactory;
import com.inec.server.model.bean.Fotografia;
import com.inec.server.model.bean.Incidencia;
import com.inec.server.model.bean.PartidoPolitico;
import com.inec.server.model.bean.PosicionFiscalizador;
import com.inec.server.model.bean.TipoIncidencia;
import com.inec.server.model.bean.Ubigeo;
import com.inec.server.model.bean.UsuarioFiscalizador;
import com.inec.server.model.dao.PMF;
import com.inec.server.model.logic.LogicFotografia;
import com.inec.server.model.logic.LogicIncidencia;
import com.inec.server.model.logic.LogicPartidoPolitico;
import com.inec.server.model.logic.LogicPosicionFiscalizador;
import com.inec.server.model.logic.LogicTipoIncidencia;
import com.inec.server.model.logic.LogicUbigeo;
import com.inec.server.model.logic.LogicUsuarioFiscalizador;
import com.inec.shared.BeanParametro;
import com.inec.shared.MyMessage;
import com.inec.shared.StringHex;
import com.inec.shared.UnknownException;

public class GestionIncidencia {

	private final static String INSERTAR="I";
	private final static String ACTUALIZAR="A";
	
	public static Incidencia registrarIncidenciaTransaction(
			String codeUsuario,				
			String codePartidoPolitico,			
			String departamento,
			String provincia,
			String distrito,
			String direccion,				
			String tipoIncidencia,
			String descripcion,
			Long fechaCelular,
			Double latitud,
			Double longitud,
			List<String> listUrl,
			List<Double> listLatitud,
			List<Double>listLongitud)throws UnknownException{
		PersistenceManager pm=null;
		Transaction tx= null;
		try{
			if(codeUsuario==null || codeUsuario.isEmpty()
					|| codePartidoPolitico==null || codePartidoPolitico.isEmpty()||
					departamento==null || departamento.isEmpty()||
					provincia==null || provincia.isEmpty() ||
					tipoIncidencia ==null || tipoIncidencia.isEmpty()){

				throw new UnknownException(MyMessage.PARAMETROS_INVALIDOS);
				
			}
			pm = PMF.getPMF().getPersistenceManager();	
			tx=pm.currentTransaction();
			tx.begin();
			LogicIncidencia logicIncidencia= new LogicIncidencia(pm);
			LogicUsuarioFiscalizador logicUsuarioFiscalizador= new LogicUsuarioFiscalizador(pm);
			LogicPartidoPolitico logicPartidoPolitico= new LogicPartidoPolitico(pm);
			LogicTipoIncidencia logicTipoIncidencia= new LogicTipoIncidencia(pm);
			LogicFotografia logicFotografia= new LogicFotografia(pm);
			LogicPosicionFiscalizador logicPosicionFiscalizador= new LogicPosicionFiscalizador(pm);
			Long version= new java.util.Date().getTime();
			
			LogicUbigeo logicUbigeo= new LogicUbigeo(pm);			
			Ubigeo beanUbigeo = (Ubigeo) logicUbigeo.getBeanByUbigeo(departamento, provincia, distrito);
			if(beanUbigeo==null){
				throw new UnknownException(MyMessage.errorNoExistencia(Ubigeo.class));
			}
			UsuarioFiscalizador beanUsuario = (UsuarioFiscalizador) logicUsuarioFiscalizador.getBeanByDni(codeUsuario);			
			if(beanUsuario==null){
				throw new UnknownException(MyMessage.errorNoExistencia(UsuarioFiscalizador.class));
			}
			TipoIncidencia beanTipoIncidencia= (TipoIncidencia) logicTipoIncidencia.getBeanByCode(tipoIncidencia);
			if(beanTipoIncidencia==null){
				throw new UnknownException(MyMessage.errorNoExistencia(TipoIncidencia.class));
			}
			PartidoPolitico beanPartidoPolitico= (PartidoPolitico) logicPartidoPolitico.getBeanByCode(codePartidoPolitico);
			if(beanPartidoPolitico==null){
				throw new UnknownException(MyMessage.errorNoExistencia(PartidoPolitico.class));
			}
			Incidencia beanIncidencia = new Incidencia();
			String codeIncidencia= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
			beanIncidencia.setIdIncidencia(KeyFactory.keyToString(
					KeyFactory.createKey(Incidencia.class.getSimpleName(), codeIncidencia)));
			beanIncidencia.setCodeIncidencia(codeIncidencia);
			beanIncidencia.setDescripcion(descripcion);
			beanIncidencia.setCodeZona(beanUsuario.getCodeZona());
			beanIncidencia.setCodeUsuarioFiscalizador(beanUsuario.getCodeUsuarioFiscalizador());
			beanIncidencia.setVersion(version);
			beanIncidencia.setCodePartidoPolitico(codePartidoPolitico);
			beanIncidencia.setNombrePartidoPolitico(beanPartidoPolitico.getNombrePartido());
			beanIncidencia.setFechaCelular(new java.util.Date(fechaCelular));
			beanIncidencia.setFechaServidor(new java.util.Date());			
			beanIncidencia.setCodeUbigeo(beanUbigeo.getCodeUbigeo());
			beanIncidencia.setBeanUbigeo(beanUbigeo);
			beanIncidencia.setCodeTipoIncidencia(beanTipoIncidencia.getCodeTipoIncidencia());
			beanIncidencia.setDireccion(direccion);
			beanIncidencia.setLatitud(latitud);
			beanIncidencia.setLongitud(longitud);
			beanIncidencia.setListUrlFoto(listUrl);
			beanIncidencia.setListLatitud(listLatitud);
			beanIncidencia.setListLongitud(listLongitud);
			BeanParametro beanParametro= new BeanParametro();
			beanParametro.setBean(beanIncidencia);
			beanParametro.setTipoOperacion(INSERTAR);
			Boolean rptaIncidencia= logicIncidencia.mantenimiento(beanParametro);			
			if(!rptaIncidencia){
				throw new UnknownException(MyMessage.ERROR_OPERACION);
			}		
			PosicionFiscalizador beanPosicionFiscalizador= new PosicionFiscalizador();
			String codePosicionFiscalizador= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
			beanPosicionFiscalizador.setIdPosicionFiscalizador(KeyFactory.keyToString(
					KeyFactory.createKey(PosicionFiscalizador.class.getSimpleName(), codePosicionFiscalizador)));			
			beanPosicionFiscalizador.setCodePosicionFiscalizador(codePosicionFiscalizador);
			beanPosicionFiscalizador.setVersion(version);
			beanPosicionFiscalizador.setCodeUsuarioFiscalizador(codeUsuario);
			beanPosicionFiscalizador.setLatitud(latitud);
			beanPosicionFiscalizador.setLongitud(longitud);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
			beanPosicionFiscalizador.setFechaFormat(sdf.format(new java.util.Date().getTime()));
			beanPosicionFiscalizador.setDepartamento(departamento);
			beanPosicionFiscalizador.setProvincia(provincia);
			beanPosicionFiscalizador.setDistrito(distrito);
			beanParametro= new BeanParametro();
			beanParametro.setBean(beanPosicionFiscalizador);
			beanParametro.setTipoOperacion(INSERTAR);
			Boolean rptaPosicionFiscalizador= logicPosicionFiscalizador.mantenimiento(beanParametro);
			if(!rptaPosicionFiscalizador){
				throw new UnknownException(MyMessage.ERROR_OPERACION);
			}			
			for (int i=0;i< listUrl.size();i++){
				Fotografia beanFotografia= new Fotografia();
				String codeFotografia= StringHex.convertStringToHex(java.util.UUID.randomUUID().toString());
				beanFotografia.setIdFotografia(KeyFactory.keyToString(
						KeyFactory.createKey(Fotografia.class.getSimpleName(), codeFotografia)));
				beanFotografia.setCodeFotografia(codeFotografia);
				beanFotografia.setCodeIncidencia(codeIncidencia);
				beanFotografia.setCodeUsuarioFiscalizador(beanIncidencia.getCodeUsuarioFiscalizador());
				beanFotografia.setFechaCaptura(new java.util.Date());
				beanFotografia.setFechaCapturaCelular(beanIncidencia.getFechaCelular());					
				beanFotografia.setUrlFotografia(listUrl.get(i));
				beanFotografia.setLatitud(listLatitud.get(i));
				beanFotografia.setLongitud(listLongitud.get(i));
				beanFotografia.setVersion(beanIncidencia.getVersion());				
				beanParametro= new BeanParametro();
				beanParametro.setBean(beanFotografia);
				beanParametro.setTipoOperacion(INSERTAR);
				Boolean rptaFotografia= logicFotografia.mantenimiento(beanParametro);
				if(!rptaFotografia){
					throw new UnknownException(MyMessage.ERROR_OPERACION);
				}
			}
			tx.commit();			
			return beanIncidencia;
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			GestionShared.closeConnection(pm, tx);
		}
	}
	
	
	public static Boolean actualizarIncidencia(
			String codeIncidencia,
			String codeUsuario,				
			String codePartidoPolitico,			
			String direccion,				
			String tipoIncidencia,
			String descripcion)throws UnknownException{
		PersistenceManager pm=null;
		try{
			pm = PMF.getPMF().getPersistenceManager();			
			LogicIncidencia logicIncidencia= new LogicIncidencia(pm);
			LogicUsuarioFiscalizador logicUsuarioFiscalizador= new LogicUsuarioFiscalizador(pm);
			UsuarioFiscalizador beanUsuario = (UsuarioFiscalizador) logicUsuarioFiscalizador.getBeanByDni(codeUsuario);			
			if(beanUsuario==null){
				throw new UnknownException(MyMessage.errorNoExistencia(UsuarioFiscalizador.class));
			}
			Incidencia beanIncidencia= (Incidencia) logicIncidencia.getBeanByCode(codeIncidencia);
			if(beanIncidencia==null){
				throw new UnknownException(MyMessage.errorNoExistencia(Incidencia.class));
			}						
			
			beanIncidencia.setDescripcion(descripcion);
			beanIncidencia.setDireccion(direccion);
			beanIncidencia.setCodePartidoPolitico(codePartidoPolitico);
			beanIncidencia.setCodeTipoIncidencia(tipoIncidencia);
			
			BeanParametro beanParametro= new BeanParametro();
			beanParametro= new BeanParametro();
			beanParametro.setBean(beanIncidencia);
			beanParametro.setTipoOperacion(ACTUALIZAR);			
			Boolean rptaIncidencia= logicIncidencia.mantenimiento(beanParametro);
			if(!rptaIncidencia){
				throw new UnknownException(MyMessage.ERROR_OPERACION);
			}
			return true;
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
	
	public static List<Incidencia> listarIncidencias(
			String codeUsuario)throws UnknownException{
		PersistenceManager pm=null;
		try{
			pm = PMF.getPMF().getPersistenceManager();
			LogicIncidencia logicIncidencia= new LogicIncidencia(pm);
			LogicUsuarioFiscalizador logicUsuarioFiscalizador= new LogicUsuarioFiscalizador(pm);			
			UsuarioFiscalizador beanUsuario = (UsuarioFiscalizador) logicUsuarioFiscalizador.getBeanByDni(codeUsuario);			
			if(beanUsuario==null){
				throw new UnknownException(MyMessage.ERROR_NO_EXISTENCIA);
			}
			List<Incidencia> listIncidencia=(List<Incidencia>)logicIncidencia.getListarBeanByUsuario(codeUsuario);
			List<Incidencia> listIncidenciaReturn=new ArrayList<>();
			java.util.Iterator<Incidencia> listIncidenciaIterator= listIncidencia.iterator();
			while(listIncidenciaIterator.hasNext()){
				Incidencia beanIncidencia=listIncidenciaIterator.next();
				beanIncidencia.setBeanUbigeo(beanIncidencia.getBeanUbigeo());
				listIncidenciaReturn.add(beanIncidencia);
			}
			return listIncidenciaReturn;					
		}catch(Exception ex){
			throw new UnknownException(ex.getMessage());
		}finally{
			GestionShared.closeConnection(pm, null);
		}
	}
}
