
package com.trascender.habilitaciones.business.interfaces;

import javax.ejb.Local;

import com.trascender.framework.exception.TrascenderException;
import com.trascender.habilitaciones.recurso.filtros.FiltroRubroSHPS;
import com.trascender.habilitaciones.recurso.filtros.FiltroServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.RegAlicuota;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;
import com.trascender.habilitaciones.recurso.persistent.shps.Rubro;

@Local
public interface BusinessTipoAlicuotaLocal {

	public static final String JNDI_NAME = "ejb/BusinessTipoAlicuotaLocal/local";

	public RegAlicuota addTipoAlicuota(RegAlicuota pTipoAlicuota) throws Exception;

	public RegAlicuota updateTipoAlicuota(RegAlicuota pTipoAlicuota) throws Exception;

	public void deleteTipoAlicuota(RegAlicuota pTipoAlicuota) throws Exception;

	public FiltroRubroSHPS findListaRubros(FiltroRubroSHPS pFiltro) throws TrascenderException;

	/**
	 * Agrega un servicio osp
	 * 
	 * @param pServicioOSP
	 * @return
	 * @throws Exception
	 */
	public ServicioOSP addServicioOSP(ServicioOSP pServicioOSP) throws Exception;

	/**
	 * Actualiza los datos de un servicio de osp
	 * 
	 * @param pServicioOSP
	 * @return
	 * @throws Exception
	 */
	public ServicioOSP updateServicioOSP(ServicioOSP pServicioOSP) throws Exception;

	/**
	 * Elimina un servicio osp del registro
	 * 
	 * @param pServicioOSP
	 * @throws Exception
	 */
	public void deleteServicioOSP(ServicioOSP pServicioOSP) throws Exception;

	/**
	 * Recupera un listado de servicios de OSP
	 * 
	 * @return
	 * @throws Exception
	 */
	public FiltroServicioOSP findListaServiciosOSP(FiltroServicioOSP pFiltro) throws Exception;

	public RegAlicuota getTipoAlicuotaPorId(long pIdRegAlicuota) throws Exception;

	public RegAlicuota getRegAlicuotaPorId(long pId);

	public Rubro getRubroPorId(long pId);

	public ServicioOSP getServicioOSPPorId(long pId) throws Exception;
}