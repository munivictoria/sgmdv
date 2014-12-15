package com.trascender.contabilidad.recurso.persistent;

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
@Table(name = "RELA_CONCEPTO_INGRESO_VARIO_CUENTA")
public class RelaConceptoIngresoVarioCuenta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6040736411057086341L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_rela_concepto_ingreso_vario_cuenta")
	@SequenceGenerator(name = "gen_id_rela_concepto_ingreso_vario_cuenta", sequenceName = "gen_id_rela_concepto_ingreso_vario_cuenta",allocationSize = 1)
	@Column(name="ID_RELA_CONCEPTO_INGRESO_VARIO_CUENTA")
	private long idRelaConceptoIngresoVarioCuenta= -1;
	
	@ManyToOne
	@JoinColumn(name = "ID_CONCEPTO_INGRESO_VARIO")
	private ConceptoIngresoVario conceptoIngresoVario;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA")
	private Cuenta cuenta;
	
	private boolean obligatoria = false;
	
	@Column(name="MONTO_POR_DEFECTO")
	private Double montoPorDefecto = 0D;
	
	public long getIdRelaConceptoIngresoVarioCuenta() {
		return idRelaConceptoIngresoVarioCuenta;
	}

	public void setIdRelaConceptoIngresoVarioCuenta(
			long idRelaConceptoIngresoVarioCuenta) {
		this.idRelaConceptoIngresoVarioCuenta = idRelaConceptoIngresoVarioCuenta;
	}

	public ConceptoIngresoVario getConceptoIngresoVario() {
		return conceptoIngresoVario;
	}

	public void setConceptoIngresoVario(ConceptoIngresoVario conceptoIngresoVario) {
		this.conceptoIngresoVario = conceptoIngresoVario;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public boolean isObligatoria() {
		return obligatoria;
	}

	public void setObligatoria(boolean obligatoria) {
		this.obligatoria = obligatoria;
	}

	public Double getMontoPorDefecto() {
		return montoPorDefecto;
	}

	public void setMontoPorDefecto(Double montoPorDefecto) {
		this.montoPorDefecto = montoPorDefecto;
	}
}
