
package com.trascender.habilitaciones.business.ejb;

import java.rmi.RemoteException;

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

import com.trascender.framework.business.interfaces.BusinessParametroLocal;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoTGILocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.exception.ObligacionTGIExistenteException;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.FiltroObligacionTGI;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion.Estado;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;

@Stateless(name = "ejb/BusinessDocumentoTGILocal")
public class BusinessDocumentoTGIBean implements BusinessDocumentoTGILocal {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3432645372919188784L;

	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre("HAB|Adm. Obligación TGI");

		Recurso documentoTGI = new Recurso();
		documentoTGI.setNombre("Obligación TGI");
		documentoTGI.setIdRecurso(DocumentoTGI.serialVersionUID);
		documentoTGI.setAtributosConsultables("Contribuyente", "persona", "Parcela", "parcela", "Calle", "calle", "Altura", "altura");
		documentoTGI.setClase(DocumentoTGI.class);
		grupo.getListaRecursos().add(documentoTGI);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	@EJB
	private BusinessParametroLocal businessParametro;

	@PersistenceContext(name = "Vipians")
	private EntityManager entityManager;

	public BusinessDocumentoTGIBean() {
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
	 * 
	 * @throws CreateException
	 * @ejb.create-method view-type = "local"
	 */
	public void ejbCreate() throws CreateException {
	}

	/**
	 * Agrega un documento de Tasa general inmobiliaria
	 * 
	 * @param pDocumentoTGI
	 *            Documento a agregar
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public DocumentoTGI addDocumentoTGI(DocumentoTGI pDocumentoTGI) throws Exception {

		this.verificarDocumentoTGI(pDocumentoTGI);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pDocumentoTGI);
		this.entityManager.persist(pDocumentoTGI);
		this.entityManager.flush();

		this.entityManager.refresh(pDocumentoTGI);

		this.actualizarCantidadPropiedades(pDocumentoTGI);
		return pDocumentoTGI;
	}

	/**
	 * Verifica si ya existe una obligacion TGI para la parcela
	 * 
	 * @param pSess
	 * @param pDocumentoTGI
	 * @throws HabilitacionesException
	 */
	private void verificarDocumentoTGI(DocumentoTGI pDocumentoTGI) throws HabilitacionesException {

		Criterio locCriterio = Criterio.getInstance(this.entityManager, DocumentoTGI.class).add(Restriccion.NOT(Restriccion.IGUAL("obligacion.estado", Estado.ANULADO)))
				.add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO)).add(Restriccion.IGUAL("obligacion.persona", pDocumentoTGI.getObligacion().getPersona()))
				.add(Restriccion.IGUAL("parcela", pDocumentoTGI.getParcela())).setMaxResults(1);

		DocumentoTGI locDocumentoTGI = (DocumentoTGI) locCriterio.uniqueResult();

		if(locDocumentoTGI != null) {
			throw new ObligacionTGIExistenteException(locDocumentoTGI);
		}
	}

	private void validarDocumentoParaUpdate(DocumentoTGI pDocumentoTGI) throws HabilitacionesException {
		if(pDocumentoTGI == null || pDocumentoTGI.getParcela() == null || pDocumentoTGI.getObligacion() == null) {
			throw new HabilitacionesException(100);
		}
	}

	/**
	 * Actualiza la cantidad de propiedades que una persona posee
	 * 
	 * @param pPersona
	 * @param pPropiedad
	 *            cantidad de propiedades que agrega
	 * @throws Exception
	 */
	private void actualizarCantidadPropiedades(DocumentoTGI pDocumento) throws Exception {

		Long cantidad = Criterio.getInstance(this.entityManager, Obligacion.class).add(Restriccion.IGUAL("persona", pDocumento.getObligacion().getPersona()))
				.add(ar.trascender.criterio.clases.Grupo.POR("documentoEspecializado.parcela.idParcela")).setProyeccion(Proyeccion.COUNT()).uniqueResult();

		pDocumento.getObligacion().getPersona().setCantidadPropiedades(cantidad.intValue());

		entityManager.merge(pDocumento.getObligacion().getPersona());
	}

	/**
	 * Actualiza los datos de un documento de tasa general inmobiliaria
	 * 
	 * @param pDocumentoTGI
	 *            documento a actualizar
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public DocumentoTGI updateDocumentoTGI(DocumentoTGI pDocumentoTGI) throws Exception {
		this.validarDocumentoParaUpdate(pDocumentoTGI);

		DocumentoTGI locDocumento = (DocumentoTGI) Criterio.getInstance(this.entityManager, DocumentoTGI.class)
				.add(Restriccion.IGUAL("idDocHabilitanteEspecializado", pDocumentoTGI.getIdDocHabilitanteEspecializado())).detachedUniqueResult();

		pDocumentoTGI = this.entityManager.merge(pDocumentoTGI);

		// Se agrega flush para que funcionen los atributos dinamicos.
		this.entityManager.flush();

		try {
			com.trascender.habilitaciones.util.Util.getInstance(this.entityManager).deleteDomicilioExcluyente(locDocumento, pDocumentoTGI);
		} catch(Exception e) {
			// No necesito hacer nada | Si falla q se joda u.u
		}
		this.actualizarCantidadPropiedades(pDocumentoTGI);
		return pDocumentoTGI;
	}

	/**
	 * Elimina un documento TGI logicamente
	 * 
	 * @param pDocumentoTGI
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public void deleteDocumentoTGI(DocumentoTGI pDocumentoTGI) throws Exception {
		pDocumentoTGI.getObligacion().terminar();
		this.entityManager.merge(pDocumentoTGI.getObligacion());
	}

	/**
	 * Listado de documento de tgi
	 * 
	 * @param pPersona
	 * @param pNumeroRegistro
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroObligacionTGI findListaDocumentosTGI(FiltroObligacionTGI pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, DocumentoTGI.class).add(Restriccion.IGUAL("obligacion.persona", pFiltro.getPersona()))
				.add(Restriccion.IGUAL("parcela.nroRegistro", ((pFiltro.getNumeroRegistro() != null) ? pFiltro.getNumeroRegistro().toString() : null)))
				.add(Restriccion.IGUAL("obligacion.estado", pFiltro.getEstado()));

		pFiltro.procesarYListar(locCriterio);

		// for (DocumentoTGI cadaDocumentoTGI : pFiltro.getListaResultados()) {
		// cadaDocumentoTGI.getObligacion().toString();
		// }
		return pFiltro;
	}

	/**
	 * Recupera un listado de obligaciones de TGI
	 * 
	 * @param pPersona
	 * @param pNumeroRegistro
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public FiltroObligacionTGI findListaObligacionesTGI(FiltroObligacionTGI pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, Obligacion.class).crearAlias("documentoEspecializado", "locDocumento")
				.crearAlias("locDocumento.parcela", "locParcela").add(Restriccion.JPQL("TYPE (locDocumento) = ".concat(DocumentoTGI.class.getSimpleName())))

				.add(Restriccion.IGUAL("locParcela.nomenclaturaCatastral.nroParcela", (pFiltro.getNroParcela() != null) ? pFiltro.getNroParcela().toString() : null))
				.add(Restriccion.IGUAL("estado", pFiltro.getEstado()));

		if(pFiltro.getPoseeExenciones() != null) {
			if(!pFiltro.getPoseeExenciones()) {
				locCriterio.add(Restriccion.ESTA_VACIO("listaRegistrosExencion"));
			} else {
				locCriterio.add(Restriccion.NOT(Restriccion.ESTA_VACIO("listaRegistrosExencion")));
			}
		}

		if(pFiltro.getPersona() != null) {
			locCriterio.add(Restriccion.IGUAL("persona", pFiltro.getPersona()));
		} else if(pFiltro.getListaIdPersona() != null) {
			locCriterio.add(Restriccion.EN("persona.id", pFiltro.getListaIdPersona()));
		}

		if(pFiltro.getParcela() != null) {
			locCriterio.add(Restriccion.IGUAL("locParcela", pFiltro.getParcela()));
		} else {
			locCriterio.add(Restriccion.EN("locParcela.id", pFiltro.getListaIdParcela()));
		}

		AtributoDinamico.addRestriccionesCriterio(locCriterio, DocumentoTGI.serialVersionUID, "idDocHabilitanteEspecializado", "locDocumento", pFiltro.getListaAtributosDinamicos());

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, DocumentoTGI.serialVersionUID, "idDocHabilitanteEspecializado", pFiltro.getListaBusquedaPorLogs(), "locDocumento");

		pFiltro.procesarYListar(locCriterio);
		for(Obligacion cadaObligacion : pFiltro.getListaResultados()) {
			if(cadaObligacion.getPersona() != null) {
				cadaObligacion.getPersona().toString();
			}

			if(cadaObligacion.getDocumentoEspecializado().getParcela() != null) {
				cadaObligacion.getDocumentoEspecializado().getParcela().toString();
			}

			if(cadaObligacion.getDocumentoEspecializado().getDomicilio() != null) {
				cadaObligacion.getDocumentoEspecializado().getDomicilio().toString();
			}
		}

		return pFiltro;
	}

	/**
	 * 
	 * @param pObligacion
	 * @return
	 * @throws Exception
	 * @ejb.interface-method view-type = "local"
	 */
	public DocumentoTGI getDocumentoTGI(Obligacion pObligacion) throws Exception {
		DocumentoTGI locDocumentoTGI = (DocumentoTGI) Criterio.getInstance(this.entityManager, DocumentoTGI.class).add(Restriccion.IGUAL("obligacion", pObligacion)).uniqueResult();

		locDocumentoTGI.toString();
		locDocumentoTGI.getObligacion().toString();
		locDocumentoTGI.getListaAtributosDinamicos().size();
		locDocumentoTGI.getDomicilio().getDomicilioCompleto().toString();
		if(locDocumentoTGI.getObligacion().getPersona() != null) {
			locDocumentoTGI.getObligacion().getPersona().toString();
		}
		locDocumentoTGI.getListaLogsAuditoria().size();
		return locDocumentoTGI;
	}
}
