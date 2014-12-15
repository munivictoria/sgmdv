package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.persistent.GrupoCiiu;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _CodigoCiiu extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4473305386562060191L;
	
	public static _CodigoCiiu i(){
		return new _CodigoCiiu();
	}
	
	public _CodigoCiiu(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _CodigoCiiu(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _CodigoCiiu(){
		this.init();
	}
	
	private void init(){
		this.codigo = getNombreCompleto("codigo");
	}
	
	@Atributo(name = "Id codigo ciiu", visibleEnTabla = true, clase = Long.class)
	public String idCodigoCiiu = this.getNombreCompleto("idCodigoCiiu");
	
	@Atributo(name = "Codigo", visibleEnTabla = true, clase = String.class)
	public String codigo = getNombreCompleto("codigo");
	
	@Atributo(name = "Descripcion", visibleEnTabla = true, clase = String.class)
	public String descripcion = getNombreCompleto("descripcion");
	
	@Atributo(name = "Informacion", visibleEnTabla = true, clase = String.class)
	public String informacion = getNombreCompleto("informacion");
	
	@Atributo(name = "Grupo ciiu", visibleEnTabla = true, clase = GrupoCiiu.class)
	public _GrupoCiiu grupoCiiu = validarInstanciacion(_GrupoCiiu.class, "grupoCiiu");

}
