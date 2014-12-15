package com.trascender.catastro.business.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import com.trascender.catastro.business.interfaces.ControlCatastro;

/**
 * @ejb.bean name="ControlCatastro"
 *           display-name="Name for ControlCatastro"
 *           description="Description for ControlCatastro"
 *           jndi-name="ejb/ControlCatastro"
 *           type="Stateless"
 *           view-type="remote"
 */
@Stateless(name = "ejb/ConstrolCatastro")
public class ControlCatastroBean implements ControlCatastro {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControlCatastroBean() {
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
	public boolean generateGrupoRecusosCatastro() throws Exception{
		//Clases catastro				
		Class.forName("com.trascender.catastro.business.ejb.BusinessCodigosCatastralesBean");
		
		Class.forName("com.trascender.catastro.business.ejb.BusinessRegistroGeograficoBean");
		
		Class.forName("com.trascender.catastro.business.ejb.BusinessRegistroParcelarioBean");
		
		Class.forName("com.trascender.catastro.business.ejb.BusinessRegistroPropiedadBean");

		Class.forName(BusinessZonificacionBean.class.getName());
		return true;
	}
}
