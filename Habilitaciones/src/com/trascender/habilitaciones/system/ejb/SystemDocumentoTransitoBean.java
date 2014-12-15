package com.trascender.habilitaciones.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.habilitaciones.business.interfaces.BusinessTransitoLocal;
import com.trascender.habilitaciones.system.interfaces.SystemDocumentoTransito;

@Stateful(name = "ejb/SystemDocumentoTransito")
public class SystemDocumentoTransitoBean implements SystemDocumentoTransito {

	private long llave;
	
	@EJB
	private BusinessTransitoLocal businessTransito;

	private static final long serialVersionUID = -1981285835812100162L;

	public SystemDocumentoTransitoBean() {
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
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}

	/**
	 * @param pLlave
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave){
		this.llave=pLlave;
	}
}
