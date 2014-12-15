package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _AsociacionParcelaManzana extends _AsociacionParcelaBridge {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3713596872190320049L;
	
	public static _AsociacionParcelaManzana i(){
		return new _AsociacionParcelaManzana();
	}
	
	public _AsociacionParcelaManzana(String nombre,
			Class<? extends MetaClase> clase) {
		super(nombre, clase);
		// TODO Auto-generated constructor stub
	}

	public _AsociacionParcelaManzana(String nombre){
		super(nombre);
		this.init();
	}
	
	public _AsociacionParcelaManzana(){
		this.init();
	}
	
	private void init(){
		
	}
	
	@Atributo(name = "Manzana", visibleEnTabla = true, clase = Manzana.class)
	public _Manzana manzana = validarInstanciacion(_Manzana.class, "manzana");

}
