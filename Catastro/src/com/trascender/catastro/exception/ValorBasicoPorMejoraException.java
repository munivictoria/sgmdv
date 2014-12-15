package com.trascender.catastro.exception;

import com.trascender.catastro.recurso.persistent.Categoria;

public class ValorBasicoPorMejoraException extends CatastroException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3609780761106240053L;
	
	private Categoria categoria;
	private Integer anio;

	public ValorBasicoPorMejoraException(Integer pAnio, Categoria pCategoria) {
		super(42);
		this.categoria=pCategoria;
		this.anio=pAnio;
		this.setMsg();
	}

	protected void setMsg() {
		this.msg = "No existe un Valor Básico por Mejora para el Año " + this.getAnio() + " en la Categoría " + this.getCategoria() + ". Por favor, verifique los datos de la Parcela.";
	}

	public Categoria getCategoria(){
		return this.categoria;
	}

	public Integer getAnio(){
		return this.anio;
	}
}