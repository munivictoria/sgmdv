package com.trascender.caja.gui.recursos;

import com.trascender.gui.framework.recursos.RecursosInterface;

public enum RecursosCaja implements RecursosInterface {
	
	IMG_CAB_REPORTES  ("resources/imgCab_reportes.png"),
	IMG_CAB_AGREGAR   ("resources/imgCab_agregar.png"),
	IMG_CAB_MODIFICAR ("resources/imgCab_modificar.png"),
	IMG_CAB_ELIMINAR  ("resources/imgCab_eliminar.png"),
	IMG_ACERCADE     ("resources/imgAbout.jpg"),
	IMG_ACERCADE_VIPIANS  ("resources/imgVipians.png");
	
	private String uri;
	
	private RecursosCaja(String pNombre){
		uri = pNombre;
	}
	
	public String getUriRecurso() {
		return this.uri;
	}
	
}
