package com.trascender.saic.recurso.filtros;

import java.util.List;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;
import com.trascender.saic.recurso.persistent.RegistroDeuda;
import com.trascender.saic.recurso.references.LiquidacionTasaRefer;

public class FiltroLiquidacionTasaRefer extends FiltroAbstracto<LiquidacionTasaRefer>{
	private static final long serialVersionUID = 7046476804466855357L;
	
	private Persona persona; 
	private Parcela parcela;
	private CuotaLiquidacion cuota;
	private PeriodoLiquidacion periodo;
	private CalendarioMunicipal calendario;
	private Integer anio;
	private List<TipoObligacion> listaTipoObligacion;
	private RegistroDeuda.TipoDeuda tipoLiquidacion;
	private RegistroDeuda.EstadoRegistroDeuda estadoLiquidacion;
	private String numeroParcela;
	private String dni;
	private boolean noCero = false;
	private boolean noAgrupar = false;
	
	public boolean isNoAgrupar() {
		return noAgrupar;
	}
	public void setNoAgrupar(boolean noAgrupar) {
		this.noAgrupar = noAgrupar;
	}
	public String getNumeroParcela() {
		return numeroParcela;
	}
	public void setNumeroParcela(String numeroParcela) {
		this.numeroParcela = numeroParcela;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public RegistroDeuda.TipoDeuda getTipoLiquidacion() {
		return tipoLiquidacion;
	}
	public void setTipoLiquidacion(RegistroDeuda.TipoDeuda tipoLiquidacion) {
		this.tipoLiquidacion = tipoLiquidacion;
	}
	public RegistroDeuda.EstadoRegistroDeuda getEstadoLiquidacion() {
		return estadoLiquidacion;
	}
	public void setEstadoLiquidacion(
			RegistroDeuda.EstadoRegistroDeuda estadoLiquidacion) {
		this.estadoLiquidacion = estadoLiquidacion;
	}
	public boolean isNoCero() {
		return noCero;
	}
	public void setNoCero(boolean noCero) {
		this.noCero = noCero;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Parcela getParcela() {
		return parcela;
	}
	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}
	public List<TipoObligacion> getListaTipoObligacion() {
		return listaTipoObligacion;
	}
	public void setListaTipoObligacion(List<TipoObligacion> listaTipoObligacion) {
		this.listaTipoObligacion = listaTipoObligacion;
	}
	public CuotaLiquidacion getCuota() {
		return cuota;
	}
	public void setCuota(CuotaLiquidacion cuota) {
		this.cuota = cuota;
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
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	
}
