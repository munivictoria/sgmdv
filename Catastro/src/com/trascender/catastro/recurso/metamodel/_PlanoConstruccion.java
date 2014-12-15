package com.trascender.catastro.recurso.metamodel;

import com.trascender.framework.recurso.metamodel._AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _PlanoConstruccion extends _Plano {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8788633128959974614L;
	
	public static _PlanoConstruccion i(){
		return new _PlanoConstruccion();
	}
	
	public _PlanoConstruccion(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
		// TODO Auto-generated constructor stub
	}

	public _PlanoConstruccion(String nombre){
		super(nombre);
		this.init();
	}
	
	public _PlanoConstruccion(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id plano de construccion", visibleEnTabla = true, clase = long.class)
	public String idPlanoConstruccion = this.getNombreCompleto("idPlanoConstruccion");
	
	@Atributo(name = "Lista de atributos dinamicos", visibleEnTabla = true, clase = AtributoDinamico.class)
	public _AtributoDinamico listaAtributosDinamicos = validarInstanciacion(_AtributoDinamico.class, "listaAtributosDinamicos");

}
