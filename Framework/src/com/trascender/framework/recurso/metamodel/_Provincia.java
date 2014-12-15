package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.persistent.Pais;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _Provincia extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2674900017247104266L;
	
	public static _Provincia i(){
		return new _Provincia();
	}
	
	public _Provincia(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _Provincia(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _Provincia(){
		this.init();
	}
	
	private void init(){
		this.nombre = getNombreCompleto("nombre");
	}
	
	@Atributo(name = "Id provincia", visibleEnTabla = true, clase = Long.class)
	public String idProvincia = this.getNombreCompleto("idProvincia");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = getNombreCompleto("nombre");
	
	@Atributo(name = "Abreviatura", visibleEnTabla = true, clase = String.class)
	public String abreviatura = getNombreCompleto("abreviatura");
	
	@Atributo(name = "Codigo", visibleEnTabla = true, clase = String.class)
	public String codigo = getNombreCompleto("codigo");
	
	@Atributo(name = "Pais", visibleEnTabla = true, clase = Pais.class)
	public _Pais pais = validarInstanciacion(_Pais.class, "pais");

}
