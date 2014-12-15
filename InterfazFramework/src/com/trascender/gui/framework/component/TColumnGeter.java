package com.trascender.gui.framework.component;

import java.lang.reflect.Method;

public class TColumnGeter extends TColumn{
	
	private Method metodo;
	
	public TColumnGeter(String titulo, Method metodo, Class clase) {
		super(titulo, clase);
		this.metodo = metodo;
	}

	public TColumnGeter(String titulo, Method metodo) {
		super(titulo, String.class);
		this.metodo = metodo;
	}

	@Override
	public Object getValue(Object objeto) throws Exception {
		return metodo.invoke(objeto);
	}

}
