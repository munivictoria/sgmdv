package com.trascender.saic.recurso.filtros;

import com.trascender.catastro.recurso.persistent.Parcela;

/**
 * 
 * @author nico
 *
 */
public class FiltroLiquidacionTGI extends FiltroLiquidacionTasa {

	private static final long serialVersionUID = 4824487231908829556L;
	
	private Parcela parcela;
	
	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}
}