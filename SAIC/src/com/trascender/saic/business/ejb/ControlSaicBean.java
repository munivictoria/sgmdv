package com.trascender.saic.business.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import com.trascender.habilitaciones.business.ejb.BusinessExencionObligacionBean;
import com.trascender.saic.business.interfaces.ControlSaic;

/**
 * @ejb.bean name="ControlSaic"
 *           display-name="Name for ControlSaic"
 *           description="Description for ControlSaic"
 *           jndi-name="ejb/ControlSaic"
 *           type="Stateless"
 *           view-type="remote"
 */
@Stateless(name = "ejb/ControlSaic")
public class ControlSaicBean implements ControlSaic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7113761113126131139L;

	public ControlSaicBean() {
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
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public boolean generateGrupoRecusosSaic() throws Exception {
		Class.forName(BusinessRegistroValuadoBean.class.getName());
		Class.forName(BusinessLiquidacionTasaBean.class.getName());
		Class.forName(BusinessEstadoCuentaContribuyenteBean.class.getName());
		Class.forName(BusinessReLiquidacionBean.class.getName());
		Class.forName(BusinessImpresionBean.class.getName());
		Class.forName(BusinessExencionRegistroDeudaBean.class.getName());
		Class.forName(BusinessExencionObligacionBean.class.getName());
		return true;
	}
  
	
	
}
