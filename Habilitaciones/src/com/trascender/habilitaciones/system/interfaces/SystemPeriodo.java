package com.trascender.habilitaciones.system.interfaces;

import javax.ejb.Remote;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.transients.FiltroCalendarioMunicipal;

@Remote
public interface SystemPeriodo {

	public final static String JNDI_NAME = "ejb/SystemPeriodo/remote";

	public CalendarioMunicipal addCalendarioMunicipal(CalendarioMunicipal pCalendario) throws Exception;
	public CalendarioMunicipal updateCalendarioMunicipal(CalendarioMunicipal pCalendario) throws Exception;
	
	public FiltroCalendarioMunicipal findListaCalendarios(FiltroCalendarioMunicipal pFiltro) throws Exception;

	public CuotaLiquidacion getCuotaSiguiente(CuotaLiquidacion pCuota) throws Exception;
	public CuotaLiquidacion getCuotaAnterior(CuotaLiquidacion pCuota) throws Exception;
	
	public CalendarioMunicipal getCalendarioById(Long pId) throws TrascenderException;
	
	public CuotaLiquidacion getCuotaPorId(Long pId) throws TrascenderException;
	
	public void deleteCalendarioMunicipal(CalendarioMunicipal pCalendario) throws TrascenderException;
	
	public void setLlave(long pLlave);
}
