package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _AtributoDinamicoBooleano extends _AtributoDinamico {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3315725654759288882L;
	
	public static _AtributoDinamicoBooleano i(){
		return new _AtributoDinamicoBooleano();
	}
	
	public _AtributoDinamicoBooleano(String pNombre,
			Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _AtributoDinamicoBooleano(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _AtributoDinamicoBooleano(){
		this.init();
	}
	
	private void init(){
	}
	
	@Atributo(name = "Valor", visibleEnTabla = true, clase = Boolean.class)
	public String valor = this.getNombreCompleto("valor");

}
