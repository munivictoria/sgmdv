package com.trascender.compras.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.compras.business.interfaces.BusinessFacturaLocal;
import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.filtros.FiltroFacturaSubsidio;
import com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio;
import com.trascender.compras.system.interfaces.SystemAdministracionFacturaSubsidio;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;

@Stateful(name = "ejb/SystemAdministracionFacturaSubsidio")
public class SystemAdministracionFacturaSubsidioBean implements SystemAdministracionFacturaSubsidio{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private BusinessFacturaLocal locBusinessFactura;
	private long llave;
	
	public SystemAdministracionFacturaSubsidioBean() {
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
	
	/**
	 * Default Create method
	 * 
	 * @throws javax.ejb.CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	
	/**
	 * @ejb.interface-method view-type="remote"
	 */
	public void setLlave(long pLlave){
		this.llave = pLlave;
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio addFacturaSubsidio(
			com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio pFacturaSubsidio) 
			throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, 
					FacturaSubsidio.serialVersionUID, Permiso.Accion.INSERT)){
				return this.locBusinessFactura.addFacturaSubsidio(pFacturaSubsidio);
			}
			else{
				throw new TrascenderComprasException(880);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(881);
		}
	}
		
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio updateFacturaSubsidio(
			com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio pFacturaSubsidio)
			throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave,
					FacturaSubsidio.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.locBusinessFactura.updateFacturaSubsidio(pFacturaSubsidio);
			}
			else{
				throw new TrascenderComprasException(880);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(882);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public void deleteFacturaSubsidio(
			com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio pFacturaSubsidio)
			throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave,
					FacturaSubsidio.serialVersionUID, Permiso.Accion.DELETE)){
				this.locBusinessFactura.deleteFacturaSubsidio(pFacturaSubsidio);
			}
			else{
				throw new TrascenderComprasException(880);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(883);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaSubsidio getFacturaSubsidioPorId(
			long pId) throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, 
					FacturaSubsidio.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locBusinessFactura.getFacturaSubsidioPorId(pId);
			}
			else{
				throw new TrascenderComprasException(880);
			}
		}
		catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(884);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public FiltroFacturaSubsidio findListaFacturasSubsidios(FiltroFacturaSubsidio pFiltro)
			throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, 
					FacturaSubsidio.serialVersionUID, Permiso.Accion.SELECT)){
				
				return this.locBusinessFactura.findListaFacturaSubsidio(pFiltro);
			}
			else{
				throw new TrascenderComprasException(880);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(884);
		}
	}
	
}
