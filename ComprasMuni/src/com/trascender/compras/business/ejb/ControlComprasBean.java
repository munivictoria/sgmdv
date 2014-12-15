package com.trascender.compras.business.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import com.trascender.compras.business.interfaces.ControlCompras;

@Stateless(name = "ControlComprasLocal")
public class ControlComprasBean implements ControlCompras {

	private static final long serialVersionUID = 8549507840145700723L;

	public ControlComprasBean() {
		super();
		
	}

	public void setSessionContext(SessionContext ctx)
		throws EJBException,
		RemoteException {
		

	}

	public void ejbRemove() throws EJBException, RemoteException {
		

	}

	public void ejbActivate() throws EJBException, RemoteException {
		

	}

	public void ejbPassivate() throws EJBException, RemoteException {
		

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
	 * Business method
	 * @ejb.interface-method  view-type = "both"
	 */
	public boolean generateGrupoRecusosCompras() throws Exception{
		Class.forName("com.trascender.compras.business.ejb.BusinessBienBean");
		Class.forName("com.trascender.compras.business.ejb.BusinessOrdenCompraBean");
		Class.forName("com.trascender.compras.business.ejb.BusinessProveedorBean");
		Class.forName("com.trascender.compras.business.ejb.BusinessSolicitudSuministroBean");
		Class.forName("com.trascender.compras.business.ejb.BusinessFacturaBean");
		Class.forName("com.trascender.compras.business.ejb.BusinessLicitacionBean");
		Class.forName(BusinessStockBean.class.getName().toString());
		return true;
	}
	
}
