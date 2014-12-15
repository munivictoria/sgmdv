package com.trascender.contabilidad.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.contabilidad.business.interfaces.BusinessImpresionTicketCajaLocal;
import com.trascender.contabilidad.exception.TrascenderContabilidadException;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.system.interfaces.SystemImpresionTicketCaja;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.SecurityMgr;

/**
 * @ejb.bean name="SystemImpresionTicketCaja"
 *           display-name="Name for SystemImpresionTicketCaja"
 *           description="Description for SystemImpresionTicketCaja"
 *           jndi-name="ejb/SystemImpresionTicketCaja"
 *           type="Stateful"
 *           view-type="remote"
 */

@Stateful(name = "ejb/SystemImpresionTicketCaja")
public class SystemImpresionTicketCajaBean implements SystemImpresionTicketCaja{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	@EJB
	private BusinessImpresionTicketCajaLocal businessImpresionBean;
	
	private long llave;
	
	@EJB
	private SystemUsuario systemUsuario;

	public SystemImpresionTicketCajaBean() {
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
	 * @ejb.interface-method view-type ="remote"
	 */
	public void setLlave(long llave) throws TrascenderException{
		try{
			this.llave = llave;
			this.systemUsuario.setLlave(llave);
		}
		catch(Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(605);
		}
	}
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pIdTicketCaja
	 * @throws com.trascender.framework.exception
	 */
	public void getReporteTicketCaja(Long pIdTicketCaja) throws com.trascender.framework.exception.TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TicketCaja.serialVersionUID, Permiso.Accion.SELECT)){
				this.businessImpresionBean.getReporteTicketCaja(pIdTicketCaja);
			} else {
				throw new TrascenderContabilidadException(900);
			}
		} 
		catch (TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new TrascenderContabilidadException(22);
		}
	}

}
