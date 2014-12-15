package com.trascender.habilitaciones.business.interfaces;

import javax.ejb.Local;

import com.trascender.habilitaciones.recurso.filtros.FiltroObligacionCementerio;
import com.trascender.habilitaciones.recurso.filtros.FiltroParcelaCementerio;
import com.trascender.habilitaciones.recurso.filtros.FiltroTipoSepultura;
import com.trascender.habilitaciones.recurso.persistent.Obligacion;
import com.trascender.habilitaciones.recurso.persistent.cementerio.DocumentoCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.ParcelaCementerio;
import com.trascender.habilitaciones.recurso.persistent.cementerio.TipoSepultura;


@Local
public interface BusinessDocumentoCementerioLocal {
	
	public static final String JNDI_NAME = "ejb/BusinessDocumentoCementerioLocal/local";

	public DocumentoCementerio addDocumentoCementerio(DocumentoCementerio pDocumentoCementerio) throws Exception;
	public DocumentoCementerio updateDocumentoCementerio(DocumentoCementerio pDocumentoCementerio) throws Exception;
	public void deleteDocumentoCementerio(DocumentoCementerio pDocumentoCementerio) throws Exception;
	public FiltroObligacionCementerio findListaObligacionesCementerio(FiltroObligacionCementerio pFiltro) throws Exception;
	public DocumentoCementerio getDocumentoCementerio(Obligacion pObligacion) throws Exception;
	
	public TipoSepultura addTipoSepultura(TipoSepultura pTipoSepultura) throws Exception;
	public TipoSepultura updateTipoSepultura(TipoSepultura pTipoSepultura) throws Exception;
	public void deleteTipoSepultura(TipoSepultura pTipoSepultura) throws Exception;
	public FiltroTipoSepultura findListaTipoSepultura(FiltroTipoSepultura pFiltro) throws Exception;
	public TipoSepultura getTipoSepulturaById(long pId) throws Exception;
	
	public ParcelaCementerio addParcelaCementerio(ParcelaCementerio pParcelaCementerio) throws Exception;
	public ParcelaCementerio updateParcelaCementerio(ParcelaCementerio pParcelaCementerio) throws Exception;
	public void deleteParcelaCementerio(ParcelaCementerio pParcelaCementerio) throws Exception;
	public FiltroParcelaCementerio findListaParcelaCementerio(FiltroParcelaCementerio pFiltro) throws Exception;
	public ParcelaCementerio getParcelaCementerioById(long pId) throws Exception;
	
}
