package com.trascender.catastro.business.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.trascender.catastro.recurso.filtros.FiltroZona;
import com.trascender.catastro.recurso.filtros.FiltroZonificacion;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.catastro.recurso.persistent.Zonificacion;

@Local
public interface BusinessZonificacionLocal {
	
	public static final String JNDI_NAME="BusinessZonificacionLocal/local";
	
	public FiltroZona findListaZonas(FiltroZona pFiltro) throws Exception;
	public FiltroZonificacion findListaZonificacion(FiltroZonificacion pFiltro) throws Exception;
	public void addZonificacion(Zonificacion pZonificacion) throws Exception;
	public void updateZonificacion(Zonificacion pZonificacion) throws Exception;
	public void removeZonificacion(Zonificacion pZonificacion) throws Exception;
	public Zona getZonaFromParcela(Parcela pParcela,Zonificacion pZonificacion) throws Exception;
	public Zona getZonaPorId(long pId)throws Exception;
	public List<Zona> getZonasFromParcela(Parcela pParcela)throws Exception;
	public List<Zona> getZonasFromParcelaSinLimitar(Parcela pParcela)throws Exception;
	public Zonificacion getZonificacionPorId(Long pIdZonificacion)throws Exception;
	public Zona getZonaById(Long idZona) throws Exception;
	public List<Map<Long, Long>> getMapaParcelaZona(Zonificacion pZonifiacion);
	public List<Map<Long, String>> getMapaParcelaNombreZona(Zonificacion pZonifiacion);
	
//	public void setSession(Session pSession);
}
