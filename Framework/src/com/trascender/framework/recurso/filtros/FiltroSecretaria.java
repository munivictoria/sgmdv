package com.trascender.framework.recurso.filtros;

import com.trascender.framework.recurso.persistent.Secretaria;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroSecretaria extends FiltroAbstracto<Secretaria> {
	
	private static final long serialVersionUID = 223207472726573540L;
	
	private String nombre;
	private String codigoImputacion;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String pNombre) {
		nombre = pNombre;
	}

	public String getCodigoImputacion() {
		return codigoImputacion;
	}

	public void setCodigoImputacion(String pCodigoImputacion) {
		codigoImputacion = pCodigoImputacion;
	}
}
