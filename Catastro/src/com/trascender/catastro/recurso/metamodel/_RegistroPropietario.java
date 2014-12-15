package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.TituloPropiedad;
import com.trascender.framework.recurso.metamodel._Persona;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _RegistroPropietario extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7904582503893636635L;
	
	public static _RegistroPropietario i(){
		return new _RegistroPropietario();
	}
	
	public _RegistroPropietario(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
	}

	public _RegistroPropietario(String nombre){
		super(nombre);
		this.init();
	}
	
	public _RegistroPropietario(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id registro propietario", visibleEnTabla = true, clase = long.class)
	public String idRegistroPropietario = this.getNombreCompleto("idRegistroPropietario");
	
	@Atributo(name = "Descripcion", visibleEnTabla = true, clase = String.class)
	public String descripcion = this.getNombreCompleto("descripcion");
	
	@Atributo(name = "Titulo propiedad", visibleEnTabla = true, clase = TituloPropiedad.class)
	public _TituloPropiedad tituloPropiedad = validarInstanciacion(_TituloPropiedad.class, "tituloPropiedad");
//	public _TituloPropiedad tituloPropiedad = new _TituloPropiedad(getNombreCompleto("tituloPropiedad"));
	
	@Atributo(name = "Tipo titulo", visibleEnTabla = true, clase = Integer.class)
	public String tipoTitulo = this.getNombreCompleto("tipoTitulo");
	
	@Atributo(name = "Persona", visibleEnTabla = true, clase = Persona.class)
	public _Persona persona = validarInstanciacion(_Persona.class, "persona");

}
