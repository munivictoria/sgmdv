package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.persistent.dinamicos.PlantillaAtributoDinamico;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _OpcionAtributoDinamicoListado extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3544541410043974816L;
	
	public static _OpcionAtributoDinamicoListado i(){
		return new _OpcionAtributoDinamicoListado();
	}
	
	public _OpcionAtributoDinamicoListado(String pNombre,
			Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
	}
	
	public _OpcionAtributoDinamicoListado(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _OpcionAtributoDinamicoListado(){
		this.init();
	}
	
	private void init(){
	}
	
	@Atributo(name = "Id opcion de tributo listado", visibleEnTabla = true, clase = long.class)
	public String idOpcionAtriListado = this.getNombreCompleto("idOpcionAtriListado");
	
	@Atributo(name = "Valor", visibleEnTabla = true, clase = String.class)
	public String valor = this.getNombreCompleto("valor");
	
	@Atributo(name = "Plantilla de atributo dinamico", visibleEnTabla = true, clase = PlantillaAtributoDinamico.class)
	public _PlantillaAtributoDinamico plantillaAtributoDinamico = validarInstanciacion(_PlantillaAtributoDinamico.class, "plantillaAtributoDinamico");
//	public _PlantillaAtributoDinamico plantillaAtributoDinamico = new _PlantillaAtributoDinamico(getNombreCompleto("plantillaAtributoDinamico"));

}
