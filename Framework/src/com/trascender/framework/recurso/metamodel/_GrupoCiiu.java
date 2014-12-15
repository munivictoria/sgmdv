package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.persistent.SeccionCiiu;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _GrupoCiiu extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3959634454925236847L;
	
	public static _GrupoCiiu i(){
		return new _GrupoCiiu();
	}
	
	public _GrupoCiiu(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _GrupoCiiu(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _GrupoCiiu(){
		this.init();
	}
	
	private void init(){
		this.codigo = getNombreCompleto("codigo");
	}
	
	@Atributo(name = "Id grupo ciiu", visibleEnTabla = true, clase = Long.class)
	public String idGrupoCiiu = this.getNombreCompleto("idGrupoCiiu");
	
	
	@Atributo(name = "Codigo", visibleEnTabla = true, clase = String.class)
	public String codigo = getNombreCompleto("codigo");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = getNombreCompleto("nombre");
	
	@Atributo(name = "Seccion ciiu", visibleEnTabla = true, clase = SeccionCiiu.class)
	public _SeccionCiiu seccionCiiu = validarInstanciacion(_SeccionCiiu.class, "seccionCiiu");

}
