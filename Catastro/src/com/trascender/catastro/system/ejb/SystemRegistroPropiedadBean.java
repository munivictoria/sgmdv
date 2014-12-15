package com.trascender.catastro.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.catastro.business.interfaces.BusinessRegistroPropiedadLocal;
import com.trascender.catastro.exception.CatastroException;
import com.trascender.catastro.recurso.filtros.FiltroTituloPropiedad;
import com.trascender.catastro.recurso.persistent.TituloPropiedad;
import com.trascender.catastro.recurso.persistent.TituloPropiedadParcelario;
import com.trascender.catastro.system.interfaces.SystemRegistroPropiedad;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;

/**
 * @ejb.bean name="SystemRegistroPropiedad"
 *           display-name="Name for SystemRegistroPropiedad"
 *           description="Description for SystemRegistroPropiedad"
 *           jndi-name="ejb/SystemRegistroPropiedad"
 *           type="Stateless"
 *           view-type="remote"
 */
@Stateful(name = "ejb/SystemRegistroPropiedad")
public class SystemRegistroPropiedadBean implements SystemRegistroPropiedad {

	private long llave = 0;
	
	private static final long serialVersionUID = -2793648553129021764L;

	@EJB
	private BusinessRegistroPropiedadLocal localRegistroPropiedad;

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}
	
	public SystemRegistroPropiedadBean() {
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
	 * Agrega un t�tulo de propiedad
	 * @param pTituloPropiedad titulo de propiedad a agregar
	 * @throws TrascenderException 
	 * @ejb.interface-method  view-type = "remote"
	 */
	public TituloPropiedadParcelario addTituloPropiedad(TituloPropiedadParcelario pTituloPropiedad) 
		throws TrascenderException  {
			try{
				if(SecurityMgr.getInstance().getPermiso(this.llave,TituloPropiedad.serialVersionUID,Permiso.Accion.INSERT)){
					return this.localRegistroPropiedad.addTituloPropiedad(pTituloPropiedad);
				}
				else {
					throw new CatastroException(791);
				}
					
			}
			catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}
			catch(Exception e){	
				e.printStackTrace();
				throw new CatastroException(300);
			}
	}	
	
	/**
	 * Actualiza un t�tulo de propiedad
	 * @param pTituloPropiedad t�tulo de propiedad a agregar 
	 * @throws TrascenderException 
	 * @ejb.interface-method  view-type = "remote"
	 */
	public TituloPropiedadParcelario updateTituloPropiedad(TituloPropiedadParcelario pTituloPropiedad) 
		throws TrascenderException {
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,TituloPropiedad.serialVersionUID,Permiso.Accion.UPDATE)){
					return this.localRegistroPropiedad.updateTituloPropiedad(pTituloPropiedad);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(301);
			}
	}
	
	/**
	 * Recupera un listado de t�tulos de propiedad
	 * @param pFechaDesde l�mite inferior de fechas desde la cual buscar el t�tulo de propiedad
	 * @param pFechaHasta l�mite superior de fechas hasta la cual buscar el t�tulo de propiedad
	 * @param pPersona Persona a la que pertenece el t�tulo de propiedad
	 * @throws TrascenderException 
	 * @ejb.interface-method  view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public FiltroTituloPropiedad findListaTituloPropiedad(FiltroTituloPropiedad filtro) throws TrascenderException {
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,TituloPropiedad.serialVersionUID,Permiso.Accion.SELECT)){
					return this.localRegistroPropiedad.findTituloPropiedad(filtro);
				}
				else {
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(302);
			}
	}
	
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void setLlave(long pLlave) {
		this.llave=pLlave;
	}
	
	/**
	 * 
	 * @param pIdTituloPropiedad
	 * @return
	 * @ejb.interface-method view-type = "remote"
	 * @throws TrascenderException 
	 */
	public TituloPropiedad getTituloPropiedadPorId(long pIdTituloPropiedad) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,TituloPropiedad.serialVersionUID,Permiso.Accion.SELECT)){
					return this.localRegistroPropiedad.getTituloPropiedadPorId(pIdTituloPropiedad);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(307);
			}
	}

}
