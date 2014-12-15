package com.trascender.catastro.recurso.metamodel;

import com.trascender.framework.recurso.metamodel._AtributoDinamico;
import com.trascender.framework.recurso.persistent.dinamicos.AtributoDinamico;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _PlanoMensura extends _Plano {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4584987761335871932L;
	
	public static _PlanoMensura i(){
		return new _PlanoMensura();
	}
	
	public _PlanoMensura(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
	}

	public _PlanoMensura(String nombre){
		super(nombre);
		this.init();
	}
	
	public _PlanoMensura(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Id plano mesura", visibleEnTabla = true, clase = long.class)
	public String idPlanoMensura = this.getNombreCompleto("idPlanoMensura");
	
	@Atributo(name = "Lista de atributos dinamicos", visibleEnTabla = true, clase = AtributoDinamico.class)
	public _AtributoDinamico listaAtributosDinamicos = validarInstanciacion(_AtributoDinamico.class, "listaAtributosDinamicos");

}
