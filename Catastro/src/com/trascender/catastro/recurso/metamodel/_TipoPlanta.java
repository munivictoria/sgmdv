package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.TipoPlanta.Tipo;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _TipoPlanta extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3079138524630568205L;
	
	public static _TipoPlanta i(){
		return new _TipoPlanta();
	}
	
	public _TipoPlanta(String nombre){
		super(nombre);
		this.init();
	}
	
	public _TipoPlanta(){
		this.init();
	}
	
	public _TipoPlanta(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
		// TODO Auto-generated constructor stub
	}

	private void init(){}
	
	@Atributo(name = "Tipo de planta", visibleEnTabla = true, clase = Tipo.class)
	public String tipoPlanta = this.getNombreCompleto("tipoPlanta");

}
