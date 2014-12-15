package com.trascender.framework.recurso.filtros;

import com.trascender.framework.recurso.persistent.DiaFeriado;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroDiaFeriado extends FiltroAbstracto<DiaFeriado>{

	public FiltroDiaFeriado(String pNombre, Integer pAnio) {
		nombre = pNombre;
		anio = pAnio;
	}
	
	public FiltroDiaFeriado() {
	}
	
	private static final long serialVersionUID = -2085372839374335546L;
	
	
	private String nombre;
	private Integer anio;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String pNombre) {
		nombre = pNombre;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer pAnio) {
		anio = pAnio;
	}
	
	
	

}
