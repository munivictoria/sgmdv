package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _AsociacionParcelaCuadra extends _AsociacionParcelaBridge {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3666551868580928045L;
	
	public static _AsociacionParcelaCuadra i(){
		return new _AsociacionParcelaCuadra();
	}
	
	public _AsociacionParcelaCuadra(String nombre,
			Class<? extends MetaClase> clase) {
		super(nombre, clase);
		// TODO Auto-generated constructor stub
	}

	public _AsociacionParcelaCuadra(String nombre){
		super(nombre);
		this.init();
	}
	
	public _AsociacionParcelaCuadra(){
		this.init();
	}
	
	private void init(){
		
	}
	
	@Atributo(name = "Cuadra", visibleEnTabla = true, clase = Cuadra.class)
	public _Cuadra cuadra = validarInstanciacion(_Cuadra.class, "cuadra");

}
