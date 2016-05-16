package com.trascender.habilitaciones.system.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.TituloPropiedad;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Permiso.Accion;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoArrendamientoLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoAutomotorLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoCementerioLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoOSPLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoPlanObraLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoSHPSLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoTGILocal;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoTasaMenorLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessObligacionLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionArrendamiento;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionAutomotor;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionCementerio;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionOSP;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionSHPS;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.FiltroObligacionTGI;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PermisoHab;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.arrendamiento.DocumentoArrendamiento;
import com.trascender.habilitaciones.recurso.persistent.cementerio.DocumentoCementerio;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.pfo.Obra;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.habilitaciones.system.interfaces.SystemObligacion;

@Stateful(name = "ejb/SystemObligacion")
public class SystemObligacionBean implements SystemObligacion {

	private long llave;
	
	//business
	@EJB
	private BusinessObligacionLocal businessObligacionLocal;
	@EJB
	private BusinessDocumentoTGILocal businessDocumentoTGI;
	@EJB
	private BusinessDocumentoPlanObraLocal businessDocumentoPlanObra;
	@EJB
	private BusinessDocumentoOSPLocal businessDocumentoOSP;
	@EJB
	private BusinessDocumentoSHPSLocal businessDocumentoSHPS;
	@EJB
	private BusinessDocumentoTasaMenorLocal businessDocumentoTasaMenor;
	@EJB
	private BusinessDocumentoAutomotorLocal businessDocumentoAutomotor;
	@EJB
	private BusinessDocumentoCementerioLocal businessDocumentoCementerio;
	@EJB
	private BusinessDocumentoArrendamientoLocal businessDocumentoArrendamiento;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4686207645291205306L;

	public SystemObligacionBean() {
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
	 * Agrega una obligación 
	 * @param pObligacion
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void addObligacion(
				com.trascender.framework.recurso.persistent.Persona pPersona,
				com.trascender.habilitaciones.recurso.persistent.Obligacion pObligacion) throws TrascenderException{
		try{
			if (validarPermisoSegunTipoObligacion(pObligacion, Permiso.Accion.INSERT)){
				if(pPersona != null){
					pObligacion.setPersona(pPersona);
				}
				
				this.validarDocEspecializado(pObligacion);
				TrascenderEnverListener.setValoresEnAuditoriaBean(pObligacion.getDocumentoEspecializado());
				this.businessObligacionLocal.addObligacion(pObligacion);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
			
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(371);
		}
	}
	
	//si es un docSHPS validar que tenga domicilio
	private void validarDocEspecializado(Obligacion pObligacion) throws TrascenderException{
		if (pObligacion.getDocumentoEspecializado()!=null){
			//LÓGICA ESPECÍFICA DE CADA DOCUMENTO ESPECIALIZADO
			if (pObligacion.getDocumentoEspecializado() instanceof DocumentoSHPS){
				DocumentoSHPS locDocSHPS = (DocumentoSHPS)pObligacion.getDocumentoEspecializado();
				if (locDocSHPS.getDomicilio()==null){
					throw new HabilitacionesException(372);
				}
			}
			
		}
	}

	
	/**
	 * Actualiza los datos de una obligacion
	 * @param pObligacion
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public void updateObligacion(com.trascender.habilitaciones.recurso.persistent.Obligacion pObligacion) throws TrascenderException{
		try{
			if (validarPermisoSegunTipoObligacion(pObligacion, Permiso.Accion.UPDATE)){
				this.validarDocEspecializado(pObligacion);
				System.out.println("estado Obligacion = "+pObligacion.getEstado());
				this.businessObligacionLocal.updateObligacion(pObligacion);
				
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(373);
		}
		
	}
	
	private boolean validarPermisoSegunTipoObligacion(Obligacion pObligacion, Permiso.Accion pAccion) throws Exception{
		DocHabilitanteEspecializado locDocumento = pObligacion.getDocumentoEspecializado();
		long serialVersion = 0;
		if (locDocumento instanceof DocumentoTGI){
			serialVersion = DocumentoTGI.serialVersionUID;
		} else if (locDocumento instanceof DocumentoOSP) {
			serialVersion = DocumentoOSP.serialVersionUID;
		} else if (locDocumento instanceof DocumentoSHPS) {
			serialVersion = DocumentoSHPS.serialVersionUID;
		} else if (locDocumento instanceof DocumentoArrendamiento) {
			serialVersion = DocumentoArrendamiento.serialVersionUID;
		} else if (locDocumento instanceof DocumentoCementerio) {
			serialVersion = DocumentoCementerio.serialVersionUID;
		}
		return SecurityMgr.getInstance().getPermiso(this.llave,serialVersion,pAccion);
	}
	
	/**
	 * Recupera un listado de obligaciones
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public List findListaObligaciones(Persona pPersona, TipoObligacion pTipoObligacion, Obligacion.Estado pEstadoObligacion, Parcela pParcela, Vehiculo pVehiculo) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Obligacion.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessObligacionLocal.findListaObligaciones(pPersona,pTipoObligacion,pEstadoObligacion,pParcela, pVehiculo);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(374);
		}
	}
	
	
	/**
	 * Recupera un listado de documentos habilitantes
	 * @param pPersona persona a la que pertenece la obligacion
	 * @param pTipoObligacion tipo de obligacion (especificada por el tipo de documento habilitante)
	 * @param pEstadoDocHabilitante estado del documento habilitante
	 * @param pEstadoObligacion estado en que se encuentra la obligación
	 * @return Listado de documentos habilitantes
	 * @ejb.interface-method view-type = "remote"
	 * @throws TrascenderException
	 */
	public List findListaDocHabEspecializados(Persona pPersona, 
				TipoObligacion pTipoObligacion, 
				DocHabilitanteEspecializado.Estado pEstadoDocHabilitante,
				Obligacion.Estado pEstadoObligacion) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, DocHabilitanteEspecializado.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessObligacionLocal.findListaDocHabEspecializados(pPersona, pTipoObligacion, pEstadoDocHabilitante,pEstadoObligacion);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(375);
		}
	}
	
	/**
	 * Firma un permiso de habilitaciones para el usuario acutal
	 * @param pPermisoHab
	 * @ejb.interface-method view-type = "remote"
	 */
	public PermisoHab firmarDocHabilitante(PermisoHab pPermisoHab, String pComentario) throws TrascenderException{
		try{
			return this.businessObligacionLocal.firmarDocHabilitante(this.llave, pPermisoHab, pComentario);
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(376);
		}
	}
	
	
	/**
	 * Recupera un listado de los Permisos que puede firmar el usuario pasado por parámetro
	 * @param pUsuario Usuario del que se desean recuperar los permisos a firmar
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public List findListaPermisosHabAFirmar(Usuario pUsuario, Obligacion pObligacion) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, PermisoHab.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessObligacionLocal.findListaPermisosHabAFirmar(pUsuario,pObligacion);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(377);
		}
	}
	
	
	/**
	 * Recupera un listado de los permisos a habilitar por el usuario actual
	 * @return Listado de PermisoHab
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public List findListaPermisosHabAFirmar(Obligacion pObligacion) throws TrascenderException{
		Usuario locUsuario=SecurityMgr.getInstance().getUsuario(this.llave);
		return this.findListaPermisosHabAFirmar(locUsuario,pObligacion);
	}

	/**
	 * Recupera un listado de los permisos a habilitar por el usuario actual
	 * @return Listado de PermisoHab
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public List findListaPermisosHabAFirmar(Persona pPersona) throws TrascenderException{
		Usuario locUsuario=SecurityMgr.getInstance().getUsuario(this.llave);
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, PermisoHab.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessObligacionLocal.findListaPermisosHabAFirmar(locUsuario,pPersona);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(377);
		}
	}
	
	
	/**
	 * Recupera una obligación por el id
	 * @param pIdObligacion id de la obligacion a recuperar
	 * @return obligacion
	 * @throws TrascenderException 
	 * @ejb.interface-method view-type = "remote"
	 */
	public Obligacion getObligacionPorId(long pIdObligacion) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getUsuario(this.llave) != null) {
				return this.businessObligacionLocal.getObligacionPorId(pIdObligacion);
			} else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(378);
		}
	}
	
	/**
	 * 
	 * @param pObligacion
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public TipoObligacion getTipoObligacionFromObligacion(Obligacion pObligacion) throws Exception{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Obligacion.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessObligacionLocal.getTipoObligacionFromObligacion(pObligacion);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(379);
		}
	}
	
	
	/**
	 * Recupera el listado de obligaciones cuyos documentos son del tipo TGI y cumplen con los parámetros ingresados 
	 * @param pPersona persona a la que pertenece la obligacion
	 * @param pNumeroRegistro número de registro de la parcela
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroObligacionTGI findListaObligacionesTGI(FiltroObligacionTGI pFiltro) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoTGI.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessDocumentoTGI.findListaObligacionesTGI(pFiltro);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(380);
		}
	}
	


	/**
	 * 
	 * @param pPersona
	 * @param pNumeroRegistro
	 * @return
	 * @ejb.interface-method view-type = "remote"
	 */
	public FiltroObligacionOSP findListaObligacionesOSP(FiltroObligacionOSP pFiltro) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, DocumentoOSP.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessDocumentoOSP.findListaObligacionesOSP(pFiltro);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(381);
		}
	}
	
	/**
	 * 
	 * @param pPersona
	 * @param pNumeroRegistro
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "remote"
	 */
	public List findListaObligacionesPFO(
			Persona pPersona, 
			Integer pNumeroRegistro,
			Obra pObra) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, Obligacion.serialVersionUID, Permiso.Accion.SELECT)){
				return this.businessDocumentoPlanObra.findListaObligacionPlanObra(pPersona, pNumeroRegistro,pObra);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(382);
		}
	}
	
	/**
	 * 
	 * @param pPersona
	 * @param pNumeroInscripcion
	 * @return
	 * @ejb.interface-method view-type = "remote" 
	 */
	public FiltroObligacionSHPS findListaObligacionesSHPS(FiltroObligacionSHPS pFiltro) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoSHPS.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessDocumentoSHPS.findListaObligacionesSHPS(pFiltro);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(373);
		}
		
	}
	
	/**
	 * Business method
	 * @ejb.interface-method view-type="remote"
	 * @param pPersona
	 * @param pParcela
	 * @throws Exception
	 */
	public void modificarTitularObligacion(Persona pPersona, Parcela pParcela) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave, TituloPropiedad.serialVersionUID, Accion.UPDATE)){
				this.businessObligacionLocal.modificarTitularObligacion(pPersona, pParcela);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch (TrascenderException e){
			throw e;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(5456);
		}
	}
	
	/**
	 * Coloca el valor de la llave
	 * @param pLlave
	 * @ejb.interface-method view-type = "remote"
	 */
	public void setLlave(long pLlave){
		this.llave=pLlave;
	}
	
	public FiltroObligacionTasaMenor findListaObligacionesTasaMenor(FiltroObligacionTasaMenor pFiltro) throws TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Obligacion.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessDocumentoTasaMenor.findListaObligacionesTasaMenor(pFiltro);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(374);
		}
	}
	
	public FiltroObligacionAutomotor findListaObligacionesAutomotor(FiltroObligacionAutomotor pFiltro) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Obligacion.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessDocumentoAutomotor.findListaObligacionesAutomotor(pFiltro);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(373);
		}
	}
	
	public FiltroObligacionCementerio findListaObligacionesCementerio(FiltroObligacionCementerio pFiltro) throws TrascenderException{
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,Obligacion.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessDocumentoCementerio.findListaObligacionesCementerio(pFiltro);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(373);
		}
	}

	@Override
	public FiltroObligacionArrendamiento findListaObligacionesArrendamiento(
			FiltroObligacionArrendamiento pFiltro) throws TrascenderException {
		try{
			if (SecurityMgr.getInstance().getPermiso(this.llave,DocumentoArrendamiento.serialVersionUID,Permiso.Accion.SELECT)){
				return this.businessDocumentoArrendamiento.findListaObligacionesArrendamiento(pFiltro);
			}
			else{
				throw new HabilitacionesException(799);
			}
		}
		catch(TrascenderException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new HabilitacionesException(373);
		}
	}
}
