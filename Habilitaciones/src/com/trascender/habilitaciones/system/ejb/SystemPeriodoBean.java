package com.trascender.habilitaciones.system.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import com.trascender.framework.business.interfaces.BusinessCalendarioLocal;
import com.trascender.framework.exception.TrascenderException;
import com.trascender.framework.exception.TrascenderFrameworkException;
import com.trascender.framework.recurso.persistent.Permiso.Accion;
import com.trascender.framework.recurso.transients.Calendario;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.SecurityMgr;
import com.trascender.habilitaciones.business.interfaces.BusinessPeriodoLocal;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.transients.FiltroCalendarioMunicipal;
import com.trascender.habilitaciones.system.interfaces.SystemPeriodo;

@Stateful(name="ejb/SystemPeriodo")
public class SystemPeriodoBean implements SystemPeriodo{

	@EJB
	private BusinessCalendarioLocal busnessCalendario;

	@EJB
	private BusinessPeriodoLocal businessPeriodo;

	private long llave;

	public void setLlave(long pLlave){
		this.llave = pLlave;
	}

	public CalendarioMunicipal addCalendarioMunicipal(CalendarioMunicipal pCalendario) throws Exception {
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, Calendario.serialVersionUID, Accion.INSERT)){
				return this.businessPeriodo.addCalendarioMunicipal(pCalendario);
			}else{
				throw new TrascenderFrameworkException(805);
			}
		}catch (TrascenderException locE) {
			locE.printStackTrace();
			throw locE;
		}catch (Exception locE2) {
			locE2.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

	public CalendarioMunicipal updateCalendarioMunicipal(CalendarioMunicipal pCalendario) throws Exception {
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, Calendario.serialVersionUID, Accion.UPDATE )){
				return this.businessPeriodo.updateCalendarioMunicipal(pCalendario);
			}else{
				throw new TrascenderFrameworkException(805);
			}
		}catch (TrascenderException locE) {
			locE.printStackTrace();
			throw locE;
		}catch (Exception locE2) {
			locE2.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

	public FiltroCalendarioMunicipal findListaCalendarios(FiltroCalendarioMunicipal pFiltro)
			throws Exception {
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, Calendario.serialVersionUID, Accion.SELECT )){
				return this.businessPeriodo.findListaCalendariosMunicipales(pFiltro);
			}else{
				throw new TrascenderFrameworkException(805);
			}
		}catch (TrascenderException locE) {
			locE.printStackTrace();
			throw locE;
		}catch (Exception locE2) {
			locE2.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

	//	public Periodo getPeriodo(Calendar pFecha, Integer pNumeroPeriodo,
	//			Integer pAño, Periodicidad pPeriodicidad, TipoObligacion pTipoObligacion)
	//			throws Exception {
	//		try{
	//			if(SecurityMgr.getInstance().getPermiso(this.llave, Calendario.serialVersionUID, Accion.UPDATE )){
	//				return this.businessPeriodo.getPeriodo(pFecha, pNumeroPeriodo, pAño, pPeriodicidad, pTipoObligacion);
	//			}else{
	//				throw new TrascenderFrameworkException(805);
	//			}
	//		}catch (TrascenderException locE) {
	//			locE.printStackTrace();
	//			throw new TrascenderFrameworkException(853);
	//		}catch (Exception locE2) {
	//			locE2.printStackTrace();
	//			throw new TrascenderFrameworkException(999);
	//		}
	//	}
	//
	//	public Periodo getSiguientePeriodo(Periodicidad pPeriodicidad,
	//			Integer pNumeroPeriodo, Integer pAño, TipoObligacion pTipoObligacion)
	//			throws Exception {
	//		try{
	//			if(SecurityMgr.getInstance().getPermiso(this.llave, Calendario.serialVersionUID, Accion.SELECT )){
	//				return this.businessPeriodo.getSiguientePeriodo(pPeriodicidad, pNumeroPeriodo, pAño, pTipoObligacion);
	//			}else{
	//				throw new TrascenderFrameworkException(805);
	//			}
	//		}catch (TrascenderException locE) {
	//			locE.printStackTrace();
	//			throw new TrascenderFrameworkException(853);
	//		}catch (Exception locE2) {
	//			locE2.printStackTrace();
	//			throw new TrascenderFrameworkException(999);
	//		}
	//	}

	public CalendarioMunicipal getCalendarioById(Long pId) throws TrascenderException {
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, Calendario.serialVersionUID, Accion.SELECT )){
				return this.businessPeriodo.getCalendarioMunicipalById(pId);
			}else{
				throw new TrascenderFrameworkException(805);
			}
		}catch (TrascenderException locE) {
			locE.printStackTrace();
			throw locE;
		}catch (Exception locE2) {
			locE2.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

	public CuotaLiquidacion getCuotaSiguiente(CuotaLiquidacion pCuota)throws Exception {
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, Periodo.serialVersionUID, Accion.SELECT )){
				return this.businessPeriodo.getCuotaSiguiente(pCuota);
			}else{
				throw new TrascenderFrameworkException(805);
			}
		}catch (TrascenderException locE) {
			locE.printStackTrace();
			throw locE;
		}catch (Exception locE2) {
			locE2.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}

	public CuotaLiquidacion getCuotaAnterior(CuotaLiquidacion pCuota) throws Exception {
		try{
			if(SecurityMgr.getInstance().getPermiso(this.llave, Periodo.serialVersionUID, Accion.SELECT )){
				return this.businessPeriodo.getCuotaAnterior(pCuota);
			}else{
				throw new TrascenderFrameworkException(805);
			}
		}catch (TrascenderException locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(853);
		}catch (Exception locE2) {
			locE2.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}
	
	public CuotaLiquidacion getCuotaPorId(Long pId) throws TrascenderException {
		try{
			return this.businessPeriodo.getCuotaPorId(pId);
		}catch (TrascenderException locE) {
			locE.printStackTrace();
			throw new TrascenderFrameworkException(854);
		}catch (Exception locE2) {
			locE2.printStackTrace();
			throw new TrascenderFrameworkException(999);
		}
	}
}
