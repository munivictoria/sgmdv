package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.catastro.recurso.persistent.Manzana;
import com.trascender.catastro.recurso.persistent.ParcelaPorCuadra;
import com.trascender.framework.recurso.metamodel._AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _Cuadra extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 743413138512709948L;
	
	public static _Cuadra i(){
		System.out.println("--------------------------Paso por aca C1");
		return new _Cuadra();
	}
	
	public _Cuadra(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
		System.out.println("--------------------------Paso por aca C2");
	}

	public _Cuadra(String nombre){
		super(nombre);
		this.init();
		System.out.println("--------------------------Paso por aca C3");
	}
	
	public _Cuadra(){
		this.init();
		System.out.println("--------------------------Paso por aca C4");
	}
	
	private void init(){}
	
	@Atributo(name = "Id cuadra", visibleEnTabla = true, clase = long.class)
	public String idCuadra = this.getNombreCompleto("idCuadra");
	
	@Atributo(name = "Numeracion desde", visibleEnTabla = true, clase = Integer.class)
	public String numeracionDesde = this.getNombreCompleto("numeracionDesde");
	
	@Atributo(name = "Numeracion hasta", visibleEnTabla = true, clase = Integer.class)
	public String numeracionHasta = this.getNombreCompleto("numeracionHasta");
	
	@Atributo(name = "Tipo de numeracion", visibleEnTabla = true, clase = Character.class)
	public String tipoNumeracion = this.getNombreCompleto("tipoNumeracion");
	
	@Atributo(name = "Metros lineales", visibleEnTabla = true, clase = Double.class)
	public String metrosLineales = this.getNombreCompleto("metrosLineales");
	
	@Atributo(name = "Calle comienza", visibleEnTabla = true, clase = Calle.class)
	public _Calle calleComienza = validarInstanciacion(_Calle.class, "calleComienza");
	
	@Atributo(name = "Calle finaliza", visibleEnTabla = true, clase = Calle.class)
	public _Calle calleFinaliza = validarInstanciacion(_Calle.class, "calleFinaliza");
	
	@Atributo(name = "Calle", visibleEnTabla = true, clase = Calle.class)
	public _Calle calle = validarInstanciacion(_Calle.class, "calle");
//	public _Calle calle = new _Calle(getNombreCompleto("calle"));
	
	@Atributo(name = "Activo", visibleEnTabla = true, clase = boolean.class)
	public String activo = this.getNombreCompleto("activo");
	
	@Atributo(name = "Manzana", visibleEnTabla = true, clase = Manzana.class)
	public _Manzana manzana = validarInstanciacion(_Manzana.class, "manzana");
//	public _Manzana manzana = new _Manzana(getNombreCompleto("manzana"));
	
	@Atributo(name = "Lista de parcelas por cuadra", visibleEnTabla = true, clase = ParcelaPorCuadra.class)
	public _ParcelaPorCuadra listaParcelasPorCuadra = validarInstanciacion(_ParcelaPorCuadra.class, "listaParcelasPorCuadra");
//	public _ParcelaPorCuadra listaParcelasPorCuadra = new _ParcelaPorCuadra(getNombreCompleto("idCalle"));
	
	@Atributo(name = "Lista de atributos dinamicos", visibleEnTabla = true, clase = AtributoDinamico.class)
	public _AtributoDinamico listaAtributosDinamicos = validarInstanciacion(_AtributoDinamico.class, "listaAtributosDinamicos");

}
