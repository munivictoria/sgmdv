package com.trascender.saic.recurso.filtros;

import java.util.List;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.CalendarioMunicipal;
import com.trascender.habilitaciones.recurso.persistent.CuotaLiquidacion;
import com.trascender.habilitaciones.recurso.persistent.PeriodoLiquidacion;
import com.trascender.saic.recurso.persistent.LiquidacionTasa;
import com.trascender.saic.recurso.persistent.RegistroDeuda;

/**
 * 
 * @author nico
 *
 */
public class FiltroLiquidacionTasa extends FiltroAbstracto<LiquidacionTasa> {

	private static final long serialVersionUID = -3457463870852900353L;
	
	private Persona persona; 
	private CuotaLiquidacion cuota;
	private PeriodoLiquidacion periodo;
	private CalendarioMunicipal calendario;
	private Integer anio;
	private RegistroDeuda.TipoDeuda tipoLiquidacion;
	private RegistroDeuda.EstadoRegistroDeuda estadoLiquidacion;
	private boolean noCero = false;
	private List<AtributoDinamico<?>> listaAtributosDinamicos;
	
	public Persona getPersona() {
		return persona;
	}
	
	public void setPersona(Persona persona) {
		this.persona = persona;
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
	
	public RegistroDeuda.EstadoRegistroDeuda getEstadoLiquidacion() {
		return estadoLiquidacion;
	}
	
	public void setEstadoLiquidacion(RegistroDeuda.EstadoRegistroDeuda estadoLiquidacion) {
		this.estadoLiquidacion = estadoLiquidacion;
	}
	
	public boolean isNoCero() {
		return noCero;
	}
	
	public void setNoCero(boolean noCero) {
		this.noCero = noCero;
	}
		
	public RegistroDeuda.TipoDeuda getTipoLiquidacion() {
		return tipoLiquidacion;
	}
	
	public void setTipoLiquidacion(RegistroDeuda.TipoDeuda tipoLiquidacion) {
		this.tipoLiquidacion = tipoLiquidacion;
	}

	public List<AtributoDinamico<?>> getListaAtributosDinamicos() {
		return listaAtributosDinamicos;
	}

	public void setListaAtributosDinamicos(List<AtributoDinamico<?>> listaAtributosDinamicos) {
		this.listaAtributosDinamicos = listaAtributosDinamicos;
	}
}
