package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.persistent.FirmaPermiso;
import com.trascender.framework.recurso.persistent.Permiso;
import com.trascender.framework.recurso.persistent.Rol;
import com.trascender.framework.recurso.persistent.Usuario.Estado;
import com.trascender.framework.recurso.persistent.referencia.PersonaFisicaRfr;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _Usuario extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3232149798035411272L;
	
	public static _Usuario i(){
		return new _Usuario();
	}
	
	
	
	public _Usuario(String nombre, Class<? extends Metaclase> clase){
		super(nombre, clase);
	}
	
	public _Usuario(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _Usuario(){
		this.init();
	}
	
	private void init(){
//		this.user = getNombreCompleto("user");
	}
	
	@Atributo(name = "Id usuario", visibleEnTabla = true, clase = Long.class)
	public String idUsuario = this.getNombreCompleto("idUsuario");
	
	@Atributo(name = "User", visibleEnTabla = true, clase = String.class)
	public String user = getNombreCompleto("user");
	
	@Atributo(name = "Password", visibleEnTabla = true, clase = String.class)
	public String password = getNombreCompleto("password");
	
	@Atributo(name = "Estado", visibleEnTabla = true, clase = Estado.class)
	public String estado = getNombreCompleto("estado");
	
	@Atributo(name = "KeyGen", visibleEnTabla = true, clase = long.class)
	public String keyGen = getNombreCompleto("keyGen");
	
	@Atributo(name = "Firma permiso", visibleEnTabla = true, clase = FirmaPermiso.class)
	public _FirmaPermiso firmaPermiso = validarInstanciacion(_FirmaPermiso.class, "firmaPermiso");
	
	@Atributo(name = "Permisos", visibleEnTabla = true, clase = Permiso.class)
	public _Permiso permisos = validarInstanciacion(_Permiso.class, "permisos");
	
	@Atributo(name = "Persona fisica rfr", visibleEnTabla = true, clase = PersonaFisicaRfr.class)
	public _PersonaFisicaRfr personaFisicaRfr = validarInstanciacion(_PersonaFisicaRfr.class, "personaFisicaRfr");
	
	@Atributo(name = "Id persona fisica", visibleEnTabla = true, clase = Long.class)
	public String idPersonaFisica = getNombreCompleto("idPersonaFisica");
	
	@Atributo(name = "Lista de roles", visibleEnTabla = true, clase = Rol.class)
	public _Rol listaRoles = validarInstanciacion(_Rol.class, "listaRoles");

}
