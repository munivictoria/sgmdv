package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.persistent.Persona.Estado;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _Persona extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3145389981730020707L;
	
	public static _Persona i(){
		return new _Persona();
	}
	
	public _Persona(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _Persona(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _Persona(){
		this.init();
	}
	
	private void init(){
		this.cuim = getNombreCompleto("cuim");
	}
	
	@Atributo(name = "Id persona externa", visibleEnTabla = true, clase = Long.class)
	public String idPersona = this.getNombreCompleto("idPersona");
	
	@Atributo(name = "Cuim", visibleEnTabla = true, clase = String.class)
	public String cuim = getNombreCompleto("cuim");
	
	@Atributo(name = "Domicilio", visibleEnTabla = true, clase = Domicilio.class)
	public _Domicilio domicilio = validarInstanciacion(_Domicilio.class, "domicilio");
	
	@Atributo(name = "Estado", visibleEnTabla = true, clase = Estado.class)
	public String estado = getNombreCompleto("estado");
	
	@Atributo(name = "Telefono", visibleEnTabla = true, clase = String.class)
	public String telefono = getNombreCompleto("telefono");
	
	@Atributo(name = "Celular", visibleEnTabla = true, clase = String.class)
	public String celular = getNombreCompleto("celular");
	
	@Atributo(name = "Email", visibleEnTabla = true, clase = String.class)
	public String email = getNombreCompleto("email");
	
	@Atributo(name = "Cantidad de propiedades", visibleEnTabla = true, clase = Integer.class)
	public String cantidadPropiedades = getNombreCompleto("cantidadPropiedades");
	
	@Atributo(name = "Domcilio postal", visibleEnTabla = true, clase = Domicilio.class)
	public _Domicilio domicilioPostal = validarInstanciacion(_Domicilio.class, "domicilioPostal");

}
