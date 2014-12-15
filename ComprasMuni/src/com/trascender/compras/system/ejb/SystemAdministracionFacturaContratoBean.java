package com.trascender.compras.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.compras.business.interfaces.BusinessFacturaLocal;
import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.filtros.FiltroFacturaContrato;
import com.trascender.compras.recurso.persistent.suministros.FacturaContrato;
import com.trascender.compras.system.interfaces.SystemAdministracionFacturaContrato;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;


@Stateful(name = "ejb/SystemAdministracionFacturaContrato")
public class SystemAdministracionFacturaContratoBean implements SystemAdministracionFacturaContrato{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private BusinessFacturaLocal locBusinessFactura;
	private long llave;
	
	public SystemAdministracionFacturaContratoBean() {
		super();
	}

	public void setSessionContext(SessionContext arg0) throws EJBException,
	RemoteException {
		
	}

	
	public void ejbActivate() throws EJBException, RemoteException {
		
	}

	public void ejbPassivate() throws EJBException, RemoteException {
		
	}

	public void ejbRemove() throws EJBException, RemoteException {
		
	}
	
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public void setLlave(long pLlave){
		this.llave = pLlave;
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaContrato addFacturaContrato(
			com.trascender.compras.recurso.persistent.suministros.FacturaContrato pFacturaContrato) 
			throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, 
					FacturaContrato.serialVersionUID, Permiso.Accion.INSERT)){
				return this.locBusinessFactura.addFacturaContrato(pFacturaContrato);
			}
			else{
				throw new TrascenderComprasException(890);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(891);
		}
	}
		
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaContrato updateFacturaContrato(
			com.trascender.compras.recurso.persistent.suministros.FacturaContrato pFacturaContrato)
			throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave,
					FacturaContrato.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.locBusinessFactura.updateFacturaContrato(pFacturaContrato);
			}
			else{
				throw new TrascenderComprasException(890);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(892);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public void deleteFacturaContrato(
			com.trascender.compras.recurso.persistent.suministros.FacturaContrato pFacturaContrato)
			throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave,
					FacturaContrato.serialVersionUID, Permiso.Accion.DELETE)){
				this.locBusinessFactura.deleteFacturaContrato(pFacturaContrato);
			}
			else{
				throw new TrascenderComprasException(890);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(893);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaContrato getFacturaContratoPorId(
			long pId) throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, 
					FacturaContrato.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locBusinessFactura.getFacturaContratoPorID(pId);
			}
			else{
				throw new TrascenderComprasException(890);
			}
		}
		catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(894);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public FiltroFacturaContrato findListaFacturasContrato(FiltroFacturaContrato pFiltro)
			throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, 
					FacturaContrato.serialVersionUID, Permiso.Accion.SELECT)){
				
				return this.locBusinessFactura.findListaFacturaContrato(pFiltro);
			}
			else{
				throw new TrascenderComprasException(890);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(894);
		}
	}
	
	
}
