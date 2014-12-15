package com.trascender.framework.recurso.filtros;

import com.trascender.framework.recurso.persistent.ReportesJasper;
import com.trascender.framework.util.FiltroAbstracto;

public class FiltroReportesJasper extends FiltroAbstracto<ReportesJasper> {
	
	public static final long serialVersionUID = 7854697460746221773L;
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String pNombre) {
		nombre = pNombre;
	}
	
}
