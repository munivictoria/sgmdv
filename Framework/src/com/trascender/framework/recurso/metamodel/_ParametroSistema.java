package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.persistent.ParametroSistema.Estado;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _ParametroSistema extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8901281935912100693L;
	
	public static _ParametroSistema i(){
		return new _ParametroSistema();
	}
	
	public _ParametroSistema(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _ParametroSistema(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _ParametroSistema(){
		this.init();
	}
	
	private void init(){
		this.codigo = getNombreCompleto("codigo");
	}
	
	@Atributo(name = "Id parametro sistema", visibleEnTabla = true, clase = Long.class)
	public String idParametroSistema = this.getNombreCompleto("idParametroSistema");
	
	@Atributo(name = "Codigo", visibleEnTabla = true, clase = String.class)
	public String codigo = getNombreCompleto("codigo");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = getNombreCompleto("nombre");
	
	@Atributo(name = "Estado", visibleEnTabla = true, clase = Estado.class)
	public String estado = getNombreCompleto("estado");

}
