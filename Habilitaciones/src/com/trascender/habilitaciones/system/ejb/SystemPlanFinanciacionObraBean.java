package com.trascender.habilitaciones.system.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoPlanObraLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.filtros.FiltroObra;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra;
import com.trascender.habilitaciones.system.interfaces.SystemPlanFinanciacionObra;

@Stateful(name = "ejb/SystemPlanFinanciacionObra")
public class SystemPlanFinanciacionObraBean implements SystemPlanFinanciacionObra {
	private long llave;
	
	@EJB
	private BusinessDocumentoPlanObraLocal businessDocumentoPlanObra;
	/**
	 * 
	 */
	private static final long serialVersionUID = -9128309235394458827L;

	public SystemPlanFinanciacionObraBean() {
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
	 * Agrega una obra en el registro
	 * @param pObra obra a agregar
	 * @return obra actualizada
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.pfo.Obra addObra(com.trascender.habilitaciones.recurso.persistent.pfo.Obra pObra) 
	throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Obra.serialVersionUID,Permiso.Accion.INSERT)){
				return this.businessDocumentoPlanObra.addObra(pObra);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(430);
		}
	}
	
	
	/**
	 * Actualiza los datos de una obra
	 * @param pObra obra a actualizar
	 * @return obra con los datos actualizados
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.pfo.Obra updateObra(com.trascender.habilitaciones.recurso.persistent.pfo.Obra pObra) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,Obra.serialVersionUID,Permiso.Accion.UPDATE)){
					return this.businessDocumentoPlanObra.updateObra(pObra);
				}
				else{
					throw new HabilitacionesException(799);
				}
			}
			catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new HabilitacionesException(431);
			}
	}
	
	/**
	 * Elimina una obra del registro
	 * @param pObra obra a eliminar
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteObra(com.trascender.habilitaciones.recurso.persistent.pfo.Obra pObra) 
	throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Obra.serialVersionUID,Permiso.Accion.DELETE)){
				this.businessDocumentoPlanObra.deleteObra(pObra);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
			
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(432);
		}
	}
	
	/**
	 * Recupera un listado de obras
	 * @param pDescripcion descripción de la obra
	 * @param pTipoObra tipo de obra
	 * @param pCuadra cuadra 333
	 * @return lista de obras
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public FiltroObra findListaObras(FiltroObra pFiltro) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,Obra.serialVersionUID,Permiso.Accion.SELECT)){
					return this.businessDocumentoPlanObra.findListaObras(pFiltro);
				}
				else{
					throw new HabilitacionesException(799);
				}
			}
			catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new HabilitacionesException(433);
			}
	
	}
	
	/**
	 * Agrega un plan de cuenta por obra
	 * @param pPlanCuentaObra
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra addPlanCuentaObra(
		com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra pPlanCuentaObra) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,PlanCuentaObra.serialVersionUID,Permiso.Accion.INSERT)){
					return this.businessDocumentoPlanObra.addPlanCuentaObra(pPlanCuentaObra);
				}
				else{
					throw new HabilitacionesException(799);
				}
			}
			catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new HabilitacionesException(434);
			}
	}
	
	/**
	 * Actualiza los datos de un plan de cuenta
	 * @param pPlanCuentaObra plan de cuenta a actualizar
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra updatePlanCuentaObra(
		com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra pPlanCuentaObra) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,PlanCuentaObra.serialVersionUID,Permiso.Accion.UPDATE)){
					return this.businessDocumentoPlanObra.updatePlanCuentaObra(pPlanCuentaObra);
				}
				else{
					throw new HabilitacionesException(799);
				}
			}
			catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new HabilitacionesException(435); 
			}
	}
	
	
	/**
	 * Elimina un plan de cuentas por obra 
	 * @param pPlanCuentaObra
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deletePlanCuentaObra(com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra pPlanCuentaObra) 
	throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,PlanCuentaObra.serialVersionUID,Permiso.Accion.DELETE)){
				this.businessDocumentoPlanObra.deletePlanCuentaObra(pPlanCuentaObra);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(436);
		}
		
	}
	
	/**
	 * Recupera un listado de planes de cuenta
	 * @param pNombre nombre del plan de cuenta por obra
	 * @return listado de planes de cuenta
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote" 
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findListaPlanCuentaObra(String pNombre) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, PlanCuentaObra.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessDocumentoPlanObra.findListaPlanCuentaObra(pNombre);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(437);
		}
	}
	
	/**
	 * Genera una obligación para cada titular de una parcela que es afectada por una obra. utilizando el plan de cuentas y la plantilla pasadas por parámetro 
	 * @param pPlantillaObligacion plantilla a utilizar para generar las obligaciones
	 * @param pObra obra asociada
	 * @param pPlanCuentaDefecto plan de cuentas por defecto
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public int generarObligacionesFromObra(com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion pPlantillaObligacion,
		com.trascender.habilitaciones.recurso.persistent.pfo.Obra pObra,
		com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra pPlanCuentaDefecto) 
		throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Obligacion.serialVersionUID,Permiso.Accion.INSERT)){
				return this.businessDocumentoPlanObra.generarObligacionesFromObra(pPlantillaObligacion, pObra, pPlanCuentaDefecto);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(438);
		}
	}

	/**
	 * Recupera el documento habilitante especializado de un plan de obra a partir de la obligación
	 * @param pObligacion obligación asociada
	 * @return DocumentoPlanObra asociado
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra getDocumentoHabilitantePFO(com.trascender.habilitaciones.recurso.persistent.Obligacion pObligacion) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave, DocumentoPlanObra.serialVersionUID, Permiso.Accion.SELECT)){
					return this.businessDocumentoPlanObra.getDocumentoPlanObra(pObligacion);
				}
				else{
					throw new HabilitacionesException(799);
				}
			}
			catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new HabilitacionesException(439);
			}
	}
	
	
	
	/**
	 * 
	 * @param pPersona
	 * @param pNumeroRegistro
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findListaDocumentosPlanObra(
		com.trascender.framework.recurso.persistent.Persona pPersona, 
		Integer pNumeroRegistro,
		com.trascender.habilitaciones.recurso.persistent.pfo.Obra pObra) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave, DocumentoPlanObra.serialVersionUID, Permiso.Accion.SELECT)){
					return this.businessDocumentoPlanObra.findListaDocumentosPlanObra(pPersona, pNumeroRegistro,pObra);
				}
				else{
					throw new HabilitacionesException(799);
				}
			}
			catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}
			catch(Exception e) {
				e.printStackTrace();
				throw new HabilitacionesException(440);
			}
	}
	
	/**
	 * Recupera una obra por id
	 * @param pId
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.pfo.Obra getObraPorId(long pId) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,Obra.serialVersionUID,Permiso.Accion.SELECT)){
					return this.businessDocumentoPlanObra.getObraPorId(pId);
				}
				else{
					throw new HabilitacionesException(799);
				}
			}
			catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new HabilitacionesException(441);
			}
		
	}
	
	/**
	 * Recupera un plan de financiación de obra por id
	 * @param pId número de identificación único asociado
	 * @return PlanCuentaObra
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.pfo.PlanCuentaObra getPlanCuentaObraPorId(long pId) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,PlanCuentaObra.serialVersionUID,Permiso.Accion.SELECT)){
					return this.businessDocumentoPlanObra.getPlanCuentaObraPorId(pId);
				}
				else{
					throw new HabilitacionesException(799);
				}
			}
			catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new HabilitacionesException(442);
			}
	}
	
	
	/**
	 * Actualiza los datos del documento pfo
	 * @param pDocumentoPlanObra
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra updateDocumentoPFO(com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra pDocumentoPlanObra) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoPlanObra.serialVersionUID,Permiso.Accion.UPDATE)){
					return this.businessDocumentoPlanObra.updateDocumentoPlanObra(pDocumentoPlanObra);
				}
				else{
					throw new HabilitacionesException(799);
				}
			}
			catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new HabilitacionesException(443);
			}
	}
	
	/**
	 * Recupera el listado de cuadras afectadas de un documentoPFO
	 * @param pDocumentoPlanObra
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public java.util.List getListaCuadrasAfectadasFromDocumentoPFO(com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra pDocumentoPlanObra) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave, DocumentoPlanObra.serialVersionUID, Permiso.Accion.SELECT)){
					return this.businessDocumentoPlanObra.getListaCuadrasAfectadasFromDocumentoPFO(pDocumentoPlanObra);
				}
				else{
					throw new HabilitacionesException(799);
				}
			}
			catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new HabilitacionesException(444);
			}
	}
	
	@SuppressWarnings("unchecked")
	public void anularObligacionesFromObra(ArrayList<Obligacion> pListaObligacionesFromObra, java.util.List pListaCuadra)
		throws TrascenderException{
			try {
				if(SecurityMgr.getInstance().getPermiso(this.llave, Obligacion.serialVersionUID, Permiso.Accion.DELETE)){
					this.businessDocumentoPlanObra.anularObligacionesFromObra(pListaObligacionesFromObra, pListaCuadra);
				} else{
					throw new HabilitacionesException(799);
				}
			} catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}
			catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
	}
	
	/**
	 * @param pLlave
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave){
		this.llave = pLlave;
	}
	
}
