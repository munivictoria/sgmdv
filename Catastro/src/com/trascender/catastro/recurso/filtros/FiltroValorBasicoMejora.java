package com.trascender.catastro.recurso.filtros;

import com.trascender.catastro.recurso.persistent.Categoria;
import com.trascender.catastro.recurso.persistent.ValorBasicoMejora;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroValorBasicoMejora extends FiltroAbstracto<ValorBasicoMejora>{

	private static final long serialVersionUID = -8984269187685459214L;
	

	private Categoria categoria;


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	

}
