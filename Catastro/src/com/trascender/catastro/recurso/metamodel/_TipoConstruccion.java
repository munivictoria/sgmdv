package com.trascender.catastro.recurso.metamodel;

import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _TipoConstruccion extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 348383588209821523L;
	
	public static _TipoConstruccion i(){
		return new _TipoConstruccion();
	}
	
	public _TipoConstruccion(String nombre){
		super(nombre);
		this.init();
	}
	
	public _TipoConstruccion(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
		// TODO Auto-generated constructor stub
	}

	public _TipoConstruccion(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id tipo construccion", visibleEnTabla = true, clase = long.class)
	public String idTipoConstruccion = this.getNombreCompleto("idTipoConstruccion");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = this.getNombreCompleto("nombre");
	
	@Atributo(name = "Descripcion", visibleEnTabla = true, clase = String.class)
	public String descripcion = this.getNombreCompleto("descripcion");
	
	@Atributo(name = "Activo", visibleEnTabla = true, clase = boolean.class)
	public String activo = this.getNombreCompleto("activo");

}
