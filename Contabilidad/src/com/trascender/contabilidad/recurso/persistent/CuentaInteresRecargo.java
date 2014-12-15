package com.trascender.contabilidad.recurso.persistent;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.trascender.habilitaciones.recurso.persistent.ConceptoPorMora;

@Entity
@Table(name = "CUENTA_INTERES_RECARGO")
@PrimaryKeyJoinColumn(name = "ID_ASOCIACION_CUENTA")
public class CuentaInteresRecargo extends AsociacionCuenta {

	public static final long serialVersionUID = -7506577083524594922L;
	
	@ManyToOne
	@JoinColumn(name = "ID_CONCEPTO_MORA")
	private ConceptoPorMora conceptoPorMora;

	public ConceptoPorMora getConceptoPorMora() {
		return conceptoPorMora;
	}

	public void setConceptoPorMora(ConceptoPorMora conceptoPorMora) {
		this.conceptoPorMora = conceptoPorMora;
	}
}
