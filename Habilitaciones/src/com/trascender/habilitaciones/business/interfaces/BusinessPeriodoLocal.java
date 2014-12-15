package com.trascender.habilitaciones.business.interfaces;

import javax.ejb.Local;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.transients.FiltroCalendarioMunicipal;

@Local
public interface BusinessPeriodoLocal {

	public final static String JNDI_NAME = "ejb/BusinessPeriodo";

	public CalendarioMunicipal addCalendarioMunicipal(CalendarioMunicipal pCalendario) throws Exception;
	
	public FiltroCalendarioMunicipal findListaCalendariosMunicipales(FiltroCalendarioMunicipal pFiltro) throws Exception;

	public CuotaLiquidacion getCuotaSiguiente(CuotaLiquidacion pCuota) throws Exception;
	public CuotaLiquidacion getCuotaAnterior(CuotaLiquidacion pCuota) throws Exception;
	
	public CalendarioMunicipal getCalendarioMunicipalById(Long pId) throws Exception;
	
	public CalendarioMunicipal updateCalendarioMunicipal(CalendarioMunicipal pCalendario) throws Exception;
	
	public CuotaLiquidacion getCuotaPorId(Long pId) throws TrascenderException;
}
