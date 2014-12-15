package com.trascender.saic.business.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Exencion.Estado;
import com.trascender.saic.recurso.persistent.ExencionRegistroDeuda;

@Local
public interface BusinessExencionRegistroDeudaLocal{

	public static final String JNDI_NAME="BusinessExencionRegistroDeudaLocal/local";
	
	public ExencionRegistroDeuda addExencionRegistroDeuda (ExencionRegistroDeuda pExencionRegistroDeuda) throws Exception;
	
	public ExencionRegistroDeuda updateExencionRegistroDeuda (ExencionRegistroDeuda pExencionRegistroDeuda) throws Exception;
	
	public ExencionRegistroDeuda deleteExencionRegistroDeuda (ExencionRegistroDeuda pExencionRegistroDeuda) throws Exception;
	
	public ExencionRegistroDeuda getExencionRegistroDeudaByID (Long pId) throws Exception;
	
	public List<ExencionRegistroDeuda> findListaExencionesRegistrosDeuda(String pNombre, 
			CuotaLiquidacion pCuota,
			PeriodoLiquidacion pPeriodo,
			CalendarioMunicipal pCalendario,
			Estado pEstado,
			Double pPorcentaje) throws Exception;
}
