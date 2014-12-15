package com.trascender.catastro.recurso.metamodel;

import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _TipoCalle extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4596067833548705958L;
	
	public static _TipoCalle i(){
		return new _TipoCalle();
	}
	
	public _TipoCalle(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
	}

	public _TipoCalle(String nombre){
		super(nombre);
		this.init();
	}
	
	public _TipoCalle(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id de tipo calle", visibleEnTabla = true, clase = long.class)
	public String idTipoCalle = this.getNombreCompleto("idTipoCalle");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = this.getNombreCompleto("nombre");
	
	@Atributo(name = "Descripcion", visibleEnTabla = true, clase = String.class)
	public String descripcion = this.getNombreCompleto("descripcion");
	
	@Atributo(name = "Activo", visibleEnTabla = true, clase = String.class)
	public String activo = this.getNombreCompleto("activo");

}
