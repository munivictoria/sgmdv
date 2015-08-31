/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.business.ejb;

import java.rmi.RemoteException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.trascender.criterio.clases.Criterio;
import ar.trascender.criterio.clases.Proyeccion;
import ar.trascender.criterio.clases.Restriccion;

import com.trascender.expedientes.business.interfaces.BusinessCatalogos;
import com.trascender.expedientes.enums.EstadoPlantilla;
import com.trascender.expedientes.exception.CatalogosException;
import com.trascender.expedientes.recurso.filtro.FiltroDocumentoCatalogo;
import com.trascender.expedientes.recurso.filtro.FiltroEstadoTramite;
import com.trascender.expedientes.recurso.filtro.FiltroFaseCatalogo;
import com.trascender.expedientes.recurso.filtro.FiltroTramiteCatalogo;
import com.trascender.expedientes.recurso.persistent.DocumentoCatalogo;
import com.trascender.expedientes.recurso.persistent.EstadoTramite;
import com.trascender.expedientes.recurso.persistent.FaseCatalogo;
import com.trascender.expedientes.recurso.persistent.FaseProcedimiento;
import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;
import com.trascender.expedientes.recurso.persistent.TramiteProcedimiento;
import com.trascender.framework.recurso.transients.Grupo;
import com.trascender.framework.recurso.transients.Recurso;
import com.trascender.framework.util.SecurityMgr;

@Stateless(name = "BusinessCatalogos")
public class BusinessCatalogosBean implements BusinessCatalogos {

	private static final int FASE_NOMBRE_REPETIDO = 1;
	private static final int TRAMITTE_NOMBRE_REPETIDO = 2;
	private static final int DOCUMENTO_REPETIDO_TRAMITTE = 3;
	private static final int ESTADO_TRAMITE = 4;
	private static final int FALATA_ESTADO_TRAMITE = 5;
	private static final int RECUPERAR_TRAMITE_CATALOGO = 6;
	private static final int RECUPERAR_FASE_CATALOGO = 7;
	private static final int RECUPERAR_DOCUEMENTO_CATALOGO = 8;
	private static final int RECUPERAR_ESTADO_TRAMITE_CATALOGO = 9;

	@PersistenceContext(name = "Vipians")
	private EntityManager entity;

	private static final long serialVersionUID = 6702215392234132101L;
	public static final String NAME = "EXP|Adm. de Cat\341logos";

	static {
		Grupo grupo = new Grupo();
		grupo.setId(serialVersionUID);
		grupo.setNombre(NAME);

		Recurso tramiteCatalogo = new Recurso();
		tramiteCatalogo.setIdRecurso(TramiteCatalogo.serialVersionUID);
		tramiteCatalogo.setNombre("Tr\341mite de Cat\341logo");
		tramiteCatalogo.setAtributosConsultables("Nombre", "nombre");
		tramiteCatalogo.setClase(TramiteCatalogo.class);
		grupo.getListaRecursos().add(tramiteCatalogo);

		Recurso faseCatalogo = new Recurso();
		faseCatalogo.setIdRecurso(FaseCatalogo.serialVersionUID);
		faseCatalogo.setNombre("Fase de Cat\341logo");
		faseCatalogo.setAtributosConsultables("Nombre", "nombre");
		faseCatalogo.setClase(FaseCatalogo.class);
		grupo.getListaRecursos().add(faseCatalogo);

		Recurso documentoCatalogo = new Recurso();
		documentoCatalogo.setIdRecurso(DocumentoCatalogo.serialVersionUID);
		documentoCatalogo.setNombre("Documento de Cat\341logo");
		documentoCatalogo.setAtributosConsultables("Nombre", "nombre", "Tipo", "tipo");
		documentoCatalogo.setClase(DocumentoCatalogo.class);
		grupo.getListaRecursos().add(documentoCatalogo);

		Recurso estadosTramite = new Recurso();
		estadosTramite.setIdRecurso(EstadoTramite.serialVersionUID);
		estadosTramite.setNombre("Estado Tr\341mite");
		estadosTramite.setAtributosConsultables("Nombre", "nombre");
		estadosTramite.setClase(EstadoTramite.class);
		grupo.getListaRecursos().add(estadosTramite);

		SecurityMgr.getInstance().addGrupo(grupo);
	}

	@Override
	public void addTramiteCatalogo(TramiteCatalogo pTramiteCatalogo) throws RemoteException, CatalogosException, Exception {
		validacionTramteNombreRepetido(pTramiteCatalogo);
		entity.persist(pTramiteCatalogo);
	}

	@Override
	public void updateTramiteCatalogo(TramiteCatalogo pTramiteCatalogo) throws RemoteException, CatalogosException, Exception {
		validarTramiteCatalogo(pTramiteCatalogo);
		entity.merge(pTramiteCatalogo);
	}

	@Override
	public String deleteTramiteCatalogo(TramiteCatalogo pTramiteCatalogo) throws Exception, RemoteException {
		Criterio locCriterio1 = Criterio.getInstance(this.entity, FaseCatalogo.class)
				.crearAlias("listaTramitesCatalogos", "tramite")
				.add(Restriccion.IGUAL("tramite.idTramiteCatalogo", pTramiteCatalogo.getIdTramiteCatalogo()))
				.setProyeccion(Proyeccion.COUNT());
		Criterio locCriterio2 = Criterio.getInstance(this.entity, TramiteProcedimiento.class)
				.add(Restriccion.IGUAL("tramiteCatalogo", pTramiteCatalogo))
				.setProyeccion(Proyeccion.COUNT());

		Long cantidad1 = locCriterio1.uniqueResult();
		Long cantidad2 = locCriterio2.uniqueResult();
		if((cantidad1 + cantidad2) > 0) {
			pTramiteCatalogo.setEstado(EstadoPlantilla.BAJA);
			entity.merge(pTramiteCatalogo);
			return "El Tramite Catalago se ha dado de baja exitosamente";
		} else {
			pTramiteCatalogo = entity.find(TramiteCatalogo.class, pTramiteCatalogo.getIdTramiteCatalogo());
			entity.remove(pTramiteCatalogo);
			return "El Tramite Catalago se ha eliminado exitosamente";
		}
	}

	@Override
	public TramiteCatalogo getTramiteCatalogoPorId(long pId) throws Exception, RemoteException {
		TramiteCatalogo locTramiteCatalogo = (TramiteCatalogo) Criterio.getInstance(this.entity, TramiteCatalogo.class)
				.add(Restriccion.IGUAL("idTramiteCatalogo", pId))
				.uniqueResult();

		locTramiteCatalogo.toString();
		locTramiteCatalogo.getListaDocumentosCatalogos().size();
		locTramiteCatalogo.getListaAtributosDinamicos().size();
		locTramiteCatalogo.getListaEstadosTramite().size();
		
		return entity.find(TramiteCatalogo.class, pId);
	}

	private void validarTramiteCatalogo(TramiteCatalogo pTramiteCatalogo) throws Exception {
		Criterio locCriterio = Criterio.getInstance(this.entity, TramiteCatalogo.class)
				.add(Restriccion.LIKE("nombre", pTramiteCatalogo.getNombre(), false))
				.add(Restriccion.DISTINTO("idTramiteCatalogo", pTramiteCatalogo.getIdTramiteCatalogo()))
				.setProyeccion(Proyeccion.COUNT());

		if((Long) locCriterio.uniqueResult() > 0) {
			throw new CatalogosException(2);
		}
		if(pTramiteCatalogo.getListaEstadosTramite().size() <= 0) {
			throw new CatalogosException(FALATA_ESTADO_TRAMITE);
		}
	}

	@Override
	public FiltroTramiteCatalogo findListaTramiteCatalogos(FiltroTramiteCatalogo pFiltro) throws Exception, RemoteException {
		Criterio locCriterio = Criterio.getInstance(entity, TramiteCatalogo.class)
				.add(Restriccion.ILIKE("nombre", pFiltro.getNombre()))
				.add(Restriccion.IGUAL("estado", pFiltro.getEstado()));
		
		pFiltro.procesarYListar(locCriterio);
		
		return pFiltro;
	}

	@Override
	public void addFaseCatalogo(FaseCatalogo pFaseCatalogo) throws RemoteException, CatalogosException, Exception {
		validacionFaseNombreRepetido(pFaseCatalogo);
		entity.persist(pFaseCatalogo);
	}

	@Override
	public void updateFaseCatalogo(FaseCatalogo pFaseCatalogo) throws RemoteException, CatalogosException, Exception {
		validacionFaseNombreRepetido(pFaseCatalogo);
		entity.merge(pFaseCatalogo);
	}

	@Override
	public String deleteFaseCatalogo(FaseCatalogo pFaseCatalogo) throws Exception, RemoteException {
		Criterio locCriterio = Criterio.getInstance(this.entity, FaseCatalogo.class)
				.crearAlias("listaFasesEspeciales", "fasesEspeciales")
				.add(Restriccion.IGUAL("fasesEspeciales.idFaseCatalogo", pFaseCatalogo.getIdFaseCatalogo()))
				.setProyeccion(Proyeccion.COUNT());
		
		Criterio criterio = Criterio.getInstance(this.entity, FaseProcedimiento.class)
				.add(Restriccion.IGUAL("faseCatalogo.idFaseCatalogo", pFaseCatalogo.getIdFaseCatalogo()))
				.setProyeccion(Proyeccion.COUNT());
		Long cant = criterio.uniqueResult();

		Long cantidad = locCriterio.uniqueResult();
		if(cantidad > 0 || cant > 0) {
			pFaseCatalogo.setEstado(EstadoPlantilla.BAJA);
			entity.merge(pFaseCatalogo);
			return "La Fase Catalago se ha dado de baja exitosamente"; 
		} else {
			pFaseCatalogo = entity.find(FaseCatalogo.class, pFaseCatalogo.getIdFaseCatalogo());
			entity.remove(pFaseCatalogo);
			return "La Fase Catalago se ha eliminado exitosamente"; 
		}
	}

	@Override
	public FaseCatalogo getFaseCatalogoPorId(long pId) throws Exception, RemoteException {
		FaseCatalogo locFaseCatalogo = (FaseCatalogo) Criterio.getInstance(this.entity, FaseCatalogo.class)
				.add(Restriccion.IGUAL("idFaseCatalogo", pId))
				.uniqueResult();

		refrescarFasesCatalogos(locFaseCatalogo);

		return locFaseCatalogo;
	}

	private void refrescarFasesCatalogos(FaseCatalogo pFaseCatalogo) {
		pFaseCatalogo.toString();
		pFaseCatalogo.getListaAtributosDinamicos().size();
		for(TramiteCatalogo tc : pFaseCatalogo.getListaTramitesCatalogos()) {
			tc.getListaDocumentosCatalogos().size();
		}
		for(FaseCatalogo fc : pFaseCatalogo.getListaFasesEspeciales()) {
			refrescarFasesCatalogos(fc);
		}
	}

	@Override
	public FiltroFaseCatalogo findListaFaseCatalogo(FiltroFaseCatalogo pFiltro) throws Exception, RemoteException {
		Criterio locCriterio = Criterio.getInstance(entity, FaseCatalogo.class)
				.add(Restriccion.ILIKE("nombre", pFiltro.getNombre()))
				.add(Restriccion.IGUAL("estado", pFiltro.getEstado()));
		
		pFiltro.procesarYListar(locCriterio);
		
		return pFiltro;
	}

	@Override
	public void addDocumentoCatalogo(DocumentoCatalogo pDocumentoCatalogo) throws RemoteException, CatalogosException, Exception {
		validacionDocumentoNombreRepetido(pDocumentoCatalogo);
		entity.persist(pDocumentoCatalogo);
	}

	@Override
	public void updateDocumentoCatalogo(DocumentoCatalogo pDocumentoCatalogo) throws RemoteException, CatalogosException, Exception {
		validacionDocumentoNombreRepetido(pDocumentoCatalogo);
		entity.merge(pDocumentoCatalogo);
	}

	@Override
	public String deleteDocumentoCatalogo(DocumentoCatalogo pDocumentoCatalogo) throws Exception, RemoteException {
		Criterio locCriterio = Criterio.getInstance(this.entity, TramiteCatalogo.class)
				.crearAlias("listaDocumentosCatalogos", "documentos")
				.add(Restriccion.IGUAL("documentos.idDocumentoCatalogo", pDocumentoCatalogo.getIdDocumentoCatalogo()))
				.setProyeccion(Proyeccion.COUNT());

		Long cantidad = locCriterio.uniqueResult();
		if(cantidad > 0) {
			pDocumentoCatalogo.setEstado(EstadoPlantilla.BAJA);
			entity.merge(pDocumentoCatalogo);
			return "El Documento Catalago se ha dado de baja exitosamente";
		} else {
			pDocumentoCatalogo = entity.find(DocumentoCatalogo.class, pDocumentoCatalogo.getIdDocumentoCatalogo());
			entity.remove(pDocumentoCatalogo);
			return "El Documento Catalago se ha eliminado exitosamente";
		}
	}

	@Override
	public DocumentoCatalogo getDocumentoCatalogoPorId(long pId) throws Exception, RemoteException {
		DocumentoCatalogo locDocumentoCatalogo = (DocumentoCatalogo) Criterio.getInstance(this.entity, DocumentoCatalogo.class)
				.add(Restriccion.IGUAL("idDocumentoCatalogo", pId))
				.uniqueResult();
		
		locDocumentoCatalogo.toString();
		
		return locDocumentoCatalogo;
	}

	@Override
	public FiltroDocumentoCatalogo findListaDocumentoCatalogo(FiltroDocumentoCatalogo pFiltro) throws Exception, RemoteException {
		Criterio locCriterio = Criterio.getInstance(entity, DocumentoCatalogo.class)
				.add(Restriccion.ILIKE("nombre", pFiltro.getNombre()))
				.add(Restriccion.IGUAL("estado", pFiltro.getEstado()))
				.add(Restriccion.IGUAL("tipo", pFiltro.getTipo()));
		
		pFiltro.procesarYListar(locCriterio);
		
		return pFiltro;
	}

	private void validacionFaseNombreRepetido(FaseCatalogo pFaseCatalogo) throws RemoteException, Exception, CatalogosException {
		Criterio locCriterio = Criterio.getInstance(this.entity, FaseCatalogo.class)
				.add(Restriccion.LIKE("nombre", pFaseCatalogo.getNombre(), false))
				.add(Restriccion.DISTINTO("id", pFaseCatalogo.getIdFaseCatalogo()))
				.setProyeccion(Proyeccion.COUNT());

		Long cantidad = locCriterio.uniqueResult();
		if(cantidad > 0) {
			throw new CatalogosException(FASE_NOMBRE_REPETIDO);
		}
	}

	private void validacionTramteNombreRepetido(TramiteCatalogo pTramiteCatalogo) throws RemoteException, Exception, CatalogosException {
		FiltroTramiteCatalogo ftc = new FiltroTramiteCatalogo();
		ftc.setNombre(pTramiteCatalogo.getNombre());
		ftc = findListaTramiteCatalogos(ftc);
		
		if(pTramiteCatalogo.getIdTramiteCatalogo() > 0 && ftc.getListaResultados().size() > 1)
			throw new CatalogosException(TRAMITTE_NOMBRE_REPETIDO);
		else if(pTramiteCatalogo.getIdTramiteCatalogo() <= 0 && !ftc.getListaResultados().isEmpty())
			throw new CatalogosException(TRAMITTE_NOMBRE_REPETIDO);
		if(pTramiteCatalogo.getListaEstadosTramite().size() <= 0) {
			throw new CatalogosException(FALATA_ESTADO_TRAMITE);
		}
	}

	private void validacionDocumentoNombreRepetido(DocumentoCatalogo pDocumentoCatalogo) throws RemoteException, Exception, CatalogosException {
		FiltroDocumentoCatalogo fdc = new FiltroDocumentoCatalogo();
		fdc.setNombre(pDocumentoCatalogo.getNombre());
		fdc.setTipo(pDocumentoCatalogo.getTipo());
		
		Criterio locCriterio = Criterio.getInstance(entity, DocumentoCatalogo.class)
				.add(Restriccion.LIKE("nombre", fdc.getNombre()))
				.add(Restriccion.IGUAL("tipo", fdc.getTipo()));
		
		fdc.procesarYListar(locCriterio);
		
		if(pDocumentoCatalogo.getIdDocumentoCatalogo() > 0 && fdc.getListaResultados().size() > 1)
			throw new CatalogosException(DOCUMENTO_REPETIDO_TRAMITTE);
		else if(pDocumentoCatalogo.getIdDocumentoCatalogo() <= 0 && !fdc.getListaResultados().isEmpty())
			throw new CatalogosException(DOCUMENTO_REPETIDO_TRAMITTE);
	}

	@Override
	public FiltroEstadoTramite findListaEstadosTramite(FiltroEstadoTramite pFiltro) throws Exception, RemoteException {
		Criterio locCriterio = Criterio.getInstance(entity, EstadoTramite.class)
				.add(Restriccion.ILIKE("nombre", pFiltro.getNombre()))
				.add(Restriccion.IGUAL("estado", pFiltro.getEstado()));
		
		pFiltro.procesarYListar(locCriterio);
		
		return pFiltro;
	}

	@Override
	public EstadoTramite getEstadosTramitePorId(long pId) throws Exception, RemoteException {
		EstadoTramite locEstadosTramite = (EstadoTramite) Criterio.getInstance(this.entity, EstadoTramite.class)
				.add(Restriccion.IGUAL("idEstadoTramite", pId))
				.uniqueResult();
		
		return locEstadosTramite;
	}

	@Override
	public void addEstadosTramite(EstadoTramite pEstadosTramite) throws RemoteException, Exception {
		validarEstadosTramite(pEstadosTramite);
		entity.persist(pEstadosTramite);
	}

	@Override
	public void updateEstadosTramite(EstadoTramite pEstadosTramite) throws RemoteException, CatalogosException, Exception {
		validarEstadosTramite(pEstadosTramite);
		entity.merge(pEstadosTramite);
	}

	@Override
	public String deleteEstadosTramite(EstadoTramite pEstadosTramite) throws Exception, RemoteException {
		Criterio locCriterio = Criterio.getInstance(this.entity, TramiteCatalogo.class)
				.crearAlias("listaEstadosTramite", "estadoTramite")
				.add(Restriccion.IGUAL("estadoTramite.idEstadoTramite", pEstadosTramite.getIdEstadoTramite()))
				.setProyeccion(Proyeccion.COUNT());

		Long cantidad = locCriterio.uniqueResult();
		if(cantidad > 0) {
			pEstadosTramite.setEstado(EstadoPlantilla.BAJA);
			entity.merge(pEstadosTramite);
			return "El Estado Tramite se ha dado de baja exitosamente";
		} else {
			pEstadosTramite = entity.find(EstadoTramite.class, pEstadosTramite.getIdEstadoTramite());
			entity.remove(pEstadosTramite);
			return "El Estado Tramite se ha eliminado exitosamente";
		}
	}

	public void validarEstadosTramite(EstadoTramite pEstadosTramite) throws CatalogosException {
		if(pEstadosTramite != null) {
			if(pEstadosTramite.getNombre() != null) {
				Criterio locCriterio = Criterio.getInstance(entity, EstadoTramite.class)
						.add(Restriccion.IGUAL("nombre", pEstadosTramite.getNombre()))
						.add(Restriccion.DISTINTO("idEstadoTramite", pEstadosTramite.getIdEstadoTramite()));
				
				EstadoTramite estadoTramite = locCriterio.uniqueResult();
				if(estadoTramite != null) {
					throw new CatalogosException(ESTADO_TRAMITE);
				}
			}
		}
	}

	public void restoreTramiteCatalogo(TramiteCatalogo pTramiteCatalogo) throws Exception {
		try {
			pTramiteCatalogo.setEstado(EstadoPlantilla.ACTIVO);
			this.updateTramiteCatalogo(pTramiteCatalogo);
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatalogosException(RECUPERAR_TRAMITE_CATALOGO);
		}
	}

	public void restoreFaseCatalogo(FaseCatalogo pFaseCatalogo) throws Exception {
		try {
			pFaseCatalogo.setEstado(EstadoPlantilla.ACTIVO);
			this.updateFaseCatalogo(pFaseCatalogo);
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatalogosException(RECUPERAR_FASE_CATALOGO);
		}
	}

	public void restoreDocumentoCatalogo(DocumentoCatalogo pDocumentoCatalogo) throws Exception {
		try {
			pDocumentoCatalogo.setEstado(EstadoPlantilla.ACTIVO);
			this.updateDocumentoCatalogo(pDocumentoCatalogo);
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatalogosException(RECUPERAR_DOCUEMENTO_CATALOGO);
		}
	}

	public void restoreEstadoTramiteCatalogo(EstadoTramite pEstadoTramiteCatalogo) throws Exception {
		try {
			pEstadoTramiteCatalogo.setEstado(EstadoPlantilla.ACTIVO);
			this.updateEstadosTramite(pEstadoTramiteCatalogo);
		} catch(Exception e) {
			e.printStackTrace();
			throw new CatalogosException(RECUPERAR_ESTADO_TRAMITE_CATALOGO);
		}
	}
	
}