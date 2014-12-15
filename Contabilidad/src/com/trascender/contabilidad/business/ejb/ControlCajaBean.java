package com.trascender.contabilidad.business.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import com.trascender.contabilidad.business.interfaces.ControlCaja;

/**
 * @ejb.bean name="ControlCaja"
 *           display-name="Name for ControlCaja"
 *           description="Description for ControlCaja"
 *           jndi-name="ejb/ControlCaja"
 *           type="Stateless"
 *           view-type="remote"
 */
@Stateless(name = "ejb/ControlCajaLocal")
public class ControlCajaBean implements ControlCaja{

	/**
	 * 
	 */
	private static final long serialVersionUID = 713865818106542746L;

	public ControlCajaBean() {
		super();
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
	 * Business method
	 * @ejb.interface-method view-type="both"
	 * @return
	 * @throws Exception
	 */
	public boolean generateGrupoRecursosCaja() throws Exception {
		//Clases Caja
		Class.forName(BusinessCajaBean.class.getName().toString());
		Class.forName("com.trascender.contabilidad.business.ejb.BusinessCajaChicaBean");
		//Clases Contabilidad
		Class.forName(BusinessSubdiarioCajaBean.class.getName().toString());
		Class.forName(BusinessPlanDeCuentaBean.class.getName().toString());
		Class.forName(BusinessPresupuestoBean.class.getName().toString());
		Class.forName(BusinessLibroDiarioBean.class.getName().toString());
		Class.forName(BusinessBancoBean.class.getName().toString());
		//Clases IngresoVario
		Class.forName(BusinessIngresoVarioBean.class.getName().toString());
		return true;
	}
}
