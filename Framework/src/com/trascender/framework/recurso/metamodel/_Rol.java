package com.trascender.framework.recurso.metamodel;

import java.util.Date;

import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Rol.Estado;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _Rol extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4481851982398441128L;
	
	public static _Rol i(){
		return new _Rol();
	}
	
	public _Rol(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
	}

	public _Rol(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _Rol(){
		this.init();
	}
	
	private void init(){
		this.nombre = getNombreCompleto("nombre");
	}
	
	@Atributo(name = "Id rol", visibleEnTabla = true, clase = Long.class)
	public String idRol = this.getNombreCompleto("idRol");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = getNombreCompleto("nombre");
	
	@Atributo(name = "Firma", visibleEnTabla = true, clase = boolean.class)
	public String firma = getNombreCompleto("firma");
	
	@Atributo(name = "Desde", visibleEnTabla = true, clase = Date.class)
	public String desde = getNombreCompleto("desde");
	
	@Atributo(name = "Hasta", visibleEnTabla = true, clase = Date.class)
	public String hasta = getNombreCompleto("hasta");
	
	@Atributo(name = "Estado", visibleEnTabla = true, clase = Estado.class)
	public String estado = getNombreCompleto("estado");
	
	@Atributo(name = "Area", visibleEnTabla = true, clase = Area.class)
	public _Area area = validarInstanciacion(_Area.class, "area");
	
	@Atributo(name = "Lista de permisos", visibleEnTabla = true, clase = Permiso.class)
	public _Permiso listaPermisos = validarInstanciacion(_Permiso.class, "listaPermisos");

}
