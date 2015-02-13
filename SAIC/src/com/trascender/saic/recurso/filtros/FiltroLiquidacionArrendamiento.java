package com.trascender.saic.recurso.filtros;

import com.trascender.catastro.recurso.persistent.Parcela;

public class FiltroLiquidacionArrendamiento extends FiltroLiquidacionTasa {
	
	private Parcela parcela;

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}
	

}
