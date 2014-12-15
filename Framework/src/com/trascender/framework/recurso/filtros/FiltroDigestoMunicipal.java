package com.trascender.framework.recurso.filtros;

import com.trascender.framework.recurso.persistent.DigestoMunicipal;
import com.trascender.framework.recurso.persistent.DigestoMunicipal.EjeTematico;
import com.trascender.framework.recurso.persistent.DigestoMunicipal.Estado;
import com.trascender.framework.recurso.persistent.DigestoMunicipal.Tipo;
import com.trascender.framework.util.FiltroAbstracto;

/**
 * @author jsantacruz
 */
public class FiltroDigestoMunicipal extends FiltroAbstracto<DigestoMunicipal>{

	public FiltroDigestoMunicipal(Integer pNumero, Integer pAnio, Tipo pTipo,
			Estado pEstado, EjeTematico pEjeTematico, String pDescripcion) {
		numero = pNumero;
		anio = pAnio;
		tipo = pTipo;
		estado = pEstado;
		ejeTematico = pEjeTematico;
		descripcion = pDescripcion;
	}
	public FiltroDigestoMunicipal() {
	}
	
	
	public static final long serialVersionUID = 6107697902920161708L;
	
	private Integer numero;
	private Integer anio;
	private DigestoMunicipal.Tipo tipo;
	private DigestoMunicipal.Estado estado; 
	private DigestoMunicipal.EjeTematico ejeTematico;
	private String descripcion;
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer pNumero) {
		numero = pNumero;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer pAnio) {
		anio = pAnio;
	}
	public DigestoMunicipal.Tipo getTipo() {
		return tipo;
	}
	public void setTipo(DigestoMunicipal.Tipo pTipo) {
		tipo = pTipo;
	}
	public DigestoMunicipal.Estado getEstado() {
		return estado;
	}
	public void setEstado(DigestoMunicipal.Estado pEstado) {
		estado = pEstado;
	}
	public DigestoMunicipal.EjeTematico getEjeTematico() {
		return ejeTematico;
	}
	public void setEjeTematico(DigestoMunicipal.EjeTematico pEjeTematico) {
		ejeTematico = pEjeTematico;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String pDescripcion) {
		descripcion = pDescripcion;
	}
}
