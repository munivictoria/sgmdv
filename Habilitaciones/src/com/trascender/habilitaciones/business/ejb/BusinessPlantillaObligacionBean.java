
package com.trascender.habilitaciones.business.ejb;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

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

import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;
import com.trascender.habilitaciones.business.interfaces.BusinessObligacionLocal;
import com.trascender.habilitaciones.business.interfaces.BusinessPlantillaObligacionLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.DocHabCompuesto;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitante;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.PermisoHab;
import com.trascender.habilitaciones.recurso.persistent.PlantillaDocHabCompuesto;
import com.trascender.habilitaciones.recurso.persistent.PlantillaDocHabilitante;
import com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion;
import com.trascender.habilitaciones.recurso.persistent.PlantillaPermiso;
import com.trascender.habilitaciones.recurso.persistent.PlantillaSellado;
import com.trascender.habilitaciones.recurso.persistent.Sellado;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.habilitaciones.recurso.persistent.cementerio.DocumentoCementerio;
import com.trascender.habilitaciones.recurso.persistent.osp.DocumentoOSP;
import com.trascender.habilitaciones.recurso.persistent.pfo.DocumentoPlanObra;
import com.trascender.habilitaciones.recurso.persistent.shps.DocumentoSHPS;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.DocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor.EstadoPlantillaDocTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;
import com.trascender.habilitaciones.recurso.persistent.transito.DocumentoAutomotor;

@Stateless(name = "ejb/BusinessPlantillaObligacionLocal")
public class BusinessPlantillaObligacionBean implements BusinessPlantillaObligacionLocal {

	private static final long serialVersionUID = -8852791878662727411L;

	@EJB
	private BusinessObligacionLocal businessObligacion;

	static {
		Grupo locGrupo = new Grupo();
		locGrupo.setNombre("HAB|Adm de Plantillas de Obligaciones");
		locGrupo.setId(serialVersionUID);

		Recurso locPlantillaObligacion = new Recurso();
		locPlantillaObligacion.setNombre("Plantilla de Obligaciones");
		locPlantillaObligacion.setAtributosConsultables("Nombre", "nombre", "Tipo de Obligación", "tipoObligacion");
		locPlantillaObligacion.setIdRecurso(PlantillaObligacion.serialVersionUID);
		locPlantillaObligacion.setClase(PlantillaObligacion.class);

		Recurso locPlantillaSellado = new Recurso();
		locPlantillaSellado.setIdRecurso(PlantillaSellado.serialVersionUID);
		locPlantillaSellado.setNombre("Plantilla de Sellado");
		locPlantillaSellado.setClase(PlantillaSellado.class);

		Recurso locPlantillaPermiso = new Recurso();
		locPlantillaPermiso.setIdRecurso(PlantillaPermiso.serialVersionUID);
		locPlantillaPermiso.setNombre("Plantilla de Permiso");
		locPlantillaPermiso.setClase(PlantillaPermiso.class);

		Recurso locPlantillaDocHabCompuesto = new Recurso();
		locPlantillaDocHabCompuesto.setIdRecurso(PlantillaDocHabCompuesto.serialVersionUID);
		locPlantillaDocHabCompuesto.setNombre("Plantilla Documento Hab. Compuesto");
		locPlantillaDocHabCompuesto.setClase(PlantillaDocHabCompuesto.class);

		locGrupo.getListaRecursos().add(locPlantillaObligacion);
		locGrupo.getListaRecursos().add(locPlantillaSellado);
		locGrupo.getListaRecursos().add(locPlantillaPermiso);
		locGrupo.getListaRecursos().add(locPlantillaDocHabCompuesto);

		SecurityMgr.getInstance().addGrupo(locGrupo);
	}

	public BusinessPlantillaObligacionBean() {
	}

	/**
	 * Create method
	 * 
	 * @ejb.create-method view-type = "local"
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}

	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {

	}

	public void ejbRemove() throws EJBException, RemoteException {

	}

	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {

	}

	@PersistenceContext(name = "Vipians")
	private EntityManager entityManager;

	/**
	 * Agrega una plantilla de obligación al sistema
	 * 
	 * @param pPlantillaObligacion
	 *            plantilla de obligación a agregar
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 *
	 */
	public PlantillaObligacion addPlantillaObligacion(PlantillaObligacion pPlantillaObligacion) throws Exception {
		this.validarPlantillaObligacion(pPlantillaObligacion);

		for(PlantillaDocHabilitante cadaPlantillaDocHabilitante : pPlantillaObligacion.getListaDocumentosHabilitantes()) {
			cadaPlantillaDocHabilitante.setRaiz(pPlantillaObligacion);
			this.comprobarDocumentosHabilitantes(cadaPlantillaDocHabilitante);
		}

		TrascenderEnverListener.setValoresEnAuditoriaBean(pPlantillaObligacion);

		pPlantillaObligacion = this.entityManager.merge(pPlantillaObligacion);
		this.entityManager.flush();

		return pPlantillaObligacion;
	}

	/**
	 * Valida que no aya una plantilla obligacion con el mismo nombre.
	 * 
	 * @param pPlantillaObligacion
	 * @throws Exception
	 */
	private void validarPlantillaObligacion(PlantillaObligacion pPlantillaObligacion) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, PlantillaObligacion.class)
				.add(Restriccion.LIKE("nombre", pPlantillaObligacion.getNombre(), true, Posicion.EXACTA))
				.add(Restriccion.IGUAL("tipoObligacion", pPlantillaObligacion.getTipoObligacion())).setProyeccion(Proyeccion.COUNT());

		if(pPlantillaObligacion.getIdPlantillaObligacion() != -1) {
			locCriterio.add(Restriccion.NOT(Restriccion.IGUAL("idPlantillaObligacion", pPlantillaObligacion.getIdPlantillaObligacion())));
		}

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new HabilitacionesException(0);
		}

	}

	/**
	 * Comprueba recursivamente los documentos habilitantes
	 * 
	 * @param pPlantillaObligacion
	 * @throws Exception
	 */
	private void comprobarDocumentosHabilitantes(PlantillaDocHabilitante pPlantillaObligacion) throws Exception {
		if(pPlantillaObligacion instanceof PlantillaDocHabCompuesto) {
			PlantillaDocHabCompuesto locCompuesto = (PlantillaDocHabCompuesto) pPlantillaObligacion;
			if(locCompuesto.getHijos().isEmpty()) {
				throw new HabilitacionesException(2);
			}
			for(PlantillaDocHabilitante locHijosDocumentoCompuesto : locCompuesto.getHijos()) {
				locHijosDocumentoCompuesto.setPadre(locCompuesto);
				locHijosDocumentoCompuesto.setRaiz(locCompuesto.getRaiz());
				this.comprobarDocumentosHabilitantes(locHijosDocumentoCompuesto);
			}
		} else if(pPlantillaObligacion instanceof PlantillaPermiso) {
			PlantillaPermiso locPlantillaPermiso = (PlantillaPermiso) pPlantillaObligacion;
			if(locPlantillaPermiso.getRol() == null) {
				throw new HabilitacionesException(3);
			}
		}
	}

	/**
	 * Actualiza los datos de una plantilla de obligacion
	 * 
	 * @param pPlnatillaObligacion
	 *            plantilla de obligación con los datos actualizados
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public PlantillaObligacion updatePlantillaObligacion(PlantillaObligacion pPlantillaObligacion) throws Exception {
		this.validarPlantillaObligacion(pPlantillaObligacion);

		for(PlantillaDocHabilitante locPlantillaDocHabilitante : pPlantillaObligacion.getListaDocumentosHabilitantes()) {
			locPlantillaDocHabilitante.setRaiz(pPlantillaObligacion);
			this.comprobarDocumentosHabilitantes(locPlantillaDocHabilitante);
		}

		TrascenderEnverListener.setValoresEnAuditoriaBean(pPlantillaObligacion);

		pPlantillaObligacion = this.entityManager.merge(pPlantillaObligacion);
		this.entityManager.flush();
		// for (PlantillaDocHabilitante locDoc: pPlantillaObligacion.getListaDocumentosHabilitantes()){
		// this.savePlantillaDocHabilitante(locDoc);
		// }
		return pPlantillaObligacion;
	}

	private void savePlantillaDocHabilitante(PlantillaDocHabilitante pPlantilla) throws Exception {
		if(pPlantilla instanceof PlantillaDocHabCompuesto) {
			PlantillaDocHabCompuesto locPlantillaDocHabCompuesto = (PlantillaDocHabCompuesto) pPlantilla;
			for(PlantillaDocHabilitante locPlantilla : locPlantillaDocHabCompuesto.getHijos()) {
				this.savePlantillaDocHabilitante(locPlantilla);
			}
		}
		pPlantilla = this.entityManager.merge(pPlantilla);
	}

	/**
	 * Elimina físicamente una plantilla de obligacion
	 * 
	 * @param pPlantillaObligacion
	 *            plantilla de obligación a eliminar
	 * @throws Exception
	 *             plantilla de obligacion a eliminar
	 * @ejb.interface-method view-type = "local"
	 */
	public void deletePlantillaObligacion(com.trascender.habilitaciones.recurso.persistent.PlantillaObligacion pPlantillaObligacion) throws Exception {

		pPlantillaObligacion = (PlantillaObligacion) Criterio.getInstance(this.entityManager, PlantillaObligacion.class)
				.add(Restriccion.IGUAL("idPlantillaObligacion", pPlantillaObligacion.getIdPlantillaObligacion())).uniqueResult();

		this.entityManager.remove(pPlantillaObligacion);
	}

	/**
	 * Recupera un listado de plantillas de obligación
	 * 
	 * @param pNombre
	 *            primeras letras del nombre de la plantilla de obligacion
	 * @return Listado
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroPlantillaObligacion findListaPlantillaObligacion(FiltroPlantillaObligacion pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, PlantillaObligacion.class).add(Restriccion.ILIKE("nombre", pFiltro.getNombre()))
				.add(Restriccion.IGUAL("tipoObligacion", pFiltro.getTipoObligacion()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, PlantillaObligacion.serialVersionUID, "idPlantillaObligacion", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		for(PlantillaObligacion cadaPlantilla : pFiltro.getListaResultados()) {
			if(cadaPlantilla.getListaDocumentosHabilitantes() != null) {
				cadaPlantilla.getListaDocumentosHabilitantes().size();
			}
		}

		return pFiltro;
	}

	public List<PlantillaObligacion> findPlantillasObligacionTasaMenor() {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, PlantillaObligacion.class).add(
				Restriccion.JPQL("tipoObligacion = SOME (SELECT pdtm.tipoObligacion FROM PlantillaDocumentoTasaMenor pdtm WHERE pdtm.estado = 'ACTIVA')"));

		List<PlantillaObligacion> locLista = locCriterio.list();
		for(PlantillaObligacion cadaPlantilla : locLista) {
			if(cadaPlantilla.getListaDocumentosHabilitantes() != null) {
				cadaPlantilla.getListaDocumentosHabilitantes().size();
			}
		}

		return locCriterio.list();
	}

	/**
	 * Genera un arbol de obligaciones a partir de la plantilla de obligaciones
	 * 
	 * @param pPersona
	 *            persona a la que pertenece la obligacion
	 * @param pPlantillaObligacion
	 *            plantilla de obligacion de la cual tomar como parámetro
	 * @return Obligación
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public Obligacion generarArbolObligacion(PlantillaObligacion pPlantillaObligacion) throws Exception {
		if(pPlantillaObligacion == null) {
			throw new HabilitacionesException(6);
		}

		Obligacion locObligacion = new Obligacion();
		locObligacion.setDescripcion(pPlantillaObligacion.getDescripcion());
		locObligacion.setNombre(pPlantillaObligacion.getNombre());

		for(PlantillaDocHabilitante locPlantillaDocumentoHabilitante : pPlantillaObligacion.getListaDocumentosHabilitantes()) {
			this.addNodo(locObligacion, null, locPlantillaDocumentoHabilitante);
		}

		// lógica específica de cada tipo de obligacion
		if(pPlantillaObligacion.getTipoObligacion().getNombre().equals("SHPS")) {
			DocHabilitanteEspecializado locDocSHPS = new DocumentoSHPS();
			locObligacion.setDocumentoEspecializado(locDocSHPS);
			locDocSHPS.setObligacion(locObligacion);
		}
		// LÓGICA PFO
		else if(pPlantillaObligacion.getTipoObligacion().getNombre().equals("PLAN_FINANCIACION_OBRA")) {
			DocumentoPlanObra locDocumentoPlanObra = new DocumentoPlanObra();
			locObligacion.setDocumentoEspecializado(locDocumentoPlanObra);
			locDocumentoPlanObra.setObligacion(locObligacion);
		}

		// LÓGICA TGI
		else if(pPlantillaObligacion.getTipoObligacion().getNombre().equals("TGI")) {
			DocumentoTGI locDocumentoTGI = new DocumentoTGI();
			locDocumentoTGI.setObligacion(locObligacion);
			locObligacion.setDocumentoEspecializado(locDocumentoTGI);
		}

		// LÓGICA OSP
		else if(pPlantillaObligacion.getTipoObligacion().getNombre().equals("OYSP")) {
			DocumentoOSP locDocumentoOSP = new DocumentoOSP();
			locDocumentoOSP.setObligacion(locObligacion);
			locObligacion.setDocumentoEspecializado(locDocumentoOSP);
		}

		else if(pPlantillaObligacion.getTipoObligacion().getNombre().equals("AUTOMOTOR")) {
			DocumentoAutomotor locDocumentoAutomotor = new DocumentoAutomotor();
			locDocumentoAutomotor.setObligacion(locObligacion);
			locObligacion.setDocumentoEspecializado(locDocumentoAutomotor);
		} else if(pPlantillaObligacion.getTipoObligacion().getNombre().equals("CEMENTERIO")) {
			DocumentoCementerio locDocumentoCementerio = new DocumentoCementerio();
			locDocumentoCementerio.setObligacion(locObligacion);
			locObligacion.setDocumentoEspecializado(locDocumentoCementerio);
		} else {
			// Busca una PlantillaDocumentoTasaMenor asociada a la PlantillaObligacion
			PlantillaDocumentoTasaMenor locPlantilla = Criterio.getInstance(entityManager, PlantillaDocumentoTasaMenor.class)
					.add(Restriccion.IGUAL("tipoObligacion", pPlantillaObligacion.getTipoObligacion())).uniqueResult();
			if(locPlantilla != null) {
				if(locPlantilla.getEstado().equals(EstadoPlantillaDocTasaMenor.INACTIVA)) {
					throw new HabilitacionesException(728);
				}
				DocumentoTasaMenor locDocumentoTasaMenor = locPlantilla.generarDocumentoTasaMenor();
				locDocumentoTasaMenor.setObligacion(locObligacion);
				locObligacion.setDocumentoEspecializado(locDocumentoTasaMenor);
			}
		}
		return locObligacion;
	}

	// ------------------------------------------------------------------------------------------------------------------- (MÉTODOS NECESARIOS PARA GENERACIÓN
	// DE ARBOL)
	/**
	 * Agrega recursivamente nodos a un documento habilitante padre tomando la plantilla del documento hijo
	 * 
	 * @param pPadre
	 *            documento habilitante padre (documento compuesto)
	 * @param pHijo
	 *            plantilla del documento habilitante hijo
	 */
	private void addNodo(Obligacion pObligacion, DocHabCompuesto pPadre, PlantillaDocHabilitante pHijo) {
		DocHabilitante locAgregar;
		if(pHijo instanceof PlantillaSellado) {
			locAgregar = this.getSellado((PlantillaSellado) pHijo);
		} else if(pHijo instanceof PlantillaPermiso) {
			locAgregar = this.getPermiso((PlantillaPermiso) pHijo);
		} else {

			PlantillaDocHabCompuesto locPlantilla = (PlantillaDocHabCompuesto) pHijo;
			DocHabCompuesto locDocHabCompuesto = this.getDocHabCompuesto((PlantillaDocHabCompuesto) pHijo);
			locDocHabCompuesto.setDescripcion(locPlantilla.getDescripcion());
			locDocHabCompuesto.setObligacion(pObligacion);

			for(PlantillaDocHabilitante hijo : ((PlantillaDocHabCompuesto) pHijo).getHijos()) {
				this.addNodo(pObligacion, locDocHabCompuesto, hijo);
			}
			locAgregar = locDocHabCompuesto;
		}
		locAgregar.setNombre(pHijo.getNombre());
		locAgregar.setFechaHoraCreacion(Calendar.getInstance().getTime());
		locAgregar.setObligacion(pObligacion);
		if(pPadre != null) {
			pPadre.getListaDocumentosHabilitantes().add(locAgregar);
			locAgregar.setPadre(pPadre);
		} else {
			pObligacion.getListaDocumentosHabilitantes().add(locAgregar);
		}
	}

	/**
	 * Obtiene un permiso desde una plantilla
	 * 
	 * @param pPlantillaPermiso
	 * @return
	 */
	private PermisoHab getPermiso(PlantillaPermiso pPlantillaPermiso) {
		PermisoHab locPermiso = new PermisoHab();
		locPermiso.setRol(pPlantillaPermiso.getRol());
		return locPermiso;
	}

	/**
	 * Obtiene un doc Habilitante compuesto a partir de una plantilla
	 * 
	 * @param pPlantillaDocHabilitante
	 * @return
	 */
	private DocHabCompuesto getDocHabCompuesto(PlantillaDocHabCompuesto pPlantillaDocHabilitante) {
		DocHabCompuesto locDocHabCompuesto = new DocHabCompuesto();
		locDocHabCompuesto.setDescripcion(pPlantillaDocHabilitante.getDescripcion());
		return locDocHabCompuesto;
	}

	/**
	 * Obtiene un sellado desde una platnilla
	 * 
	 * @param pPlantillaSellado
	 * @return
	 */
	private Sellado getSellado(PlantillaSellado pPlantillaSellado) {
		Sellado locSellado = new Sellado();
		locSellado.setDescripcion(pPlantillaSellado.getDescripcion());
		locSellado.setPagado(false);
		locSellado.setValor(pPlantillaSellado.getValor());
		return locSellado;
	}

	// ------------------------------------------------------------------------------------------------------------------- (FIN DE MÉTODOS NECESARIOS PARA EL
	// ARBOL)
	private void mostrarPlantillaObligacion(PlantillaObligacion pPlantillaObligacion) {
		System.out.println("Plantilla doc=" + pPlantillaObligacion);
		for(PlantillaDocHabilitante locDocHabilitante : pPlantillaObligacion.getListaDocumentosHabilitantes()) {
			this.mostrarPlantillaDocHabilitante(locDocHabilitante, 1);
		}
	}

	private void mostrarPlantillaDocHabilitante(PlantillaDocHabilitante pPlantillaDocHabilitante, int nivel) {
		String print = "";
		for(int i = 0; i < nivel; i++) {
			print += "\t";
		}
		System.out.println("DocHabilitante: " + print + pPlantillaDocHabilitante);

		if(pPlantillaDocHabilitante instanceof PlantillaDocHabCompuesto) {
			PlantillaDocHabCompuesto locDocHabCompuesto = (PlantillaDocHabCompuesto) pPlantillaDocHabilitante;
			for(PlantillaDocHabilitante locDocHabilitante : locDocHabCompuesto.getHijos()) {
				this.mostrarPlantillaDocHabilitante(locDocHabilitante, nivel + 1);
			}
		}

	}

	public PlantillaObligacion getPlantillaObligacion(long pId) throws Exception {

		PlantillaObligacion locPlantilla = (PlantillaObligacion) Criterio.getInstance(this.entityManager, PlantillaObligacion.class)
				.add(Restriccion.IGUAL("idPlantillaObligacion", pId)).uniqueResult();

		if(locPlantilla == null) {
			throw new HabilitacionesException(6);
		}

		for(PlantillaDocHabilitante cadaDocumento : locPlantilla.getListaDocumentosHabilitantes()) {
			cargarPlantillaDocumentoHabilitante(cadaDocumento);
		}

		locPlantilla.getTipoObligacion().toString();
		locPlantilla.getListaLogsAuditoria().size();
		locPlantilla.toString();

		return locPlantilla;
	}

	public List findListaPlantillaObligacion(TipoObligacion pTipoObligacion) throws Exception {
		List<TipoObligacion> locListaResultado = Criterio.getInstance(this.entityManager, TipoObligacion.class).add(Restriccion.IGUAL("nombre", pTipoObligacion)).uniqueResult();
		return locListaResultado;
	}

	private void cargarPlantillaDocumentoHabilitante(PlantillaDocHabilitante pPlantillaDocHabilitante) {
		pPlantillaDocHabilitante.toString();
		if(pPlantillaDocHabilitante.getPadre() != null) {
			pPlantillaDocHabilitante.getPadre().toString();
		}

		// si es compuesto llamo recursivamente al metododo
		if(pPlantillaDocHabilitante instanceof PlantillaDocHabCompuesto) {
			PlantillaDocHabCompuesto locPlantillaDocHabCompuesta = (PlantillaDocHabCompuesto) pPlantillaDocHabilitante;
			locPlantillaDocHabCompuesta.getHijos().toString();
			for(PlantillaDocHabilitante cadaHijoPlantillaDocHabCompuesto : locPlantillaDocHabCompuesta.getHijos()) {
				cargarPlantillaDocumentoHabilitante(cadaHijoPlantillaDocHabCompuesto);
			}
		}
	}

	public boolean esTipoObligacionTasaMenor(TipoObligacion pTipo) {
		Long cantidad = Criterio.getInstance(entityManager, PlantillaDocumentoTasaMenor.class).setProyeccion(Proyeccion.COUNT()).add(Restriccion.IGUAL("tipoObligacion", pTipo))
				.uniqueResult();
		return cantidad.longValue() > 0;
	}

	/**
	 * Recupera una plantilla obligacion segun la obligacion pasada por parametro si no la encuentra devuelve alguna otra para el mismo tipo de la obligacion.
	 * 
	 * @param pObligacion
	 * @return
	 * @throws Exception
	 */
	public PlantillaObligacion getPlantillaObligacionFromObligacion(Obligacion pObligacion) throws Exception {
		FiltroPlantillaObligacion locFiltro = new FiltroPlantillaObligacion();
		locFiltro.setNombre(pObligacion.getNombre());
		locFiltro.setTipoObligacion(this.businessObligacion.getTipoObligacionFromObligacion(pObligacion));
		Collection<PlantillaObligacion> locListaPlantilla = this.findListaPlantillaObligacion(locFiltro).getListaResultados();
		if(locListaPlantilla != null && !locListaPlantilla.isEmpty()) {
			PlantillaObligacion locPlantilla = locListaPlantilla.iterator().next();
			locPlantilla.toString();
			for(PlantillaDocHabilitante cadaDoc : locPlantilla.getListaDocumentosHabilitantes()) {
				cadaDoc.toString();
			}
			return locPlantilla;
		} else {
			throw new HabilitacionesException(8);
		}
	}

}
