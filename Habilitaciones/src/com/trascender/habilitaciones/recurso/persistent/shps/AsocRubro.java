package com.trascender.habilitaciones.recurso.persistent.shps;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.trascender.habilitaciones.recurso.persistent.AsocRegAlicuota;


@Entity
@DiscriminatorValue("SHPS")
public class AsocRubro extends AsocRegAlicuota{
	private static final long serialVersionUID = 4176656596956892543L;

	private Boolean principal;

	public Boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}

	@Override
	public String toString() {
		return this.getRegistroAlicuota().getCodigo() + " [" + this.getRegistroAlicuota().getNombre() + "]";
	}
}
