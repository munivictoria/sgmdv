package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.trascender.framework.recurso.transients.Calendario;


@Entity
@DiscriminatorValue(value = "CUOTA")
public class CondicionAplicacionExencionCuota extends CondicionAplicacionExencion{
	
	private static final long serialVersionUID = 9188681021331877927L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CUOTA_LIQUIDACION")
	private CuotaLiquidacion cuota;

	public CuotaLiquidacion getCuota() {
		return cuota;
	}

	public void setCuota(CuotaLiquidacion cuota) {
		this.cuota = cuota;
	}

	@Override
	public String getNombre() {
		PeriodoLiquidacion locPeriodo = this.cuota.getPeriodo();
		Calendario locCalendario = locPeriodo.getCalendario();
		return "[CUOTA] " + this.cuota.getNombre() + ". Período: " + this.cuota.getPeriodo() + ". Calendario: " + locCalendario + ". Año: " + locCalendario.getAnio();
	}

	@Override
	public boolean seAplica(CuotaLiquidacion c) {
		if(c.equals(this.cuota)){
			return true;
		}
		return false;
	}
}
