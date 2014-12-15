package com.trascender.compras.recurso.filtros;

import com.trascender.compras.recurso.persistent.suministros.AutorizacionSolicitudSuministro;
import com.trascender.framework.recurso.persistent.Area;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroAutorizacionSolicitudSuministro extends FiltroAbstracto<AutorizacionSolicitudSuministro>{

	private static final long serialVersionUID = -2472033327654107969L;
	
	private Area area;

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
}
