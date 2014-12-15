package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.Planta.Edificacion;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _Planta extends _TipoPlanta {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4359198389922150323L;
	
	public static _Planta i(){
		return new _Planta();
	}
	
	public _Planta(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
		// TODO Auto-generated constructor stub
	}

	public _Planta(String nombre){
		super(nombre);
		this.init();
	}
	
	public _Planta(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Tipo de edificacion", visibleEnTabla = true, clase = Edificacion.class)
	public String tipoEdificacion = this.getNombreCompleto("tipoEdificacion");
	
	@Atributo(name = "De regadio", visibleEnTabla = true, clase = boolean.class)
	public String deRegadio = this.getNombreCompleto("deRegadio");
	
	@Atributo(name = "De secano", visibleEnTabla = true, clase = boolean.class)
	public String deSecano = this.getNombreCompleto("deSecano");

}
