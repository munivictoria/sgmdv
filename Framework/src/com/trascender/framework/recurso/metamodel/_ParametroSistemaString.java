package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _ParametroSistemaString extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2131081001943017302L;
	
	public static _ParametroSistemaString i(){
		return new _ParametroSistemaString();
	}
	
	public _ParametroSistemaString(String pNombre,
			Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _ParametroSistemaString(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _ParametroSistemaString(){
		this.init();
	}
	
	private void init(){
		this.cadena = getNombreCompleto("cadena");
	}
	
	@Atributo(name = "Id parametro de sistema string", visibleEnTabla = true, clase = Long.class)
	public String idParametroSistemaString = this.getNombreCompleto("idParametroSistemaString");
	
	@Atributo(name = "Cadena", visibleEnTabla = true, clase = String.class)
	public String cadena = getNombreCompleto("cadena");

}
