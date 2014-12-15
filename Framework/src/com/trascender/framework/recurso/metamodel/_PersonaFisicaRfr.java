package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _PersonaFisicaRfr extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8873266213153219190L;
	
	public static _PersonaFisicaRfr i(){
		return new _PersonaFisicaRfr();
	}
	
	public _PersonaFisicaRfr(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _PersonaFisicaRfr(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _PersonaFisicaRfr(){
		this.init();
	}
	
	private void init(){
		this.nombre = getNombreCompleto("nombre");
	}
	
	@Atributo(name = "Id persona fisica rfr", visibleEnTabla = true, clase = Long.class)
	public String idPersonaFisicaRfr = this.getNombreCompleto("idPersonaFisicaRfr");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = getNombreCompleto("nombre");
	
	@Atributo(name = "Apellido", visibleEnTabla = true, clase = String.class)
	public String apellido = getNombreCompleto("apellido");
}
