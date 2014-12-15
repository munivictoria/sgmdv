package com.trascender.habilitaciones.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * El concepto por mora hace referencia al interés y el recargo aplicado sobre una tasa
 * luego de la liquidación. Por lo tanto es considerado solo en la reliquidación
 * @author Mariano Lusardi
 * @since 1.1
 */
@Entity
@Table(name = "CONCEPTO_MORA")
public class ConceptoPorMora implements Serializable, Cloneable{

	/**
	 * 
	 */
	public static final long serialVersionUID = -5651629992445712626L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="gen_id_concepto_mora")
	@SequenceGenerator(name = "gen_id_concepto_mora", sequenceName = "gen_id_concepto_mora", allocationSize = 1)
	@Column(name = "ID_CONCEPTO_MORA")
	private long idConceptoPorMora = -1;
	private String nombre;
	private String formula;
	
	public long getIdConceptoPorMora() {
		return idConceptoPorMora;
	}
	public void setIdConceptoPorMora(long idConceptoPorMora) {
		this.idConceptoPorMora = idConceptoPorMora;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}

	@Override
	public String toString() {
		return this.getNombre();
	}
	
	@Override
	public ConceptoPorMora clone() throws CloneNotSupportedException {
		ConceptoPorMora nuevoConceptoPorMora = (ConceptoPorMora) super.clone();
		nuevoConceptoPorMora.setIdConceptoPorMora(-1);
		return nuevoConceptoPorMora;
	}
}
