package com.trascender.catastro.recurso.metamodel;

import java.util.Date;

import com.trascender.catastro.recurso.persistent.RegistroPropietario;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _TituloPropiedad extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3455181995608790295L;
	
	public static _TituloPropiedad i(){
		return new _TituloPropiedad();
	}
	
	public _TituloPropiedad(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
	}

	public _TituloPropiedad(String nombre){
		super(nombre);
		this.init();
	}
	
	public _TituloPropiedad(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id titulo propiedad", visibleEnTabla = true, clase = long.class)
	public String idTituloPropiedad = this.getNombreCompleto("idTituloPropiedad");
	
	@Atributo(name = "Titulo", visibleEnTabla = true, clase = String.class)
	public String titulo = this.getNombreCompleto("titulo");
	
	@Atributo(name = "Fecha inscripcion", visibleEnTabla = true, clase = Date.class)
	public String fechaInscripcion = this.getNombreCompleto("fechaInscripcion");
	
	@Atributo(name = "Lista de registros propietarios", visibleEnTabla = true, clase = RegistroPropietario.class)
	public _RegistroPropietario listaRegistrosPropietarios = validarInstanciacion(_RegistroPropietario.class, "listaRegistrosPropietarios");
//	public _RegistroPropietario listaRegistrosPropietarios = new _RegistroPropietario(getNombreCompleto("listaRegistrosPropietarios"));

}
