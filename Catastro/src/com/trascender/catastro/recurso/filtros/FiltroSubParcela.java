package com.trascender.catastro.recurso.filtros;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.SubParcela;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroSubParcela extends FiltroAbstracto<SubParcela>{

	private static final long serialVersionUID = -557194581502920261L;
	
	private Parcela parcelaPadre;
	private Persona titular;

	public Parcela getParcelaPadre() {
		return parcelaPadre;
	}

	public void setParcelaPadre(Parcela parcelaPadre) {
		this.parcelaPadre = parcelaPadre;
	}

	public Persona getTitular() {
		return titular;
	}

	public void setTitular(Persona titular) {
		this.titular = titular;
	}
	
	

	
	
}
