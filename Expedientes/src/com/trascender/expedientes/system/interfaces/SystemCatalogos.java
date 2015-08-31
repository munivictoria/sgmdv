/**
 * 
 * © Copyright 2015, CoDeSoft
 * Todos los derechos reservados.
 * 
 */

package com.trascender.expedientes.system.interfaces;

import java.rmi.RemoteException;

import javax.ejb.Remote;

import com.trascender.expedientes.exception.CatalogosException;
import com.trascender.expedientes.recurso.filtro.FiltroDocumentoCatalogo;
import com.trascender.expedientes.recurso.filtro.FiltroEstadoTramite;
import com.trascender.expedientes.recurso.filtro.FiltroFaseCatalogo;
import com.trascender.expedientes.recurso.filtro.FiltroTramiteCatalogo;
import com.trascender.expedientes.recurso.persistent.DocumentoCatalogo;
import com.trascender.expedientes.recurso.persistent.EstadoTramite;
import com.trascender.expedientes.recurso.persistent.FaseCatalogo;
import com.trascender.expedientes.recurso.persistent.TramiteCatalogo;

@Remote
public interface SystemCatalogos {

	public final static String JNDI_NAME = "SystemCatalogos/remote";

	public void setLlave(long pLlave) throws RemoteException;

	public void addTramiteCatalogo(TramiteCatalogo pTramiteCatalogo) throws Exception, RemoteException, CatalogosException;

	public void updateTramiteCatalogo(TramiteCatalogo pTramiteCatalogo) throws Exception, RemoteException, CatalogosException;

	public String deleteTramiteCatalogo(TramiteCatalogo pTramiteCatalogo) throws Exception, RemoteException;

	public TramiteCatalogo getTramiteCatalogoPorId(long pId) throws Exception, RemoteException;

	public FiltroTramiteCatalogo findListaTramiteCatalogos(FiltroTramiteCatalogo pFiltro) throws Exception, RemoteException;

	public void addFaseCatalogo(FaseCatalogo pFaseCatalogo) throws Exception, RemoteException, CatalogosException;

	public void updateFaseCatalogo(FaseCatalogo pFaseCatalogo) throws Exception, RemoteException, CatalogosException;

	public String deleteFaseCatalogo(FaseCatalogo pFaseCatalogo) throws Exception, RemoteException;

	public FaseCatalogo getFaseCatalogoPorId(long pId) throws Exception, RemoteException;

	public FiltroFaseCatalogo findListaFaseCatalogo(FiltroFaseCatalogo pFiltro) throws Exception, RemoteException;

	public void addDocumentoCatalogo(DocumentoCatalogo pDocumentoCatalogo) throws Exception, RemoteException, CatalogosException;

	public void updateDocumentoCatalogo(DocumentoCatalogo pDocumentoCatalogo) throws Exception, RemoteException, CatalogosException;

	public String deleteDocumentoCatalogo(DocumentoCatalogo pDocumentoCatalogo) throws Exception, RemoteException;

	public DocumentoCatalogo getDocumentoCatalogoPorId(long pId) throws Exception, RemoteException;

	public FiltroDocumentoCatalogo findListaDocumentoCatalogo(FiltroDocumentoCatalogo pFiltro) throws Exception, RemoteException;

	public FiltroEstadoTramite findListaEstadosTramite(FiltroEstadoTramite pFiltroEstadosTramite) throws Exception;

	public EstadoTramite getEstadosTramitePorId(long pId) throws Exception;

	public void addEstadosTramite(EstadoTramite pEstadosTramite) throws Exception;

	public void updateEstadosTramite(EstadoTramite pEstadosTramite) throws Exception;

	public String deleteEstadosTramite(EstadoTramite pEstadosTramite) throws Exception;

	public void restoreTramiteCatalogo(TramiteCatalogo pTramiteCatalogo) throws Exception;

	public void restoreFaseCatalogo(FaseCatalogo pFaseCatalogo) throws Exception;

	public void restoreDocumentoCatalogo(DocumentoCatalogo pDocumentoCatalogo) throws Exception;

	public void restoreEstadoTramiteCatalogo(EstadoTramite pEstadoTramiteCatalogo) throws Exception;
	
}
