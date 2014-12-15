package com.trascender.contabilidad.recurso.transients;

import java.io.Serializable;
import java.util.Date;

public abstract class MovCuentaCorriente implements Serializable{
	private static final long serialVersionUID = 7567248215671749681L;
	
	private Date fecha;
	private Double importe;
	private Integer numero;
	
	private CuentaCorriente cuentaCorriente;
	
	public CuentaCorriente getCuentaCorriente() {
		return cuentaCorriente;
	}
	public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}


}
