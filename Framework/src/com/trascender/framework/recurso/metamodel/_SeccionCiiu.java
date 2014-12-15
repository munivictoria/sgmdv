package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _SeccionCiiu extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5554115350072302412L;
	
	public static _SeccionCiiu i(){
		return new _SeccionCiiu();
	}
	
	public _SeccionCiiu(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _SeccionCiiu(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _SeccionCiiu(){
		this.init();
	}
	
	private void init(){
		this.codigo = getNombreCompleto("codigo");
	}
	
	@Atributo(name = "Id seccion ciiu", visibleEnTabla = true, clase = Long.class)
	public String idSeccionCiiu = this.getNombreCompleto("idSeccionCiiu");
	
	@Atributo(name = "Codigo", visibleEnTabla = true, clase = String.class)
	public String codigo = getNombreCompleto("codigo");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = getNombreCompleto("nombre");

}
