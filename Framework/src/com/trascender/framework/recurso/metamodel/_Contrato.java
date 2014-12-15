package com.trascender.framework.recurso.metamodel;

import java.util.Date;

import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.framework.recurso.transients.annotation.Atributo;
import com.trascender.framework.util.Metaclase;

public class _Contrato extends Metaclase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5964917174346792348L;
	
	public static _Contrato i(){
		return new _Contrato();
	}
	
	public _Contrato(String pNombre, Class<? extends Metaclase> pClase) {
		super(pNombre, pClase);
		// TODO Auto-generated constructor stub
	}

	public _Contrato(String nombre) {
		super(nombre);
		this.init();
	}
	
	public _Contrato(){
		this.init();
	}
	
	private void init(){
		this.codigoContrato = getNombreCompleto("numero");
	}
	
	@Atributo(name = "Id contrato", visibleEnTabla = true, clase = Long.class)
	public String idContrato = this.getNombreCompleto("idContrato");
	
	@Atributo(name = "Persona", visibleEnTabla = true, clase = Persona.class)
	public _Persona persona = validarInstanciacion(_Persona.class, "persona");
	
	@Atributo(name = "Codigo de contrato", visibleEnTabla = true, clase = String.class)
	public String codigoContrato = getNombreCompleto("codigoContrato");
	
	@Atributo(name = "Descripcion", visibleEnTabla = true, clase = String.class)
	public String descripcion = getNombreCompleto("descripcion");
	
	@Atributo(name = "Fecha de inicio", visibleEnTabla = true, clase = Date.class)
	public String fechaInicio = getNombreCompleto("fechaInicio");
	
	@Atributo(name = "Fecha de fin", visibleEnTabla = true, clase = Date.class)
	public String fechaFin = getNombreCompleto("fechaFin");
	
	@Atributo(name = "Total", visibleEnTabla = true, clase = Double.class)
	public String total = getNombreCompleto("total");
	
	@Atributo(name = "Cantidad de cuotas", visibleEnTabla = true, clase = Integer.class)
	public String cantidadCuotas = getNombreCompleto("cantidadCuotas");
	
	@Atributo(name = "Precio por cuota", visibleEnTabla = true, clase = Double.class)
	public String precioPorCuota = getNombreCompleto("precioPorCuota");

}
