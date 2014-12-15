package com.trascender.catastro.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
/**
 * Contiene todos los datos de la nomenclatura Excepto numero parcela y numero manzana que son redundantes
 * @author jsantacruz
 */
public class NomenclaturaCatastral implements Serializable{
	
	public static final long serialVersionUID = -7073749638619312578L;
	
	private String departamento;
	private String pedania;
	private String circunscripcion;
	private String distrito;
	@Column(name="SUB_DISTRITO")
	private String subDistrito;
	private String seccion;
	private String quinta;
	private String chacra;
	private String lote;
	@Column(name="NRO_PARCELA")
	private String nroParcela;
	
	
	
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getPedania() {
		return pedania;
	}
	public void setPedania(String pedania) {
		this.pedania = pedania;
	}
	public String getCircunscripcion() {
		return circunscripcion;
	}
	public void setCircunscripcion(String circunscripcion) {
		this.circunscripcion = circunscripcion;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public String getSubDistrito() {
		return subDistrito;
	}
	public void setSubDistrito(String subDistrito) {
		this.subDistrito = subDistrito;
	}
	public String getSeccion() {
		return seccion;
	}
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	public String getQuinta() {
		return quinta;
	}
	public void setQuinta(String quinta) {
		this.quinta = quinta;
	}
	public String getChacra() {
		return chacra;
	}
	public void setChacra(String chacra) {
		this.chacra = chacra;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	
	public String getNroParcela() {
		return nroParcela;
	}
	public void setNroParcela(String nroParcela) {
		this.nroParcela = nroParcela;
	}
	@Override
	public String toString() {
		return "[Departamento: " + departamento
				+ ", Pedania: " + pedania 
				+ ", Circunscripcion: "	+ circunscripcion 
				+ ", Distrito: " + distrito 
				+ ", Sub Distrito: " + subDistrito 
				+ ", Seccion: " + seccion 
				+ ", Quinta: " + quinta
				+ ", Chacra:" + chacra 
				+ ", Lote: " + lote + "]";
	}
	
	
	
	
}
