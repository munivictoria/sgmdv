package com.trascender.catastro.recurso.metamodel;

import java.util.Date;

import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.Servicio;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _ServicioPorCuadra extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 481387417544985623L;
	
	public static _ServicioPorCuadra i(){
		return new _ServicioPorCuadra();
	}
	
	public _ServicioPorCuadra(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
		// TODO Auto-generated constructor stub
	}

	public _ServicioPorCuadra(String nombre){
		super(nombre);
		this.init();
	}
	
	public _ServicioPorCuadra(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id de servicio por cuadra", visibleEnTabla = true, clase = long.class)
	public String idServicioPorCuadra = this.getNombreCompleto("idServicioPorCuadra");
	
	@Atributo(name = "Fecha alta", visibleEnTabla = true, clase = Date.class)
	public String fechaAlta = this.getNombreCompleto("fechaAlta");
	
	@Atributo(name = "Cuadra", visibleEnTabla = true, clase = Cuadra.class)
	public _Cuadra cuadra = validarInstanciacion(_Cuadra.class, "cuadra");
	
	@Atributo(name = "Servicio", visibleEnTabla = true, clase = Servicio.class)
	public _Servicio servicio = validarInstanciacion(_Servicio.class, "servicio");

}
