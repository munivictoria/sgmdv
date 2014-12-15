package com.trascender.catastro.recurso.filtros;

import java.util.Date;

import com.trascender.catastro.recurso.persistent.DeclaracionJurada;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroDeclaracionJurada extends FiltroAbstracto<DeclaracionJurada>{

	private static final long serialVersionUID = -2254918402999273062L;
	

	private String codigoDDJJ;
	private Date desde;
	private Date hasta;
	private Parcela parcela;
	
	public String getCodigoDDJJ() {
		return codigoDDJJ;
	}
	public void setCodigoDDJJ(String codigoDDJJ) {
		this.codigoDDJJ = codigoDDJJ;
	}
	public Date getDesde() {
		return desde;
	}
	public void setDesde(Date desde) {
		this.desde = desde;
	}
	public Date getHasta() {
		return hasta;
	}
	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	public Parcela getParcela() {
		return parcela;
	}
	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

}
