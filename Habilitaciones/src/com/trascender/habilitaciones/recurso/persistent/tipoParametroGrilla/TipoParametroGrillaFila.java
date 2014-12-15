package com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "TIPO_PARAMETRO_GRILLA_FILA")
public class TipoParametroGrillaFila implements Comparable<TipoParametroGrillaFila>, Serializable, Cloneable{
	public static final long serialVersionUID = 1484393768508626189L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="GEN_ID_TIPO_PARAMETRO_GRILLA_FILA")
	@SequenceGenerator(name = "GEN_ID_TIPO_PARAMETRO_GRILLA_FILA", sequenceName = "GEN_ID_TIPO_PARAMETRO_GRILLA_FILA",allocationSize = 1)
	@Column(name = "ID_TIPO_PARAMETRO_GRILLA_FILA")
	private long idTipoParametroGrillaFila = -1;
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_PARAMETRO_GRILLA")
	private TipoParametroGrilla grilla;
	
	@Column(name ="NRO_FILA")
	private Integer nroFila;
	
	private String condicion;
	
	@OrderBy("nroColumna")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fila")
	private List<TipoParametroGrillaFilaColumna> columnas = new ArrayList<TipoParametroGrillaFilaColumna>();

	public TipoParametroGrilla getGrilla() {
		return grilla;
	}

	public void setGrilla(TipoParametroGrilla grilla) {
		this.grilla = grilla;
	}

	public Integer getNroFila() {
		return nroFila;
	}

	public void setNroFila(Integer nroFila) {
		this.nroFila = nroFila;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public List<TipoParametroGrillaFilaColumna> getColumnas() {
		return columnas;
	}

	public void setColumnas(List<TipoParametroGrillaFilaColumna> columnas) {
		this.columnas = columnas;
	}
	
	public void addColumna(TipoParametroGrillaFilaColumna columna) {
		columna.setFila(this);
		this.columnas.add(columna);
	}
	
	public String getStringColumnas() {
		String locStringColumnas = "";
		for (TipoParametroGrillaFilaColumna cadaColumna : this.getColumnas()) {
			locStringColumnas += "[Condicion] "
					+ cadaColumna.getCondicion() + " - [Valor] "
					+ cadaColumna.getValor() + "\n";
		}
		return locStringColumnas;
	}
	
	public long getIdTipoParametroGrillaFila() {
		return idTipoParametroGrillaFila;
	}

	public void setIdTipoParametroGrillaFila(long idTipoParametroGrillaFila) {
		this.idTipoParametroGrillaFila = idTipoParametroGrillaFila;
	}

	public void setStringColumnas(String pString){}

	public int compareTo(TipoParametroGrillaFila o) {
		return this.nroFila.compareTo(o.nroFila);
	}
	
	public void imprimir(){
		Collections.sort(columnas);
		for (TipoParametroGrillaFilaColumna cadaColumna : this.columnas) {
			System.out.print("\t " + cadaColumna.getCondicion() + "("+cadaColumna.getValor()+")");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idTipoParametroGrillaFila ^ (idTipoParametroGrillaFila >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoParametroGrillaFila other = (TipoParametroGrillaFila) obj;
		if (this.idTipoParametroGrillaFila == -1 && other.idTipoParametroGrillaFila == -1){
			return this == other;
		}
		if (idTipoParametroGrillaFila != other.idTipoParametroGrillaFila)
			return false;
		return true;
	}
	
	@Override
	public TipoParametroGrillaFila clone() throws CloneNotSupportedException{
		TipoParametroGrillaFila locFila = (TipoParametroGrillaFila) super.clone();
		locFila.setIdTipoParametroGrillaFila(-1);
		List<TipoParametroGrillaFilaColumna> auxColumnas = new ArrayList<TipoParametroGrillaFilaColumna>();
		
		for (TipoParametroGrillaFilaColumna cadaColumna : this.columnas) {
			auxColumnas.add(cadaColumna.clone());
			cadaColumna.setFila(locFila);
		}
		
		locFila.setColumnas(auxColumnas);
		
		return locFila;
	}
}
