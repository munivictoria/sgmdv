package com.trascender.contabilidad.recurso.persistent;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "CUENTA_CONCEPTO_INGRESO_VARIO")
@PrimaryKeyJoinColumn(name = "ID_ASOCIACION_CUENTA")
public class CuentaConceptoIngresoVario extends AsociacionCuenta{

	public static final long serialVersionUID = 596268511018024580L;
	
	@ManyToOne
	@JoinColumn(name = "ID_CONCEPTO_INGRESO_VARIO")
	private ConceptoIngresoVario conceptoIngresoVario;

	public ConceptoIngresoVario getConceptoIngresoVario() {
		return conceptoIngresoVario;
	}

	public void setConceptoIngresoVario(ConceptoIngresoVario conceptoIngresoVario) {
		this.conceptoIngresoVario = conceptoIngresoVario;
	}
	
}
