package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _ParcelaPorCuadra extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1755623282169708289L;
	
	public static _ParcelaPorCuadra i(){
		return new _ParcelaPorCuadra();
	}	
	
	public _ParcelaPorCuadra(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
	}

	public _ParcelaPorCuadra(String nombre){
		super(nombre);
		this.init();
	}
	
	public _ParcelaPorCuadra(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id parcela por cuadra", visibleEnTabla = true, clase = long.class)
	public String idParcelaPorCuadra = this.getNombreCompleto("idParcelaPorCuadra");
	
	@Atributo(name = "Parcela", visibleEnTabla = true, clase = Parcela.class)
	public _Parcela parcela = validarInstanciacion(_Parcela.class, "parcela");
//	public _Parcela parcela = new _Parcela(getNombreCompleto("parcela"));
	
	@Atributo(name = "Cuadra", visibleEnTabla = true, clase = Cuadra.class)
	public _Cuadra cuadra = validarInstanciacion(_Cuadra.class, "cuadra");
//	public _Cuadra cuadra = new _Cuadra(getNombreCompleto("cuadra"));
	
	@Atributo(name = "Metros por cuadra", visibleEnTabla = true, clase = Double.class)
	public String metrosPorCuadra = this.getNombreCompleto("metrosPorCuadra");

}
