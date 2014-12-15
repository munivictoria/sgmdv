package com.trascender.habilitaciones.recurso.persistent.tipoParametroGrilla;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO_PARAMETRO_GRILLA_FILA_COLUMNA")
public class TipoParametroGrillaFilaColumna implements Comparable<TipoParametroGrillaFilaColumna>, Serializable, Cloneable{
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	private static final long serialVersionUID = 7445959356254411359L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_ID_TIPO_PARAMETRO_GRILLA_FILA_COLUMNA")
	@SequenceGenerator(name = "gen_ID_TIPO_PARAMETRO_GRILLA_FILA_COLUMNA", sequenceName = "gen_ID_TIPO_PARAMETRO_GRILLA_FILA_COLUMNA",allocationSize = 1)
	@Column(name = "ID_TIPO_PARAMETRO_GRILLA_FILA_COLUMNA")
	private long idTipoParametroGrillaFilaColumna = -1;
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_PARAMETRO_GRILLA_FILA")
	private TipoParametroGrillaFila fila;
	
	@Column(name = "NRO_COLUMNA")
	private Integer nroColumna;
	
	private String condicion;
	
	private String valor;

	public TipoParametroGrillaFila getFila() {
		return fila;
	}

	public void setFila(TipoParametroGrillaFila fila) {
		this.fila = fila;
	}
	
	public Integer getNroColumna() {
		return nroColumna;
	}

	public void setNroColumna(Integer nroColumna) {
		this.nroColumna = nroColumna;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	
	public long getIdTipoParametroGrillaFilaColumna() {
		return idTipoParametroGrillaFilaColumna;
	}

	public void setIdTipoParametroGrillaFilaColumna(
			long idTipoParametroGrillaFilaColumna) {
		this.idTipoParametroGrillaFilaColumna = idTipoParametroGrillaFilaColumna;
	}

	public int compareTo(TipoParametroGrillaFilaColumna o) {
		return nroColumna.compareTo(o.nroColumna);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idTipoParametroGrillaFilaColumna ^ (idTipoParametroGrillaFilaColumna >>> 32));
		return result;
	}
	
	public String toString(){
		return "["+condicion+"]:"+"["+valor+"]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoParametroGrillaFilaColumna other = (TipoParametroGrillaFilaColumna) obj;
		if (this.idTipoParametroGrillaFilaColumna == -1 && other.idTipoParametroGrillaFilaColumna == -1){
			return this == other;
		}
		if (idTipoParametroGrillaFilaColumna != other.idTipoParametroGrillaFilaColumna)
			return false;
		return true;
	}
	
	@Override
	public TipoParametroGrillaFilaColumna clone() throws CloneNotSupportedException {
		TipoParametroGrillaFilaColumna locColumna = (TipoParametroGrillaFilaColumna) super.clone();
		locColumna.setIdTipoParametroGrillaFilaColumna(-1);
		return locColumna;
	}
}
