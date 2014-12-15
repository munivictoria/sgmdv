package com.trascender.saic.recurso.filtros;

import java.util.List;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.DeclaracionJuradaSHPS;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;

public class FiltroDDJJSHPS extends FiltroAbstracto<DeclaracionJuradaSHPS>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8395606805002975766L;

	private Persona persona;
	private String nroInscripcion;
	private Integer anio;
	private CalendarioMunicipal calendario;
	private PeriodoLiquidacion periodo;
	private CuotaLiquidacion cuota;
	private List<Long> listaIdPersonas;
	
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public String getNroInscripcion() {
		return nroInscripcion;
	}
	public void setNroInscripcion(String nroInscripcion) {
		this.nroInscripcion = nroInscripcion;
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
	public List<Long> getListaIdPersonas() {
		return listaIdPersonas;
	}
	public void setListaIdPersonas(List<Long> listaIdPersonas) {
		this.listaIdPersonas = listaIdPersonas;
	}
	
}
