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
 * Es una clase "auxiliar" para salvar las formulas relacionadas a un TipoTasa,
 * necesaria pues las formulas son muy grandes y crean conflictos al levantarlas de la base,
 * y la anotacion @Basic(LAZY) no funcionó para anotar la formula cuando estaba
 * directamente en TipoTasa
 * 
 * @author fer
 *
 */
@Entity
@Table(name = "FORMULA_TIPO_TASA")
public class FormulaTipoTasa implements Serializable{

	private static final long serialVersionUID = 4827643057454813294L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_formula_tipo_tasa")
	@SequenceGenerator(name = "gen_id_formula_tipo_tasa", sequenceName = "gen_id_formula_tipo_tasa",allocationSize = 1)
	@Column(name="ID_FORMULA_TIPO_TASA")
	private long idFormulaTipoTasa = -1;
	
	private String formula;

	public long getIdFormulaTipoTasa() {
		return idFormulaTipoTasa;
	}

	public void setIdFormulaTipoTasa(long idFormulaTipoTasa) {
		this.idFormulaTipoTasa = idFormulaTipoTasa;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	
}
