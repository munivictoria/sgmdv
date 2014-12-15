package com.trascender.framework.business.interfaces;

import java.util.Calendar;
import java.util.Collection;

import javax.ejb.Local;

import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.recurso.transients.Calendario.EstadoCalendario;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.Periodicidad;

@Local
public interface BusinessCalendarioLocal {

	public final static String JNDI_NAME = "ejb/BusinessCalendario";

	public Calendario addCalendario(Calendario pCalendario) throws Exception;
	
	public Calendario updateCalendario(Calendario pCalendario) throws Exception;

	public Collection<Calendario> findListaCalendarios(Long pIdTipoObligacion, 
			Integer pAnio,
			EstadoCalendario pEstado) throws Exception;

	public Periodo getPeriodo(Calendar pFecha,
			Integer pNumeroPeriodo,
			Integer pAño,
			Periodicidad pPeriodicidad,
			Long pIdTipoObligacion)
			throws Exception;

	public Periodo getSiguientePeriodo(
			Periodicidad pPeriodicidad,
			Integer pNumeroPeriodo, 
			Integer pAño,
			Long pIdTipoObligacion) throws Exception;
}
