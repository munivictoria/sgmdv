package com.trascender.catastro.recurso.metamodel;

import java.util.Date;

import com.trascender.catastro.recurso.persistent.Parcela;
import com.trascender.catastro.recurso.persistent.Plano.Estado;
import com.trascender.catastro.recurso.persistent.Plano.Tipo;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.MetaClase;

public class _Plano extends MetaClase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7307042411341752493L;
	
	public static _Plano i(){
		return new _Plano();
	}
	
	public _Plano(String nombre, Class<? extends MetaClase> clase) {
		super(nombre, clase);
	}

	public _Plano(String nombre){
		super(nombre);
		this.init();
	}
	
	public _Plano(){
		this.init();
	}
	
	private void init(){}
	
	@Atributo(name = "Plano", visibleEnTabla = true, clase = String.class)
	public String plano = this.getNombreCompleto("plano");
	
	@Atributo(name = "Folio", visibleEnTabla = true, clase = String.class)
	public String folio = this.getNombreCompleto("folio");
	
	@Atributo(name = "Tomo", visibleEnTabla = true, clase = String.class)
	public String tomo = this.getNombreCompleto("tomo");
	
	@Atributo(name = "Tipo", visibleEnTabla = true, clase = Tipo.class)
	public String tipo = this.getNombreCompleto("tipo");
	
	@Atributo(name = "Estado", visibleEnTabla = true, clase = Estado.class)
	public String estado = this.getNombreCompleto("estado");
	
	@Atributo(name = "Numero expediente", visibleEnTabla = true, clase = String.class)
	public String nroExpediente = this.getNombreCompleto("nroExpediente");
	
	@Atributo(name = "Numero registro", visibleEnTabla = true, clase = String.class)
	public String nroRegistro = this.getNombreCompleto("nroRegistro");
	
	@Atributo(name = "Fecha inscripcion", visibleEnTabla = true, clase = Date.class)
	public String fechaInscripcion = this.getNombreCompleto("fechaInscripcion");
	
	@Atributo(name = "Parcela", visibleEnTabla = true, clase = Parcela.class)
	public _Parcela parcela = validarInstanciacion(_Parcela.class, "parcela");
//	public _Parcela parcela = new _Parcela(getNombreCompleto("parcela"));

}
