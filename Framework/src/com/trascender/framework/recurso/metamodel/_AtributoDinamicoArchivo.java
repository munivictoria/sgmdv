package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.transients.annotation.Atributo;

public class _AtributoDinamicoArchivo extends _AtributoDinamico {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3957274039734599163L;
	
	public static _AtributoDinamicoArchivo i(){
		return instanciar(_AtributoDinamicoArchivo.class);
	}
	
	@Atributo(name = "Valor", visibleEnTabla = true, clase = byte[].class)
	public String valor = this.getNombreCompleto("valor");
	
	@Atributo(name = "Nombre archivo", visibleEnTabla = true, clase = String.class)
	public String nombreArchivo = this.getNombreCompleto("nombreArchivo");

}
