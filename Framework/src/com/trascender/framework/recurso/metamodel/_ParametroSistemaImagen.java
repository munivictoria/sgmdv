package com.trascender.framework.recurso.metamodel;

import java.io.File;

import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _ParametroSistemaImagen extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6457899388614830261L;
	
	public static _ParametroSistemaImagen i(){
		return new _ParametroSistemaImagen();
	}
	
	public _ParametroSistemaImagen(String pNombre,
			Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _ParametroSistemaImagen(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _ParametroSistemaImagen(){
		this.init();
	}
	
	private void init(){
		this.extension = getNombreCompleto("extension");
	}
	
	@Atributo(name = "Id parametro de sistema de imagen", visibleEnTabla = true, clase = Long.class)
	public String idParametroSistemaImagen = this.getNombreCompleto("idParametroSistemaImagen");
	
	@Atributo(name = "Imagen", visibleEnTabla = true, clase = byte[].class)
	public String imagen = getNombreCompleto("imagen");
	
	@Atributo(name = "Extension", visibleEnTabla = true, clase = String.class)
	public String extension = getNombreCompleto("extension");
	
	@Atributo(name = "Archivo", visibleEnTabla = true, clase = File.class)
	public String archivo = getNombreCompleto("archivo");

}
