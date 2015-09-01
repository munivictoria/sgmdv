package com.trascender.saic.system.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.saic.business.interfaces.BusinessEstadoCuentaContribuyenteLocal;
import com.trascender.saic.business.interfaces.BusinessRefinanciacionLocal;
import com.trascender.saic.exception.SaicException;
import com.trascender.saic.recurso.filtros.FiltroLiquidacionTasaRefer;
import com.trascender.saic.recurso.filtros.FiltroPlantillaPlanDePago;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.PlantillaPlanDePago;
import com.trascender.saic.recurso.persistent.RegistroDeuda.EstadoRegistroDeuda;
import com.trascender.saic.recurso.references.LiquidacionTasaRefer;
import com.trascender.saic.recurso.transients.LiquidacionTasaAgrupada;
import com.trascender.saic.system.interfaces.SystemEstadoCuentaContribuyente;

/**
 * @ejb.bean name="SystemEstadoCuentaContribuyente"
 *           display-name="Name for SystemEstadoCuentaContribuyente"
 *           description="Description for SystemEstadoCuentaContribuyente"
 *           jndi-name="ejb/SystemEstadoCuentaContribuyente"
 *           type="Stateful"
 *           view-type="remote"
 */
@Stateless(name="ejb/SystemEstadoCuentaContribuyente")
public class SystemEstadoCuentaContribuyenteBean implements SystemEstadoCuentaContribuyente {

	private long llave;

	@EJB
	private BusinessEstadoCuentaContribuyenteLocal business;
	
	@EJB
	private BusinessRefinanciacionLocal businessRefinanciacion;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8348935980964509536L;

	public SystemEstadoCuentaContribuyenteBean() {
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
	 * 
	 * @param pPersona
	 * @param pEstadoObligacion
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public java.util.List getListaObligacionesContribuyente(Long pIdTramite,
			com.trascender.framework.recurso.persistent.Persona pPersona, 
			com.trascender.habilitaciones.recurso.persistent.Obligacion.Estado pEstadoObligacion) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,LiquidacionTasa.codigoEstadoCuenta,Permiso.Accion.SELECT)){
				return this.business.getListaObligacionesContribuyente(pIdTramite,pPersona, pEstadoObligacion);
			}
			else{
				throw new SaicException(222);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new SaicException(888);
		}
	}

	/**
	 * @param pPersona
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public java.util.List getListaDocEspecializadosContribuyente(Persona pPersona) throws TrascenderException{

		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,LiquidacionTasa.codigoEstadoCuenta,Permiso.Accion.SELECT)){
				return this.business.getListaDocEspecializadosContribuyente(pPersona);
			}
			else{
				throw new SaicException(222);
			}

		}
		catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new SaicException(370);
		}
	}

	/**
	 * 
	 * @param pPersona
	 * @param pEstadoObligacion
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public java.util.List getListaEstadosCuentaContribuyente(com.trascender.framework.recurso.persistent.Persona pPersona, 
			Obligacion.Estado pEstadoObligacion) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,LiquidacionTasa.codigoEstadoCuenta,Permiso.Accion.SELECT)){
				return this.business.getListaEstadosCuentaContribuyente(pPersona, pEstadoObligacion);
			}
			else{
				throw new SaicException(222);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new SaicException(371);
		}
	}

	/**
	 * @ejb.interface-method view-type="remote"
	 * @param pPersona
	 * @param pEstadoObligacion
	 * @return
	 * @throws Exception
	 */
	@Override
	public java.util.List getListaObligacionesContribuyente(
			com.trascender.framework.recurso.persistent.Persona pPersona,
			com.trascender.habilitaciones.recurso.persistent.Obligacion.Estado pEstadoObligacion)
					throws TrascenderException{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, Obligacion.serialVersionUID, Permiso.Accion.SELECT)){
				return this.business.getListaObligacionesContribuyente(pPersona, pEstadoObligacion);
			}
			else{
				throw new SaicException(222);
			}
		}
		catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new SaicException(370);
		}

	}

	/**
	 * @ejb.interface-method view-type = "remote"
	 * @param pObligacion
	 * @param pIsVencida
	 * @return
	 * @throws TrascenderException
	 */
	@Override
	public java.util.List getListaRegistrosDeudaObligacion(com.trascender.habilitaciones.recurso.persistent.Obligacion pObligacion, EstadoRegistroDeuda pEstadoRegistroDeuda, String pEstado, Periodo pPeriodo) throws TrascenderException{

		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,LiquidacionTasa.codigoEstadoCuenta,Permiso.Accion.SELECT)){
				return this.business.getListaRegistrosDeudaObligacion(pObligacion,pEstadoRegistroDeuda, pEstado, pPeriodo);
			}
			else{
				throw new SaicException(222);
			}

		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new SaicException(222);
		}
	}


	/**
	 * 
	 * @param pObligacion
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public java.util.List getListaDeudasContribuyente(com.trascender.habilitaciones.recurso.persistent.Obligacion pObligacion) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,LiquidacionTasa.codigoEstadoCuenta,Permiso.Accion.SELECT)){
				return this.business.getListaDeudasContribuyente(pObligacion);
			}
			else{
				throw new SaicException(222);
			}

		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new SaicException(222);
		}
	}

	/**
	 * 
	 * @param pObligacion
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public java.util.List getListaDeudasContribuyente(com.trascender.habilitaciones.recurso.persistent.Obligacion pObligacion, Periodo pPeriodo) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,LiquidacionTasa.codigoEstadoCuenta,Permiso.Accion.SELECT)){
				return new ArrayList(this.business.getListaDeudasContribuyente(pObligacion, pPeriodo));
			}
			else{
				throw new SaicException(222);
			}

		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new SaicException(222);
		}
	}

	/**
	 * 
	 * @param pListaObligaciones
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public java.util.List getListaDeudasContribuyente(java.util.List pListaObligaciones) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, LiquidacionTasa.codigoEstadoCuenta, Permiso.Accion.SELECT)){
				return this.business.getListaDeudasContribuyente(pListaObligaciones);
			}
			else{
				throw new SaicException(222);
			}
		}
		catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new SaicException(222);
		}
	}

	/**
	 * 
	 * @param pLlave
	 * @ejb.interface-method view-type = "remote"
	 */
	@Override
	public void setLlave(long pLlave){
		this.llave = pLlave;
	}

	@Override
	public LiquidacionTasaAgrupada inicializarLiquidacionTasaAgrupada(
			LiquidacionTasaRefer pLiquidacion) {
		return this.inicializarLiquidacionTasaAgrupada(pLiquidacion, false);
	}
	
	@Override
	public LiquidacionTasaAgrupada inicializarLiquidacionTasaAgrupada(
			LiquidacionTasaRefer pLiquidacion, boolean actualizar) {
		this.business.setLlave(llave);
		return this.business.inicializarLiquidacionTasaAgrupada(pLiquidacion, actualizar);
	}

	@Override
	public FiltroLiquidacionTasaRefer findListaLiquidacionTasaRefer(FiltroLiquidacionTasaRefer pFiltro) throws Exception {
		return business.findListaLiquidacionTasaRefer(pFiltro);
	}

	@Override
	public void addPlantillaPlanDePago(PlantillaPlanDePago plantilla) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, PlantillaPlanDePago.serialVersionUID, Permiso.Accion.INSERT)){
				this.businessRefinanciacion.addPlantillaPlanDePago(plantilla);
			}
			else{
				throw new SaicException(222);
			}
		}
		catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new SaicException(222);
		}
	}

	@Override
	public void updatePlantillaPlanDePago(PlantillaPlanDePago plantilla) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, PlantillaPlanDePago.serialVersionUID, Permiso.Accion.UPDATE)){
				this.businessRefinanciacion.updatePlantillaPlanDePago(plantilla);
			}
			else{
				throw new SaicException(222);
			}
		}
		catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new SaicException(222);
		}
	}

	@Override
	public void deletePlantillaPlanDePago(PlantillaPlanDePago plantilla) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, PlantillaPlanDePago.serialVersionUID, Permiso.Accion.DELETE)){
				this.businessRefinanciacion.deletePlantillaPlanDePago(plantilla);
			}
			else{
				throw new SaicException(222);
			}
		}
		catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new SaicException(222);
		}		
	}

	@Override
	public FiltroPlantillaPlanDePago findListaPlantillaPlanDePago(
			FiltroPlantillaPlanDePago filtro) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getUsuario(llave) != null) {
				return businessRefinanciacion.findListaPlantillaPlanDePago(filtro);
			} else {
				throw new SaicException(222);
			}
		}
		catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new SaicException(222);
		}
	}

}
