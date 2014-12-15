package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _AtributoDinamicoDecimal extends _AtributoDinamico {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1347818017847059436L;
	
	public static _AtributoDinamicoDecimal i(){
		return new _AtributoDinamicoDecimal();
	}
	
	public _AtributoDinamicoDecimal(String pNombre,
			Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _AtributoDinamicoDecimal(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _AtributoDinamicoDecimal(){
		this.init();
	}
	
	private void init(){
	}
	
	@Atributo(name = "Valor", visibleEnTabla = true, clase = Double.class)
	public String valor = this.getNombreCompleto("valor");

}
