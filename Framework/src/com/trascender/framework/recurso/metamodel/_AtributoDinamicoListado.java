package com.trascender.framework.recurso.metamodel;

import com.trascender.framework.recurso.persistent.dinamicos.OpcionAtributoDinamicoListado;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _AtributoDinamicoListado extends _AtributoDinamico {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1378955391104678976L;
	
	public static _AtributoDinamicoListado i(){
		return new _AtributoDinamicoListado();
	}
	
	public _AtributoDinamicoListado(String pNombre,
			Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _AtributoDinamicoListado(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _AtributoDinamicoListado(){
		this.init();
	}
	
	private void init(){
	}
	
	@Atributo(name = "Valor", visibleEnTabla = true, clase = OpcionAtributoDinamicoListado.class)
	public _OpcionAtributoDinamicoListado valor = validarInstanciacion(_OpcionAtributoDinamicoListado.class, "valor");

}
