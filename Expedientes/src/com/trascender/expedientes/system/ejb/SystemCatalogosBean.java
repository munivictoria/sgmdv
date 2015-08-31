/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.system.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.trascender.expedientes.business.interfaces.BusinessCatalogos;
import com.trascender.expedientes.exception.CatalogosException;
import com.trascender.expedientes.recurso.filtro.FiltroDocumentoCatalogo;
import com.trascender.expedientes.recurso.filtro.FiltroEstadoTramite;
import com.trascender.expedientes.recurso.filtro.FiltroFaseCatalogo;
import com.trascender.expedientes.recurso.filtro.FiltroTramiteCatalogo;
import com.trascender.expedientes.recurso.persistent.DocumentoCatalogo;
import com.trascender.expedientes.recurso.persistent.EstadoTramite;
import com.trascender.expedientes.recurso.persistent.FaseCatalogo;
import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;
import com.trascender.expedientes.system.interfaces.SystemCatalogos;

@Stateless(name = "SystemCatalogos")
public class SystemCatalogosBean implements SystemCatalogos {

	@SuppressWarnings("unused")
	private long llave = 0;

	@EJB
	private BusinessCatalogos locCatalogos;

	@Override
	public void setLlave(long pLlave) throws RemoteException {
		llave = pLlave;
	}

	@Override
	public void addTramiteCatalogo(TramiteCatalogo pTramiteCatalogo) throws Exception, RemoteException, CatalogosException {
		locCatalogos.addTramiteCatalogo(pTramiteCatalogo);
	}

	@Override
	public void updateTramiteCatalogo(TramiteCatalogo pTramiteCatalogo) throws Exception, RemoteException, CatalogosException {
		locCatalogos.updateTramiteCatalogo(pTramiteCatalogo);
	}

	@Override
	public String deleteTramiteCatalogo(TramiteCatalogo pTramiteCatalogo) throws Exception, RemoteException {
		return locCatalogos.deleteTramiteCatalogo(pTramiteCatalogo);
	}

	@Override
	public TramiteCatalogo getTramiteCatalogoPorId(long pId) throws Exception, RemoteException {
		return locCatalogos.getTramiteCatalogoPorId(pId);
	}

	@Override
	public FiltroTramiteCatalogo findListaTramiteCatalogos(FiltroTramiteCatalogo pFiltro) throws Exception, RemoteException {
		return locCatalogos.findListaTramiteCatalogos(pFiltro);
	}

	@Override
	public void addFaseCatalogo(FaseCatalogo pFaseCatalogo) throws Exception, RemoteException, CatalogosException {
		locCatalogos.addFaseCatalogo(pFaseCatalogo);
	}

	@Override
	public void updateFaseCatalogo(FaseCatalogo pFaseCatalogo) throws Exception, RemoteException, CatalogosException {
		locCatalogos.updateFaseCatalogo(pFaseCatalogo);
	}

	@Override
	public String deleteFaseCatalogo(FaseCatalogo pFaseCatalogo) throws Exception, RemoteException {
		return locCatalogos.deleteFaseCatalogo(pFaseCatalogo);
	}

	@Override
	public FaseCatalogo getFaseCatalogoPorId(long pId) throws Exception, RemoteException {
		return locCatalogos.getFaseCatalogoPorId(pId);
	}

	@Override
	public FiltroFaseCatalogo findListaFaseCatalogo(FiltroFaseCatalogo pFiltro) throws Exception, RemoteException {
		return locCatalogos.findListaFaseCatalogo(pFiltro);
	}

	@Override
	public void addDocumentoCatalogo(DocumentoCatalogo pDocumentoCatalogo) throws Exception, RemoteException, CatalogosException {
		locCatalogos.addDocumentoCatalogo(pDocumentoCatalogo);
	}

	@Override
	public void updateDocumentoCatalogo(DocumentoCatalogo pDocumentoCatalogo) throws Exception, RemoteException, CatalogosException {
		locCatalogos.updateDocumentoCatalogo(pDocumentoCatalogo);
	}

	@Override
	public String deleteDocumentoCatalogo(DocumentoCatalogo pDocumentoCatalogo) throws Exception, RemoteException {
		return locCatalogos.deleteDocumentoCatalogo(pDocumentoCatalogo);
	}

	@Override
	public DocumentoCatalogo getDocumentoCatalogoPorId(long pId) throws Exception, RemoteException {
		return locCatalogos.getDocumentoCatalogoPorId(pId);
	}

	@Override
	public FiltroDocumentoCatalogo findListaDocumentoCatalogo(FiltroDocumentoCatalogo pFiltro) throws Exception, RemoteException {
		return locCatalogos.findListaDocumentoCatalogo(pFiltro);
	}

	@Override
	public FiltroEstadoTramite findListaEstadosTramite(FiltroEstadoTramite pFiltroEstadosTramite) throws Exception {
		return locCatalogos.findListaEstadosTramite(pFiltroEstadosTramite);
	}

	@Override
	public EstadoTramite getEstadosTramitePorId(long pId) throws Exception {
		return locCatalogos.getEstadosTramitePorId(pId);
	}

	@Override
	public void addEstadosTramite(EstadoTramite pEstadosTramite) throws Exception {
		locCatalogos.addEstadosTramite(pEstadosTramite);
	}

	@Override
	public void updateEstadosTramite(EstadoTramite pEstadosTramite) throws Exception {
		locCatalogos.updateEstadosTramite(pEstadosTramite);
	}

	@Override
	public String deleteEstadosTramite(EstadoTramite pEstadosTramite) throws Exception {
		return locCatalogos.deleteEstadosTramite(pEstadosTramite);
	}

	@Override
	public void restoreTramiteCatalogo(TramiteCatalogo pTramiteCatalogo) throws Exception {
		locCatalogos.restoreTramiteCatalogo(pTramiteCatalogo);
	}

	@Override
	public void restoreFaseCatalogo(FaseCatalogo pFaseCatalogo) throws Exception {
		locCatalogos.restoreFaseCatalogo(pFaseCatalogo);
	}

	@Override
	public void restoreDocumentoCatalogo(DocumentoCatalogo pDocumentoCatalogo) throws Exception {
		locCatalogos.restoreDocumentoCatalogo(pDocumentoCatalogo);
	}

	@Override
	public void restoreEstadoTramiteCatalogo(EstadoTramite pEstadoTramiteCatalogo) throws Exception {
		locCatalogos.restoreEstadoTramiteCatalogo(pEstadoTramiteCatalogo);
	}
	
}