package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _Pais extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8362424336119099490L;
	
	public static _Pais i(){
		return new _Pais();
	}
	
	public _Pais(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _Pais(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _Pais(){
		this.init();
	}
	
	private void init(){
		this.nombre = getNombreCompleto("nombre");
	}
	
	@Atributo(name = "Id pais", visibleEnTabla = true, clase = Long.class)
	public String idPais = this.getNombreCompleto("idPais");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = getNombreCompleto("nombre");
	
	@Atributo(name = "Abreviatura", visibleEnTabla = true, clase = String.class)
	public String abreviatura = getNombreCompleto("abreviatura");

}
