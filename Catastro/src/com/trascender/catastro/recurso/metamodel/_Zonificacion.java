package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.catastro.recurso.persistent.Zonificacion.Estado;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _Zonificacion extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1756132123172336749L;
	
	public static _Zonificacion i(){
		return new _Zonificacion();
	}
	
	public _Zonificacion(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
	}

	public _Zonificacion(String nombre){
		super(nombre);
		this.init();
	}
	
	public _Zonificacion(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id zonificacion", visibleEnTabla = true, clase = long.class)
	public String idZonificacion = this.getNombreCompleto("idZonificacion");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = this.getNombreCompleto("nombre");
	
	@Atributo(name = "Estado", visibleEnTabla = true, clase = Estado.class)
	public String estado = this.getNombreCompleto("estado");
	
	@Atributo(name = "Lista de zonas", visibleEnTabla = true, clase = Zona.class)
	public _Zona listaZonas = validarInstanciacion(_Zona.class, "listaZonas");
//	public _Zona listaZonas = new _Zona(getNombreCompleto("listaZonas"));

}
