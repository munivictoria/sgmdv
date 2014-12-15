package com.trascender.contabilidad.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CHEQUE")
public class PagoTicketCheque extends PagoTicket{
	
	@Column(name = "NUMERO_CHEQUE")
	private String numero;
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String getDescripcion() {
		String resultado = "Cheque ";
		if (this.numero != null){
			if (numero.length() > 12){
				resultado += numero.substring(numero.length() - 12 );
			} else {
				resultado += numero; 
			}
		}
		return resultado;
	}

}
