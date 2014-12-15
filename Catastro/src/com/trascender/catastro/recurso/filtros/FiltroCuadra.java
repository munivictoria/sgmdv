package com.trascender.catastro.recurso.filtros;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroCuadra extends FiltroAbstracto<Cuadra>{

	private static final long serialVersionUID = 2764869296715597494L;
	

	private Calle calle;
	private Calle calleComienza;
	private Calle calleFinaliza;
	private Character tipoNumeracion;
	private Integer numeracionDesde;
	private Integer numeracionHasta;
	private Boolean estado;
	private Manzana manzana;
	
	public Calle getCalle() {
		return calle;
	}
	public void setCalle(Calle calle) {
		this.calle = calle;
	}
	public Calle getCalleComienza() {
		return calleComienza;
	}
	public void setCalleComienza(Calle calleComienza) {
		this.calleComienza = calleComienza;
	}
	public Calle getCalleFinaliza() {
		return calleFinaliza;
	}
	public void setCalleFinaliza(Calle calleFinaliza) {
		this.calleFinaliza = calleFinaliza;
	}
	public Character getTipoNumeracion() {
		return tipoNumeracion;
	}
	public void setTipoNumeracion(Character tipoNumeracion) {
		this.tipoNumeracion = tipoNumeracion;
	}
	public Integer getNumeracionDesde() {
		return numeracionDesde;
	}
	public void setNumeracionDesde(Integer numeracionDesde) {
		this.numeracionDesde = numeracionDesde;
	}
	public Integer getNumeracionHasta() {
		return numeracionHasta;
	}
	public void setNumeracionHasta(Integer numeracionHasta) {
		this.numeracionHasta = numeracionHasta;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public Manzana getManzana() {
		return manzana;
	}
	public void setManzana(Manzana manzana) {
		this.manzana = manzana;
	}
	
	

}
