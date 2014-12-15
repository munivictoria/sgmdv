package com.trascender.saic.recurso.filtros;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.habilitaciones.recurso.persistent.TipoObligacion;

/**
 * 
 * @author nico
 *
 */
public class FiltroLiquidacionTasaMenor extends FiltroLiquidacionTasa {

	private static final long serialVersionUID = -7065112431789089726L;
	
	private Parcela parcela;
	private TipoObligacion tipoObligacionTasaMenor;
	
	public Parcela getParcela() {
		return parcela;
	}
	
	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}
	
	public TipoObligacion getTipoObligacionTasaMenor() {
		return tipoObligacionTasaMenor;
	}
	
	public void setTipoObligacionTasaMenor(TipoObligacion tipoObligacionTasaMenor) {
		this.tipoObligacionTasaMenor = tipoObligacionTasaMenor;
	}
}