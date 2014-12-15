package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.persistent.Domicilio;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _Municipalidad extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6520688401253912390L;
	
	public static _Municipalidad i(){
		return new _Municipalidad();
	}
	
	public _Municipalidad(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _Municipalidad(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _Municipalidad(){
		this.init();
	}
	
	private void init(){
		this.nombre = getNombreCompleto("nombre");
	}
	
	@Atributo(name = "Id municipalidad", visibleEnTabla = true, clase = Long.class)
	public String idMunicipalidad = this.getNombreCompleto("idMunicipalidad");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = getNombreCompleto("nombre");
	
	@Atributo(name = "Domicilio", visibleEnTabla = true, clase = Domicilio.class)
	public _Domicilio domicilio = validarInstanciacion(_Domicilio.class, "domicilio");
	
	@Atributo(name = "Telefono", visibleEnTabla = true, clase = String.class)
	public String telefono = getNombreCompleto("telefono");
	
	@Atributo(name = "Numero de cliente pago facil", visibleEnTabla = true, clase = Integer.class)
	public String numeroClientePagoFacil = getNombreCompleto("numeroClientePagoFacil");
	
	@Atributo(name = "Encabezado de reportes", visibleEnTabla = true, clase = String.class)
	public String encabezadoReportes = getNombreCompleto("encabezadoReportes");
	
	@Atributo(name = "Subencabezado de reportes", visibleEnTabla = true, clase = String.class)
	public String subencabezadoReportes = getNombreCompleto("subencabezadoReportes");
	
	@Atributo(name = "Logo", visibleEnTabla = true, clase = byte[].class)
	public String logo = getNombreCompleto("logo");
	
	@Atributo(name = "Nombre de logo", visibleEnTabla = true, clase = String.class)
	public String nombreLogo = getNombreCompleto("nombreLogo");

}
