package com.trascender.framework.recurso.metamodel;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _FirmaPermiso extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4501656360073393952L;
	
	public static _FirmaPermiso i(){
		return new _FirmaPermiso();
	}
	
	public _FirmaPermiso(String nombre, Class<? extends Metaclase> clase){
		super(nombre, clase);
	}
	
	public _FirmaPermiso(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _FirmaPermiso(){
		this.init();
	}
	
	private void init(){
//		this.comentario = getNombreCompleto("comentario");
	}
	
	@Atributo(name = "Id firma permiso", visibleEnTabla = true, clase = Long.class)
	public String idFirmaPermiso = this.getNombreCompleto("idFirmaPermiso");
	
	@Atributo(name = "Fecha de hora", visibleEnTabla = true, clase = Date.class)
	public String fechaHora = getNombreCompleto("fechaHora");
	
	@Atributo(name = "Comentario", visibleEnTabla = true, clase = String.class)
	public String comentario = getNombreCompleto("comentario");
	
	@Atributo(name = "Usuario", visibleEnTabla = true, clase = Usuario.class)
	public _Usuario usuario = validarInstanciacion(_Usuario.class, "usuario");

}
