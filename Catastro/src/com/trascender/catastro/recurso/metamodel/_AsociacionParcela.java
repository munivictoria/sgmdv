package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _AsociacionParcela extends _AsociacionParcelaBridge {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7398545096744791845L;
	
	public static _AsociacionParcela i(){
		return new _AsociacionParcela();
	}
	
	public _AsociacionParcela(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
	}

	public _AsociacionParcela(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _AsociacionParcela(){
		this.init();
	}
	
	private void init(){
	}
	
	@Atributo(name = "Parcela", visibleEnTabla = true, clase = Parcela.class)
	public _Parcela parcela = validarInstanciacion(_Parcela.class, "parcela");
//	public _Parcela parcela = new _Parcela(getNombreCompleto("parcela"));

}
