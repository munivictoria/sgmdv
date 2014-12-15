package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "TIPO_MODIFICADOR")
public class TipoModificador implements Serializable,Cloneable{

	public enum EnumTipoModificador{
		MULTA_AUTOMATICA, DEUDA
	};
	/**
	 * 
	 */
	public static final long serialVersionUID = -7705731255063021426L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_tipo_modificador")
	@SequenceGenerator(name = "gen_id_tipo_modificador", sequenceName = "gen_id_tipo_modificador", allocationSize = 1)
	@Column(name = "ID_TIPO_MODIFICADOR")
	private long idTipoModificador=-1;
	
	private String nombre;
	
	private boolean fijo=false;
	
	@Column(name = "SOBRE_SALDO_NETO")
	private boolean sobreSaldoNeto=false;
	
	@Column(name = "DESDE_DIAS")
	private Integer desdeDias=0;
	
	@Column(name = "HASTA_DIAS")
	private Integer hastaDias;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_TASA")
	private TipoTasa tipoTasa;
	
	private String condicion;
	
	@Column(name = "DESDE_MESES")
	private Integer desdeMeses;
	
	@Column(name = "HASTA_MESES")
	private Integer hastaMeses;
	
	@Enumerated(EnumType.STRING)
	private EnumTipoModificador tipo=EnumTipoModificador.DEUDA;
	
	@Column(name="QUITAR_AL_RELIQUIDAR_VENC")
	private Boolean quitarReliquidarVencida = false;
	
	@Column(name="NOMBRE_COLUMNA_REPORTES")
	private String nombreColumnaReportes;
	
	@OrderBy("idVariableFormula")
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinTable(name = "RELA_TIPO_MODIFICADOR_VARIABLE_FORMULA", joinColumns = @JoinColumn(name = "ID_TIPO_MODIFICADOR"), inverseJoinColumns = @JoinColumn(name = "ID_VARIABLE_FORMULA"))
	private List<VariableFormula> listaVariables = new ArrayList<VariableFormula>();
	
	public EnumTipoModificador getTipo() {
		return tipo;
	}
	public void setTipo(EnumTipoModificador tipo) {
		this.tipo = tipo;
	}
	public TipoTasa getTipoTasa() {
		return tipoTasa;
	}
	public void setTipoTasa(TipoTasa tipoTasa) {
		this.tipoTasa = tipoTasa;
	}
	public boolean isFijo() {
		return fijo;
	}
	public void setFijo(boolean fijo) {
		this.fijo = fijo;
	}

	public long getIdTipoModificador() {
		return idTipoModificador;
	}
	public void setIdTipoModificador(long idTipoModificador) {
		this.idTipoModificador = idTipoModificador;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean isSobreSaldoNeto() {
		return sobreSaldoNeto;
	}
	public void setSobreSaldoNeto(boolean sobreSaldoNeto) {
		this.sobreSaldoNeto = sobreSaldoNeto;
	}
	
	public Boolean getQuitarReliquidarVencida() {
		return quitarReliquidarVencida;
	}
	public void setQuitarReliquidarVencida(Boolean quitarReliquidarVencida) {
		this.quitarReliquidarVencida = quitarReliquidarVencida;
	}
	
	public List<VariableFormula> getListaVariables() {
		return listaVariables;
	}
	public void setListaVariables(List<VariableFormula> listaVariables) {
		this.listaVariables = listaVariables;
	}
	@Override
	public TipoModificador clone() throws CloneNotSupportedException {
		TipoModificador locTipoModificador=(TipoModificador)super.clone();
		List<VariableFormula> locListaVariables = new ArrayList<VariableFormula>();
		for (VariableFormula cadaVariable : this.listaVariables) {
			locListaVariables.add(cadaVariable.clone());
		}
		locTipoModificador.setListaVariables(locListaVariables);
		locTipoModificador.setIdTipoModificador(-1);
		return locTipoModificador;
	}
	
	
	public String getSimbolo(){
		return (this.isFijo())?"$":"%";
	}
	
	public String getTipoValor(){
		return (this.isFijo())?"Fijo":"Porcentual";
	}
	
	public String getAplicableSobre(){
		return (this.isSobreSaldoNeto())?"Tasa":"SubTotal";
	}
	
	public String getCondicion() {
		return condicion;
	}
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	public Integer getDesdeDias() {
		return desdeDias;
	}
	public void setDesdeDias(Integer desdeDias) {
		this.desdeDias = desdeDias;
	}
	
	public Integer getDesdeMeses() {
		return desdeMeses;
	}
	public void setDesdeMeses(Integer desdeMeses) {
		this.desdeMeses = desdeMeses;
	}
	
	public Integer getHastaDias() {
		return hastaDias;
	}
	public void setHastaDias(Integer hastaDias) {
		this.hastaDias = hastaDias;
	}
	
	public Integer getHastaMeses() {
		return hastaMeses;
	}
	public void setHastaMeses(Integer hastaMeses) {
		this.hastaMeses = hastaMeses;
	}
	
	public String getNombreColumnaReportes() {
		return nombreColumnaReportes;
	}
	public void setNombreColumnaReportes(String nombreColumnaReportes) {
		this.nombreColumnaReportes = nombreColumnaReportes;
	}
	
	@Override
	public String toString() {
		String locNombre="";
		if (this.nombre !=null){
			locNombre+=this.nombre;
		}
		
		if (this.isSobreSaldoNeto()){
			locNombre+=" sobre neto.";
		}
		else{
			locNombre+=" sobre subtotal.";
		}
		return locNombre;
	}
	
	public String getStringQuitarReliquidarVencida(){
		if(this.getQuitarReliquidarVencida()){
			return "Si";
		}
		return "No";
	}
	
	public void setStringQuitarReliquidarVencida(String s){
	}
}
