package com.trascender.expedientes.recurso.persistent;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.trascender.framework.recurso.persistent.Area;

@Entity
@DiscriminatorValue( value = "area")
public class AreaResponsable extends Responsabilidad{
	private static final long serialVersionUID = -5642602333567155302L;
	
	@ManyToOne
	@JoinColumn(name = "ID_AREA")
	private Area area;

	@Override
	public Object getEntidadResponsable() {
		return getArea();
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
}
