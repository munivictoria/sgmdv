package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _AtributoDinamicoCadena extends _AtributoDinamico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2407685372564674593L;
	
	public static _AtributoDinamicoCadena i(){
		return new _AtributoDinamicoCadena();
	}
	
	public _AtributoDinamicoCadena(String pNombre,
			Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _AtributoDinamicoCadena(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _AtributoDinamicoCadena(){
		this.init();
	}
	
	private void init(){
	}
	
	@Atributo(name = "Valor", visibleEnTabla = true, clase = String.class)
	public String valor = this.getNombreCompleto("valor");

}
