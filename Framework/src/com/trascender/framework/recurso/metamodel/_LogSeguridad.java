package com.trascender.framework.recurso.metamodel;

import java.util.Date;

import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _LogSeguridad extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -961026039244970762L;
	
	public static _LogSeguridad i(){
		return new _LogSeguridad();
	}
	
	public _LogSeguridad(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _LogSeguridad(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _LogSeguridad(){
		this.init();
	}
	
	private void init(){
		this.nombreUsuario = getNombreCompleto("nombreUsuario");
	}
	
	@Atributo(name = "Id log seguridad", visibleEnTabla = true, clase = Long.class)
	public String idLogSeguridad = this.getNombreCompleto("idLogSeguridad");
	
	@Atributo(name = "Nombre usuario", visibleEnTabla = true, clase = String.class)
	public String nombreUsuario = getNombreCompleto("nombreUsuario");
	
	@Atributo(name = "Persona usuario", visibleEnTabla = true, clase = String.class)
	public String personaUsuario = getNombreCompleto("personaUsuario");
	
	@Atributo(name = "Fecha de hora", visibleEnTabla = true, clase = Date.class)
	public String fechaHora = getNombreCompleto("fechaHora");
	
	@Atributo(name = "Remote address", visibleEnTabla = true, clase = String.class)
	public String remoteAddress = getNombreCompleto("remoteAddress");
	
	@Atributo(name = "Remote host", visibleEnTabla = true, clase = String.class)
	public String remoteHost = getNombreCompleto("remoteHost");
	
	@Atributo(name = "Remote port", visibleEnTabla = true, clase = String.class)
	public String remotePort = getNombreCompleto("remotePort");
	
	@Atributo(name = "Nombre recurso", visibleEnTabla = true, clase = String.class)
	public String nombreRecurso = getNombreCompleto("nombreRecurso");
	
	@Atributo(name = "Accion", visibleEnTabla = true, clase = Permiso.Accion.class)
	public String accion = getNombreCompleto("accion");
	
	@Atributo(name = "Habilitado", visibleEnTabla = true, clase = boolean.class)
	public String habilitado = getNombreCompleto("habilitado");

}
