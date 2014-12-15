package com.trascender.catastro.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.naming.NamingException;

import com.trascender.catastro.business.interfaces.BusinessRegistroParcelarioLocal;
import com.trascender.catastro.exception.CatastroException;
import com.trascender.catastro.recurso.filtros.FiltroDeclaracionJurada;
import com.trascender.catastro.recurso.persistent.CoeficienteDepreciacion;
import com.trascender.catastro.recurso.persistent.DeclaracionJurada;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.RegistroMejora;
import com.trascender.catastro.system.interfaces.SystemAdministracionDDJJ;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;
/**
 * @ejb.bean name="SystemAdministracionDDJJ"
 *           display-name="Name for SystemAdministracionDDJJ"
 *           description="Description for SystemAdministracionDDJJ"
 *           jndi-name="ejb/SystemAdministracionDDJJ"
 *           type="Stateless"
 *           view-type="remote"
 */

@Stateful(name = "ejb/SystemAdministracionDDJJ")
public class SystemAdministracionDDJJBean implements SystemAdministracionDDJJ {

	@EJB
	private BusinessRegistroParcelarioLocal locRegistroParcelario;
	
	private static final long serialVersionUID = 6359217049405965098L;

	private long llave;
	
	public SystemAdministracionDDJJBean() {
		super();
	}

	public void setSessionContext(SessionContext ctx)
		throws EJBException,
		RemoteException {
	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @throws NamingException 
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	/**
	 * Agrega una nueva declaraci�n jurada
	 * @param pDeclaracionJurada declaraci�n jurada a agregar
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.DeclaracionJurada addDeclaracionJurada(
		com.trascender.catastro.recurso.persistent.DeclaracionJurada pDeclaracionJurada) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,DeclaracionJurada.serialVersionUID,Permiso.Accion.INSERT)){
					return this.locRegistroParcelario.addDeclaracionJurada(pDeclaracionJurada);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(370);
			}
	}
	
	/**
	 * Actualiza los datos de una declaraci�n jurada
	 * @param pDeclaracionJurada declaraci�n jurada que se desea actualizar
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.DeclaracionJurada updateDeclaracionJurada(
		com.trascender.catastro.recurso.persistent.DeclaracionJurada pDeclaracionJurada) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,DeclaracionJurada.serialVersionUID,Permiso.Accion.UPDATE)){
					return this.locRegistroParcelario.updateDeclaracionJurada(pDeclaracionJurada);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(781);
			}
	}
	
	/**
	 * Recupera un listado de declaraciones juradas seg�n un criterio
	 * @param pCodigoDDJJ c�digo de la declaraci�n jurada
	 * @param pDesde fecha de inscripcion desde la cual se comienza a buscar las declaraciones juradas
	 * @param pHasta fecha de inscripcion hasta la cual se buscar�n las declaraciones juradas
	 * @param pParcela parcela a la que pertenece la declaraci�n jurada
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public FiltroDeclaracionJurada findDeclaracionJurada(FiltroDeclaracionJurada filtro) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,Parcela.serialVersionUID,Permiso.Accion.SELECT)){
					return this.locRegistroParcelario.findListaDeclaracionJurada(filtro);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(372);
			}
	}
	
	/**
	 * Ingresa al sistema los datos de la llave de acceso
	 * @param pLlave llave de acceso
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave){
		this.llave=pLlave;
	}
	
	/**
	 * Agrega un registro de mejora
	 * @param pRegistroMejora Registro de mejora a agregar
	 * @return Registro de mejora con los datos actualizados
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote" 
	 */
	public com.trascender.catastro.recurso.persistent.RegistroMejora addRegistroMejora(
		com.trascender.catastro.recurso.persistent.RegistroMejora pRegistroMejora) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,RegistroMejora.serialVersionUID,Permiso.Accion.INSERT)){
					return this.locRegistroParcelario.addRegistroMejora(pRegistroMejora);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(373);
			}
	}
	
	/**
	 * Actualiza los datos de un recurso de mejora
	 * @param pRegistroMejora Registro de mejora a actualizar
	 * @return Registro de mejora acualizado
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.RegistroMejora updateRegistroMejora(
		com.trascender.catastro.recurso.persistent.RegistroMejora pRegistroMejora) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,RegistroMejora.serialVersionUID,Permiso.Accion.UPDATE)){
					return this.locRegistroParcelario.updateRegistroMejora(pRegistroMejora);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(374);
			}
	}
	
	/**
	 * Elimina un registro de mejora
	 * @param pRegistroMejora
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deleteRegistroMejora(com.trascender.catastro.recurso.persistent.RegistroMejora pRegistroMejora) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,RegistroMejora.serialVersionUID,Permiso.Accion.DELETE)){
					pRegistroMejora.setActivo(false);
					this.locRegistroParcelario.updateRegistroMejora(pRegistroMejora);
				}
				else {
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(375);
			}
	}
	
	/**
	 * Restaura el estado de un registro de mejora
	 * @param pRegistroMejora Registro de mejora a restaurar
	 * @return registro de mejora restaurado
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 * 
	 */
	public com.trascender.catastro.recurso.persistent.RegistroMejora restoreRegistroMejora(
		com.trascender.catastro.recurso.persistent.RegistroMejora pRegistroMejora) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,RegistroMejora.serialVersionUID,Permiso.Accion.UPDATE)){
					pRegistroMejora.setActivo(true);
					return this.locRegistroParcelario.updateRegistroMejora(pRegistroMejora);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(376);
			}
	}
	
	/**
	 * Recupera un listado de registros de mejora 
	 * @param pAnioConstruccion anio de construcci�n del registro de mejora
	 * @param pEstadoMejora estado del registro de mejora
	 * @param pParcela parcela a la que pertenece el registro de mejroa
	 * @param pDeclaracionJurada declaraci�n jurada relacionada con el registro de mejora
	 * @param pActivo si est� activo o no
	 * @return Listado de registros de mejora que cumplan con los criterios ingresados
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findListaRegistrosMejora(java.lang.Integer pAnioConstruccion,
		String pEstadoMejora, 
		com.trascender.catastro.recurso.persistent.Parcela pParcela,
		com.trascender.catastro.recurso.persistent.DeclaracionJurada pDeclaracionJurada,
		Boolean pActivo)
		throws TrascenderException {
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,RegistroMejora.serialVersionUID,Permiso.Accion.SELECT)){
					CoeficienteDepreciacion.EstadoCoeficiente locEstadoCoeficiente=null;
					if (pEstadoMejora!=null){
						locEstadoCoeficiente=CoeficienteDepreciacion.EstadoCoeficiente.valueOf(pEstadoMejora);
					}
					return this.locRegistroParcelario.findListaRegistrosMejora(pAnioConstruccion,locEstadoCoeficiente,pParcela,pDeclaracionJurada,pActivo);
				}
				else{
					throw new CatastroException(791);
				}
			}
			catch(TrascenderException e){
				throw e;
			}
			catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(377);
			}
			
		}
}
