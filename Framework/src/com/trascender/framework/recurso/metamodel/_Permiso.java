package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _Permiso extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4862864588392122700L;
	
	public static _Permiso i(){
		return new _Permiso();
	}
	
	public _Permiso(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
	}

	public _Permiso(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _Permiso(){
		this.init();
	}
	
	private void init(){
		//this.numero = getNombreCompleto("numero");
	}
	
	@Atributo(name = "Id permiso", visibleEnTabla = true, clase = Long.class)
	public String idPermiso = this.getNombreCompleto("idPermiso");
	
	@Atributo(name = "Id recurso", visibleEnTabla = true, clase = long.class)
	public String idRecurso = getNombreCompleto("idRecurso");
	
	@Atributo(name = "Insert", visibleEnTabla = true, clase = boolean.class)
	public String insert = getNombreCompleto("insert");
	
	@Atributo(name = "Update", visibleEnTabla = true, clase = boolean.class)
	public String update = getNombreCompleto("update");
	
	@Atributo(name = "Delete", visibleEnTabla = true, clase = boolean.class)
	public String delete = getNombreCompleto("delete");
	
	@Atributo(name = "Select", visibleEnTabla = true, clase = boolean.class)
	public String select = getNombreCompleto("select");
	
	@Atributo(name = "Audith", visibleEnTabla = true, clase = boolean.class)
	public String audith = getNombreCompleto("audith");
	
	@Atributo(name = "Rol", visibleEnTabla = true, clase = Rol.class)
	public _Rol rol = validarInstanciacion(_Rol.class, "rol");

}
