package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.TipoConstruccion;
import com.trascender.catastro.recurso.persistent.ValorBasicoMejora;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _Categoria extends MetaClase{

	public _Categoria(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4816751452162658882L;
	
	public static _Categoria i(){
		return new _Categoria();
	}
	
	public _Categoria(String nombre){
		super(nombre);
		this.init();
	}
	
	public _Categoria(){
		this.init();
	}
	
	private void init(){
	}
	
	@Atributo(name = "Id de categoria", visibleEnTabla = true, clase = long.class)
	public String idCategoria = this.getNombreCompleto("idCategoria");
	
	@Atributo(name="Codigo", visibleEnTabla=true, clase=Integer.class)
	public String codigo = this.getNombreCompleto("codigo");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = this.getNombreCompleto("nombre");
	
	@Atributo(name = "Activo", visibleEnTabla = true, clase = boolean.class)
	public String activo = this.getNombreCompleto("activo");
	
	@Atributo(name = "Tipo de construccion", visibleEnTabla = true, clase = TipoConstruccion.class)
	public _TipoConstruccion tipoConstruccion = validarInstanciacion(_TipoConstruccion.class, "tipoConstruccion");
	
	@Atributo(name = "Lista de valores basicos mejora", visibleEnTabla = true, clase = ValorBasicoMejora.class)
	public _ValorBasicoMejora listaValoresBasicosMejora = validarInstanciacion(_ValorBasicoMejora.class, "listaValoresBasicosMejora");
}
