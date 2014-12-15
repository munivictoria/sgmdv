package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _ConfiguracionAtributoTabla extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5432435024616534194L;
	
	public static _ConfiguracionAtributoTabla i(){
		return new _ConfiguracionAtributoTabla();
	}
	
	public _ConfiguracionAtributoTabla(String pNombre,
			Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _ConfiguracionAtributoTabla(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _ConfiguracionAtributoTabla(){
		this.init();
	}
	
	private void init(){
		this.nombreAtributo = getNombreCompleto("nombreAtributo");
	}
	
	@Atributo(name = "Id configuracion de atributo tabla", visibleEnTabla = true, clase = Long.class)
	public String idConfiguracionAtributoTabla = this.getNombreCompleto("idConfiguracionAtributoTabla");

	@Atributo(name = "Orden", visibleEnTabla = true, clase = Integer.class)
	public String orden = getNombreCompleto("orden");
	
	@Atributo(name = "Nombre de atributo", visibleEnTabla = true, clase = String.class)
	public String nombreAtributo = getNombreCompleto("nombreAtributo");
	
	@Atributo(name = "Nombre de atributo tabla", visibleEnTabla = true, clase = String.class)
	public String nombreAtributoTabla = getNombreCompleto("nombreAtributoTabla");
}
