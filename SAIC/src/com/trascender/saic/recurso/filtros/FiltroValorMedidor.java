package com.trascender.saic.recurso.filtros;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.ValorMedidor;
import com.trascender.habilitaciones.recurso.persistent.osp.ServicioOSP;

public class FiltroValorMedidor extends FiltroAbstracto<ValorMedidor> {

	private static final long serialVersionUID = 2325292769155324953L;

	private Calle calle;
	private ServicioOSP servicioOSP;
	private String codigoMedidor;
	private Integer anio;
	private CalendarioMunicipal calendario;
	private PeriodoLiquidacion periodo;
	private CuotaLiquidacion cuota;

	public Calle getCalle() {
		return calle;
	}
	public void setCalle(Calle calle) {
		this.calle = calle;
	}
	public ServicioOSP getServicioOSP() {
		return servicioOSP;
	}
	public void setServicioOSP(ServicioOSP servicioOSP) {
		this.servicioOSP = servicioOSP;
	}
	public String getCodigoMedidor() {
		return codigoMedidor;
	}
	public void setCodigoMedidor(String codigoMedidor) {
		this.codigoMedidor = codigoMedidor;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public CalendarioMunicipal getCalendario() {
		return calendario;
	}
	public void setCalendario(CalendarioMunicipal calendario) {
		this.calendario = calendario;
	}
	public PeriodoLiquidacion getPeriodo() {
		return periodo;
	}
	public void setPeriodo(PeriodoLiquidacion periodo) {
		this.periodo = periodo;
	}
	public CuotaLiquidacion getCuota() {
		return cuota;
	}
	public void setCuota(CuotaLiquidacion cuota) {
		this.cuota = cuota;
	}
}
