package com.trascender.habilitaciones.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateful;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoSHPSLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.filtros.FiltroInspector;
import com.trascender.habilitaciones.recurso.persistent.shps.Inspector;
import com.trascender.habilitaciones.system.interfaces.SystemInspectores;

@Stateful(name = "ejb/SystemInspectores")
public class SystemInspectoresBean implements SystemInspectores {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6998870229206969282L;
	private long llave=0;
	
	@EJB
	private BusinessDocumentoSHPSLocal businessDocumentoSHPS;
	
public SystemInspectoresBean() {
	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {

	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	/**
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}

	/**
	 * Agrega un inspector a la base de datos
	 * @param pInspector
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void addInspector(com.trascender.habilitaciones.recurso.persistent.shps.Inspector pInspector) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Inspector.serialVersionUID,Permiso.Accion.INSERT)){
				this.businessDocumentoSHPS.addInspector(pInspector);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(360);
		}
	}
	
	/**
	 * Actualiza los datos de un inspector
	 * @param pInspector inspector a actualizar
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 * 
	 */
	public void updateInspector(com.trascender.habilitaciones.recurso.persistent.shps.Inspector pInspector) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Inspector.serialVersionUID,Permiso.Accion.UPDATE)){
				this.businessDocumentoSHPS.updateInspector(pInspector);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(361);
		}
	}

	/**
	 * Elimina un inspector del registro
	 * @param pInspector inspector a eliminar
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteInspector(com.trascender.habilitaciones.recurso.persistent.shps.Inspector pInspector) throws TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Inspector.serialVersionUID,Permiso.Accion.DELETE)){
				this.businessDocumentoSHPS.deleteInspector(pInspector);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(362);
		}
	}
	
	
	/**
	 * Recupera un listado de inspectores
	 * @param pNombre nombre del inspector
 	 * @param pPersonaFisica persona física que puede llegar a ser inspector
	 * @return Listado de inspectores
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroInspector findListaInspectores(FiltroInspector pFiltro) throws TrascenderException{
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave,Inspector.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessDocumentoSHPS.findListaInspectores(pFiltro);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(363);
		}
	}
	
	public Inspector getInspectorPorId(long pId) throws Exception{
		if (SecurityMgr.getInstance().getPermiso(
				this.llave, Inspector.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessDocumentoSHPS.getInspectorPorId(pId);
		}else{
			throw new HabilitacionesException(799);
		}
	}
	
	/**
	 * Setea la llave del ejb
	 * @param pLlave llave
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave){
		this.llave=pLlave;
	}
}
