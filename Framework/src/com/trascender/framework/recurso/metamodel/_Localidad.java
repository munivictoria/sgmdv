package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.persistent.Provincia;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _Localidad extends Metaclase {
	private static final long serialVersionUID = 8368853949395281352L;
	
	public static _Localidad i(){
		return new _Localidad();
	}
	
	public _Localidad(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _Localidad(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _Localidad(){
		this.init();
	}
	
	private void init(){
	}
	
	@Atributo(name = "Id localidad", visibleEnTabla = true, clase = Long.class)
	public String idLocalidad = this.getNombreCompleto("idLocalidad");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = getNombreCompleto("nombre");
	
	@Atributo(name = "Codigo postal", visibleEnTabla = true, clase = String.class)
	public String codigoPostal = getNombreCompleto("codigoPostal");
	
	@Atributo(name = "Provincia", visibleEnTabla = true, clase = Provincia.class)
	public _Provincia provincia = validarInstanciacion(_Provincia.class, "provincia");

}
