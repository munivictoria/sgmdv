package com.trascender.framework.recurso.metamodel;

import java.util.Date;

import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _AtributoDinamicoFecha extends _AtributoDinamico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4065281368211324528L;
	
	public static _AtributoDinamicoFecha i(){
		return new _AtributoDinamicoFecha();
	}
	
	public _AtributoDinamicoFecha(String pNombre,
			Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _AtributoDinamicoFecha(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _AtributoDinamicoFecha(){
		this.init();
	}
	
	private void init(){
	}
	
	@Atributo(name = "Valor", visibleEnTabla = true, clase = Date.class)
	public String valor = this.getNombreCompleto("valor");

}
