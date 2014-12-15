package com.trascender.saic.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MODIFICADOR_TASA")
public class ModificadorTasa implements Serializable {


	private static final long serialVersionUID = 9113360169533519998L;
	
	
	@Id
	@SequenceGenerator(name = "gen_id_modificador_tasa", allocationSize = 1, sequenceName = "gen_id_modificador_tasa")
	@GeneratedValue(generator = "gen_id_modificador_tasa", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_MODIFICADOR_TASA")
	private long idModificadorTasa=-1;
	
	private String nombre;
	
	private Double valor;
	
	private boolean fijo;
	
	@Column(name = "SOBRE_SALDO_NETO")
	private boolean sobreSaldoNeto;
	
	private String condicion;
	
	@Column(name = "DESDE_DIAS")
	private Integer desdeDias;
	
	@Column(name = "HASTA_DIAS")
	private Integer hastaDias;
	
	@Column(name = "DESDE_MESES")
	private Integer desdeMeses;
	
	@Column(name = "HASTA_MESES")
	private Integer hastaMeses;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_DOC_GENERADOR_DEUDA", nullable = false)
	private Tasa tasa;

	
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
	

	public boolean isFijo() {
		return fijo;
	}
	public void setFijo(boolean fijo) {
		this.fijo = fijo;
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
	

	public long getIdModificadorTasa() {
		return idModificadorTasa;
	}
	public void setIdModificadorTasa(long idModificadorTasa) {
		this.idModificadorTasa = idModificadorTasa;
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
	
	
	public Tasa getTasa() {
		return tasa;
	}
	public void setTasa(Tasa tasa) {
		this.tasa = tasa;
	}
	

	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
	@Override
	public int hashCode() {
		if (this.idModificadorTasa==-1) return super.hashCode();
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idModificadorTasa ^ (idModificadorTasa >>> 32));
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
		final ModificadorTasa other = (ModificadorTasa) obj;
		if (idModificadorTasa != other.idModificadorTasa)
			return false;
		return true;
	}
	
	
	
}
