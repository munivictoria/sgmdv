package com.trascender.expedientes.system.interfaces;

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

	public void setLlave(long pLlave) throws java.rmi.RemoteException;

	public void addTramiteCatalogo(TramiteCatalogo pTramiteCatalogo) throws java.lang.Exception,
			java.rmi.RemoteException, CatalogosException;

	public void updateTramiteCatalogo(TramiteCatalogo pTramiteCatalogo) throws java.lang.Exception,
			java.rmi.RemoteException, CatalogosException;

	public void deleteTramiteCatalogo(TramiteCatalogo pTramiteCatalogo) throws java.lang.Exception,
			java.rmi.RemoteException;

	public TramiteCatalogo getTramiteCatalogoPorId(long pId) throws java.lang.Exception,
			java.rmi.RemoteException;

	public FiltroTramiteCatalogo findListaTramiteCatalogos(FiltroTramiteCatalogo pFiltro)
			throws java.lang.Exception, java.rmi.RemoteException;

	public void addFaseCatalogo(FaseCatalogo pFaseCatalogo) throws java.lang.Exception,
			java.rmi.RemoteException, CatalogosException;

	public void updateFaseCatalogo(FaseCatalogo pFaseCatalogo) throws java.lang.Exception,
			java.rmi.RemoteException, CatalogosException;

	public void deleteFaseCatalogo(FaseCatalogo pFaseCatalogo) throws java.lang.Exception,
			java.rmi.RemoteException;

	public FaseCatalogo getFaseCatalogoPorId(long pId) throws java.lang.Exception,
			java.rmi.RemoteException;

	public FiltroFaseCatalogo findListaFaseCatalogo(FiltroFaseCatalogo pFiltro)
			throws java.lang.Exception, java.rmi.RemoteException;

	public void addDocumentoCatalogo(DocumentoCatalogo pDocumentoCatalogo)
			throws java.lang.Exception, java.rmi.RemoteException, CatalogosException;

	public void updateDocumentoCatalogo(DocumentoCatalogo pDocumentoCatalogo)
			throws java.lang.Exception, java.rmi.RemoteException, CatalogosException;

	public void deleteDocumentoCatalogo(DocumentoCatalogo pDocumentoCatalogo)
			throws java.lang.Exception, java.rmi.RemoteException;

	public DocumentoCatalogo getDocumentoCatalogoPorId(long pId) throws java.lang.Exception,
			java.rmi.RemoteException;

	public FiltroDocumentoCatalogo findListaDocumentoCatalogo(FiltroDocumentoCatalogo pFiltro)
			throws java.lang.Exception, java.rmi.RemoteException;

	public FiltroEstadoTramite findListaEstadosTramite(FiltroEstadoTramite pFiltroEstadosTramite) throws Exception;
	
	public EstadoTramite getEstadosTramitePorId (long pId) throws Exception;
	
	public void addEstadosTramite(EstadoTramite pEstadosTramite) throws Exception;
	
	public void updateEstadosTramite(EstadoTramite pEstadosTramite) throws Exception;
	
	public void deleteEstadosTramite(EstadoTramite pEstadosTramite) throws Exception;
	
	public void restoreTramiteCatalogo(TramiteCatalogo pTramiteCatalogo) throws Exception;
	
	public void restoreFaseCatalogo(FaseCatalogo pFaseCatalogo) throws Exception;
	
	public void restoreDocumentoCatalogo(DocumentoCatalogo pDocumentoCatalogo) throws Exception;
	
	public void restoreEstadoTramiteCatalogo(EstadoTramite pEstadoTramiteCatalogo) throws Exception;
}
