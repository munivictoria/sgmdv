package com.trascender.framework.recurso.metamodel;

import java.util.Date;

import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _DiaFeriado extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8395762106451252152L;
	
	public static _DiaFeriado i(){
		return new _DiaFeriado();
	}
	
	public _DiaFeriado(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _DiaFeriado(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _DiaFeriado(){
		this.init();
	}
	
	private void init(){
		this.nombre = getNombreCompleto("nombre");
	}
	
	@Atributo(name = "Id dia feriado", visibleEnTabla = true, clase = Long.class)
	public String idDiaFeriado = this.getNombreCompleto("idDiaFeriado");
	
	@Atributo(name = "Fecha", visibleEnTabla = true, clase = Date.class)
	public String fecha = getNombreCompleto("fecha");
	
	@Atributo(name = "Nombre", visibleEnTabla = true, clase = String.class)
	public String nombre = getNombreCompleto("nombre");

}
