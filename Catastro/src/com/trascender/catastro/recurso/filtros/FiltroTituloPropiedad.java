package com.trascender.catastro.recurso.filtros;

import java.util.Date;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.TituloPropiedad;
import com.trascender.catastro.recurso.persistent.TituloPropiedadParcelario;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroTituloPropiedad extends FiltroAbstracto<TituloPropiedad>{

	private static final long serialVersionUID = 9146560797832249753L;
	

	private Date fechaDesde;
	private Date fechaHasta;
	private Persona persona;
	private Parcela parcela;
	private TituloPropiedadParcelario.TipoTransaccionCatastral tipoTransaccionCatastral;
	private Integer nrotomo;
	private Integer nroFolio; 
	private Integer nroAsiento;
	
	
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
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
	public TituloPropiedadParcelario.TipoTransaccionCatastral getTipoTransaccionCatastral() {
		return tipoTransaccionCatastral;
	}
	public void setTipoTransaccionCatastral(
			TituloPropiedadParcelario.TipoTransaccionCatastral tipoTransaccionCatastral) {
		this.tipoTransaccionCatastral = tipoTransaccionCatastral;
	}
	public Integer getNrotomo() {
		return nrotomo;
	}
	public void setNrotomo(Integer nrotomo) {
		this.nrotomo = nrotomo;
	}
	public Integer getNroFolio() {
		return nroFolio;
	}
	public void setNroFolio(Integer nroFolio) {
		this.nroFolio = nroFolio;
	}
	public Integer getNroAsiento() {
		return nroAsiento;
	}
	public void setNroAsiento(Integer nroAsiento) {
		this.nroAsiento = nroAsiento;
	}
	
	
	

}
