package com.trascender.catastro.recurso.metamodel;

import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _NomenclaturaCatastral extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3170897208648262708L;
	
	public static _NomenclaturaCatastral i(){
		return new _NomenclaturaCatastral();
	}
	
	public _NomenclaturaCatastral(String nombre,
			Class<? extends MetaClase> clase) {
		super(nombre, clase);
		// TODO Auto-generated constructor stub
	}

	public _NomenclaturaCatastral(String nombre){
		super(nombre);
		this.init();
	}
	
	public _NomenclaturaCatastral(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Departamento", visibleEnTabla = true, clase = String.class)
	public String departamento = this.getNombreCompleto("departamento");
	
	@Atributo(name = "Pedania", visibleEnTabla = true, clase = String.class)
	public String pedania = this.getNombreCompleto("pedania");
	
	@Atributo(name = "Circunscripcion", visibleEnTabla = true, clase = String.class)
	public String circunscripcion = this.getNombreCompleto("circunscripcion");
	
	@Atributo(name = "Distrito", visibleEnTabla = true, clase = String.class)
	public String distrito = this.getNombreCompleto("distrito");
	
	@Atributo(name = "Subdistrito", visibleEnTabla = true, clase = String.class)
	public String subDistrito = this.getNombreCompleto("subDistrito");
	
	@Atributo(name = "Seccion", visibleEnTabla = true, clase = String.class)
	public String seccion = this.getNombreCompleto("seccion");
	
	@Atributo(name = "Quinta", visibleEnTabla = true, clase = String.class)
	public String quinta = this.getNombreCompleto("quinta");
	
	@Atributo(name = "Chacra", visibleEnTabla = true, clase = String.class)
	public String chacra = this.getNombreCompleto("chacra");
	
	@Atributo(name = "Lote", visibleEnTabla = true, clase = String.class)
	public String lote = this.getNombreCompleto("lote");
	
	@Atributo(name = "Numero de parcela", visibleEnTabla = true, clase = String.class)
	public String nroParcela = this.getNombreCompleto("nroParcela");

}
