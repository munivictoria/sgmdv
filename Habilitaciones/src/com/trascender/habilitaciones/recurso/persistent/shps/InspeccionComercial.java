package com.trascender.habilitaciones.recurso.persistent.shps;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@DiscriminatorValue(value = "COMERCIAL")
public class InspeccionComercial extends InspeccionSHPS {

	/**
	 * 
	 */
	public static final long serialVersionUID = -5947675855033386854L;
	
	@ManyToOne
	@JoinColumn(name = "ID_LOCAL_COMERCIAL")
	private LocalComercial localComercial;

	public LocalComercial getLocalComercial() {
		return localComercial;
	}
	public void setLocalComercial(LocalComercial localComercial) {
		this.localComercial = localComercial;
	}
	
}
