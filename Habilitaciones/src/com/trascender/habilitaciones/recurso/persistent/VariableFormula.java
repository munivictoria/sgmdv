package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "VARIABLE_FORMULA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_VARIABLE")
public abstract class VariableFormula implements Serializable, Cloneable, Comparable<VariableFormula>{
	private static final long serialVersionUID = 7940319258541796209L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_variable_formula")
	@SequenceGenerator(name = "gen_id_variable_formula", sequenceName = "gen_id_variable_formula",allocationSize = 1)
	@Column(name="ID_VARIABLE_FORMULA")
	protected long idVariableFormula = -1;
	
	protected String nombre;
	
	protected String expresion;
	
	public long getIdVariableFormula() {
		return idVariableFormula;
	}
	public void setIdVariableFormula(long idVariableFormula) {
		this.idVariableFormula = idVariableFormula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getExpresion() {
		return expresion;
	}
	public void setExpresion(String expresion) {
		this.expresion = expresion;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idVariableFormula ^ (idVariableFormula >>> 32));
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
		VariableFormula other = (VariableFormula) obj;
		if (idVariableFormula == -1 && other.idVariableFormula == -1){
			return this == other;
		}
		if (idVariableFormula != other.idVariableFormula)
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return nombre + " ["+expresion+"]";
	}
	
	public int compareTo(VariableFormula o) {
		return new Long(this.idVariableFormula).compareTo(new Long(o.idVariableFormula));
	}
	
	@Override
	public VariableFormula clone() throws CloneNotSupportedException{
		VariableFormula locVariable = (VariableFormula) super.clone();
		locVariable.setIdVariableFormula(-1);
		return  locVariable;
	}
	
}
