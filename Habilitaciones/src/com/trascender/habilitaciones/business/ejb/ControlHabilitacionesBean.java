package com.trascender.habilitaciones.business.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import com.trascender.habilitaciones.business.interfaces.ControlHabilitaciones;

/**
 * @ejb.bean name="ControlHabilitaciones"
 *           display-name="Name for ControlHabilitaciones"
 *           description="Description for ControlHabilitaciones"
 *           jndi-name="ejb/ControlHabilitaciones"
 *           type="Stateless"
 *           view-type="remote"
 */
@Stateless(name = "ejb/ControlHabilitaciones")
public class ControlHabilitacionesBean implements ControlHabilitaciones {

	private static final long serialVersionUID = 4376522349820117061L;

	public ControlHabilitacionesBean() {
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
	 * 
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public boolean generateGrupoRecusosHabilitaciones() throws Exception {
		Class.forName(BusinessDocumentoOSPBean.class.getName());
		Class.forName(BusinessDocumentoPlanObraBean.class.getName());
		Class.forName(BusinessDocumentoSHPSBean.class.getName());
		Class.forName(BusinessDocumentoTGIBean.class.getName());
		Class.forName(BusinessObligacionBean.class.getName());
		Class.forName(BusinessPlantillaObligacionBean.class.getName());
		Class.forName(BusinessTipoAlicuotaBean.class.getName());
		Class.forName(BusinessDocumentoTransitoBean.class.getName());
		Class.forName(BusinessTipoTasaBean.class.getName());
		return true;
	}
	/**
	 * Create method
	 * @ejb.create-method  view-type = "remote"
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
}
