package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.catastro.recurso.persistent.TipoCalle;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _Calle extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4787735612388792573L;
	
	public static _Calle i(){
		return new _Calle();
	}
	
	public _Calle(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
	}

	public _Calle(String nombre){
		super(nombre);
		this.init();
	}
	
	public _Calle(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id de calle", visibleEnTabla = true, clase = long.class)
	public String idCalle = this.getNombreCompleto("idCalle");
	
	@Atributo(name = "Codigo", visibleEnTabla = true, clase = String.class)
	public String codigo = this.getNombreCompleto("codigo");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = this.getNombreCompleto("nombre");
	
	@Atributo(name = "Tipo de calle", visibleEnTabla = true, clase = TipoCalle.class)
	public _TipoCalle tipoCalle = validarInstanciacion(_TipoCalle.class, "tipoCalle");
	
	@Atributo(name = "Activo", visibleEnTabla = true, clase = boolean.class)
	public String activo = this.getNombreCompleto("activo");
	
	@Atributo(name = "Lista de cuadras comenzadas", visibleEnTabla = true, clase = Cuadra.class)
	public _Cuadra listaCuadrasComenzadas = validarInstanciacion(_Cuadra.class, "listaCuadrasComenzadas");
	
	@Atributo(name = "Lista de cuadras finalizadas", visibleEnTabla = true, clase = Cuadra.class)
	public _Cuadra listaCuadrasFinalizadas = validarInstanciacion(_Cuadra.class, "listaCuadrasFinalizadas");
	
	@Atributo(name = "Lista de cuadras", visibleEnTabla = true, clase = Cuadra.class)
	public _Cuadra listaCuadras = validarInstanciacion(_Cuadra.class, "listaCuadras");
//	public _Cuadra listaCuadras = new _Cuadra(getNombreCompleto("listaCuadras"));

}
