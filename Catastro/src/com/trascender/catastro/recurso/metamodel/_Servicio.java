package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.Servicio.Estado;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _Servicio extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7276739893202759318L;
	
	public static _Servicio i(){
		return new _Servicio();
	}
	
	public _Servicio(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
		// TODO Auto-generated constructor stub
	}

	public _Servicio(String nombre){
		super(nombre);
		this.init();
	}
	
	public _Servicio(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id servicio", visibleEnTabla = true, clase = long.class)
	public String idServicio = this.getNombreCompleto("idServicio");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = this.getNombreCompleto("nombre");
	
	@Atributo(name = "Descripcion", visibleEnTabla = true, clase = String.class)
	public String descripcion = this.getNombreCompleto("descripcion");
	
	@Atributo(name = "Estado", visibleEnTabla = true, clase = Estado.class)
	public String estado = this.getNombreCompleto("estado");

}
