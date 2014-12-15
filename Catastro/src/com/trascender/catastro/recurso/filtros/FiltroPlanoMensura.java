package com.trascender.catastro.recurso.filtros;

import java.util.Date;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Plano;
import com.trascender.catastro.recurso.persistent.PlanoMensura;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroPlanoMensura extends FiltroAbstracto<PlanoMensura>{

	private static final long serialVersionUID = 1806445800889496806L;
	

	private String plano;
	private String tomo;
	private String folio;
	private Parcela parcela;
	private Date fechaInscripcion;
	private Plano.Estado estado;
	
	public String getPlano() {
		return plano;
	}
	public void setPlano(String plano) {
		this.plano = plano;
	}
	public String getTomo() {
		return tomo;
	}
	public void setTomo(String tomo) {
		this.tomo = tomo;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public Parcela getParcela() {
		return parcela;
	}
	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public Plano.Estado getEstado() {
		return estado;
	}
	public void setEstado(Plano.Estado estado) {
		this.estado = estado;
	}
	
	
	

}
