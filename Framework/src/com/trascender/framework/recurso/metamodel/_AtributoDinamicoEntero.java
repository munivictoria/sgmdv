package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _AtributoDinamicoEntero extends _AtributoDinamico {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5217378080066675415L;
	
	public static _AtributoDinamicoEntero i(){
		return new _AtributoDinamicoEntero();
	}
	
	public _AtributoDinamicoEntero(String pNombre,
			Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _AtributoDinamicoEntero(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _AtributoDinamicoEntero(){
		this.init();
	}
	
	private void init(){
	}
	
	@Atributo(name = "Valor", visibleEnTabla = true, clase = Long.class)
	public String valor = this.getNombreCompleto("valor");

}
