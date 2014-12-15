
package com.trascender.habilitaciones.business.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;
import ar.trascender.criterio.enums.Posicion;

import com.trascender.framework.business.interfaces.BusinessParametroLocal;
import com.trascender.framework.business.interfaces.BusinessUsuarioLocal;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoSHPSLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessObligacionLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.filtros.FiltroInspector;
import com.trascender.habilitaciones.recurso.filtros.FiltroLibretaSanitaria;
import com.trascender.habilitaciones.recurso.filtros.FiltroLocalComercial;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionSHPS;
import com.trascender.habilitaciones.recurso.filtros.FiltroTransporteVehicular;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.RegistroValuado;
import com.trascender.habilitaciones.recurso.persistent.shps.AsocRubro;
import com.trascender.habilitaciones.recurso.persistent.shps.ClausuraSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.ConstanciaVacunacion;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.InhabilitacionTemporariaLS;
import com.trascender.habilitaciones.recurso.persistent.shps.InspeccionComercial;
import com.trascender.habilitaciones.recurso.persistent.shps.InspeccionSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.InspeccionVehicular;
import com.trascender.habilitaciones.recurso.persistent.shps.Inspector;
import com.trascender.habilitaciones.recurso.persistent.shps.LibretaSanitaria;
import com.trascender.habilitaciones.recurso.persistent.shps.LocalComercial;
import com.trascender.habilitaciones.recurso.persistent.shps.LogModificaciones;
import com.trascender.habilitaciones.recurso.persistent.shps.RenovacionLibretaSanitaria;
import com.trascender.habilitaciones.recurso.persistent.shps.Rubro;
import com.trascender.habilitaciones.recurso.persistent.shps.TransporteVehicular;

@Stateless(name = "ejb/BusinessDocumentoSHPSLocal")
public class BusinessDocumentoSHPSBean implements BusinessDocumentoSHPSLocal {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8487546948353880953L;

	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre("HAB|Adm. Obligación SHPS");

		Recurso localComercial = new Recurso();
		Recurso clausuraSHPS = new Recurso();
		Recurso inspeccion = new Recurso();
		Recurso inspeccionComercial = new Recurso();
		Recurso inspeccionVehicular = new Recurso();
		Recurso transporteVehicular = new Recurso();
		Recurso libretaSanitaria = new Recurso();
		Recurso renovacionLS = new Recurso();
		Recurso inhabilitacionLibretaSanitaria = new Recurso();
		Recurso vacunacion = new Recurso();
		Recurso documentoEspecializado = new Recurso();
		Recurso inspector = new Recurso();
		Recurso regAlicuota = new Recurso();

		documentoEspecializado.setIdRecurso(DocumentoSHPS.serialVersionUID);
		documentoEspecializado.setNombre("Obligación S.H.P.S.");
		documentoEspecializado.setAtributosConsultables("Contribuyente", "stringPersona", Tipo.TEXTO_LARGO, "Nro Inscripción", "numeroInscripcion", "Rubros", "stringListaRegAlicuotas",
				Tipo.TEXTO_LARGO, "Calle", "calle", "Altura", "altura");
		documentoEspecializado.setClase(DocumentoSHPS.class);
		grupo.getListaRecursos().add(documentoEspecializado);

		regAlicuota.setIdRecurso(Rubro.serialVersionUID);
		regAlicuota.setNombre("Registro de Alícuotas");
		regAlicuota.setAtributosConsultables("Código Ciiu", "codigo", "Descripción Ciiu", "descripcion", Tipo.TEXTO_LARGO, "Nombre", "nombre", "Alícuota", "valor", "Mínimo", "minimo");
		regAlicuota.setClase(Rubro.class);
		grupo.getListaRecursos().add(regAlicuota);

		localComercial.setIdRecurso(LocalComercial.serialVersionUID);
		localComercial.setNombre("Local Comercial");
		localComercial.setAtributosConsultables("Nº Inscripción", "numeroInscripcion", "Sup. Cubierta", "superficieCubiertaAfectada", "Sup. Semicubierta",
				"superficieSemicubiertaAfectada", "Descripción", "descripcion", Tipo.TEXTO_LARGO);
		localComercial.setClase(LocalComercial.class);
		grupo.getListaRecursos().add(localComercial);

		clausuraSHPS.setIdRecurso(ClausuraSHPS.serialVersionUID);
		clausuraSHPS.setNombre("Clausuras");
		clausuraSHPS.setClase(ClausuraSHPS.class);
		grupo.getListaRecursos().add(clausuraSHPS);

		transporteVehicular.setIdRecurso(TransporteVehicular.serialVersionUID);
		transporteVehicular.setNombre("Transporte Vehicular");
		transporteVehicular.setAtributosConsultables("Nº Inscripción", "numeroInscripcion", "Fecha Alta", "fechaAlta", Tipo.FECHA, "Vehículo", "vehiculo", "Descripción", "descripcion",
				Tipo.TEXTO_LARGO, "Fecha Baja", "fechaBaja", Tipo.FECHA);
		transporteVehicular.setClase(TransporteVehicular.class);
		grupo.getListaRecursos().add(transporteVehicular);

		libretaSanitaria.setIdRecurso(LibretaSanitaria.serialVersionUID);
		libretaSanitaria.setNombre("Libreta Sanitaria");
		libretaSanitaria.setAtributosConsultables("Número", "numeroLibretaSanitaria", "Persona", "nombrePersonaFisica");
		libretaSanitaria.setClase(LibretaSanitaria.class);
		grupo.getListaRecursos().add(libretaSanitaria);

		inspector.setIdRecurso(Inspector.serialVersionUID);
		inspector.setNombre("Inspector");
		inspector.setAtributosConsultables("Nombre", "nombre", "Persona", "nombrePersonaFisica");
		inspector.setClase(Inspector.class);
		grupo.getListaRecursos().add(inspector);

		inspeccion.setIdRecurso(InspeccionSHPS.serialVersionUID);
		inspeccion.setNombre("Inspección");
		inspeccion.setClase(InspeccionSHPS.class);
		grupo.getListaRecursos().add(inspeccion);

		inspeccionComercial.setIdRecurso(InspeccionComercial.serialVersionUID);
		inspeccionComercial.setNombre("Inspección Comercial");
		inspeccionComercial.setClase(InspeccionComercial.class);
		grupo.getListaRecursos().add(inspeccionComercial);

		inspeccionVehicular.setIdRecurso(InspeccionVehicular.serialVersionUID);
		inspeccionVehicular.setNombre("Inspección Vehicular");
		inspeccionVehicular.setClase(InspeccionVehicular.class);
		grupo.getListaRecursos().add(inspeccionVehicular);

		renovacionLS.setIdRecurso(RenovacionLibretaSanitaria.serialVersionUID);
		renovacionLS.setNombre("Renovación de Libreta Sanitaria");
		renovacionLS.setClase(RenovacionLibretaSanitaria.class);
		grupo.getListaRecursos().add(renovacionLS);

		inhabilitacionLibretaSanitaria.setIdRecurso(InhabilitacionTemporariaLS.serialVersionUID);
		inhabilitacionLibretaSanitaria.setNombre("Inhabilitación Temporaria");
		inhabilitacionLibretaSanitaria.setClase(InhabilitacionTemporariaLS.class);
		grupo.getListaRecursos().add(inhabilitacionLibretaSanitaria);

		vacunacion.setIdRecurso(ConstanciaVacunacion.serialVersionUID);
		vacunacion.setNombre("Constancia de Vacunación");
		vacunacion.setClase(ConstanciaVacunacion.class);
		grupo.getListaRecursos().add(vacunacion);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	@EJB
	private BusinessObligacionLocal businessObligacionLocal;

	@EJB
	private BusinessUsuarioLocal businessUsuarioLocal;

	@EJB
	private BusinessParametroLocal businessParametro;

	@PersistenceContext(name = "Vipians")
	private EntityManager entityManager;

	public BusinessDocumentoSHPSBean() {
	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {

	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method view-type = "local"
	 */
	public void ejbCreate() throws CreateException {
	}

	/**
	 * Recupera la lista de documentos especializados de SHPS de una persona
	 * 
	 * @param pPersonaFisica
	 *            pPersonaFisica
	 * @return listado de documentos especializados
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public java.util.List findListaDocumentosSHPS(com.trascender.framework.recurso.persistent.Persona pPersona) throws Exception {

		List<DocumentoSHPS> locListaResultado = Criterio.getInstance(this.entityManager, DocumentoSHPS.class).add(Restriccion.IGUAL("obligacion.persona", pPersona)).list();

		for(DocumentoSHPS cadaDocumento : locListaResultado) {
			cadaDocumento.toString();
			cadaDocumento.getObligacion().toString();
			cadaDocumento.getRegistroAlicuota().toString();
			cadaDocumento.getObligacion().getPersona().toString();
		}
		return locListaResultado;
	}

	/**
	 * Actualiza los datos de un documento de shps
	 * 
	 * @param pDocumentoSHPS
	 *            documento a actualizar
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void updateDocumentoSHPS(DocumentoSHPS pDocumentoSHPS) throws Exception {
		// TODO: El metodo esta andando pero lo que hace es actualizar el id doc hab esp que tiene la lib sanit como clave foranea, eso lo que
		// hace es restringir la libreta de forma tal que pueda pertenecer solo a una obligacion
		// si se pone el id libreta sanitaria del lado de el doc hab especializado, se va a poder tener asociado a cada documento una libreta
		// sanitaria permitiendo que las mismas puedan estar asociada a mas de una obligacion a la vez

		this.entityManager.flush();

//		Persona locPersona = pDocumentoSHPS.getObligacion().getPersona();
//
//		// si el numeroInscripcion de pDocumentoSHPS no esta en la lista logModificaciones , lo agrego
//		if(pDocumentoSHPS.getNumeroInscripcion() != null) {
//			boolean existe = false;
//			Iterator<LogModificaciones> locIterador = pDocumentoSHPS.getListaModificaciones().iterator();
//			while((!existe) && (locIterador.hasNext())) {
//				LogModificaciones locLogModificaciones = locIterador.next();
//				if(locLogModificaciones.getNumeroInscripcion().equals(pDocumentoSHPS.getNumeroInscripcion())) {
//					existe = true;
//				}
//			}
//
//			if(!existe) {
//				LogModificaciones locLog = new LogModificaciones();
//				locLog.setDocumentoSHPS(pDocumentoSHPS);
//				locLog.setFecha(Calendar.getInstance().getTime());
//				locLog.setNumeroInscripcion(pDocumentoSHPS.getNumeroInscripcion());
//			}
//		}

		/**
		 * No se valida mas esto, no es requerido.
		 */
		// if(pDocumentoSHPS.getListaLibretaSanitarias() != null && !pDocumentoSHPS.getListaLibretaSanitarias().isEmpty()){
		// boolean banderaPersona = false;
		// for(LibretaSanitaria cadaLibreta : pDocumentoSHPS.getListaLibretaSanitarias()){
		// if (cadaLibreta.getPersonaFisica() != null) {
		// if (cadaLibreta.getPersonaFisica()
		// .getIdPersonaFisica() == pDocumentoSHPS.getObligacion()
		// .getPersona().getIdPersona()) {
		// banderaPersona = true;
		// }
		// } else {
		// throw new HabilitacionesException(48);
		// }
		// }
		// if (!banderaPersona){
		// throw new HabilitacionesException(22);
		// }
		// }

		// this.entityManager.merge(pDocumentoSHPS.getDomicilio());

		// Necesito recuperar el documento viejo para saber q paso con el domicilio despues lo desatacho
		DocumentoSHPS locDocumentoViejo = (DocumentoSHPS) Criterio.getInstance(this.entityManager, DocumentoSHPS.class)
				.add(Restriccion.IGUAL("idDocHabilitanteEspecializado", pDocumentoSHPS.getIdDocHabilitanteEspecializado())).uniqueResult();

		try {
			com.trascender.habilitaciones.util.Util.getInstance(this.entityManager).deleteDomicilioExcluyente(locDocumentoViejo, pDocumentoSHPS);
		} catch(Exception e) {
			// no necesito hacer nada
		}
		System.out.println("domicilioo");
		System.out.println(pDocumentoSHPS.getDomicilio().toString());
		System.out.println(pDocumentoSHPS.getDomicilio().getIdDomicilio());

		if(pDocumentoSHPS.getDomicilio().getIdDomicilio() == -1) {
			this.entityManager.persist(pDocumentoSHPS.getDomicilio());
			this.entityManager.merge(pDocumentoSHPS.getDomicilio());
		}
		TrascenderEnverListener.setValoresEnAuditoriaBean(pDocumentoSHPS);
		this.entityManager.detach(locDocumentoViejo);
		pDocumentoSHPS.getObligacion().setDocumentoEspecializado(pDocumentoSHPS);

		this.entityManager.clear();
		pDocumentoSHPS = this.entityManager.merge(pDocumentoSHPS);
		this.entityManager.flush();

		this.validarAsocRubroShps(pDocumentoSHPS);
	}

	public Integer getNroInscripcionDocEspSHPS() {
		Long numeroInscripcion = 0l;
			try {
			Criterio locCriterio = Criterio.getInstance(this.entityManager, Obligacion.class)
					.setModoDebug(true)
					.crearAlias("documentoEspecializado", "locDocumento")
					.add(Restriccion.IGUAL("estado", Obligacion.Estado.CREADO))
					.add(Restriccion.JPQL("TYPE(locDocumento) = 'SHPS'"))
					.setProyeccion(Proyeccion.PROP("MAX(CAST(numero_inscripcion as long))").SIN_PROCESAR_ENTIDADES());
	
			numeroInscripcion = locCriterio.uniqueResult();
			if(numeroInscripcion == null){
				numeroInscripcion = 0L;
			}
			numeroInscripcion++;
		} catch (Exception e){
			e.printStackTrace();
		}
		return Integer.valueOf(numeroInscripcion.intValue());
	}

	/**
	 * @param pLocalComercial
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void addLocalComercial(LocalComercial pLocalComercial) throws Exception {
		this.validarLocalComercial(pLocalComercial);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pLocalComercial);
		this.entityManager.merge(pLocalComercial);
		this.entityManager.flush();
	}

	/**
	 * Valida que no aya un local comercial con un mimo numero de inscripcion
	 * 
	 * @param pLocalComercial
	 * @throws Exception
	 */
	private void validarLocalComercial(LocalComercial pLocalComercial) throws Exception {
		if(pLocalComercial.getIdParcela() == null) {
			throw new HabilitacionesException(64);
		}
		Criterio locCriterio = Criterio.getInstance(this.entityManager, LocalComercial.class).setProyeccion(Proyeccion.COUNT()).add(Restriccion.NULO("fechaBaja"))
				.add(Restriccion.IGUAL("numeroInscripcion", pLocalComercial.getNumeroInscripcion()));

		if(pLocalComercial.getIdLocalComercial() != -1) {
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idLocalComercial", pLocalComercial.getIdLocalComercial())));
		}

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new HabilitacionesException(59);
		}

		// validacion no puede haber 2 locales comerciales en la misma parcela
		if(((Long) Criterio.getInstance(this.entityManager, LocalComercial.class).add(Restriccion.DISTINTO("idLocalComercial", pLocalComercial.getIdLocalComercial()))
				.add(Restriccion.NOT(Restriccion.NULO("fechaBaja"))).add(Restriccion.IGUAL("idParcela", pLocalComercial.getIdParcela())).setProyeccion(Proyeccion.COUNT())
				.setModoDebug(true).uniqueResult()).longValue() > 0) {
			throw new HabilitacionesException(63);
		}

		for(InspeccionComercial cadaInspeccion : pLocalComercial.getListaInspeccionesComerciales()) {
			cadaInspeccion.setLocalComercial(pLocalComercial);
		}

	}

	/**
	 * Actualiza los datos del local comercial
	 * 
	 * @param pLocalComercial
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void updateLocalComercial(LocalComercial pLocalComercial) throws Exception {
		// for(InspeccionComercial cadaInspeccion : pLocalComercial.getListaInspeccionesComerciales()){
		// if (cadaInspeccion.getIdInspeccion() == -1){
		// this.entityManager.persist(cadaInspeccion);
		// } else {
		// this.entityManager.merge(pLocalComercial);
		// }
		// }
		this.validarLocalComercial(pLocalComercial);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pLocalComercial);
		entityManager.merge(pLocalComercial);
		entityManager.flush();
	}

	public FiltroLocalComercial findListaLocalesComerciales(FiltroLocalComercial pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, LocalComercial.class).add(Restriccion.IGUAL("numeroInscripcion", pFiltro.getNumeroInscripcion()));
		// .add(Restriccion.IGUAL("idParcela", ((pFiltro.getParcela() != null) ? pFiltro.getParcela().getIdParcela() : null)));

		if(pFiltro.getParcela() != null) {
			locCriterio.crearAlias("listaDocumentosSHPS", "cadaDocumento").add(Restriccion.IGUAL("cadaDocumento", pFiltro.getDocumentoEspecializado()))
					.add(Restriccion.IGUAL("idParcela", pFiltro.getParcela().getIdParcela()));
		} else {
			locCriterio.add(Restriccion.EN("idParcela", pFiltro.getListaIdParcelas()));
		}

		if(pFiltro.getActivo() != null) {
			locCriterio.add(((pFiltro.getActivo()) ? Restriccion.NULO("fechaBaja") : Restriccion.NOT(Restriccion.NULO("fechaBaja"))));
		} else {
			locCriterio.add(Restriccion.NULO("fechaBaja"));
		}

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, LocalComercial.serialVersionUID, "idLocalComercial", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);
		return pFiltro;
	}

	/**
	 * Valida que no haya una inspeccion comercial para un mismo local en una misma fecha
	 * 
	 * @param pInspeccionComercial
	 * @throws Exception
	 */
	private void validarInspeccionComercial(InspeccionComercial pInspeccionComercial) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, InspeccionComercial.class).add(Restriccion.IGUAL("localComercial", pInspeccionComercial.getLocalComercial()))
				.add(Restriccion.IGUAL("fecha", pInspeccionComercial.getFecha())).setProyeccion(Proyeccion.COUNT());

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new HabilitacionesException(364);
		}
	}

	/**
	 * @param pLocalComercial
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void addInspeccionComercial(InspeccionComercial pInspeccionComercial) throws Exception {
		this.validarInspeccionComercial(pInspeccionComercial);

		this.entityManager.persist(pInspeccionComercial);
	}

	/**
	 * @param pLocalComercial
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void updateInspeccionComercial(InspeccionComercial pInspeccionComercial) throws Exception {
		this.validarInspeccionComercial(pInspeccionComercial);

		this.entityManager.merge(pInspeccionComercial);
	}

	/**
	 * 
	 * @param pInspeccionComercial
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteInspeccionComercial(InspeccionComercial pInspeccionComercial) throws Exception {
		this.entityManager.getReference(InspeccionComercial.class, pInspeccionComercial);
		this.entityManager.remove(pInspeccionComercial);
	}

	/**
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public java.util.List findListaInspeccionesComerciales(Date pFechaDesde, Date pFechaHasta, LocalComercial pLocalComercial, InspeccionSHPS.Estado pEstado) throws Exception {

		return Criterio.getInstance(this.entityManager, InspeccionComercial.class).add(Restriccion.MAYOR("fecha", pFechaDesde)).add(Restriccion.MENOR("fecha", pFechaHasta))
				.add(Restriccion.IGUAL("localComercial", pLocalComercial)).add(Restriccion.IGUAL("estado", pEstado)).list();
	}

	/**
	 * @param pLocalComercial
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void addInspeccionVehicular(InspeccionVehicular pInspeccionVehicular) throws Exception {
		this.entityManager.persist(pInspeccionVehicular);
	}

	/**
	 * @param pLocalComercial
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void updateInspeccionVehicular(InspeccionVehicular pInspeccionVehicular) throws Exception {

		this.entityManager.merge(pInspeccionVehicular);
	}

	/**
	 * 
	 * @param pInspeccionComercial
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteInspeccionVehicular(InspeccionVehicular pInspeccionVehicular) throws Exception {
		this.entityManager.remove(pInspeccionVehicular);
	}

	/**
	 * Recupera un listado de inspecciones vehiculares
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param pTransporteVehicular
	 *            vehiculo al que pertenece la inspección
	 * @param pEstado
	 *            estado de la inspección
	 * @return Listado de inspecciones vehiculares que cumplen con los requisitos
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public java.util.List findListaInspeccionesVehiculares(Date pFechaDesde, Date pFechaHasta, TransporteVehicular pTransporteVehicular, InspeccionSHPS.Estado pEstado) throws Exception {

		return Criterio.getInstance(this.entityManager, TransporteVehicular.class).add(Restriccion.MAYOR("fecha", pFechaDesde)).add(Restriccion.MENOR("fecha", pFechaHasta))
				.add(Restriccion.IGUAL("transporteVehicular", pTransporteVehicular)).add(Restriccion.IGUAL("estado", pEstado)).list();
	}

	/**
	 * @param pLibretaSanitaria
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void addLibretaSanitaria(LibretaSanitaria pLibretaSanitaria) throws Exception {

		this.validarLibretaSanitaria(pLibretaSanitaria);

		this.updateColeccionesLibretaSanitaria(pLibretaSanitaria);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pLibretaSanitaria);
		this.entityManager.persist(pLibretaSanitaria);
		this.entityManager.flush();
	}

	/**
	 * Valida que no aya una libreta sanitaria con un mismo numero
	 * 
	 * @param pLibretaSanitaria
	 * @throws Exception
	 */
	private void validarLibretaSanitaria(LibretaSanitaria pLibretaSanitaria) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, LibretaSanitaria.class).setProyeccion(Proyeccion.COUNT());

		locCriterio.add(Restriccion.IGUAL("numeroLibretaSanitaria", pLibretaSanitaria.getNumeroLibretaSanitaria())).add(Restriccion.IGUAL("activo", new Boolean(true)));

		if(pLibretaSanitaria.getIdLibretaSanitaria() != -1) {
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idLibretaSanitaria", pLibretaSanitaria.getIdLibretaSanitaria())));
		}

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new HabilitacionesException(47);
		}

		if(((Long) Criterio.getInstance(this.entityManager, LibretaSanitaria.class).add(Restriccion.IGUAL("personaFisica", pLibretaSanitaria.getPersonaFisica()))
				.add(Restriccion.NOT(Restriccion.IGUAL("idLibretaSanitaria", pLibretaSanitaria.getIdLibretaSanitaria()))).add(Restriccion.DISTINTO("activo", new Boolean(false)))
				.setProyeccion(Proyeccion.COUNT()).uniqueResult()) > 0) {
			throw new HabilitacionesException(62);
		}

	}

	/**
	 * @param pLibretaSanitaria
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void updateLibretaSanitaria(LibretaSanitaria pLibretaSanitaria) throws Exception {
		this.validarLibretaSanitaria(pLibretaSanitaria);

		this.updateColeccionesLibretaSanitaria(pLibretaSanitaria);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pLibretaSanitaria);
		this.entityManager.merge(pLibretaSanitaria);
		this.entityManager.flush();
	}

	/**
	 * Setea la libreta sanitaria a todas las dependencias que sean necesarias
	 * 
	 * @param pLibretaSanitaria
	 */
	private void updateColeccionesLibretaSanitaria(LibretaSanitaria pLibretaSanitaria) {

		for(ConstanciaVacunacion locConstancia : pLibretaSanitaria.getListaConstanciasVacunaciones()) {
			locConstancia.setLibretaSanitaria(pLibretaSanitaria);
		}

		for(InhabilitacionTemporariaLS locInhabilitacion : pLibretaSanitaria.getListaInhabilitacionesTemporarias()) {
			locInhabilitacion.setLibretaSanitaria(pLibretaSanitaria);
		}

		for(RenovacionLibretaSanitaria locRenovacion : pLibretaSanitaria.getListaRenovaciones()) {
			locRenovacion.setLibretaSanitaria(pLibretaSanitaria);
		}
	}

	/**
	 * @param pLibretaSanitaria
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteLibretaSanitaria(LibretaSanitaria pLibretaSanitaria) throws Exception {
		pLibretaSanitaria.setActivo(false);
		this.updateLibretaSanitaria(pLibretaSanitaria);
	}

	/**
	 * @param pPersonaFisica
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroLibretaSanitaria findListaLibretasSanitarias(FiltroLibretaSanitaria pFiltro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, LibretaSanitaria.class).add(Restriccion.IGUAL("numeroLibretaSanitaria", pFiltro.getNumeroLibretaSanitaria()))
				.add(Restriccion.IGUAL("personaFisica", pFiltro.getPersonaFisica())).add(Restriccion.IGUAL("activo", pFiltro.isEstado()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, LibretaSanitaria.serialVersionUID, "idLibretaSanitaria", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		return pFiltro;
	}

	/**
	 * Agrega un transporte vehicular nuevo
	 * 
	 * @param pTransporteVehicular
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void addTransporteVehicular(TransporteVehicular pTransporteVehicular) throws Exception {

		this.validarTransporteVehicular(pTransporteVehicular);

		if(pTransporteVehicular.getFechaAlta() == null) {
			pTransporteVehicular.setFechaAlta(Calendar.getInstance().getTime());
		}

		TrascenderEnverListener.setValoresEnAuditoriaBean(pTransporteVehicular);
		this.entityManager.persist(pTransporteVehicular);
		this.entityManager.flush();
	}

	private void validarTransporteVehicular(TransporteVehicular pTransporteVehicular) throws HabilitacionesException {

		Criterio locCriterio = Criterio.getInstance(entityManager, TransporteVehicular.class);

		Long locResultado = (Long) locCriterio.add(Restriccion.IGUAL("numeroInscripcion", pTransporteVehicular.getNumeroInscripcion())).add(Restriccion.NULO("fechaBaja"))
				.add(Restriccion.NOT(Restriccion.IGUAL("idTransporteVehicular", pTransporteVehicular.getIdTransporteVehicular()))).setProyeccion(Proyeccion.COUNT()).uniqueResult();
		if(locResultado > 0) {
			throw new HabilitacionesException(60);
		}

	}

	/**
	 * Actualiza en la base de datos un transporte vehicular
	 * 
	 * @param pTransporteVehicular
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void updateTransporteVehicular(TransporteVehicular pTransporteVehicular) throws Exception {

		for(InspeccionVehicular cadaInspeccion : pTransporteVehicular.getListaInspeccionesVehiculares()) {
			if(cadaInspeccion.getIdInspeccion() == -1) {
				entityManager.persist(cadaInspeccion);
			} else {
				entityManager.merge(cadaInspeccion);
			}
		}
		this.validarTransporteVehicular(pTransporteVehicular);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pTransporteVehicular);
		this.entityManager.merge(pTransporteVehicular);
		this.entityManager.flush();
	}

	/**
	 * Da de baja un transporte vehicular
	 * 
	 * @param pTransporteVehicular
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteTransporteVehicular(com.trascender.habilitaciones.recurso.persistent.shps.TransporteVehicular pTransporteVehicular) throws Exception {
		if(pTransporteVehicular.getFechaBaja() == null) {
			pTransporteVehicular.setFechaBaja(Calendar.getInstance().getTime());
		}
		TrascenderEnverListener.setValoresEnAuditoriaBean(pTransporteVehicular);
		this.entityManager.merge(pTransporteVehicular);
		this.entityManager.flush();
	}

	/**
	 * Da de baja un local comercial
	 * 
	 * @param pLocalComercial
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteLocalComercial(LocalComercial pLocalComercial) throws Exception {
		if(pLocalComercial.getFechaBaja() == null) {
			pLocalComercial.setFechaBaja(Calendar.getInstance().getTime());
		}

		this.validarAsociacionesLocalComercial(pLocalComercial);
		this.updateLocalComercial(pLocalComercial);
	}

	private void validarAsociacionesLocalComercial(LocalComercial pLocalComercial) throws Exception {
		if(((Long) Criterio.getInstance(this.entityManager, DocumentoSHPS.class).crearAlias("listaLocalesComerciales", "cadaLocal").add(Restriccion.IGUAL("cadaLocal", pLocalComercial))
				.setProyeccion(Proyeccion.COUNT()).setModoDebug(true).uniqueResult()) > 0) {
			throw new HabilitacionesException(61);
		}

	}

	/**
	 * Crea un nuevo tranporte vehicular a partir del anterior dado de baja, para mantener histórico
	 * 
	 * @param pTransporteVehicular
	 *            Transporte vehicular a agregar
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public TransporteVehicular restoreTransporteVehicular(TransporteVehicular pTransporteVehicular) throws Exception {
		TransporteVehicular locTransporteVehicular = new TransporteVehicular();
		locTransporteVehicular.setDescripcion(pTransporteVehicular.getDescripcion());
		locTransporteVehicular.setFechaAlta(Calendar.getInstance().getTime());
		locTransporteVehicular.setNumeroInscripcion(pTransporteVehicular.getNumeroInscripcion());
		locTransporteVehicular.setVehiculo(pTransporteVehicular.getVehiculo());
		locTransporteVehicular.setListaDocumentosSHPS(pTransporteVehicular.getListaDocumentosSHPS());
		this.addTransporteVehicular(locTransporteVehicular);
		return locTransporteVehicular;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroTransporteVehicular findListaTransportesVehiculares(FiltroTransporteVehicular pFiltro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, TransporteVehicular.class).add(Restriccion.ILIKE("numeroInscripcion", pFiltro.getNumeroInscripcion()))
				.add(Restriccion.IGUAL("vehiculo", pFiltro.getVehiculo())).add(((pFiltro.getActivo()) ? Restriccion.NULO("fechaBaja") : Restriccion.NOT(Restriccion.NULO("fechaBaja"))));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, TransporteVehicular.serialVersionUID, "idTransporteVehicular", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		return pFiltro;
	}

	/**
	 * 
	 * @param pObligacion
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public DocumentoSHPS getDocumentoHabilitanteSHPS(Obligacion pObligacion) throws Exception {
		DocumentoSHPS locDocumento = (DocumentoSHPS) Criterio.getInstance(this.entityManager, DocumentoSHPS.class).add(Restriccion.IGUAL("obligacion", pObligacion)).uniqueResult();

		locDocumento.toString();
		locDocumento.getListaLogsModificaciones().size();
		locDocumento.getDomicilio().getDomicilioCompleto().toString();

		for(ClausuraSHPS cadaClausura : locDocumento.getListaClausuras()) {
			cadaClausura.toString();
		}

		for(LocalComercial cadaLocalComercial : locDocumento.getListaLocalesComerciales()) {
			cadaLocalComercial.toString();
		}

		for(LogModificaciones cadaModif : locDocumento.getListaModificaciones()) {
			cadaModif.toString();
		}

		for(RegistroValuado cadaRegistroValuado : locDocumento.getListaRegistrosValuados()) {
			cadaRegistroValuado.toString();
		}

		for(TransporteVehicular cadaTransporteVehicular : locDocumento.getListaTransportesVehiculares()) {
			cadaTransporteVehicular.toString();
		}
		for(RegAlicuota cadaRegAlicuota : locDocumento.getListaRegAlicuotas()) {
			cadaRegAlicuota.toString();
		}
		for(LibretaSanitaria cadaLibreta : locDocumento.getListaLibretaSanitarias()) {
			cadaLibreta.toString();
		}
		locDocumento.getObligacion().getPersona().getDenominacion();
		if(locDocumento.getObligacion().getPersona().getDomicilioPostal() != null) {
			locDocumento.getObligacion().getPersona().getDomicilioPostal().toString();
		}
		if(locDocumento.getObligacion().getPersona().getDomicilio() != null) {
			locDocumento.getObligacion().getPersona().getDomicilio().toString();
		}

		locDocumento.getListaAtributosDinamicos().size();
		locDocumento.getListaLogsAuditoria().size();
		if(locDocumento.getContador() != null)
			locDocumento.getContador().toString();

		return locDocumento;
	}

	/**
	 * Recupera una libreta sanitaria por el id
	 * 
	 * @param pId
	 *            id de la libreta sanitaria
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public LibretaSanitaria getLibretaSanitariaPorId(long pId) throws Exception {

		LibretaSanitaria locLibretaSanitaria = (LibretaSanitaria) Criterio.getInstance(this.entityManager, LibretaSanitaria.class).add(Restriccion.IGUAL("idLibretaSanitaria", pId))
				.uniqueResult();

		if(locLibretaSanitaria != null) {
			locLibretaSanitaria.toString();
			locLibretaSanitaria.getPersonaFisica().toString();
			locLibretaSanitaria.getListaLogsAuditoria().size();

			for(ConstanciaVacunacion cadaConstanciaVacunacion : locLibretaSanitaria.getListaConstanciasVacunaciones()) {
				cadaConstanciaVacunacion.toString();
			}

			for(InhabilitacionTemporariaLS cadaInhabilitacion : locLibretaSanitaria.getListaInhabilitacionesTemporarias()) {
				cadaInhabilitacion.toString();
			}

			for(RenovacionLibretaSanitaria cadaRenovacion : locLibretaSanitaria.getListaRenovaciones()) {
				cadaRenovacion.toString();
			}
		}

		return locLibretaSanitaria;
	}

	/**
	 * Agrega un inspector al sistema
	 * 
	 * @param pInspector
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void addInspector(Inspector pInspector) throws Exception {
		TrascenderEnverListener.setValoresEnAuditoriaBean(pInspector);
		this.entityManager.persist(pInspector);
		this.entityManager.flush();
	}

	/**
	 * Actualiza un inspector
	 * 
	 * @param pInspector
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void updateInspector(Inspector pInspector) throws Exception {
		TrascenderEnverListener.setValoresEnAuditoriaBean(pInspector);
		this.entityManager.merge(pInspector);
		this.entityManager.flush();
	}

	/**
	 * Intenta eliminar físicamente un inspector, en caso que no tenga ninguna inspección relacionada
	 * 
	 * @param pInspector
	 *            inspector asociado
	 * @throws Exception
	 */
	public void deleteInspector(Inspector pInspector) throws Exception {
		if(pInspector == null) {
			throw new HabilitacionesException(365);
		}
		if(((Long) Criterio.getInstance(this.entityManager, InspeccionSHPS.class).add(Restriccion.IGUAL("inspector", pInspector)).setProyeccion(Proyeccion.COUNT()).uniqueResult()) > 0) {
			throw new HabilitacionesException(54);
		}

		pInspector = (Inspector) Criterio.getInstance(this.entityManager, Inspector.class).add(Restriccion.IGUAL("idInspector", pInspector.getIdInspector())).uniqueResult();

		this.entityManager.remove(pInspector);
	}

	public FiltroInspector findListaInspectores(FiltroInspector pFiltro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, Inspector.class).add(Restriccion.LIKE("nombre", pFiltro.getNombre(), false, Posicion.AL_PRINCIPIO))
				.add(Restriccion.IGUAL("persona", pFiltro.getPersona()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Inspector.serialVersionUID, "idInspector", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		return pFiltro;
	}

	public Inspector getInspectorPorId(long pId) throws Exception {
		Inspector locInspector = (Inspector) Criterio.getInstance(this.entityManager, Inspector.class).add(Restriccion.IGUAL("idInspector", pId)).uniqueResult();

		locInspector.toString();
		locInspector.getPersona().toString();
		locInspector.getListaLogsAuditoria().size();
		return locInspector;
	}

	/**
	 * Valida si esta firmado y setea la clausura activa o inactiva
	 * 
	 * @param pClausuraSHPS
	 */
	private void comprobarClausura(ClausuraSHPS pClausuraSHPS) {
		if(pClausuraSHPS.getFirma() == null) {
			pClausuraSHPS.setClausuraActiva(true);
		} else {
			pClausuraSHPS.setClausuraActiva(false);
		}

	}

	/**
	 * 
	 * @param pClausuraSHPS
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public ClausuraSHPS addClausuraSHPS(ClausuraSHPS pClausuraSHPS) throws Exception {
		this.comprobarClausura(pClausuraSHPS);

		this.entityManager.persist(pClausuraSHPS);

		if(pClausuraSHPS.isClausuraActiva()) {
			this.updateEstadoObligacionPorClausura(pClausuraSHPS);
		}

		this.entityManager.flush();

		return pClausuraSHPS;
	}

	/**
	 * Verifica una clausura y actualiza el estado de la obligacion a inhabilitado
	 * 
	 * @param pClausura
	 * @throws Exception
	 */
	private void updateEstadoObligacionPorClausura(ClausuraSHPS pClausura) throws Exception {
		if(pClausura.getDocumentoSHPS() == null) {
			throw new HabilitacionesException(58);
		}

		Obligacion locObligacion = (Obligacion) Criterio.getInstance(this.entityManager, Obligacion.class)
				.add(Restriccion.IGUAL("documentoEspecializado.idDocHabilitanteEspecializado", pClausura.getDocumentoSHPS().getIdDocHabilitanteEspecializado())).uniqueResult();

		locObligacion.toString();
		locObligacion.getDocumentoEspecializado().toString();

		DocumentoSHPS locDocumentoSHPS = (DocumentoSHPS) locObligacion.getDocumentoEspecializado();

		boolean isClausuraActiva = false;

		if(!locDocumentoSHPS.getListaClausuras().contains(pClausura)) {
			locDocumentoSHPS.getListaClausuras().add(pClausura);
		}

		Iterator<ClausuraSHPS> locIterador = locDocumentoSHPS.getListaClausuras().iterator();

		while((locIterador.hasNext()) && (!isClausuraActiva)) {
			ClausuraSHPS locClausura = locIterador.next();
			if(locClausura.isClausuraActiva()) {
				isClausuraActiva = true;
			}
		}

		if(isClausuraActiva) {
			locObligacion.inhabilitar();
		} else {
			locObligacion.habilitar();
		}

		this.businessObligacionLocal.updateObligacion(locObligacion);
	}

	/**
	 * Actualiza los datos de una clausura y de esa manera ejecuta la actualización de la obligacion (verifica los estados)
	 * 
	 * @param pClausuraSHPS
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public ClausuraSHPS updateClausuraSHPS(ClausuraSHPS pClausuraSHPS) throws Exception {

		this.comprobarClausura(pClausuraSHPS);

		this.entityManager.merge(pClausuraSHPS);

		return pClausuraSHPS;
	}

	/**
	 * Recupera un listado de clausuras
	 * 
	 * @param pFechaAltaDesde
	 *            fecha de alta desde la cual filtrar el listado
	 * @param pFechaAltaHasta
	 *            fecha de alta hasta la cual filtrar el listado
	 * @param pFechaBajaDesde
	 *            fecha de baja desde la cual filtrar el listado
	 * @param pFechaBajaHasta
	 *            fecha de baja hasta la cual filtrar el listado
	 * @param isActiva
	 *            si la clausura se encuentra activa o no
	 * @param pDocumentoSHPS
	 *            documento al que pertenecen las clausuras
	 * @return Listado de clausuras
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public java.util.List findListaClausurasSHPS(java.util.Date pFechaAltaDesde, java.util.Date pFechaAltaHasta, java.util.Date pFechaBajaDesde, java.util.Date pFechaBajaHasta,
			Boolean isActiva, DocumentoSHPS pDocumentoSHPS) throws Exception {

		List locListaClausura = Criterio.getInstance(this.entityManager, ClausuraSHPS.class)

		.add(Restriccion.MAYOR("fechaAlta", pFechaAltaDesde)).add(Restriccion.MENOR("fechaAlta", pFechaAltaHasta)).add(Restriccion.MAYOR("fechaBaja", pFechaBajaDesde))
				.add(Restriccion.MENOR("fechaBaja", pFechaBajaHasta)).add(Restriccion.IGUAL("clausuraActiva", isActiva)).add(Restriccion.IGUAL("documentoSHPS", pDocumentoSHPS))

				.list();
		for(Object object : locListaClausura) {
			ClausuraSHPS cadaClausuraSHPS = (ClausuraSHPS) object;
			cadaClausuraSHPS.getDocumentoSHPS().toString();
		}
		return locListaClausura;
	}

	/**
	 * @param pId
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public TransporteVehicular getTransporteVehicularPorId(long pId) throws Exception {
		TransporteVehicular locTransporteVehicular = (TransporteVehicular) Criterio.getInstance(this.entityManager, TransporteVehicular.class)
				.add(Restriccion.IGUAL("idTransporteVehicular", pId)).uniqueResult();

		locTransporteVehicular.toString();

		for(DocumentoSHPS cadaDocumento : locTransporteVehicular.getListaDocumentosSHPS()) {
			cadaDocumento.toString();
		}

		for(InspeccionVehicular cadaInspeccion : locTransporteVehicular.getListaInspeccionesVehiculares()) {
			cadaInspeccion.toString();
		}

		locTransporteVehicular.getListaLogsAuditoria().size();

		return locTransporteVehicular;
	}

	/**
	 * @param pId
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public LocalComercial getLocalComercialPorId(long pId) throws Exception {

		LocalComercial locLocalComercial = (LocalComercial) Criterio.getInstance(this.entityManager, LocalComercial.class).add(Restriccion.IGUAL("idLocalComercial", pId))
				.uniqueResult();

		locLocalComercial.toString();

		for(DocumentoSHPS cadaDocumento : locLocalComercial.getListaDocumentosSHPS()) {
			cadaDocumento.toString();
		}

		for(InspeccionComercial cadaInspeccion : locLocalComercial.getListaInspeccionesComerciales()) {
			cadaInspeccion.toString();
		}

		locLocalComercial.getListaLogsAuditoria().size();

		return locLocalComercial;
	}

	/**
	 * 
	 * @param pUsuario
	 * @param pClausura
	 * @param pComentario
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public FirmaPermiso firmarClausura(Usuario pUsuario, ClausuraSHPS pClausura, String pComentario) throws Exception {

		FirmaPermiso locFirma = this.businessUsuarioLocal.firmar(pUsuario, pComentario);
		pClausura.setFirma(locFirma);
		pClausura.setClausuraActiva(false);

		if(pClausura.getFechaBaja() == null) {
			pClausura.setFechaBaja(Calendar.getInstance().getTime());
		}

		this.updateClausuraSHPS(pClausura);

		return locFirma;
	}

	/**
	 * Listado de las clausuras por usuario
	 * 
	 * @param pUsuario
	 * @param pEstado
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public java.util.List findListaClausurasPorUsuario(Usuario pUsuario, Boolean pEstado) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, ClausuraSHPS.class);

		if(pUsuario.getListaRoles() != null && !pUsuario.getListaRoles().isEmpty()) {
			locCriterio.add(Restriccion.EN("rol", pUsuario.getListaRoles()));
		} else
			return new ArrayList();

		locCriterio.add(Restriccion.IGUAL("clausuraActiva", pEstado));

		List locListaClausuraSHPS = locCriterio.list();

		for(Object object : locListaClausuraSHPS) {
			ClausuraSHPS cadaClausuraSHPS = (ClausuraSHPS) object;
			cadaClausuraSHPS.getDocumentoSHPS().toString();
		}

		return locListaClausuraSHPS;
	}

	/**
	 * Listado de las clausuras por usuario
	 * 
	 * @param pPersona
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public java.util.List findListaClausurasPorUsuario(com.trascender.framework.recurso.persistent.Usuario pUsuario) throws Exception {
		return this.findListaClausurasPorUsuario(pUsuario, null);
	}

	/**
	 * 
	 * @param pPersona
	 * @param pNumeroInscripcion
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroObligacionSHPS findListaObligacionesSHPS(FiltroObligacionSHPS pFiltro) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, Obligacion.class).crearAlias("documentoEspecializado", "locDocumento")
				.add(Restriccion.JPQL("TYPE (locDocumento) = ".concat(DocumentoSHPS.class.getSimpleName())));

		AtributoDinamico.addRestriccionesCriterio(locCriterio, DocumentoSHPS.serialVersionUID, "idDocHabilitanteEspecializado", "locDocumento", pFiltro.getListaAtributosDinamicos());
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, DocumentoSHPS.serialVersionUID, "idDocHabilitanteEspecializado", pFiltro.getListaBusquedaPorLogs(), "locDocumento");

		locCriterio.add(Restriccion.IGUAL("persona", pFiltro.getPersona())).add(Restriccion.ILIKE("locDocumento.numeroInscripcion", pFiltro.getNumeroInscripcion()))
				.add(Restriccion.IGUAL("estado", pFiltro.getEstado())).add(Restriccion.IGUAL("persona.cuim", pFiltro.getCuim()))
				.add(Restriccion.IGUAL("locDocumento.contador", pFiltro.getContador())).setModoDebug(true);

		if(pFiltro.getPoseeExenciones() != null) {
			if(!pFiltro.getPoseeExenciones()) {
				locCriterio.add(Restriccion.ESTA_VACIO("listaRegistrosExencion"));
			} else {
				locCriterio.add(Restriccion.NOT(Restriccion.ESTA_VACIO("listaRegistrosExencion")));
			}
		}

		pFiltro.procesarYListar(locCriterio);

		for(Obligacion cadaObligacion : pFiltro.getListaResultados()) {
			cadaObligacion.getPersona().toString();
			cadaObligacion.toString();
			cadaObligacion.getDocumentoEspecializado().toString();
			cadaObligacion.getStringListaRegAlicuotas().toString();
			if(cadaObligacion.getDocumentoEspecializado().getDomicilio() != null) {
				cadaObligacion.getDocumentoEspecializado().getDomicilio().toString();
			}

		}

		return pFiltro;
	}

	private void validarAsocRubroShps(DocumentoSHPS pDocShps) {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, AsocRubro.class)
				.add(Restriccion.IGUAL("docHabilitanteEspecializado.idDocHabilitanteEspecializado", pDocShps.getIdDocHabilitanteEspecializado()))
				.add(Restriccion.NOT(Restriccion.EN("registroAlicuota", pDocShps.getListaRegAlicuotas())));
		locCriterio.setModoDebug(true);

		for(Object cadaObj : locCriterio.list()) {
			entityManager.remove(cadaObj);
		}
	}

}
