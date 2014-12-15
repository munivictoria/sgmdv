package com.trascender.habilitaciones.recurso.transients;

import java.io.Serializable;
import java.util.Calendar;

import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;

public class FiltroCuotaLiquidacion extends FiltroAbstracto<CuotaLiquidacion> implements Serializable {

	public static final long serialVersionUID = -7830842358195428594L;
	
	private Integer numeroCuota;
	private Integer numeroPeriodo;
	private PeriodoLiquidacion periodo;
	private CalendarioMunicipal calendario;
	private Calendar fechaInicio;
	private Calendar fechaFin;
	private Calendar fechaVencimiento;
	
	public Integer getNumeroCuota() {
		return numeroCuota;
	}
	public void setNumeroCuota(Integer numeroCuota) {
		this.numeroCuota = numeroCuota;
	}
	public Integer getNumeroPeriodo() {
		return numeroPeriodo;
	}
	public void setNumeroPeriodo(Integer numeroPeriodo) {
		this.numeroPeriodo = numeroPeriodo;
	}
	public PeriodoLiquidacion getPeriodo() {
		return periodo;
	}
	public void setPeriodo(PeriodoLiquidacion periodo) {
		this.periodo = periodo;
	}
	public CalendarioMunicipal getCalendario() {
		return calendario;
	}
	public void setCalendario(CalendarioMunicipal calendario) {
		this.calendario = calendario;
	}
	public Calendar getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Calendar getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Calendar getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Calendar fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
}
