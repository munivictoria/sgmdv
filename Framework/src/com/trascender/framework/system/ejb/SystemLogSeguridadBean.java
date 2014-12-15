package com.trascender.framework.system.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.LogSeguridad;
import com.trascender.framework.system.interfaces.SystemLogSeguridadLocal;

/**
 * @ejb.bean name="SystemLogSeguridad"
 *           display-name="Name for SystemLogSeguridad"
 *           description="Description for SystemLogSeguridad"
 *           jndi-name="ejb/SystemLogSeguridad"
 *           type="Stateless"
 *           view-type="local"
 */
@Stateless(name = "ejb/SystemLogSeguridad")
public class SystemLogSeguridadBean implements SystemLogSeguridadLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8818994976336688180L;

	public SystemLogSeguridadBean() {
	}
	
	@PersistenceContext(name="Vipians")
	private EntityManager entityManager;
	

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext pCtx)
		throws EJBException,
		RemoteException {
	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}
	
	
	/**
	 * 
	 * @ejb.interface-method view-type = "local"
	 */
	public void registrarLogSeguridad(com.trascender.framework.recurso.transients.Conexion pConexion, String pNombreUsuario,String pNombreRecurso, Boolean pHabilitado, com.trascender.framework.recurso.persistent.Permiso.Accion pAccion) throws TrascenderException{
		try{
//			LogSeguridad locLogSeguridad=new LogSeguridad();
//		
//			//creo la instancia y 
//			locLogSeguridad.setAccion(pAccion);
//			locLogSeguridad.setFechaHora(Calendar.getInstance().getTime());
//			locLogSeguridad.setHabilitado(pHabilitado);
//			locLogSeguridad.setNombreRecurso(pNombreRecurso);
//			
//			Usuario locUsuario=pConexion.getUsuario();
//			if (locUsuario!=null){
//				locLogSeguridad.setNombreUsuario(locUsuario.getUser());
//				locLogSeguridad.setPersonaUsuario(locUsuario.getNombrePersonaFisica());	
//			}
//			else{
//				//SIGNIFICA QUE FU� UN ERROR DE LOGUEO POR ESO TOMO EL PASADO POR PAR�METRO
//				locLogSeguridad.setNombreUsuario(pNombreUsuario);
//			}
//			
//			
//			locLogSeguridad.setRemoteAddress(pConexion.getRemoteAddress());
//			locLogSeguridad.setRemoteHost(pConexion.getRemoteHost());
//			locLogSeguridad.setRemotePort(pConexion.getRemotePort());
//			
//			Session sess=GestorPersistencia.getInstance().getSession();
//			Transaction tx=null;
//			try{
//				tx=sess.beginTransaction();
//				sess.save(locLogSeguridad);
//				tx.commit();
//			}
//			catch(Exception e){
//				if ((tx!=null)&&(tx.isActive())) tx.rollback();
//				e.printStackTrace();
//				throw new TrascenderFrameworkException(450);
//			}
//			finally{
//				sess.close();
//			}
		}
		catch(Exception e){
			System.err.println("Log de seguridad");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Recupera el listado de logs de seguridad seg�n los par�metros
	 * @param pUsuario
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public java.util.List getListaLogsSeguridad(java.util.Date pFechaDesde, java.util.Date pFechaHasta,com.trascender.framework.recurso.persistent.Usuario pUsuario) throws Exception{
		try{
			return Criterio.getInstance(this.entityManager, LogSeguridad.class)
				.add(Restriccion.MAYOR("fechaHora", pFechaDesde))
				.add(Restriccion.MENOR("fechaHora", pFechaHasta))
				.add(Restriccion.ILIKE("nombreUsuario",pUsuario.getUser()))
				.list();
		}catch (Exception locE) {
			locE.printStackTrace();
		}
		return new ArrayList();
	}

}
