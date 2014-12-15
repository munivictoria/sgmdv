package com.trascender.habilitaciones.recurso.persistent.shps;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@DiscriminatorValue(value = "VEHICULAR")
public class InspeccionVehicular extends InspeccionSHPS{

	/**
	 * 
	 */
	public static final long serialVersionUID = -8471309229715386886L;

	@ManyToOne
	@JoinColumn(name = "ID_TRANSPORTE_VEHICULAR", nullable = false)
	private TransporteVehicular transporteVehicular;
	
	public TransporteVehicular getTransporteVehicular() {
		return transporteVehicular;
	}

	public void setTransporteVehicular(TransporteVehicular transporteVehicular) {
		this.transporteVehicular = transporteVehicular;
	}
	
	
}
