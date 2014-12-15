package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.persistent.PersonaJuridica;
import com.trascender.framework.recurso.persistent.Socio.CargoSocietario;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _Socio extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3925216971124573964L;
	
	public static _Socio i(){
		return new _Socio();
	}
	
	public _Socio(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _Socio(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _Socio(){
		this.init();
	}
	
	private void init(){
//		this.numero = getNombreCompleto("numero");
	}
	
	@Atributo(name = "Id socio", visibleEnTabla = true, clase = Long.class)
	public String idSocio = this.getNombreCompleto("idSocio");
	
	@Atributo(name = "Persona juridica", visibleEnTabla = true, clase = PersonaJuridica.class)
	public _PersonaJuridica personaJuridica = validarInstanciacion(_PersonaJuridica.class, "personaJuridica");
	
	@Atributo(name = "Persona", visibleEnTabla = true, clase = Persona.class)
	public _Persona persona = validarInstanciacion(_Persona.class, "persona");
	
	@Atributo(name = "Cargo", visibleEnTabla = true, clase = CargoSocietario.class)
	public String cargo = getNombreCompleto("cargo");

}
