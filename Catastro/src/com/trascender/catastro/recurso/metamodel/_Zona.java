package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.AsociacionParcelaBridge;
import com.trascender.catastro.recurso.persistent.Zona.Estado;
import com.trascender.catastro.recurso.persistent.Zonificacion;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _Zona extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3541422528241181757L;
	
	public static _Zona i(){
		return new _Zona();
	}
	
	public _Zona(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
	}

	public _Zona(String nombre){
		super(nombre);
		this.init();
	}
	
	public _Zona(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id de zona", visibleEnTabla = true, clase = long.class)
	public String idZona = this.getNombreCompleto("idZona");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = this.getNombreCompleto("nombre");
	
	@Atributo(name = "Descripcion", visibleEnTabla = true, clase = String.class)
	public String descripcion = this.getNombreCompleto("descripcion");
	
	@Atributo(name = "Estado", visibleEnTabla = true, clase = Estado.class)
	public String estado = this.getNombreCompleto("estado");
	
	@Atributo(name = "Lista de asociacion parcela", visibleEnTabla = true, clase = AsociacionParcelaBridge.class)
	public _AsociacionParcelaBridge listaAsociacionParcela = validarInstanciacion(_AsociacionParcelaBridge.class, "listaAsociacionParcela");
//	public _AsociacionParcelaBridge listaAsociacionParcela = new _AsociacionParcelaBridge(getNombreCompleto("listaAsociacionParcela"));
	
	@Atributo(name = "Zonificacion", visibleEnTabla = true, clase = Zonificacion.class)
	public _Zonificacion zonificacion = validarInstanciacion(_Zonificacion.class, "zonificacion");
//	public _Zonificacion zonificacion = new _Zonificacion(getNombreCompleto("zonificacion"));
	
	@Atributo(name = "Prioridad", visibleEnTabla = true, clase = Integer.class)
	public String prioridad = this.getNombreCompleto("prioridad");

}
