
package com.trascender.habilitaciones.business.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.LazyInitializationException;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Orden;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.RegistroPropietario;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.system.interfaces.SystemUsuario;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoOSPLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoSHPSLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessObligacionLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.exception.ObligacionTGIExistenteException;
import com.trascender.habilitaciones.recurso.persistent.AsocRegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.DocHabCompuesto;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitante;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion.Estado;
import com.trascender.habilitaciones.recurso.persistent.PermisoHab;
import com.trascender.habilitaciones.recurso.persistent.Sellado;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.cementerio.DocumentoCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.osp.AsocServicioOsp;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.pfo.CuadraAfectada;
import com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra;
import com.trascender.habilitaciones.recurso.persistent.shps.AsocRubro;
import com.trascender.habilitaciones.recurso.persistent.shps.ClausuraSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.shps.LocalComercial;
import com.trascender.habilitaciones.recurso.persistent.shps.LogModificaciones;
import com.trascender.habilitaciones.recurso.persistent.shps.TransporteVehicular;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.DocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor;
import com.trascender.habilitaciones.recurso.persistent.transito.Vehiculo;
import com.trascender.habilitaciones.util.Util;

@Stateless(name = "ejb/BusinessObligacionLocal")
public class BusinessObligacionBean implements BusinessObligacionLocal {

	@EJB
	private BusinessDocumentoSHPSLocal businessDocumentoSHPS;

	@EJB
	private BusinessDocumentoOSPLocal businessDocumentoOSP;

	private static final long serialVersionUID = 4944764326743310114L;

	static {
		Grupo locGrupo = new Grupo();
		locGrupo.setNombre("HAB|Adm. de Obligaciones");
		locGrupo.setId(serialVersionUID);

		Recurso locObligacion = new Recurso();
		locObligacion.setNombre("Habilitar Obligación");
		locObligacion.setIdRecurso(Obligacion.serialVersionUID);
		locObligacion.setClase(Obligacion.class);

		Recurso locSellado = new Recurso();
		locSellado.setIdRecurso(Sellado.serialVersionUID);
		locSellado.setNombre("Sellado");
		locSellado.setClase(Sellado.class);

		Recurso locPermiso = new Recurso();
		locPermiso.setIdRecurso(PermisoHab.serialVersionUID);
		locPermiso.setNombre("Permiso de Habilitación");
		locPermiso.setClase(PermisoHab.class);

		Recurso locDocHabCompuesto = new Recurso();
		locDocHabCompuesto.setIdRecurso(DocHabCompuesto.serialVersionUID);
		locDocHabCompuesto.setNombre("Documento Habilitante Compuesto");
		locDocHabCompuesto.setClase(DocHabCompuesto.class);

		locGrupo.getListaRecursos().add(locObligacion);
		locGrupo.getListaRecursos().add(locSellado);
		locGrupo.getListaRecursos().add(locPermiso);
		locGrupo.getListaRecursos().add(locDocHabCompuesto);

		SecurityMgr.getInstance().addGrupo(locGrupo);
	}

	public BusinessObligacionBean() {
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

	@EJB
	private SystemUsuario systemUsuario;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Agrega una obligación al sistema
	 * 
	 * @param pObligacion
	 *            Obligación a agregar
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void addObligacion(Obligacion pObligacion) throws Exception {

		if((!pObligacion.getEstado().equals(Obligacion.Estado.PENDIENTE_DE_ALTA)) && (pObligacion.getPersona() == null)) {
			throw new HabilitacionesException(370);
		}
		for(DocHabilitante locDocHabilitante : pObligacion.getListaDocumentosHabilitantes()) {
			locDocHabilitante.setObligacion(pObligacion);
			this.verificarDocHabilitante(locDocHabilitante);
		}

		pObligacion.getDocumentoEspecializado().setObligacion(pObligacion);

		this.saveDocumentoHabiltanteEspecializado(pObligacion.getDocumentoEspecializado());

		if(pObligacion.getNumeroTramite() == 0) {
			pObligacion.setNumeroTramite(Util.getInstance(this.entityManager).getNumeroTramite());
			System.out.println("Agregando Obligacion con nro tramite: " + pObligacion.getNumeroTramite());
		}

		// this.entityManager.setFlushMode(FlushModeType.COMMIT);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pObligacion.getDocumentoEspecializado());
		DocHabilitanteEspecializado locDocumentoMergeado = this.entityManager.merge(pObligacion.getDocumentoEspecializado());
		locDocumentoMergeado.postPersist();
		this.entityManager.flush();
	}

	/**
	 * Verifica el tipo de documento especializado y realiza la lógica específica de cada uno
	 * 
	 * @param pSess
	 *            sesión a la que está asociado el documento
	 * @param pDocEspecializado
	 *            Documento a analizar
	 * @throws Exception
	 */
	private void saveDocumentoHabiltanteEspecializado(DocHabilitanteEspecializado pDocEspecializado) throws Exception {
		if(pDocEspecializado != null) {
			if(pDocEspecializado.getFechaCreacion() == null) {
				pDocEspecializado.setFechaCreacion(Calendar.getInstance().getTime());
			}

			// LÓGICA ESPECIAL DE CADA DOCUMENTO ESPECIALIZADO
			if(pDocEspecializado instanceof DocumentoSHPS) {
				DocumentoSHPS locDocumentoSHPS = (DocumentoSHPS) pDocEspecializado;
				this.saveDocumentoSHPS(locDocumentoSHPS);
			} else if(pDocEspecializado instanceof DocumentoPlanObra) {
				DocumentoPlanObra locDocumentoPlanObra = (DocumentoPlanObra) pDocEspecializado;
				this.saveDocumentoPlanObra(locDocumentoPlanObra);
			} else if(pDocEspecializado instanceof DocumentoOSP) {
				DocumentoOSP locDocumentoOSP = (DocumentoOSP) pDocEspecializado;
				this.saveDocumentoOSP(locDocumentoOSP);
			} else if(pDocEspecializado instanceof DocumentoTGI) {
				DocumentoTGI locDocumentoTGI = (DocumentoTGI) pDocEspecializado;
				this.verificarDocumentoTGI(locDocumentoTGI);
				this.saveDocumentoTGI(locDocumentoTGI);
			} else if(pDocEspecializado instanceof DocumentoTasaMenor) {
				DocumentoTasaMenor locDocumentoTasaMenor = (DocumentoTasaMenor) pDocEspecializado;
				this.saveDocumentoTasaMenor(locDocumentoTasaMenor);
			} else if(pDocEspecializado instanceof DocumentoCementerio) {
				DocumentoCementerio locDocumentoCementerio = (DocumentoCementerio) pDocEspecializado;
				this.validarParcelasCementerio(locDocumentoCementerio);
			} else if(pDocEspecializado instanceof DocumentoAutomotor) {
				DocumentoAutomotor locDocumentoAutomotor = (DocumentoAutomotor) pDocEspecializado;
				this.saveDocumentoAutomotor(locDocumentoAutomotor);
			}
		}

	}

	private void saveDocumentoAutomotor(DocumentoAutomotor pDocumentoAutomotor) throws TrascenderException {
		Long cantidad = Criterio.getInstance(entityManager, DocumentoAutomotor.class).add(Restriccion.IGUAL("vehiculo", pDocumentoAutomotor.getVehiculo()))
				.add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO))
				.add(Restriccion.DISTINTO("idDocHabilitanteEspecializado", pDocumentoAutomotor.getIdDocHabilitanteEspecializado())).setProyeccion(Proyeccion.COUNT()).uniqueResult();

		if(cantidad > 0) {
			throw new HabilitacionesException(398);
		}
	}

	private void saveDocumentoTasaMenor(DocumentoTasaMenor pDocumentoTasaMenor) throws Exception {
		Long cantidad = Criterio.getInstance(this.entityManager, DocumentoTasaMenor.class)
				.add(Restriccion.DISTINTO("idDocHabilitanteEspecializado", pDocumentoTasaMenor.getIdDocHabilitanteEspecializado()))
				.add(Restriccion.DISTINTO("obligacion.estado", Estado.ANULADO)).add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO))
				.add(Restriccion.IGUAL("obligacion.persona", pDocumentoTasaMenor.getObligacion().getPersona())).add(Restriccion.IGUAL("parcela", pDocumentoTasaMenor.getParcela()))
				.add(Restriccion.IGUAL("plantillaDocumentoTasaMenor", pDocumentoTasaMenor.getPlantillaDocumentoTasaMenor())).setProyeccion(Proyeccion.COUNT()).uniqueResult();

		if(cantidad.longValue() > 0) {
			throw new HabilitacionesException(726);
		}
	}

	/**
	 * Verifica si ya existe una obligacion TGI para la parcela
	 * 
	 * @param pSess
	 * @param pDocumentoTGI
	 * @throws HabilitacionesException
	 */
	private void verificarDocumentoTGI(DocumentoTGI pDocumentoTGI) throws HabilitacionesException {
		if(pDocumentoTGI.getEstado().equals(DocHabilitanteEspecializado.Estado.ACTIVO)) {

			Criterio loccCriterio = Criterio.getInstance(this.entityManager, DocumentoTGI.class)
					.add(Restriccion.NOT(Restriccion.IGUAL("idDocHabilitanteEspecializado", pDocumentoTGI.getIdDocHabilitanteEspecializado()))).setMaxResults(1);

			if(pDocumentoTGI != null) {
				loccCriterio.add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO));

				if(pDocumentoTGI.getParcela() != null) {
					loccCriterio.crearAlias("parcela", "locParcela");
					loccCriterio.add(Restriccion.IGUAL("locParcela.idParcela", pDocumentoTGI.getParcela().getIdParcela()));
				}
			}

			List locListaDocumentosTGI = loccCriterio.list();

			if(locListaDocumentosTGI != null && !locListaDocumentosTGI.isEmpty()) {
				// con el primer elemento de la lista es suficiente
				DocumentoTGI locDocumentoTGI = (DocumentoTGI) locListaDocumentosTGI.get(0);
				throw new ObligacionTGIExistenteException(locDocumentoTGI);
			}
		}
	}

	/**
	 * Setea todos los valores de las colecciones de los documentos de OySP
	 * 
	 * @param pSess
	 * @param pDocumentoOSP
	 * @throws Exception
	 */
	private void saveDocumentoOSP(DocumentoOSP pDocumentoOSP) throws Exception {
		this.entityManager.clear();
		Criterio locCriterio = Criterio.getInstance(this.entityManager, DocumentoOSP.class).add(Restriccion.IGUAL("numeroCuenta", pDocumentoOSP.getNumeroCuenta()))
				.add(Restriccion.IGUAL("numeroSubCuenta", pDocumentoOSP.getNumeroSubCuenta()))
				.add(Restriccion.NOT(Restriccion.IGUAL("idDocHabilitanteEspecializado", pDocumentoOSP.getIdDocHabilitanteEspecializado())))
				.add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO)).setProyeccion(Proyeccion.COUNT());

		// Primero valido
		if((Long) locCriterio.uniqueResult() > 0) {
			if(pDocumentoOSP.getIdDocHabilitanteEspecializado() != -1) {
				throw new HabilitacionesException(24);
			} else {
				pDocumentoOSP.setNumeroCuenta(businessDocumentoOSP.getSugerenciaNumeroCuenta());
			}
		}

		this.validarAsocServicioOsp(pDocumentoOSP);

		List<String> locListaCodigos = new ArrayList<String>();
		for(AsocRegAlicuota cadaAsocRegAlic : pDocumentoOSP.getListaAsocRegAlicuota()) {
			ServicioOSP cadaServicio = (ServicioOSP) cadaAsocRegAlic.getRegistroAlicuota();
			if(cadaServicio.isMedido()) {
				locListaCodigos.add(cadaServicio.getCodigo());
			}
		}

		if(!locListaCodigos.isEmpty()) {
			locCriterio = Criterio.getInstance(this.entityManager, DocumentoOSP.class)
			// .add(Restriccion.IGUAL("codigoMedidor", pDocumentoOSP.getCodigoMedidor()))
			// .add(Restriccion.EN("codigoMedidor", locListaCodigos)) [!]
					.add(Restriccion.NOT(Restriccion.IGUAL("idDocHabilitanteEspecializado", pDocumentoOSP.getIdDocHabilitanteEspecializado())))
					// .add(Restriccion.NOT(Restriccion.NULO("codigoMedidor"))) [!]
					.add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO)).setProyeccion(Proyeccion.COUNT());

			// if((Long)locCriterio.uniqueResult() > 0){ [!]
			// throw new HabilitacionesException(25); [!]
			// }
		}
		locListaCodigos = null;

		// Verifica si la parcela ya tiene una obligacion asociada.
		Criterio locQuery = Criterio.getInstance(this.entityManager, DocumentoOSP.class).setModoDebug(true)
				.add(Restriccion.IGUAL("parcela", pDocumentoOSP.getParcela()))
				// .add(Restriccion.CONTIENE("listaAsocRegAlicuota", pDocumentoOSP.getListaAsocRegAlicuota(), Contiene.ALGUNO))
				.add(Restriccion.NOT(Restriccion.IGUAL("idDocHabilitanteEspecializado", pDocumentoOSP.getIdDocHabilitanteEspecializado())))
				.add(Restriccion.OR(Restriccion.IGUAL("obligacion.estado", Obligacion.Estado.CREADO), Restriccion.IGUAL("obligacion.estado", Obligacion.Estado.PENDIENTE_DE_ALTA)))
				.setProyeccion(Proyeccion.COUNT());

		if(((Long) locQuery.uniqueResult()) > 0) {
			throw new HabilitacionesException(27);
		}
	}

	/**
	 * Setea todos los valores de las colecciones de los documentos de TGI
	 * 
	 * @param pSess
	 * @param pDocumentoTGI
	 * @throws Exception
	 */
	private void saveDocumentoTGI(DocumentoTGI pDocumentoTGI) throws Exception {
	}

	/**
	 * Setea los valores de las colecciones del documento plan obra
	 * 
	 * @param pSess
	 * @param pDocumentoPlanObra
	 * @throws Exception
	 */
	private void saveDocumentoPlanObra(DocumentoPlanObra pDocumentoPlanObra) throws Exception {
		try {
			for(CuadraAfectada locCuadraAfectada : pDocumentoPlanObra.getListaCuadrasAfectadas()) {
				locCuadraAfectada.setDocumentoPlanObra(pDocumentoPlanObra);
			}
		} catch(LazyInitializationException e) {
			// NO TIENE QUE HACER NADA PORQUE SIGNIFICA QUE ACTUALIZA SOLO EL ESTADO
		}
	}

	/**
	 * Setea todos los valores de las colecciones del documento shps
	 * 
	 * @param pDocumentoSHPS
	 * @throws Exception
	 */
	private void saveDocumentoSHPS(DocumentoSHPS pDocumentoSHPS) throws Exception {

		// Libreta sanitaria tiene su propia administracion, esto no se valida aqui.
		// //valida que la libreta no este repetida
		// if (pDocumentoSHPS.getListaLibretaSanitarias() != null) {
		// for(LibretaSanitaria cadaLibreta : pDocumentoSHPS.getListaLibretaSanitarias()){
		// Criterio locCriteria = Criterio.getInstance(this.entityManager, LibretaSanitaria.class)
		// .add(Restriccion.NOT(Restriccion.IGUAL("idLibretaSanitaria", (cadaLibreta.getIdLibretaSanitaria()))))
		// .add(Restriccion.IGUAL("numeroLibretaSanitaria", cadaLibreta.getNumeroLibretaSanitaria()))
		// .setProyeccion(Proyeccion.COUNT());
		//
		// if ((Long) locCriteria.uniqueResult() > 0) {
		// throw new HabilitacionesException(23);
		// }
		// }
		// }

		/**
		 * No va mas esta validacion, pues el dueño de la Obligacion puede no tener libreta sanitaria, pero si quiere cargar la de los empleados.
		 */
		// //valida que al menos una de la persona de la lista de libretas sanitarias sea la misma que suscribe la obligacion
		// if(pDocumentoSHPS.getListaLibretaSanitarias() != null && !pDocumentoSHPS.getListaLibretaSanitarias().isEmpty()){
		// pDocumentoSHPS.getObligacion().toString();
		// pDocumentoSHPS.getObligacion().getPersona().toString();
		// pDocumentoSHPS.getListaLibretaSanitarias().toString();
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

		if(pDocumentoSHPS.getNumeroInscripcion() != null) {
			boolean existe = false;

			if(pDocumentoSHPS.getListaModificaciones() != null) {

				if(!pDocumentoSHPS.getListaModificaciones().isEmpty()) {
					Iterator<LogModificaciones> locIterador = pDocumentoSHPS.getListaModificaciones().iterator();
					while((!existe) && (locIterador.hasNext())) {
						LogModificaciones locLogModificaciones = locIterador.next();
						locLogModificaciones.toString();
						if(locLogModificaciones.getNumeroInscripcion().equals(pDocumentoSHPS.getNumeroInscripcion())) {
							existe = true;
						}
					}
				}
			}
			if(!existe) {
				LogModificaciones locLog = new LogModificaciones();
				locLog.setDocumentoSHPS(pDocumentoSHPS);
				locLog.setFecha(Calendar.getInstance().getTime());
				locLog.setNumeroInscripcion(pDocumentoSHPS.getNumeroInscripcion());
				pDocumentoSHPS.getListaModificaciones().add(locLog);
			}
		}

		// Listado de clausuras
		if(!pDocumentoSHPS.getListaClausuras().isEmpty() && pDocumentoSHPS.getListaClausuras() != null) {
			for(ClausuraSHPS cadaClausura : pDocumentoSHPS.getListaClausuras()) {
				cadaClausura.setDocumentoSHPS(pDocumentoSHPS);
			}
		}

		// Listado de local comercial
		if(!pDocumentoSHPS.getListaLocalesComerciales().isEmpty()) {
			for(LocalComercial cadaLocal : pDocumentoSHPS.getListaLocalesComerciales()) {
				System.out.println("Id Local: " + cadaLocal.getIdLocalComercial());
				// FIXME VER SI HACIENDO UN GET POR ID DESDE WEB ME VIENE LA LISTA CARGADA MIENTRAS UN PARCHESIN
				cadaLocal = this.businessDocumentoSHPS.getLocalComercialPorId(cadaLocal.getIdLocalComercial());
				cadaLocal.getListaDocumentosSHPS().toString();
				if(!cadaLocal.getListaDocumentosSHPS().contains(pDocumentoSHPS)) {
					cadaLocal.getListaDocumentosSHPS().add(pDocumentoSHPS);
				}
			}
		}

		// Listado de transportes vehiculares
		pDocumentoSHPS.getListaTransportesVehiculares().toString();
		if(pDocumentoSHPS.getListaTransportesVehiculares() != null && !pDocumentoSHPS.getListaTransportesVehiculares().isEmpty()) {
			for(TransporteVehicular cadaTransporteVehicular : pDocumentoSHPS.getListaTransportesVehiculares()) {
				cadaTransporteVehicular = entityManager.find(TransporteVehicular.class, cadaTransporteVehicular.getIdTransporteVehicular());
				cadaTransporteVehicular.getListaDocumentosSHPS().toString();
				if(!cadaTransporteVehicular.getListaDocumentosSHPS().contains(pDocumentoSHPS)) {
					cadaTransporteVehicular.getListaDocumentosSHPS().add(pDocumentoSHPS);
				}
			}
		}

		this.validarAsocRubroShps(pDocumentoSHPS);
	}

	/**
	 * 
	 * Actualiza los datos de una obligacion
	 * 
	 * @param pObligacion
	 *            Obligación a actualizar
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void updateObligacion(Obligacion pObligacion) throws Exception {

		for(DocHabilitante locDocHabilitante : pObligacion.getListaDocumentosHabilitantes()) {
			locDocHabilitante.setObligacion(pObligacion);
			this.verificarDocHabilitante(locDocHabilitante);
			this.entityManager.merge(locDocHabilitante);
		}

		pObligacion.getDocumentoEspecializado().setObligacion(pObligacion);
		this.saveDocumentoHabiltanteEspecializado(pObligacion.getDocumentoEspecializado());

		TrascenderEnverListener.setValoresEnAuditoriaBean(pObligacion.getDocumentoEspecializado());

		this.entityManager.merge(pObligacion.getDocumentoEspecializado());

		// Se agregar flush por los atributos dinamicos
		this.entityManager.flush();
	}

	/**
	 * Verifica que el documento tenga los elemento necesareos.
	 * 
	 * @param pDocHabilitante
	 * @throws TrascenderException
	 */
	private void verificarDocHabilitante(DocHabilitante pDocHabilitante) throws TrascenderException {
		// si tiene padre le asigna la obligacion del mismo
		if(pDocHabilitante.getPadre() != null) {
			pDocHabilitante.setObligacion(pDocHabilitante.getPadre().getObligacion());
		}
		// si no tiene fecha le asigna la del sistema
		if(pDocHabilitante.getFechaHoraCreacion() == null) {
			pDocHabilitante.setFechaHoraCreacion(Calendar.getInstance().getTime());
		}
		// si es compuesto valído que tenga hijos
		if(pDocHabilitante instanceof DocHabCompuesto) {
			if(((DocHabCompuesto) pDocHabilitante).getListaDocumentosHabilitantes().isEmpty()) {
				throw new HabilitacionesException(2);
			}
			for(DocHabilitante locDocHabilitante : ((DocHabCompuesto) pDocHabilitante).getListaDocumentosHabilitantes()) {
				this.verificarDocHabilitante(locDocHabilitante);
			}
		}
	}

	/**
	 * Recupera un listado de las obligaciones que cumplan con los parámetros ingresados
	 * 
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public java.util.List findListaObligaciones(com.trascender.framework.recurso.persistent.Persona pPersona, TipoObligacion pTipoObligacion,
			com.trascender.habilitaciones.recurso.persistent.Obligacion.Estado pEstado, Parcela pParcela, Vehiculo pVehiculo) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, Obligacion.class)
				.add(Restriccion.IGUAL("estado", pEstado));

		locCriterio.add(Restriccion.IGUAL("documentoEspecializado.vehiculo", pVehiculo));
		locCriterio.add(Restriccion.IGUAL("documentoEspecializado.parcela", pParcela));
		locCriterio.add(Restriccion.IGUAL("persona", pPersona));
		locCriterio.add(Restriccion.IGUAL("estado", pEstado));

		String locRestriccion = "";

		if(pTipoObligacion != null) {

			if(pTipoObligacion.getNombre().equals("TGI")) {
				locRestriccion = DocumentoTGI.class.getSimpleName();
			} else if(pTipoObligacion.getNombre().equals("SHPS")) {
				locRestriccion = DocumentoSHPS.class.getSimpleName();
			} else if(pTipoObligacion.getNombre().equals("OYSP")) {
				locRestriccion = DocumentoOSP.class.getSimpleName();
			} else if(pTipoObligacion.getNombre().equals("PLAN_FINANCIACION_OBRA")) {
				locRestriccion = DocumentoPlanObra.class.getSimpleName();
			}

			locCriterio.crearAlias("documentoEspecializado", "locDocumento");
			locCriterio.add(Restriccion.JPQL("TYPE(locDocumento) = ".concat(locRestriccion)));
		}

		List loclistadoObligaciones = locCriterio.list();

		for(Object cadaObject : loclistadoObligaciones) {
			Obligacion locObligacion = (Obligacion) cadaObject;

			// Me aseguro que traiga la persona
			Persona locPersona = locObligacion.getPersona();
			locPersona.toString();

			// Me aseguro que traiga todo del documento Habilitante
			locObligacion.toString();
			if(locObligacion.getDocumentoEspecializado() != null) {
				locObligacion.getDocumentoEspecializado().toString();
			}
			if(locObligacion.getListaRegistrosExencion() != null) {
				locObligacion.getListaRegistrosExencion().size();
			}
			locObligacion.getServicios().toString();
		}

		return loclistadoObligaciones;
	}

	/**
	 * @param pPersona
	 * @param pTipoObligacion
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public java.util.List findListaDocHabEspecializados(Persona pPersona, TipoObligacion pTipoObligacion, DocHabilitanteEspecializado.Estado pEstadoDocEspecializado,
			Obligacion.Estado pEstadoObligacion) throws Exception {

		Criterio locCriterio = null;
		// TODO TERMINAR DEPENDIENDO DEL TIPO
		if(pTipoObligacion.getNombre().equals("OYSP"))
			locCriterio = Criterio.getInstance(this.entityManager, DocumentoOSP.class);
		else if(pTipoObligacion.getNombre().equals("SHPS"))
			locCriterio = Criterio.getInstance(this.entityManager, DocumentoSHPS.class);
		else if(pTipoObligacion.getNombre().equals("PLAN_FINANCIACION_OBRA"))
			locCriterio = Criterio.getInstance(this.entityManager, DocumentoPlanObra.class);
		else if(pTipoObligacion.getNombre().equals("TGI"))
			locCriterio = Criterio.getInstance(this.entityManager, DocumentoTGI.class);
		else
			locCriterio = Criterio.getInstance(this.entityManager, DocHabilitanteEspecializado.class);

		if(locCriterio != null) {
			locCriterio.add(Restriccion.IGUAL("persona", pPersona));

			if(pEstadoDocEspecializado != null) {
				locCriterio.add(Restriccion.IGUAL("estado", pEstadoDocEspecializado));
			} else {
				locCriterio.add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO));
			}

			locCriterio.add(Restriccion.IGUAL("locObligacion.estado", pEstadoObligacion));

			return locCriterio.list();
		} else {
			return null;
		}
	}

	/**
	 * Firma un permiso de habilitaciones para el usuario acutal
	 * 
	 * @param pPermisoHab
	 * @ejb.interface-method view-type = "local"
	 */
	public PermisoHab firmarDocHabilitante(long pLlave, PermisoHab pPermisoHab, String pComentario) throws Exception {

		Usuario locUsuario = SecurityMgr.getInstance().getUsuario(pLlave);
		boolean permitido = false;

		for(Rol locRol : locUsuario.getListaRoles()) {
			permitido = permitido || locRol.equals(pPermisoHab.getRol());
		}

		if(!permitido) {
			throw new HabilitacionesException(21);
		}

		this.systemUsuario.setLlave(pLlave);
		FirmaPermiso locFirma = this.systemUsuario.firmar(pComentario);

		pPermisoHab = this.entityManager.find(PermisoHab.class, pPermisoHab.getIdDocHabilitante());
		pPermisoHab.setFirma(locFirma);

		pPermisoHab.habilitar();

		this.entityManager.persist(locFirma);
		this.entityManager.merge(pPermisoHab);

		return pPermisoHab;
	}

	/**
	 * Recupera un listado de los Permisos que puede firmar el usuario pasado por parámetro
	 * 
	 * @param pUsuario
	 *            Usuario del que se desean recuperar los permisos a firmar
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "local"
	 */
	public List findListaPermisosHabAFirmar(Usuario pUsuario, Obligacion pObligacion) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, PermisoHab.class).add(Restriccion.IGUAL("estado", DocHabilitante.Estado.CREADO)).setModoDebug(true)
				.setMaxResults(1000);

		if(pUsuario != null) {
			if((pUsuario.getListaRoles() != null) && (!pUsuario.getListaRoles().isEmpty())) {
				locCriterio.add(Restriccion.EN("rol", pUsuario.getListaRoles()));
			} else {
				// en caso que el parámetro no tenga lista de roles, entonces debe retornar una lista vacía
				return new ArrayList();
			}
		}

		if(pObligacion != null) {
			locCriterio.add(Restriccion.IGUAL("obligacion", pObligacion));
		}

		List locListaLista = locCriterio.list();

		for(Object cadaObject : locListaLista) {
			PermisoHab locPermisoHab = (PermisoHab) cadaObject;
			locPermisoHab.getObligacion().toString();
			locPermisoHab.getObligacion().getDocumentoEspecializado().toString();

			locPermisoHab.getRol().toString();
			locPermisoHab.getFirma();
		}
		return locListaLista;
	}

	/**
	 * Recupera un listado de los Permisos que puede firmar el usuario pasado por parámetro
	 * 
	 * @param pUsuario
	 *            Usuario del que se desean recuperar los permisos a firmar
	 * @param pPersona
	 *            Persona de la cual se recuperaran las obligaciones pendientes de firma
	 * @return
	 * @throws TrascenderException
	 * @ejb.interface-method view-type = "local"
	 */
	public List findListaPermisosHabAFirmar(Usuario pUsuario, Persona pPersona) throws Exception {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, PermisoHab.class).add(Restriccion.IGUAL("estado", DocHabilitante.Estado.CREADO));

		if(pUsuario != null) {
			if((pUsuario.getListaRoles() != null) && (!pUsuario.getListaRoles().isEmpty())) {
				locCriterio.add(Restriccion.EN("rol", pUsuario.getListaRoles()));
			} else {
				return new ArrayList();
			}
		}

		if(pPersona != null) {
			locCriterio.add(Restriccion.IGUAL("obligacion.persona", pPersona));
		}

		List locListaResultado = locCriterio.list();

		for(Object cadaObject : locListaResultado) {
			PermisoHab locPermisoHab = (PermisoHab) cadaObject;
			locPermisoHab.getObligacion().toString();
			locPermisoHab.getObligacion().getPersona().toString();
			locPermisoHab.getObligacion().getDocumentoEspecializado().toString();

			locPermisoHab.getRol().toString();
			locPermisoHab.getFirma();
		}
		return locListaResultado;
	}

	/**
	 * Retorna una obligación por el id
	 * 
	 * @param pIdObligacion
	 *            id de la obligación a recuperar
	 * @return obligacion
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public Obligacion getObligacionPorId(long pIdObligacion) throws Exception {
		Obligacion locObligacion = this.entityManager.find(Obligacion.class, pIdObligacion);

		if(locObligacion != null) {
			locObligacion.getListaDocumentosHabilitantes().toString();
			locObligacion.getDocumentoEspecializado().toString();
			if(locObligacion.getPersona() != null) {
				locObligacion.getPersona().toString();
				locObligacion.getPersona().getDomicilio().getDomicilioCompleto().toString();
				locObligacion.getPersona().getDomicilioPostal().getDomicilioCompleto().toString();
			}
			locObligacion.toString();
			if(locObligacion.getListaRegistrosExencion() != null && !locObligacion.getListaRegistrosExencion().isEmpty()) {
				locObligacion.getListaRegistrosExencion().toString();
			}
		}

		return locObligacion;
	}

	/**
	 * 
	 * @param pObligacion
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public TipoObligacion getTipoObligacionFromObligacion(Obligacion pObligacion) throws Exception {

		DocHabilitanteEspecializado locDocHabEspecializado = pObligacion.getDocumentoEspecializado();

		if(locDocHabEspecializado != null) {
			if(locDocHabEspecializado instanceof DocumentoSHPS) {
				return getTipoObligacionPorNombre("SHPS");
			} else if(locDocHabEspecializado instanceof DocumentoPlanObra) {
				return getTipoObligacionPorNombre("PLAN_FINANCIACION_OBRA");
			} else if(locDocHabEspecializado instanceof DocumentoTGI) {
				return getTipoObligacionPorNombre("TGI");
			} else if(locDocHabEspecializado instanceof DocumentoOSP) {
				return getTipoObligacionPorNombre("OYSP");
			} else if(locDocHabEspecializado instanceof DocumentoTasaMenor) {
				DocumentoTasaMenor locDocumentoTasaMenor = (DocumentoTasaMenor) locDocHabEspecializado;
				// Medio al pedo volver a buscarla en la base, pero puede estar en lazy, asi que se deja.
				return getTipoObligacionPorNombre(locDocumentoTasaMenor.getPlantillaDocumentoTasaMenor().getTipoObligacion().getNombre());
			} else if(locDocHabEspecializado instanceof DocumentoAutomotor) {
				return getTipoObligacionPorNombre("AUTOMOTOR");
			} else if(locDocHabEspecializado instanceof DocumentoCementerio) {
				return getTipoObligacionPorNombre("CEMENTERIO");
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public void modificarTitularObligacion(Persona pPersona, Parcela pParcela) throws Exception {
		pParcela = this.entityManager.find(Parcela.class, pParcela.getIdParcela());
		pParcela.toString();
		pParcela.getTituloPropiedad().toString();
		for(RegistroPropietario registro : pParcela.getTituloPropiedad().getListaRegistrosPropietarios()) {
			registro.getPersona().toString();
		}

		List<Obligacion> listaObligaciones = Criterio.getInstance(this.entityManager, Obligacion.class).add(Restriccion.IGUAL("documentoEspecializado.parcela", pParcela)).list();

		for(Obligacion locObligacion : listaObligaciones) {
			locObligacion.setPersona(pPersona);
			this.entityManager.merge(locObligacion);
		}
	}

	@SuppressWarnings("unchecked")
	public void modificarDomicilioObligacion(Persona pPersona) throws Exception {
		List<Obligacion> listaObligaciones = Criterio.getInstance(this.entityManager, Obligacion.class).add(Restriccion.IGUAL("persona", pPersona)).list();

		for(Obligacion cadaObligacion : listaObligaciones) {
			DocHabilitanteEspecializado locDocHabilitante = cadaObligacion.getDocumentoEspecializado();
			locDocHabilitante.setDomicilio(pPersona.getDomicilio());
		}
	}

	public List<TipoObligacion> findListaTipoObligacion(String pNombre, Boolean pEsTipoTasaMenor) {
		Criterio locCriterio = Criterio.getInstance(entityManager, TipoObligacion.class).add(Orden.ASC("idTipoObligacion")).add(Restriccion.LIKE("nombre", pNombre, false));
		if(pEsTipoTasaMenor != null && pEsTipoTasaMenor) {
			locCriterio.add(Restriccion.JPQL("e IN (SELECT pdtm.tipoObligacion FROM PlantillaDocumentoTasaMenor pdtm)"));
		}

		List<TipoObligacion> locListaResultado = locCriterio.list();

		for(TipoObligacion cadaTipo : locListaResultado) {
			cadaTipo.toString();
			// for(CalendarioMunicipal cadaCalendario : cadaTipo.getListaCalendarioMunicipal()){
			// cadaCalendario.toString();
			// cadaCalendario.getListaPeriodos().toString();
			// }
		}
		return locListaResultado;
	}

	private TipoObligacion getTipoObligacionPorNombre(String pNombre) {
		return Criterio.getInstance(entityManager, TipoObligacion.class).add(Restriccion.LIKE("nombre", pNombre, false)).uniqueResult();
	}

	private void validarAsocServicioOsp(DocumentoOSP pDocOsp) {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, AsocServicioOsp.class)
				.add(Restriccion.IGUAL("docHabilitanteEspecializado.idDocHabilitanteEspecializado", pDocOsp.getIdDocHabilitanteEspecializado()))
				.add(Restriccion.NOT(Restriccion.EN("registroAlicuota", pDocOsp.getListaRegAlicuotas())));

		for(Object cadaObj : locCriterio.list()) {
			entityManager.remove(cadaObj);
		}
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

	private void validarParcelasCementerio(DocumentoCementerio pDocCementerio) {
		System.out.println("ANTES");
		Criterio locCriterio = Criterio.getInstance(this.entityManager, ParcelaCementerio.class)
				.add(Restriccion.IGUAL("docHabilitanteEspecializado.idDocHabilitanteEspecializado", pDocCementerio.getIdDocHabilitanteEspecializado()))
				.add(Restriccion.NOT(Restriccion.EN("registroAlicuota", pDocCementerio.getListaRegAlicuotas())));
		locCriterio.setModoDebug(true);

		for(Object cadaObj : locCriterio.list()) {
			ParcelaCementerio cadaParcela = (ParcelaCementerio) cadaObj;
			cadaParcela.setDocHabilitanteEspecializado(null);
		}
	}
}
