package com.trascender.compras.recurso.filtros;

import com.trascender.compras.recurso.persistent.suministros.TipoBien;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroTipoBien extends FiltroAbstracto<TipoBien> {
	
	private static final long serialVersionUID = 8848665475946415996L;
	
	private String nombre;
	private String descripcion;
	private String codigoImputacion;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigoImputacion() {
		return codigoImputacion;
	}

	public void setCodigoImputacion(String codigoImputacion) {
		this.codigoImputacion = codigoImputacion;
	}
}
