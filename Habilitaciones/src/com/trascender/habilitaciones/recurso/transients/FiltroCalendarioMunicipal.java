package com.trascender.habilitaciones.recurso.transients;

import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.framework.util.Periodicidad;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.Plan;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;

public class FiltroCalendarioMunicipal extends FiltroAbstracto<CalendarioMunicipal>{

	public static final long serialVersionUID = 9084161206944941937L;

	public FiltroCalendarioMunicipal() {

	}

	public static FiltroCalendarioMunicipal getInstance(){
		return new FiltroCalendarioMunicipal();
	}

	private Integer anio; 
	private Periodo periodo;
	private CuotaLiquidacion cuotaLiquidacion;
	private String nombre;
	private Periodicidad periodicidad;
	private TipoObligacion tipoObligacion;
	private Plan plan;


	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Periodo getPeriodo() {
		return periodo;
	}
	public FiltroCalendarioMunicipal setPeriodo(Periodo periodo) {
		this.periodo = periodo;
		return this;
	}
	public String getNombre() {
		return nombre;
	}
	public FiltroCalendarioMunicipal setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	public Periodicidad getPeriodicidad() {
		return periodicidad;
	}
	public FiltroCalendarioMunicipal setPeriodicidad(Periodicidad periodicidad) {
		this.periodicidad = periodicidad;
		return this;
	}
	public TipoObligacion getTipoObligacion() {
		return tipoObligacion;
	}
	public FiltroCalendarioMunicipal setTipoObligacion(TipoObligacion tipoObligacion) {
		this.tipoObligacion = tipoObligacion;
		return this;
	}
	public Integer getAnio() {
		return anio;
	}
	public FiltroCalendarioMunicipal setAnio(Integer anio) {
		this.anio = anio;
		return this;
	}
	public CuotaLiquidacion getCuotaLiquidacion() {
		return cuotaLiquidacion;
	}
	public FiltroCalendarioMunicipal setCuotaLiquidacion(CuotaLiquidacion cuotaLiquidacion) {
		this.cuotaLiquidacion = cuotaLiquidacion;
		return this;
	}




}
