package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.Categoria;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _ValorBasicoMejora extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5526084315049199218L;
	
	public static _ValorBasicoMejora i(){
		return new _ValorBasicoMejora();
	}
	
	public _ValorBasicoMejora(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
		// TODO Auto-generated constructor stub
	}

	public _ValorBasicoMejora(String nombre){
		super(nombre);
		this.init();
	}
	
	public _ValorBasicoMejora(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id valor basico mejora", visibleEnTabla = true, clase = long.class)
	public String idValorBasicoMejora = this.getNombreCompleto("idValorBasicoMejora");
	
	@Atributo(name = "Valor", visibleEnTabla = true, clase = Double.class)
	public String valor = this.getNombreCompleto("valor");
	
	@Atributo(name = "Año vigente", visibleEnTabla = true, clase = Integer.class)
	public String anioVigente = this.getNombreCompleto("anioVigente");
	
	@Atributo(name = "Categoria", visibleEnTabla = true, clase = Categoria.class)
	public _Categoria categoria = validarInstanciacion(_Categoria.class, "categoria");

}
