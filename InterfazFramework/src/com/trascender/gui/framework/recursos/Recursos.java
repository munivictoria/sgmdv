package com.trascender.gui.framework.recursos;

public enum Recursos implements RecursosInterface {
	IMG_LOGIN("resources/llaveLogin.png"),
	IMG_CONECTAR("resources/conectar.png"),
	IMG_DESCONECTAR("resources/desconectar.png"),
	ICO_APLICACION("resources/icono.png"),
	IMG_CAB_ADMIN("resources/imgCab_admin.png");
	
	private String uriRecurso;
	
	private Recursos(String pNombre){
		this.uriRecurso=pNombre;
	}
	
	public String getUriRecurso(){
		return this.uriRecurso; 
	}
}
