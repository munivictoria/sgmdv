package com.trascender.catastro.recurso.metamodel;

import org.omg.RTCORBA._ClientProtocolPolicyLocalBase;

import com.trascender.catastro.recurso.persistent.Calle;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _AsociacionParcelaCalle extends _AsociacionParcelaBridge {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5378142623001151699L;
	
	public static _AsociacionParcelaCalle i(){
		return new _AsociacionParcelaCalle();
	}
	
	public _AsociacionParcelaCalle(String nombre,
			Class<? extends MetaClase> clase) {
		super(nombre, clase);
		// TODO Auto-generated constructor stub
	}

	public _AsociacionParcelaCalle(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _AsociacionParcelaCalle(){
		this.init();
	}
	
	private void init(){
	}
	
	@Atributo(name = "Calle", visibleEnTabla = true, clase = Calle.class)
	public _Calle calle = validarInstanciacion(_Calle.class, "calle");

}
