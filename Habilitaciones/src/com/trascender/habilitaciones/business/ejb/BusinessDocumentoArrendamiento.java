package com.trascender.habilitaciones.business.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoArrendamientoLocal;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionArrendamiento;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.arrendamiento.DocumentoArrendamiento;
import com.trascender.habilitaciones.recurso.persistent.tgi.DocumentoTGI;

@Stateless(name = "ejb/BusinessDocumentoArrendamientoLocal")
public class BusinessDocumentoArrendamiento implements BusinessDocumentoArrendamientoLocal{
	private static final long serialVersionUID = -8347840102829607818L;

	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre("HAB|Adm. Obligación Arrendamiento");

		Recurso documentoArrendamiento = new Recurso();
		documentoArrendamiento.setNombre("Obligación Arrendamiento");
		documentoArrendamiento.setIdRecurso(DocumentoArrendamiento.serialVersionUID);
		documentoArrendamiento.setAtributosConsultables("Contribuyente", "persona", "Parcela", "parcela", "Calle", "calle", "Altura", "altura");
		documentoArrendamiento.setClase(DocumentoTGI.class);
		grupo.getListaRecursos().add(documentoArrendamiento);

		SecurityMgr.getInstance().addGrupo(grupo);
	}
	
	@PersistenceContext(name = "Vipians")
	private EntityManager entityManager;

	@Override
	public void addDocumentoArrendamiento(DocumentoArrendamiento pDocumento) {
		entityManager.merge(pDocumento);
	}

	@Override
	public void updateDocumentoArrendamiento(DocumentoArrendamiento pDocumento) {
		entityManager.merge(pDocumento);		
	}

	@Override
	public void deleteDocumentoArrendamiento(DocumentoArrendamiento pDocumento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FiltroObligacionArrendamiento findListaObligacionesArrendamiento(
			FiltroObligacionArrendamiento pFiltro) {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, Obligacion.class)
				.setModoDebug(true)
				.crearAlias("documentoEspecializado", "locDocEsp")
				.add(Restriccion.IGUAL("estado", pFiltro.getEstado()))
				.add(Restriccion.IGUAL("locDocEsp.parcela", pFiltro.getParcela()))
				.add(Restriccion.TYPE("locDocEsp", DocumentoArrendamiento.class));
		AtributoDinamico.addRestriccionesCriterio(locCriterio, DocumentoArrendamiento.serialVersionUID, "idDocHabilitanteEspecializado", "locDocEsp", pFiltro.getListaAtributosDinamicos());
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, DocumentoArrendamiento.serialVersionUID, "idDocHabilitanteEspecializado", pFiltro.getListaBusquedaPorLogs(), "locDocEsp");

		if(pFiltro.getPersona() != null) {
			locCriterio.add(Restriccion.IGUAL("persona", pFiltro.getPersona()));
		} else {
			locCriterio.add(Restriccion.EN("persona.id", pFiltro.getListaIdPersona()));
		}
		

		pFiltro.procesarYListar(locCriterio);

		for(Obligacion cadaObligacion : pFiltro.getListaResultados()) {
			cadaObligacion.toString();
			cadaObligacion.getPersona().toString();
			cadaObligacion.getDocumentoEspecializado().getParcela().getStringListaPropietarios();
			cadaObligacion.getDocumentoEspecializado().getDomicilio().toString();
		}
		return pFiltro;
	}

	@Override
	public DocumentoArrendamiento getDocumentoArrendamiento(Obligacion pObligacion) {
		DocumentoArrendamiento locDocumento =  Criterio.getInstance(entityManager, DocumentoArrendamiento.class)
				.add(Restriccion.IGUAL("obligacion", pObligacion))
				.uniqueResult();
		locDocumento.getParcela().toString();
		locDocumento.getObligacion().getPersona().toString();
		locDocumento.getListaLogsAuditoria().size();
		locDocumento.getListaLogsModificaciones().size();
		locDocumento.getDomicilio().toString();
		locDocumento.getListaAtributosDinamicos().size();
		return locDocumento;
	}

}
