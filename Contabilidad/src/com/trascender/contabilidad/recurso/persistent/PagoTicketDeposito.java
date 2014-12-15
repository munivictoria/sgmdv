package com.trascender.contabilidad.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DEPOSITO")
public class PagoTicketDeposito extends PagoTicket{

	private static final long serialVersionUID = -6134498997781051363L;
	
	@Column(name = "NUMERO_TRANSACCION")
	private String numeroTransaccion;

	@Override
	public String getDescripcion() {
		String resultado = "Depósito ";
		if (this.numeroTransaccion != null){
			if (numeroTransaccion.length() > 12){
				resultado += numeroTransaccion.substring(numeroTransaccion.length() - 12 );
			} else {
				resultado += numeroTransaccion; 
			}
		}
		return resultado;
	}

	public String getNumeroTransaccion() {
		return numeroTransaccion;
	}

	public void setNumeroTransaccion(String numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}
	
}
