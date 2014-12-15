package com.trascender.contabilidad.gui.abmReporteContable;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.filtros.FiltroReporteContable;
import com.trascender.contabilidad.recurso.persistent.ReporteContable;
import com.trascender.contabilidad.system.interfaces.SystemReportesContabilidad;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class ReporteContableBusquedaModel extends TAbstractBusquedaModel<ReporteContable>{
	
	private String nombre;
	
	@Override
	public List<ReporteContable> buscar() throws Exception {
		SystemReportesContabilidad locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemReportesContabilidad();  
		FiltroReporteContable locFiltro = new FiltroReporteContable();
		locFiltro.setNombre(this.getNombre());
		List<ReporteContable> locLista = locSystem.findListaReporteContable(locFiltro).getListaResultados();
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setNombre(null);
		
		this.fireActualizarDatos();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
