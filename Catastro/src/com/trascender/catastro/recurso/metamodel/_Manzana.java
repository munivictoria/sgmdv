package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.Cuadra;
import com.trascender.framework.recurso.metamodel._AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _Manzana extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4089313847712975993L;
	
	public static _Manzana i(){
		return new _Manzana();
	}
	
	public _Manzana(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
	}

	public _Manzana(String nombre){
		super(nombre);
		this.init();
	}
	
	public _Manzana(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id manzana", visibleEnTabla = true, clase = long.class)
	public String idManzana = this.getNombreCompleto("idManzana");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = this.getNombreCompleto("nombre");
	
	@Atributo(name = "Numero de manzana", visibleEnTabla = true, clase = Integer.class)
	public String nroManzana = this.getNombreCompleto("nroManzana");
	
	@Atributo(name = "Lista de cuadras delimitantes", visibleEnTabla = true, clase = Cuadra.class)
	public _Cuadra listaCuadrasDelimitantes = validarInstanciacion(_Cuadra.class, "listaCuadrasDelimitantes");
//	public _Cuadra listaCuadrasDelimitantes = new _Cuadra(getNombreCompleto("listaCuadrasDelimitantes"));
	
	@Atributo(name = "Activo", visibleEnTabla = true, clase = boolean.class)
	public String activo = this.getNombreCompleto("activo");
	
	@Atributo(name = "Lista de atributos dinamicos", visibleEnTabla = true, clase = AtributoDinamico.class)
	public _AtributoDinamico listaAtributosDinamicos = validarInstanciacion(_AtributoDinamico.class, "listaAtributosDinamicos");

}
