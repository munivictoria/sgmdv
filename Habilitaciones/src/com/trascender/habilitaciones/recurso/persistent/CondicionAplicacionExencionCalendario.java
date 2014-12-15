package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@DiscriminatorValue(value = "CALENDARIO")
public class CondicionAplicacionExencionCalendario extends CondicionAplicacionExencion{

	private static final long serialVersionUID = 1216473910914843065L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CALENDARIO")
	private CalendarioMunicipal calendario;

	public CalendarioMunicipal getCalendario() {
		return calendario;
	}

	public void setCalendario(CalendarioMunicipal calendario) {
		this.calendario = calendario;
	}

	@Override
	public String getNombre() {
		return "[CALENDARIO] " + this.calendario.getNombre() + ". Año: " + this.calendario.getAnio();
	}

	@Override
	public boolean seAplica(CuotaLiquidacion c) {
		if(c.getPeriodo().getCalendario().equals(this.calendario)){
			return true;
		}
		return false;
	}
}
