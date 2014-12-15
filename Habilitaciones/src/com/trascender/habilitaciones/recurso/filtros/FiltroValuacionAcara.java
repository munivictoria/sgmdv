package com.trascender.habilitaciones.recurso.filtros;

import java.util.List;

import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.util.FiltroAbstracto;
import com.trascender.habilitaciones.recurso.persistent.transito.Modelo;
import com.trascender.habilitaciones.recurso.persistent.transito.ValuacionAcara;
import com.trascender.habilitaciones.recurso.persistent.transito.ValuacionAcara.Moneda;

public class FiltroValuacionAcara extends FiltroAbstracto<ValuacionAcara>{
	
	public static final long serialVersionUID = 2240704728941045460L;
	
	private Integer anio;
	private Double valor;
	private Moneda moneda;
	private Modelo modelo;
	private List<AtributoDinamico<?>> listaAtributoDinamico;
	private Boolean activa;
	

	public List<AtributoDinamico<?>> getListaAtributoDinamico() {
		return listaAtributoDinamico;
	}

	public void setListaAtributoDinamico(
			List<AtributoDinamico<?>> listaAtributoDinamico) {
		this.listaAtributoDinamico = listaAtributoDinamico;
	}
	
	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Boolean getActiva() {
		return activa;
	}

	public void setActiva(Boolean activa) {
		this.activa = activa;
	}
}
