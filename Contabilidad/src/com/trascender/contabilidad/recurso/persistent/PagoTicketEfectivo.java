package com.trascender.contabilidad.recurso.persistent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EFECTIVO")
public class PagoTicketEfectivo extends PagoTicket{

	@Override
	public String getDescripcion() {
		return "Efectivo";
	}

}
