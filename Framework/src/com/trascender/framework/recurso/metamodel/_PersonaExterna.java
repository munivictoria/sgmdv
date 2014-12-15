package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

//clase PersonaExterna esta deprecated
public class _PersonaExterna extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3519938294444992697L;
	
	public static _PersonaExterna i(){
		return new _PersonaExterna();
	}
	
	public _PersonaExterna(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _PersonaExterna(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _PersonaExterna(){
		this.init();
	}
	
	private void init(){
		this.nombre = getNombreCompleto("nombre");
	}
	
	@Atributo(name = "Id persona externa", visibleEnTabla = true, clase = Long.class)
	public String idPersonaExterna = this.getNombreCompleto("idPersonaExterna");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = getNombreCompleto("nombre");

}
