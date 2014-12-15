package com.trascender.catastro.recurso.metamodel;

import java.util.Date;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _VolanteCatastral extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 858600996126856231L;
	
	public static _VolanteCatastral i(){
		return new _VolanteCatastral();
	}
	
	public _VolanteCatastral(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
		// TODO Auto-generated constructor stub
	}

	public _VolanteCatastral(String nombre){
		super(nombre);
		this.init();
	}
	
	public _VolanteCatastral(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id volante catastral", visibleEnTabla = true, clase = long.class)
	public String idVolanteCatastral = this.getNombreCompleto("idVolanteCatastral");
	
	@Atributo(name = "Fecha", visibleEnTabla = true, clase = Date.class)
	public String fecha = this.getNombreCompleto("fecha");
	
	@Atributo(name = "Numero volante catastral", visibleEnTabla = true, clase = Integer.class)
	public String nroVolanteCatastral = this.getNombreCompleto("nroVolanteCatastral");
	
	@Atributo(name = "Radio", visibleEnTabla = true, clase = boolean.class)
	public String radio = this.getNombreCompleto("radio");
	
	@Atributo(name = "Avaluo total terreno", visibleEnTabla = true, clase = Double.class)
	public String avaluoTotalTerreno = this.getNombreCompleto("avaluoTotalTerreno");
	
	@Atributo(name = "Avaluo total mejoras", visibleEnTabla = true, clase = Double.class)
	public String avaluoTotalMejoras = this.getNombreCompleto("avaluoTotalMejoras");
	
	@Atributo(name = "Parcela", visibleEnTabla = true, clase = Parcela.class)
	public _Parcela parcela = validarInstanciacion(_Parcela.class, "parcela");

}
