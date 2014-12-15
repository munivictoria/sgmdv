package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _RfrCalle extends Metaclase {

	private static final long serialVersionUID = 479935298286006662L;
	
	
	public static _RfrCalle i(){
		return new _RfrCalle();
	}
	
	public _RfrCalle(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _RfrCalle(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _RfrCalle(){
		this.init();
	}
	
	private void init(){
		this.codigo = getNombreCompleto("codigo");
	}
	
	@Atributo(name = "Id rfr calle", visibleEnTabla = true, clase = Long.class)
	public String idRfrCalle = this.getNombreCompleto("idRfrCalle");
	
	@Atributo(name = "codigo", visibleEnTabla = true, clase = String.class)
	public String codigo = getNombreCompleto("codigo");
	
	@Atributo(name = "nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = getNombreCompleto("nombre");

}
