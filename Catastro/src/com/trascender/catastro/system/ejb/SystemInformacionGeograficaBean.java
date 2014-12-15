package com.trascender.catastro.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.catastro.business.interfaces.BusinessRegistroGeograficoLocal;
import com.trascender.catastro.exception.CatastroException;
import com.trascender.catastro.recurso.filtros.FiltroCalle;
import com.trascender.catastro.recurso.filtros.FiltroCuadra;
import com.trascender.catastro.recurso.filtros.FiltroManzana;
import com.trascender.catastro.recurso.filtros.FiltroTipoCalle;
import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.catastro.recurso.persistent.Servicio;
import com.trascender.catastro.recurso.persistent.TipoCalle;
import com.trascender.catastro.system.interfaces.SystemInformacionGeografica;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.util.SecurityMgr;

/**
 * @ejb.bean name="SystemInformacionGeografica"
 *           display-name="Name for SystemInformacionGeografica"
 *           description="Description for SystemInformacionGeografica"
 *           jndi-name="ejb/SystemInformacionGeografica"
 *           type="Stateless"
 *           view-type="remote"
 */
@Stateful(name = "ejb/SystemInformacionGeografica")
public class SystemInformacionGeograficaBean implements SystemInformacionGeografica {

	private long llave=0;
	
	@EJB
	private BusinessRegistroGeograficoLocal businessInformacionGeograficaLocal;

	private static final long serialVersionUID = 955725867558042034L;

	public SystemInformacionGeograficaBean() {
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
	 * Permite agregar una nueva calle
	 * @throws TrascenderException 
	 * @param pCalle calle que se desea agregar
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Calle addCalle(
		com.trascender.catastro.recurso.persistent.Calle pCalle) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,Calle.serialVersionUID,Permiso.Accion.INSERT)){
					return this.businessInformacionGeograficaLocal.addCalle(pCalle);
				}
				else{
					throw new CatastroException(791);
				}
			}catch(TrascenderException e){
				e.printStackTrace();
				throw e;
			}catch(Exception e){
				e.printStackTrace();
				throw new CatastroException(340);
			}
	}

	/**
	 * Actualiza una calle
	 * @throws TrascenderException 
	 * @param pCalle calle que se desea actualizar 
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Calle updateCalle(
		com.trascender.catastro.recurso.persistent.Calle pCalle) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,Calle.serialVersionUID,Permiso.Accion.UPDATE)){
					pCalle.setActivo(true);
					return this.businessInformacionGeograficaLocal.updateCalle(pCalle);
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
				throw new CatastroException(341);
			}
	}
	
	/**
	 * Recupera una calle según el código
	 * @param pCodigo código de la calle que se desea buscar
	 * @return Calle que corresponde al código o nulo en caso que no exista
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Calle findCalle(String pCodigo) 
		throws TrascenderException {
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,Calle.serialVersionUID,Permiso.Accion.SELECT)) {
					return this.businessInformacionGeograficaLocal.findCalle(pCodigo);
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
				throw new CatastroException(342);
			}
	}
	
	/**
	 * Obtiene un lisatado de las calles
	 * @param pNombre nombre de la calle
	 * @param pTipoCalle tipo de calle seg�n la cual queremos filtrar
	 * @throws TrascenderException 
	 * @ejb.interface-method  view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public FiltroCalle findListaCalles(FiltroCalle filtro) 
		throws TrascenderException {
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,Calle.serialVersionUID,Permiso.Accion.SELECT)){
					Boolean locEstado;
					if (filtro.getEstado()==null){
						locEstado=true;
					}
					else{
						locEstado=filtro.getEstado();
					}
					return this.businessInformacionGeograficaLocal.findCalle(filtro);	
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
				throw new CatastroException(342);
			}
	}
	
	/**
	 * Elimina una calle
	 * @param pCalle calle que se desea eliminar
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void deleteCalle(
		com.trascender.catastro.recurso.persistent.Calle pCalle)
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,Calle.serialVersionUID,Permiso.Accion.SELECT)){
					pCalle.setActivo(false);
					this.businessInformacionGeograficaLocal.updateCalle(pCalle);
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
				throw new CatastroException(343);
			}
	}
	
	/**
	 * Agrega un nuevo tipo de calle
	 * @param pTipoCalle
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.TipoCalle addTipoCalle(
		com.trascender.catastro.recurso.persistent.TipoCalle pTipoCalle)
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,TipoCalle.serialVersionUID,Permiso.Accion.INSERT)){
					return this.businessInformacionGeograficaLocal.addTipoCalle(pTipoCalle);
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
				throw new CatastroException(344);
			}
	}
	
	/**
	 * Actualiza un tipo de calle
	 * @param pTipoCalle tipo de calle que se desea actualizar
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.TipoCalle updateTipoCalle(
		com.trascender.catastro.recurso.persistent.TipoCalle pTipoCalle) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,TipoCalle.serialVersionUID,Permiso.Accion.UPDATE)){
					pTipoCalle.setActivo(true);
					return this.businessInformacionGeograficaLocal.updateTipoCalle(pTipoCalle);
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
				throw new CatastroException(345);
			}
	}
	
	/**
	 * Elimina un tipo de calle
	 * @param pTipoCalle tipo de calle que se desea eliminar
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void deleteTipoCalle(
		com.trascender.catastro.recurso.persistent.TipoCalle pTipoCalle) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,TipoCalle.serialVersionUID,Permiso.Accion.DELETE)){
					this.businessInformacionGeograficaLocal.deleteTipoCalle(pTipoCalle);
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
				throw new CatastroException(346);
			}
	}
	
	/**
	 * Realiza consultas sobre el tipo de calles
	 * @param pNombre nombre del tipo de calles
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public FiltroTipoCalle findListaTipoCalles(FiltroTipoCalle filtro) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,TipoCalle.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessInformacionGeograficaLocal.findListaTiposCalle(filtro);
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
			throw new CatastroException(347);
		}
	}
	
	/**
	 * Agrega un nuevo servicio
	 * @param pServicio servicio que se desea eliminar
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Servicio addServicio(
		com.trascender.catastro.recurso.persistent.Servicio pServicio) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Servicio.serialVersionUID,Permiso.Accion.INSERT)){
				return this.businessInformacionGeograficaLocal.addServicio(pServicio);
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
			throw new CatastroException(348);
		}
	}

	/**
	 * Actualiza un servicio
	 * @param pServicio servicio que se desea actualizar
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Servicio updateServicio(
		com.trascender.catastro.recurso.persistent.Servicio pServicio) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Servicio.serialVersionUID,Permiso.Accion.UPDATE)){
				return this.businessInformacionGeograficaLocal.updateServicio(pServicio);
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
			throw new CatastroException(349);
		}
	}
	
	/**
	 * Elimina un servicio
	 * @param pServicio servicio que se desea eliminar
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void deleteServicio(
		com.trascender.catastro.recurso.persistent.Servicio pServicio) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,Servicio.serialVersionUID,Permiso.Accion.DELETE)){
					pServicio.setEstado(Servicio.Estado.INACTIVO);
					this.businessInformacionGeograficaLocal.deleteServicio(pServicio);
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
				throw new CatastroException(350);
			}
	}
	
	/**
	 * Obtiene una lista de servicios
	 * @param pNombre primeras letras de los nombres de servicios según los cuales se desea filtrar
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findListaServicios(String pNombre) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Servicio.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessInformacionGeograficaLocal.findServicio(pNombre);
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
			throw new CatastroException(351);
		}
	}
	
	/**
	 * Agrega una manzana al sistema
	 * @param pManzana manzana que se desea agregar
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Manzana addManzana(
		com.trascender.catastro.recurso.persistent.Manzana pManzana) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Manzana.serialVersionUID,Permiso.Accion.INSERT)){
				return this.businessInformacionGeograficaLocal.addManzana(pManzana);
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
			throw new CatastroException(356);
		}
	}
	
	/**
	 * Actualiza los datos de una manzana
	 * @param pManzana manzana a actualizar
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Manzana updateManzana(
		com.trascender.catastro.recurso.persistent.Manzana pManzana) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Manzana.serialVersionUID,Permiso.Accion.UPDATE)){
				pManzana.setActivo(true);
				return this.businessInformacionGeograficaLocal.updateManzana(pManzana);
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
			throw new CatastroException(357);
		}
	}
	
	/**
	 * Elimina una manzana del registro
	 * @param pManzana manzana que se desea eliminar
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void deleteManzana(
		com.trascender.catastro.recurso.persistent.Manzana pManzana) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Manzana.serialVersionUID,Permiso.Accion.DELETE)){
				pManzana.setActivo(false);
				this.businessInformacionGeograficaLocal.updateManzana(pManzana);
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
			throw new CatastroException(358);
		}
	}
	
	/**
	 * Permite recuperar una lista de manzanas que comiencen con cierto nombre
	 * @param pNombre primeras letras del nombre de la manzana que desea recuperar
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public FiltroManzana findListaManzanas(FiltroManzana filtro)  throws TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Manzana.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessInformacionGeograficaLocal.findListaManzanas(filtro);
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
			throw new CatastroException(359);
		}
	}
	
	/**
	 * Permite recuperar una manzana
	 * @param pNumero numero de manzana que se desea recuperar
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Manzana findManzana(
		Integer pNumero) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Manzana.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessInformacionGeograficaLocal.findManzana(pNumero);
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
			throw new CatastroException(359);
		}
	}
	
	/**
	 * Agrega una cuadra al sistema
	 * @param pCuadra cuadra que se desea agregar
	 * @throws TrascenderException 
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Cuadra addCuadra(
		com.trascender.catastro.recurso.persistent.Cuadra pCuadra) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Cuadra.serialVersionUID,Permiso.Accion.INSERT)){
				return this.businessInformacionGeograficaLocal.addCuadra(pCuadra);
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
			throw new CatastroException(360);
		}
	}
	
	/**
	 * Actualiza los datos de una cuadra
	 * @param pCuadra cuadra a actualizar
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Cuadra updateCuadra(
		com.trascender.catastro.recurso.persistent.Cuadra pCuadra) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Cuadra.serialVersionUID,Permiso.Accion.UPDATE)){
				return this.businessInformacionGeograficaLocal.updateCuadra(pCuadra);
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
			throw new CatastroException(361);
		}
	}
	
	/**
	 * Elimina una cuadra del registro
	 * @param pCuadra cuadra a eliminar
	 * @throws TrascenderException
	 * @ejb.interface-method  view-type = "remote"
	 */
	public void deleteCuadra(
		com.trascender.catastro.recurso.persistent.Cuadra pCuadra) throws TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Cuadra.serialVersionUID,Permiso.Accion.DELETE)){
				pCuadra.setActivo(false);
				this.businessInformacionGeograficaLocal.updateCuadra(pCuadra);
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
			throw new CatastroException(362);
		}
	}
	
	/**
	 * Recupera el listado de cuadras por calle
	 * @param pCalle calle por la cual se recuperar�n las cuadras
	 * @param pCalleComienza calle de comienzo de la cuadra
	 * @param pCalleFinaliza calle donde finaliza la cuadra
	 * @param pNumeracionDesde numeraci�n de comienzo de la cuadra
	 * @param pNumeracionHasta numeraci�n final de la cuadra
	 * @param pEstado estado en que se encuentra la cuadra, en caso de ser valor nulo, se recuperan los registros activos 
	 * @throws TrascenderException 
	 * @ejb.interface-method  view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public FiltroCuadra findListaCuadras(FiltroCuadra filtro) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Cuadra.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessInformacionGeograficaLocal.findListaCuadras(filtro);
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
			throw new CatastroException(363);
		}
	}
	
	/**
	 * Recupera un listado de las cuadras por manzana que limitan una manzana
	 * @param pManzana manzana relacionada
	 * @return List listado de las manzanas 
	 * @ejb.interface-method view-type = "remote"
	 */
	@SuppressWarnings("unchecked")
	public java.util.List findListaCuadrasPorManzana(com.trascender.catastro.recurso.persistent.Manzana pManzana) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Cuadra.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessInformacionGeograficaLocal.findListaCuadrasPorManzana(pManzana);
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
			throw new CatastroException(367);
		}
	}
	
	/**
	 * Permite recuperar una calle previamente eliminada
	 * @param pCalle calle que se desea restaurar
	 * @throws Exception 
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Calle restoreCalle(com.trascender.catastro.recurso.persistent.Calle pCalle)
		throws Exception {
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,Calle.serialVersionUID,Permiso.Accion.UPDATE)){
					pCalle.setActivo(true);
					return this.businessInformacionGeograficaLocal.updateCalle(pCalle);
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
				throw new CatastroException(364);
			}
	}
	
	/**
	 * Restaura un tipo de calle eliminado con anterioridad
	 * @param pTipoCalle tipo de calle eliminado
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.TipoCalle restoreTipoCalle(com.trascender.catastro.recurso.persistent.TipoCalle pTipoCalle)
		throws Exception{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,TipoCalle.serialVersionUID,Permiso.Accion.UPDATE)){
					pTipoCalle.setActivo(true);
					return this.businessInformacionGeograficaLocal.updateTipoCalle(pTipoCalle);
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
				throw new CatastroException(365);
			}
	}
	
	/**
	 * Restaura una cuadra al estado anterior al ser eliminada
	 * @param pCuadra cuadra que se desea restaurar
	 * @return Cuadra restaurada
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Cuadra restoreCuadra(com.trascender.catastro.recurso.persistent.Cuadra pCuadra) 
		throws Exception{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,Cuadra.serialVersionUID,Permiso.Accion.UPDATE)){
					pCuadra.setActivo(true);
					return this.businessInformacionGeograficaLocal.updateCuadra(pCuadra);
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
				throw new CatastroException(366);
			}
	}
	
	/**
	 * 
	 * @ejb.interface-method  view-type="remote"
	 */
	public void setLlave(long pLlave){
		this.llave=pLlave;
	}
	
	/**
	 * 
	 * @param pIdManzana
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Manzana getManzanaPorId(long pIdManzana) 
		throws TrascenderException{
			try{
				if (SecurityMgr.getInstance().getPermiso(this.llave,Manzana.serialVersionUID,Permiso.Accion.SELECT)){
					return this.businessInformacionGeograficaLocal.getManzanaPorId(pIdManzana);
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
				return null;
			}
	}
	
	public TipoCalle getTipoCallePorId(long pIdTipoCalle) throws RemoteException {
		try {
			if (SecurityMgr.getInstance().getPermiso(this.llave,TipoCalle.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessInformacionGeograficaLocal.getTipoCallePorId(pIdTipoCalle);
			}
			else{
				throw new CatastroException(791);
			}
		} catch(RemoteException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 
	 * @param pId
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public com.trascender.catastro.recurso.persistent.Cuadra getCuadraPorId(Long pId) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Cuadra.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessInformacionGeograficaLocal.getCuadraPorId(pId);
			}
			else{
				throw new CatastroException(791);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public Calle getCallePorId(Long pId) throws Exception {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Calle.serialVersionUID,Permiso.Accion.INSERT)){
				return this.businessInformacionGeograficaLocal.getCallePorId(pId);
			}else{
				throw new CatastroException(791);
			}
		}catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			throw new CatastroException(342);
		}
	}
}
