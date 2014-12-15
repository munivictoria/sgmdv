package com.trascender.gui.framework.component;


public abstract class TColumn {
	
	private String titulo;
	private Class claseColumna;
	
	public abstract Object getValue(Object objeto) throws Exception;
	
	public TColumn(String titulo, Class claseColumna){
		this.titulo = titulo;
		this.claseColumna = claseColumna;
	}
	
	public TColumn(String titulo) {
		this(titulo,String.class);
	}
	
	public String getTitulo() {
		return titulo;
	}

	public Class getClaseColumna() {
		return claseColumna;
	}

}
