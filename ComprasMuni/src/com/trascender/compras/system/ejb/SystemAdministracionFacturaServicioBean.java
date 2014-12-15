package com.trascender.compras.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.compras.business.interfaces.BusinessFacturaLocal;
import com.trascender.compras.exception.TrascenderComprasException;
import com.trascender.compras.recurso.filtros.FiltroFacturaServicio;
import com.trascender.compras.recurso.persistent.suministros.FacturaServicio;
import com.trascender.compras.system.interfaces.SystemAdministracionFacturaServicio;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;

@Stateful(name = "ejb/SystemAdministracionFacturaServicio")
public class SystemAdministracionFacturaServicioBean implements SystemAdministracionFacturaServicio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private BusinessFacturaLocal locBusinessFactura;
	private long llave;
	
	public SystemAdministracionFacturaServicioBean() {
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
	public com.trascender.compras.recurso.persistent.suministros.FacturaServicio addFacturaServicio(
			com.trascender.compras.recurso.persistent.suministros.FacturaServicio pFacturaServicio) 
			throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, 
					FacturaServicio.serialVersionUID, Permiso.Accion.INSERT)){
				return this.locBusinessFactura.addFacturaServicio(pFacturaServicio);
			}
			else{
				throw new TrascenderComprasException(900);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(901);
		}
	}
		
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaServicio updateFacturaServicio(
			com.trascender.compras.recurso.persistent.suministros.FacturaServicio pFacturaServicio)
			throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave,
					FacturaServicio.serialVersionUID, Permiso.Accion.UPDATE)){
				return this.locBusinessFactura.updateFacturaServicio(pFacturaServicio);
			}
			else{
				throw new TrascenderComprasException(900);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(902);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public void deleteFacturaServicio(
			com.trascender.compras.recurso.persistent.suministros.FacturaServicio pFacturaServicio)
			throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave,
					FacturaServicio.serialVersionUID, Permiso.Accion.DELETE)){
				this.locBusinessFactura.deleteFacturaServicio(pFacturaServicio);
			}
			else{
				throw new TrascenderComprasException(900);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(903);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public com.trascender.compras.recurso.persistent.suministros.FacturaServicio getFacturaServicioPorId(
			long pId) throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, 
					FacturaServicio.serialVersionUID, Permiso.Accion.SELECT)){
				return this.locBusinessFactura.getFacturaServicioPorId(pId);
			}
			else{
				throw new TrascenderComprasException(900);
			}
		}
		catch (TrascenderException e) {
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new TrascenderComprasException(904);
		}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 */
	public FiltroFacturaServicio findListaFacturasServicio(FiltroFacturaServicio pFiltro)
			throws java.lang.Exception{
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, 
					FacturaServicio.serialVersionUID, Permiso.Accion.SELECT)){
				
				return this.locBusinessFactura.findListaFacturaServicio(pFiltro);
			}
			else{
				throw new TrascenderComprasException(900);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderComprasException(904);
		}
	}
}
