package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.persistent.Localidad;
import com.trascender.framework.recurso.persistent.RfrCalle;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _Domicilio extends Metaclase {
	
	private static final long serialVersionUID = -5099029339298375545L;
	
	public static _Domicilio i(){
		return new _Domicilio();
	}
	
	public _Domicilio(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _Domicilio(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _Domicilio(){
		this.init();
	}
	
	private void init(){
		this.numero = getNombreCompleto("numero");
	}
	
	@Atributo(name = "Id domicilio", visibleEnTabla = true, clase = Long.class)
	public String idDomicilio = this.getNombreCompleto("idDomicilio");
	
	@Atributo(name = "Calle", visibleEnTabla = true, clase = String.class)
	public String calle = getNombreCompleto("calle");
	
	@Atributo(name = "Número", visibleEnTabla = true, clase = String.class)
	public String numero = getNombreCompleto("numero");
	
	@Atributo(name = "Manzana", visibleEnTabla = true, clase = String.class)
	public String manzana = getNombreCompleto("manzana");
	
	@Atributo(name = "Torre", visibleEnTabla = true, clase = String.class)
	public String torre = getNombreCompleto("torre");
	
	@Atributo(name = "Escalera", visibleEnTabla = true, clase = String.class)
	public String escalera = getNombreCompleto("escalera");
	
	@Atributo(name = "Piso", visibleEnTabla = true, clase = String.class)
	public String piso = getNombreCompleto("piso");
	
	@Atributo(name = "Departamento", visibleEnTabla = true, clase = String.class)
	public String departamento = getNombreCompleto("departamento");
	
	@Atributo(name = "Barrio", visibleEnTabla = true, clase = String.class)
	public String barrio = getNombreCompleto("barrio");
	
	@Atributo(name = "Sector", visibleEnTabla = true, clase = String.class)
	public String sector = getNombreCompleto("sector");
	
	@Atributo(name = "Codigo postal", visibleEnTabla = true, clase = String.class)
	public String codigoPostal = getNombreCompleto("codigoPostal");

	@Atributo(name = "Calle", visibleEnTabla = true, clase = RfrCalle.class)
	public _RfrCalle relacionCalle = validarInstanciacion(_RfrCalle.class, "relacionCalle");
	
	@Atributo(name = "Localidad", visibleEnTabla = true, clase = Localidad.class)
	public _Localidad localidad = validarInstanciacion(_Localidad.class, "localidad");
}
