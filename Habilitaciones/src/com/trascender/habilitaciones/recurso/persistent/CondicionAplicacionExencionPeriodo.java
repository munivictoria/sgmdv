package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@DiscriminatorValue(value = "PERIODO")
public class CondicionAplicacionExencionPeriodo extends CondicionAplicacionExencion{

	private static final long serialVersionUID = -1446870034261547212L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERIODO")
	private PeriodoLiquidacion periodo;

	public PeriodoLiquidacion getPeriodo() {
		return periodo;
	}

	public void setPeriodo(PeriodoLiquidacion periodo) {
		this.periodo = periodo;
	}

	@Override
	public String getNombre() {
		return "[PERÍODO] " + this.periodo.getNombre() + ". Calendario: " + this.periodo.getCalendario().getNombre() + ". Año: " + this.periodo.getCalendario().getAnio();
	}

	@Override
	public boolean seAplica(CuotaLiquidacion c) {
		if(c.getPeriodo().equals(this.periodo)){
			return true;
		}
		return false;
	}
}
