package com.trascender.catastro.recurso.metamodel;

import com.trascender.catastro.recurso.persistent.Zona;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _AsociacionParcelaBridge extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -46576310270920665L;
	
	public static _AsociacionParcelaBridge i(){
		return new _AsociacionParcelaBridge();
	}
	
	public _AsociacionParcelaBridge(String nombre,
			Class<? extends MetaClase> clase) {
		super(nombre, clase);
	}

	public _AsociacionParcelaBridge(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _AsociacionParcelaBridge(){
		this.init();
	}
	
	private void init(){
	}
	
	@Atributo(name = "Id de asociacion parcela", visibleEnTabla = true, clase = Long.class)
	public String idAsociacionParcela = this.getNombreCompleto("idAsociacionParcela");
	
	@Atributo(name = "Zona", visibleEnTabla = true, clase = Zona.class)
	public _Zona zona = validarInstanciacion(_Zona.class, "zona");
//	public _Zona zona = new _Zona(getNombreCompleto("zona"));

}
