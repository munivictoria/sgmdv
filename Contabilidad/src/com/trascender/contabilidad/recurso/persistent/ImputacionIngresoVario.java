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
@Table(name = "IMPUTACION_INGRESO_VARIO")
public class ImputacionIngresoVario  implements Serializable{
	
	private static final long serialVersionUID = 8450434274561648602L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_imputacion_ingreso_vario")
	@SequenceGenerator(name = "gen_id_imputacion_ingreso_vario", sequenceName = "gen_id_imputacion_ingreso_vario",allocationSize = 1)
	@Column(name="ID_IMPUTACION_INGRESO_VARIO")
	private long idImputacionIngresoVario= -1;

	@ManyToOne
	@JoinColumn(name = "ID_INGRESO_VARIO")
	private IngresoVario ingresoVario;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA")
	private Cuenta cuenta;
	
	private Double monto = 0D;
	
	public long getIdImputacionIngresoVario() {
		return idImputacionIngresoVario;
	}

	public void setIdImputacionIngresoVario(long idImputacionIngresoVario) {
		this.idImputacionIngresoVario = idImputacionIngresoVario;
	}

	public IngresoVario getIngresoVario() {
		return ingresoVario;
	}

	public void setIngresoVario(IngresoVario ingresoVario) {
		this.ingresoVario = ingresoVario;
	}
	
	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}
}
