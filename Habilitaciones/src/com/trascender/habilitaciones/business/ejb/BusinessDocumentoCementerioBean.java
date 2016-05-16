
package com.trascender.habilitaciones.business.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.framework.business.interfaces.BusinessParametroLocal;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.AtributoConsultable.Tipo;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.BusquedaPorLog;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.framework.util.TrascenderEnverListener;
import com.trascender.habilitaciones.business.interfaces.BusinessDocumentoCementerioLocal;
import com.trascender.habilitaciones.exception.HabilitacionesException;
import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionCementerio;
import com.trascender.habilitaciones.recurso.filtros.FiltroParcelaCementerio;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoSepultura;
import com.trascender.habilitaciones.recurso.persistent.AsocRegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.cementerio.Concesion;
import com.trascender.habilitaciones.recurso.persistent.cementerio.DocumentoCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;

@Stateless(name = "ejb/BusinessDocumentoCementerioLocal")
public class BusinessDocumentoCementerioBean implements BusinessDocumentoCementerioLocal {

	private static final long serialVersionUID = -4876213812611831029L;

	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre("HAB|Adm. Obligación Cementerio");

		Recurso documentoCementerio = new Recurso();
		documentoCementerio.setNombre("Obligación Cementerio");
		documentoCementerio.setIdRecurso(DocumentoCementerio.serialVersionUID);
		documentoCementerio.setAtributosConsultables("Contribuyente", "persona");
		documentoCementerio.setClase(DocumentoCementerio.class);
		grupo.getListaRecursos().add(documentoCementerio);

		Recurso tipoSepultura = new Recurso();
		tipoSepultura.setNombre("Tipo Sepultura");
		tipoSepultura.setIdRecurso(TipoSepultura.serialVersionUID);
		tipoSepultura.setAtributosConsultables("Código", "codigo", "Descripción", "nombre", "Valor", "valor", Tipo.MONTO);
		tipoSepultura.setClase(TipoSepultura.class);
		grupo.getListaRecursos().add(tipoSepultura);

		Recurso parcelaCementerio = new Recurso();
		parcelaCementerio.setNombre("Parcela Cementerio");
		parcelaCementerio.setIdRecurso(ParcelaCementerio.serialVersionUID);
		parcelaCementerio.setAtributosConsultables("Tipo Sepultura", "registroAlicuota", "Superficie", "superficie");
		parcelaCementerio.setClase(ParcelaCementerio.class);
		grupo.getListaRecursos().add(parcelaCementerio);

		Recurso concesion = new Recurso();
		concesion.setNombre("Concesión");
		concesion.setIdRecurso(Concesion.serialVersionUID);
		concesion.setClase(Concesion.class);
		grupo.getListaRecursos().add(concesion);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	@EJB
	private BusinessParametroLocal businessParametro;

	@PersistenceContext
	private EntityManager entityManager;

	public DocumentoCementerio addDocumentoCementerio(DocumentoCementerio pDocumentoCementerio) throws Exception {
		this.validarDocumento(pDocumentoCementerio);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pDocumentoCementerio);
		this.entityManager.merge(pDocumentoCementerio);
		this.entityManager.flush();

		return pDocumentoCementerio;
	}

	public DocumentoCementerio updateDocumentoCementerio(DocumentoCementerio pDocumentoCementerio) throws Exception {

		this.validarParcelasCementerio(pDocumentoCementerio);

		DocumentoCementerio locDocumentoViejo = (DocumentoCementerio) Criterio.getInstance(this.entityManager, DocumentoCementerio.class)
				.add(Restriccion.IGUAL("idDocHabilitanteEspecializado", pDocumentoCementerio.getIdDocHabilitanteEspecializado())).uniqueResult();

		try {
			com.trascender.habilitaciones.util.Util.getInstance(this.entityManager).deleteDomicilioExcluyente(locDocumentoViejo, pDocumentoCementerio);
		} catch(Exception e) {
		}

		if(pDocumentoCementerio.getDomicilio().getIdDomicilio() == -1) {
			this.entityManager.persist(pDocumentoCementerio.getDomicilio());
			this.entityManager.merge(pDocumentoCementerio.getDomicilio());
		}

		this.entityManager.detach(locDocumentoViejo);
		pDocumentoCementerio.getObligacion().setDocumentoEspecializado(pDocumentoCementerio);
		this.entityManager.merge(pDocumentoCementerio);
		this.entityManager.flush();

		return pDocumentoCementerio;
	}

	public void deleteDocumentoCementerio(DocumentoCementerio pDocumentoCementerio) throws Exception {
		pDocumentoCementerio.getObligacion().terminar();
		this.entityManager.merge(pDocumentoCementerio.getObligacion());
	}

	public FiltroObligacionCementerio findListaObligacionesCementerio(FiltroObligacionCementerio pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, Obligacion.class)
				.setModoDebug(true)
				.crearAlias("documentoEspecializado", "locDocEsp")
				.add(Restriccion.IGUAL("locDocEsp.numeroCuenta", pFiltro.getNumeroCuenta()))
				.add(Restriccion.JPQL("TYPE (locDocEsp) = ".concat(DocumentoCementerio.class.getSimpleName())));

		AtributoDinamico.addRestriccionesCriterio(locCriterio, DocumentoCementerio.serialVersionUID, "idDocHabilitanteEspecializado", "locDocEsp", pFiltro.getListaAtributosDinamicos());

		locCriterio.add(Restriccion.MIEMBRO_DE("locDocEsp.listaAsocRegAlicuota", pFiltro.getParcelaCementerio())).add(Restriccion.IGUAL("estado", pFiltro.getEstado()));

		BusquedaPorLog.addRestriccionesCriterio(locCriterio, DocumentoCementerio.serialVersionUID, "idDocHabilitanteEspecializado", pFiltro.getListaBusquedaPorLogs(), "locDocEsp");

		if(pFiltro.getPersona() != null) {
			locCriterio.add(Restriccion.IGUAL("persona", pFiltro.getPersona()));
		} else {
			locCriterio.add(Restriccion.EN("persona.id", pFiltro.getListaIdPersonas()));
		}

		pFiltro.procesarYListar(locCriterio);

		for(Obligacion cadaObligacion : pFiltro.getListaResultados()) {
			cadaObligacion.toString();
			cadaObligacion.getPersona().toString();
			cadaObligacion.getDocumentoEspecializado().toString();
		}
		return pFiltro;
	}

	public DocumentoCementerio getDocumentoCementerio(Obligacion pObligacion) throws Exception {
		DocumentoCementerio locDocumentoCementerio = (DocumentoCementerio) Criterio.getInstance(this.entityManager, DocumentoCementerio.class)
				.add(Restriccion.IGUAL("obligacion", pObligacion)).uniqueResult();

		locDocumentoCementerio.toString();
		locDocumentoCementerio.getObligacion().toString();
		locDocumentoCementerio.getListaAtributosDinamicos().size();
		locDocumentoCementerio.getDomicilio().toString();
		locDocumentoCementerio.getListaLogsAuditoria().size();
		if(locDocumentoCementerio.getObligacion().getPersona() != null) {
			locDocumentoCementerio.getObligacion().getPersona().toString();
		}

		for(AsocRegAlicuota cadaAsocRegAlicuota : locDocumentoCementerio.getListaAsocRegAlicuota()) {
			cadaAsocRegAlicuota.getRegistroAlicuota().toString();
		}

		return locDocumentoCementerio;
	}

	public TipoSepultura addTipoSepultura(TipoSepultura pTipoSepultura) throws Exception {
		TrascenderEnverListener.setValoresEnAuditoriaBean(pTipoSepultura);
		this.entityManager.persist(pTipoSepultura);
		this.entityManager.flush();
		return pTipoSepultura;
	}

	public TipoSepultura updateTipoSepultura(TipoSepultura pTipoSepultura) throws Exception {
		TrascenderEnverListener.setValoresEnAuditoriaBean(pTipoSepultura);
		this.entityManager.merge(pTipoSepultura);
		this.entityManager.flush();
		return pTipoSepultura;
	}

	public void deleteTipoSepultura(TipoSepultura pTipoSepultura) throws Exception {

	}

	public FiltroTipoSepultura findListaTipoSepultura(FiltroTipoSepultura pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, TipoSepultura.class).add(Restriccion.IGUAL("codigo", pFiltro.getCodigo()))
				.add(Restriccion.ILIKE("nombre", pFiltro.getDescripcion()));

		AtributoDinamico.addRestriccionesCriterio(locCriterio, TipoSepultura.serialVersionUID, "idTipoAlicuota", pFiltro.getListaAtributosDinamicos());
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, TipoSepultura.serialVersionUID, "idTipoAlicuota", pFiltro.getListaBusquedaPorLogs());

		pFiltro.procesarYListar(locCriterio);

		return pFiltro;
	}

	public TipoSepultura getTipoSepulturaById(long pId) throws Exception {
		TipoSepultura locTipoSepultura = Criterio.getInstance(this.entityManager, TipoSepultura.class).add(Restriccion.IGUAL("idTipoAlicuota", pId)).uniqueResult();

		if(locTipoSepultura != null) {
			locTipoSepultura.toString();
			locTipoSepultura.getListaAtributosDinamicos().size();
			locTipoSepultura.getListaLogsAuditoria().size();
		}

		return locTipoSepultura;
	}

	public ParcelaCementerio addParcelaCementerio(ParcelaCementerio pParcelaCementerio) throws Exception {

		validarParcelaCementerio(pParcelaCementerio);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pParcelaCementerio);
		this.entityManager.persist(pParcelaCementerio);
		this.entityManager.flush();

		return pParcelaCementerio;
	}

	public ParcelaCementerio updateParcelaCementerio(ParcelaCementerio pParcelaCementerio) throws Exception {

		validarParcelaCementerio(pParcelaCementerio);
		TrascenderEnverListener.setValoresEnAuditoriaBean(pParcelaCementerio);
		this.entityManager.merge(pParcelaCementerio);
		this.entityManager.flush();
		return pParcelaCementerio;
	}

	public void deleteParcelaCementerio(ParcelaCementerio pParcelaCementerio) throws Exception {
		TrascenderEnverListener.setValoresEnAuditoriaBean(pParcelaCementerio);
		this.entityManager.merge(pParcelaCementerio);
		this.entityManager.flush();
	}

	public FiltroParcelaCementerio findListaParcelaCementerio(FiltroParcelaCementerio pFiltro) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entityManager, ParcelaCementerio.class)
				.add(Restriccion.IGUAL("registroAlicuota", pFiltro.getTipoSepultura()))
				.add(Restriccion.IGUAL("listaDifuntos.persona", pFiltro.getPersonaDifunto()))
				.add(Restriccion.IGUAL("docHabilitanteEspecializado.numeroCuenta", pFiltro.getNumeroCuenta()))
				.setModoDebug(true);
		
		

		AtributoDinamico.addRestriccionesCriterio(locCriterio, ParcelaCementerio.serialVersionUID, "idAsocRegAlicuota", pFiltro.getListaAtributoDinamico());
		AtributoDinamico.addRestriccionesCriterio(locCriterio, Concesion.serialVersionUID, "concesion.idTituloPropiedad", pFiltro.getListaAtributoDinamico2());
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, ParcelaCementerio.serialVersionUID, "idAsocRegAlicuota", pFiltro.getListaBusquedaPorLogs());
		BusquedaPorLog.addRestriccionesCriterio(locCriterio, Concesion.serialVersionUID, "concesion.idTituloPropiedad", pFiltro.getListaBusquedaPorLogs());

		if(pFiltro.getTitular() != null) {
			locCriterio.crearAlias("concesion.listaRegistrosPropietarios", "cadaPropietario").add(Restriccion.IGUAL("cadaPropietario.persona", pFiltro.getTitular()));
		} else {
			locCriterio.crearAlias("concesion.listaRegistrosPropietarios", "cadaPropietario").add(Restriccion.EN("cadaPropietario.persona.id", pFiltro.getListaIdPersonas()));
		}

		pFiltro.procesarYListar(locCriterio);

		for(ParcelaCementerio cadaParcela : pFiltro.getListaResultados()) {
			cadaParcela.toString();
			cadaParcela.getListaDifuntos().toString();
			cadaParcela.getRegistroAlicuota().toString();
			if(cadaParcela.getDocHabilitanteEspecializado() != null) {
				cadaParcela.getDocHabilitanteEspecializado().toString();
			}

			for(AtributoDinamico<?> cadaAtributo : cadaParcela.getListaAtributosDinamicos()) {
				cadaAtributo.toString();
			}
		}

		return pFiltro;
	}

	public ParcelaCementerio getParcelaCementerioById(long pId) throws Exception {
		ParcelaCementerio locParcelaCementerio = Criterio.getInstance(this.entityManager, ParcelaCementerio.class).add(Restriccion.IGUAL("idAsocRegAlicuota", pId)).uniqueResult();

		if(locParcelaCementerio != null) {
			locParcelaCementerio.toString();

			if(locParcelaCementerio.getConcesion() != null) {
				locParcelaCementerio.getConcesion().getListaLogsAuditoria().size();
				for(AtributoDinamico<?> cadaAtributoDinamico : locParcelaCementerio.getConcesion().getListaAtributosDinamicos()) {
					cadaAtributoDinamico.toString();
				}
			}
			locParcelaCementerio.getListaLogsAuditoria().size();
			for(AtributoDinamico<?> cadaAtributoDinamico : locParcelaCementerio.getListaAtributosDinamicos()) {
				cadaAtributoDinamico.toString();
			}
			locParcelaCementerio.getListaDifuntos().size();
		}

		return locParcelaCementerio;
	}

	private void validarParcelaCementerio(ParcelaCementerio pParcela) throws HabilitacionesException {
		if(pParcela.getRegistroAlicuota() == null || pParcela.getRegistroAlicuota().getIdTipoAlicuota() == -1) {
			throw new HabilitacionesException(850);
		}
	}

	private void validarDocumento(DocumentoCementerio pDocumentoCementerio) {
		for(AsocRegAlicuota cadaAsoc : pDocumentoCementerio.getListaAsocRegAlicuota()) {
			cadaAsoc.setDocHabilitanteEspecializado(pDocumentoCementerio);
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
