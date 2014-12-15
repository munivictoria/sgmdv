package com.trascender.habilitaciones.recurso.persistent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ANIO")
public class CondicionAplicacionExencionAnio extends CondicionAplicacionExencion{

	private static final long serialVersionUID = 6630506231371700086L;
	
	private Integer anio;

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	@Override
	public String getNombre() {
		return "[AÑO] " + this.anio.toString();
	}

	@Override
	public boolean seAplica(CuotaLiquidacion c) {
		if(c.getPeriodo().getCalendario().getAnio().equals(this.anio)){
			return true;
		}
		return false;
	}
}
