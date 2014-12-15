
package com.trascender.habilitaciones.business.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;
import ar.trascender.validaciones.clases.Validador;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoTasaMenorLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionTasaMenor;
import com.trascender.habilitaciones.recurso.filtros.FiltroPlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.DocHabilitanteEspecializado;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.Obligacion.Estado;
import com.trascender.habilitaciones.recurso.persistent.RegistroExencionObligacion;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.DocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor;
import com.trascender.habilitaciones.recurso.persistent.tasaMenor.PlantillaDocumentoTasaMenor.EstadoPlantillaDocTasaMenor;

@Stateless(name = "ejb/BusinessDocumentoTasaMenorLocal")
public class BusinessDocumentoTasaMenorBean implements BusinessDocumentoTasaMenorLocal {

	private static final long serialVersionUID = -3270134767744216200L;

	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre("HAB|Adm. Obligación Tasa Menor");

		Recurso documentoTasaMenor = new Recurso();
		documentoTasaMenor.setNombre("Obligación Tasa Menor");
		documentoTasaMenor.setIdRecurso(DocumentoTasaMenor.serialVersionUID);
		documentoTasaMenor.setAtributosConsultables("Parcela", "parcela", "Contribuyente", "persona", "Documento", "documentoEspecializado", "Estado", "estado", "Posee Exenciones",
				"stringPoseeExenciones");
		documentoTasaMenor.setClase(DocumentoTasaMenor.class);
		grupo.getListaRecursos().add(documentoTasaMenor);

		Recurso plantillaDocumentoTasaMenor = new Recurso();
		plantillaDocumentoTasaMenor.setNombre("Tipo Obligación Tasa Menor");
		plantillaDocumentoTasaMenor.setIdRecurso(PlantillaDocumentoTasaMenor.serialVersionUID);
		plantillaDocumentoTasaMenor.setAtributosConsultables("Nombre", "nombre");
		plantillaDocumentoTasaMenor.setClase(PlantillaDocumentoTasaMenor.class);
		grupo.getListaRecursos().add(plantillaDocumentoTasaMenor);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	@PersistenceContext
	private EntityManager entity;

	public PlantillaDocumentoTasaMenor addPlantillaDocumentoTasaMenor(PlantillaDocumentoTasaMenor pPlantilla) throws TrascenderException {
		this.validarPlantilla(pPlantilla);

		TrascenderEnverListener.setValoresEnAuditoriaBean(pPlantilla);

		pPlantilla = entity.merge(pPlantilla);
		entity.flush();

		return pPlantilla;
	}

	public void updatePlantillaDocumentoTasaMenor(PlantillaDocumentoTasaMenor pPlantilla) throws TrascenderException {
		this.validarPlantilla(pPlantilla);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pPlantilla);
		entity.merge(pPlantilla);
		entity.flush();
	}

	public void removePlantillaDocumentoTasaMenor(PlantillaDocumentoTasaMenor pPlantilla) {
		pPlantilla.setEstado(EstadoPlantillaDocTasaMenor.INACTIVA);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pPlantilla);
		this.entity.merge(pPlantilla);
		this.entity.flush();
	}

	public FiltroPlantillaDocumentoTasaMenor findListaPlantillaDocumentoTasaMenor(FiltroPlantillaDocumentoTasaMenor pFiltro) {
		Criterio locCriterio = Criterio.getInstance(entity, PlantillaDocumentoTasaMenor.class).add(Restriccion.ILIKE("nombre", pFiltro.getNombre()));
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, PlantillaDocumentoTasaMenor.serialVersionUID, "idDocumentoTasaMenor", pFiltro.getListaBusquedaPorLogs());
		pFiltro.procesarYListar(locCriterio);
		return pFiltro;
	}

	public PlantillaDocumentoTasaMenor getPlantillaDocumentoPorId(long pId) {
		PlantillaDocumentoTasaMenor locPlantilla = entity.find(PlantillaDocumentoTasaMenor.class, pId);
		if(locPlantilla != null) {
			locPlantilla.getListaPlantillasAtributos().size();
			locPlantilla.getListaPlantillasRegistroValuado().size();
			locPlantilla.getTipoObligacion().toString();
			locPlantilla.getListaLogsAuditoria().size();

			for(PlantillaAtributoDinamico cadaPlantilla : locPlantilla.getListaPlantillasAtributos()) {
				cadaPlantilla.getListaLogsAuditoria().size();
			}
			for(PlantillaAtributoDinamico cadaPlantilla : locPlantilla.getListaPlantillasRegistroValuado()) {
				cadaPlantilla.getListaLogsAuditoria().size();
			}
		}
		return locPlantilla;
	}

	private void validarPlantilla(PlantillaDocumentoTasaMenor pPlantilla) throws HabilitacionesException {
		Validador locValidador = Validador.getInstance(entity, pPlantilla);
		locValidador.validarUnico("nombre", new HabilitacionesException(725));

		List<String> locListaValidacionNombres = new ArrayList<String>();

		for(PlantillaAtributoDinamico cadaPlantillaAtributo : pPlantilla.getListaPlantillasAtributos()) {
			cadaPlantillaAtributo.setIdRecurso(PlantillaDocumentoTasaMenor.serialVersionUID);
			for(String cadaNombre : locListaValidacionNombres) {
				if(cadaNombre.equalsIgnoreCase(cadaPlantillaAtributo.getNombre())) {
					throw new HabilitacionesException(727);
				}
			}
			locListaValidacionNombres.add(cadaPlantillaAtributo.getNombre());
		}

		for(PlantillaAtributoDinamico cadaPlantillaAtributo : pPlantilla.getListaPlantillasRegistroValuado()) {
			cadaPlantillaAtributo.setIdRecurso(PlantillaDocumentoTasaMenor.serialVersionUID);
			for(String cadaNombre : locListaValidacionNombres) {
				if(cadaNombre.equalsIgnoreCase(cadaPlantillaAtributo.getNombre())) {
					throw new HabilitacionesException(727);
				}
			}
			locListaValidacionNombres.add(cadaPlantillaAtributo.getNombre());
		}

		pPlantilla.getTipoObligacion().setNombre(pPlantilla.getNombre());
	}

	public void addDocumentoTasaMenor(DocumentoTasaMenor pDocumento) throws TrascenderException {
		this.validarDocumentoTasaMenor(pDocumento);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pDocumento);
		entity.persist(pDocumento);
		// Se agrega flush para los atributos dinámicos.
		entity.flush();
		if(pDocumento.getParcela() != null) {
			this.actualizarCantidadPropiedades(pDocumento);

		}
	}

	public void updateDocumentoTasaMenor(DocumentoTasaMenor pDocumento) throws TrascenderException {
		this.validarDocumentoTasaMenor(pDocumento);
		entity.merge(pDocumento);
		// Se agregar flush para los atributos dinámicos
		entity.flush();
		if(pDocumento.getParcela() != null) {
			this.actualizarCantidadPropiedades(pDocumento);
		}
	}

	private void validarDocumentoTasaMenor(DocumentoTasaMenor pDocumento) throws HabilitacionesException {
		Long cantidad = Criterio.getInstance(this.entity, DocumentoTasaMenor.class)
				.add(Restriccion.DISTINTO("idDocHabilitanteEspecializado", pDocumento.getIdDocHabilitanteEspecializado()))
				.add(Restriccion.DISTINTO("obligacion.estado", Estado.ANULADO)).add(Restriccion.IGUAL("estado", DocHabilitanteEspecializado.Estado.ACTIVO))
				.add(Restriccion.IGUAL("obligacion.persona", pDocumento.getObligacion().getPersona())).add(Restriccion.IGUAL("parcela", pDocumento.getParcela()))
				.add(Restriccion.IGUAL("plantillaDocumentoTasaMenor", pDocumento.getPlantillaDocumentoTasaMenor())).setProyeccion(Proyeccion.COUNT()).uniqueResult();

		if(cantidad.longValue() > 0) {
			throw new HabilitacionesException(726);
		}
	}

	public void removeDocumentoTasaMenor(DocumentoTasaMenor pDocumento) throws TrascenderException {
		entity.merge(pDocumento);
		entity.remove(pDocumento);
	}

	public List<DocumentoTasaMenor> findListaDocumentoTasaMenor(Persona pPersona, Parcela pParcela) {
		return Criterio.getInstance(entity, DocumentoTasaMenor.class).add(Restriccion.IGUAL("obligacion.persona", pPersona)).add(Restriccion.IGUAL("parcela", pParcela)).list();
	}

	public DocumentoTasaMenor getDocumentoTasaMenor(Obligacion pObligacion) {
		DocumentoTasaMenor locDocumento = Criterio.getInstance(entity, DocumentoTasaMenor.class).add(Restriccion.IGUAL("obligacion", pObligacion)).uniqueResult();
		if(locDocumento != null) {
			locDocumento.toString();
			locDocumento.getDomicilio().toString();
			locDocumento.getObligacion().toString();
			locDocumento.getObligacion().getPersona().toString();
			locDocumento.getListaAtributosDinamicos().size();
			locDocumento.getListaLogsAuditoria().size();
			locDocumento.getPlantillaDocumentoTasaMenor().getListaPlantillasAtributos().size();
			if(locDocumento.getParcela() != null) {
				locDocumento.getParcela().toString();
			}
		}
		return locDocumento;
	}

	public FiltroObligacionTasaMenor findListaObligacionesTasaMenor(FiltroObligacionTasaMenor pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entity, Obligacion.class).crearAlias("documentoEspecializado", "locDocumento").crearAlias("locDocumento.parcela", "locParcela")
				.add(Restriccion.JPQL("TYPE (locDocumento) = ".concat(DocumentoTasaMenor.class.getSimpleName())))

				.add(Restriccion.IGUAL("locDocumento.plantillaDocumentoTasaMenor", pFiltro.getPlantillaDocumentoTasaMenor())).setModoDebug(true);
		if(pFiltro.getNroParcela() != null) {
			locCriterio.add(Restriccion.ILIKE("locParcela.nomenclaturaCatastral.nroParcela", pFiltro.getNroParcela().toString()));
		}

		if(pFiltro.getPersona() != null) {
			locCriterio.add(Restriccion.IGUAL("persona", pFiltro.getPersona()));
		} else {
			locCriterio.add(Restriccion.EN("persona.id", pFiltro.getListaIdPersonas()));
		}

		if(pFiltro.getParcela() != null) {
			locCriterio.add(Restriccion.IGUAL("locParcela", pFiltro.getParcela()));
		} else {
			locCriterio.add(Restriccion.EN("locParcela.id", pFiltro.getListaIdParcelas()));
		}

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, DocumentoTasaMenor.serialVersionUID, "idDocHabilitanteEspecializado", pFiltro.getListaBusquedaPorLogs(), "locDocumento");

		pFiltro.procesarYListar(locCriterio);

		for(Obligacion cadaObligacion : pFiltro.getListaResultados()) {
			cadaObligacion.getPersona().toString();
			cadaObligacion.getListaRegistrosExencion().toString();

			if(cadaObligacion.getDocumentoEspecializado() != null) {
				DocumentoTasaMenor locDocTasaMenor = (DocumentoTasaMenor) cadaObligacion.getDocumentoEspecializado();
				locDocTasaMenor.getPlantillaDocumentoTasaMenor().toString();
			}

			if(cadaObligacion.getListaDocumentosHabilitantes() != null) {
				cadaObligacion.getListaDocumentosHabilitantes().size();
			}
			for(RegistroExencionObligacion cadaRegistro : cadaObligacion.getListaRegistrosExencion()) {
				cadaRegistro.toString();
			}
			if(cadaObligacion.getDocumentoEspecializado().getParcela() != null) {
				cadaObligacion.getDocumentoEspecializado().getParcela().toString();
			}
		}
		return pFiltro;
	}

	/**
	 * Actualiza la cantidad de propiedades que una persona posee
	 * 
	 * @param pPersona
	 * @param pPropiedad
	 *            cantidad de propiedades que agrega
	 * @throws Exception
	 */
	private void actualizarCantidadPropiedades(DocumentoTasaMenor pDocumento) {

		Long cantidad = Criterio.getInstance(this.entity, Obligacion.class).add(Restriccion.IGUAL("persona", pDocumento.getObligacion().getPersona()))
				.add(ar.trascender.criterio.clases.Grupo.POR("documentoEspecializado.parcela.idParcela")).setProyeccion(Proyeccion.COUNT()).uniqueResult();

		pDocumento.getObligacion().getPersona().setCantidadPropiedades(cantidad.intValue());

		entity.merge(pDocumento.getObligacion().getPersona());
	}
}
