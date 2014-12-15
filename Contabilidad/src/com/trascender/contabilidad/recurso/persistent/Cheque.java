/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import java.text.DateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "CHEQUE")
@PrimaryKeyJoinColumn(name = "ID_MOVIMIENTO_BANCARIO")
public class Cheque extends MovimientoBancario{

	public  static final long serialVersionUID = -6531122167892550117L;
	
	@Column(name = "NUMERO_CHEQUE")
	private String numeroCheque;
	
	@Column(name = "FECHA_EMISION")
	private Date fechaEmision;
	
	@Column(name = "FECHA_PAGO")
	private Date fechaPago;
	private boolean postdatado;

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public boolean isPostdatado() {
		return postdatado;
	}

	public void setPostdatado(boolean postdatado) {
		this.postdatado = postdatado;
	}
	
	@Override
	public String toString(){
		DateFormat locDateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
		return this.numeroCheque +"  "+ this.getImporte()+" ("+locDateFormat.format(this.fechaEmision)+")";
	}
	
}
