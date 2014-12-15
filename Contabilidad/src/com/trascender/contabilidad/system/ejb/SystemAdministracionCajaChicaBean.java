package com.trascender.contabilidad.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.contabilidad.business.interfaces.BusinessCajaChicaLocal;
import com.trascender.contabilidad.exception.TrascenderContabilidadException;
import com.trascender.contabilidad.recurso.persistent.CajaChica;
import com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionCajaChica;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;

@Stateful(name = "ejb/SystemAdministracionCajaChica")
public class SystemAdministracionCajaChicaBean implements SystemAdministracionCajaChica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private BusinessCajaChicaLocal locCajaChica;
	private long llave;

	public SystemAdministracionCajaChicaBean() {
	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {

	}

	public void ejbRemove() throws EJBException, RemoteException {

	}

	public void setSessionContext(SessionContext ctx)
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
	 * @ejb.interface-method view-type="remote"
	 */
	public void setLlave (long llave){
		this.llave = llave;
	}
	
	//Caja Chica
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pCajaChica
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.CajaChica addCajaChica (com.trascender.contabilidad.recurso.persistent.CajaChica pCajaChica) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CajaChica.serialVersionUID, Permiso.Accion.INSERT))
			{
				return this.locCajaChica.addCajaChica(pCajaChica);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(320);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pCajaChica
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.CajaChica updateCajaChica (com.trascender.contabilidad.recurso.persistent.CajaChica pCajaChica) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CajaChica.serialVersionUID, Permiso.Accion.INSERT))
			{
				return this.locCajaChica.updateCajaChica(pCajaChica);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(322);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pIdCajaChica
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.CajaChica findCajaChicaByID (long pIdCajaChica) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CajaChica.serialVersionUID, Permiso.Accion.SELECT))
			{
				return this.locCajaChica.findCajaChicaByID(pIdCajaChica);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException (321);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pCajaChica
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public void deleteCajaChica (com.trascender.contabilidad.recurso.persistent.CajaChica pCajaChica) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CajaChica.serialVersionUID, Permiso.Accion.DELETE))
			{
				this.locCajaChica.deleteCajaChica(pCajaChica);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException (323);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pNombre
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public java.util.List findListaCajaChica (String pNombre) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, CajaChica.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCajaChica.findListaCajaChica(pNombre);
			} throw new TrascenderContabilidadException(900);
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(324);
		}
	}
	
	//Movimiento Caja Chica
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pMovCajaChica
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica addMovimientoCajaChica 
			(com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica pMovCajaChica) throws com.trascender.framework.exception.TrascenderException{
	try{
		if (SecurityMgr.getInstance().getPermiso(this.llave, MovimientoCajaChica.serialVersionUID, Permiso.Accion.INSERT)){
				return this.locCajaChica.addMovimientoCajaChica(pMovCajaChica);
		} else {
			throw new TrascenderContabilidadException(900);
		}
	} catch (TrascenderException e){
		e.printStackTrace();
		throw e;
	} catch (Exception e){
		e.printStackTrace();
		throw new TrascenderContabilidadException(330);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pMovCajaChica
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica updateMovimientoCajaChica
			(com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica pMovCajaChica) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, MovimientoCajaChica.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.locCajaChica.updateMovimientoCajaChica(pMovCajaChica);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(332);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pId
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica findMovimientoCajaChicaByID(long pId)
			throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, MovimientoCajaChica.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCajaChica.findMovimientoCajaChicaByID(pId);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException (331);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pMovCajaChica
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public void deleteMovimientoCajaChica (com.trascender.contabilidad.recurso.persistent.MovimientoCajaChica pMovCajaChica)throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, MovimientoCajaChica.serialVersionUID, Permiso.Accion.DELETE)){
				this.locCajaChica.deleteMovimientoCajaChica(pMovCajaChica);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch(Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException (333);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pFechaDesde
	 * @param pFechaHasta
	 * @param pConceptoMovimientoCajaChica
	 * @param pCajaChica
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findListaMovimientoCajaChica
			(java.util.Date pFechaDesde, java.util.Date pFechaHasta, com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica pConceptoMovimientoCajaChica, com.trascender.contabilidad.recurso.persistent.CajaChica pCajaChica) 
					throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, MovimientoCajaChica.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCajaChica.findListaMovimientoCajaChica(pFechaDesde, pFechaHasta, pConceptoMovimientoCajaChica, pCajaChica);				
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(334);
		}
	}
	
	//Concepto Movimiento Caja Chica
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pConceptoMovimientoCajaChica
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica addConceptoMovimientoCajaChica
			(com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica pConceptoMovimientoCajaChica) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ConceptoMovimientoCajaChica.serialVersionUID, Permiso.Accion.INSERT)){
				return this.locCajaChica.addConceptoMovimientoCajaChica(pConceptoMovimientoCajaChica);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(360);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pConceptoMovimientoCajaChica
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica updateConceptoMovimientoCajaChica 
			(com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica pConceptoMovimientoCajaChica) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ConceptoMovimientoCajaChica.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.locCajaChica.updateConceptoMovimientoCajaChica(pConceptoMovimientoCajaChica);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(362);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pConceptoMovimientoCajaChica
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public void deleteConceptoMovimientoCajaChica (com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica pConceptoMovimientoCajaChica)
			throws com.trascender.framework.exception.TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ConceptoMovimientoCajaChica.serialVersionUID, Permiso.Accion.DELETE)){
				this.locCajaChica.deleteConceptoMovimientoCajaChica(pConceptoMovimientoCajaChica);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		}catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(363);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pId
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public com.trascender.contabilidad.recurso.persistent.ConceptoMovimientoCajaChica findConceptoMovimientoCajaChicaByID 
			(Long pId) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, ConceptoMovimientoCajaChica.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCajaChica.findConceptoMovimientoCajaChicaByID(pId);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(361);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pNombre
	 * @return
	 * @throws com.trascender.framework.exception.TrascenderException
	 */
	public java.util.List findListaConceptosCajaChica (String pNombre)  throws com.trascender.framework.exception.TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, ConceptoMovimientoCajaChica.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locCajaChica.findListaConceptoMovimientoCajaChica(pNombre);
			} else {
				throw new TrascenderContabilidadException (900);
			}
		} catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		} catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(364);
		}
	}
}
