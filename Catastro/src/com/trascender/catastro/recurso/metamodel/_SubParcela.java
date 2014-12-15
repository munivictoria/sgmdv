package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.SubParcela.Estado;
import com.trascender.framework.recurso.metamodel._Persona;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _SubParcela extends _Parcela {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4698706827947857559L;
	
	public static _SubParcela i(){
		return new _SubParcela();
	}
	
	public _SubParcela(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
	}

	public _SubParcela(String nombre){
		super(nombre);
		this.init();
	}
	
	public _SubParcela(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Padre", visibleEnTabla = true, clase = Parcela.class)
	public _Parcela padre = validarInstanciacion(_Parcela.class, "padre");
//	public _Parcela padre = new _Parcela(getNombreCompleto("padre"));
	
	@Atributo(name = "Titular", visibleEnTabla = true, clase = Persona.class)
	public _Persona titular = validarInstanciacion(_Persona.class, "titular");
	
	@Atributo(name = "Estado", visibleEnTabla = true, clase = Estado.class)
	public String estado = this.getNombreCompleto("estado");

}
