package com.trascender.contabilidad.recurso.persistent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("COMPENSACION")
public class PagoTicketCompensacion extends PagoTicket{

	private static final long serialVersionUID = -2654418604693238178L;

	@Override
	public String getDescripcion() {
		return "Compensación";
	}
	

}
