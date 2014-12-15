package com.trascender.habilitaciones.recurso.persistent;

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
@Table(name = "TIPO_VENCIMIENTO")
public class TipoVencimiento implements Serializable,Cloneable {

	/**
	 * 
	 */
	public static final long serialVersionUID = 6384750739693633247L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_tipo_vencimiento")
	@SequenceGenerator(name = "gen_id_tipo_vencimiento", sequenceName = "gen_id_tipo_vencimiento", allocationSize = 1)
	@Column(name = "ID_TIPO_VENCIMIENTO")
	private long idTipoVencimiento=-1;
	
	private String condicion;
	private Integer meses;
	private Integer dias;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(name = "FORMULA_CALCULO")
	private String formulaCalculo;
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_TASA", nullable = false)
	private TipoTasa tipoTasa;
	
	public TipoTasa getTipoTasa() {
		return tipoTasa;
	}
	public void setTipoTasa(TipoTasa tipoTasa) {
		this.tipoTasa = tipoTasa;
	}
	public String getCondicion() {
		return condicion;
	}
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	public Integer getDias() {
		return dias;
	}
	public void setDias(Integer dias) {
		this.dias = dias;
	}
	
	public String getFormulaCalculo() {
		return formulaCalculo;
	}
	public void setFormulaCalculo(String formulaCalculo) {
		this.formulaCalculo = formulaCalculo;
	}
	public Integer getMeses() {
		return meses;
	}
	public void setMeses(Integer meses) {
		this.meses = meses;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public long getIdTipoVencimiento() {
		return idTipoVencimiento;
	}
	public void setIdTipoVencimiento(long idTipoVencimiento) {
		this.idTipoVencimiento = idTipoVencimiento;
	}
	
	@Override
	public String toString() {
		return (this.getNombre()!=null)?this.getNombre():"";
	}
	
	@Override
	public int hashCode() {
		if (this.idTipoVencimiento==-1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idTipoVencimiento ^ (idTipoVencimiento >>> 32));
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
		final TipoVencimiento other = (TipoVencimiento) obj;
		if (idTipoVencimiento != other.idTipoVencimiento)
			return false;
		return true;
	}
	
	
	@Override
	public TipoVencimiento clone() throws CloneNotSupportedException {
		TipoVencimiento locTipoVencimiento=(TipoVencimiento)super.clone();
		locTipoVencimiento.setIdTipoVencimiento(-1);
		locTipoVencimiento.setTipoTasa(null);
		return locTipoVencimiento;
		
	}
	
}
