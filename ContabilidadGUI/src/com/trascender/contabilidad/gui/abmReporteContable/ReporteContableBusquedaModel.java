package com.trascender.contabilidad.gui.abmReporteContable;

import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.system.interfaces.SystemReportesContabilidad;
import com.trascender.framework.recurso.filtros.FiltroReporte;
import com.trascender.framework.recurso.persistent.reporteDinamico.Reporte;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class ReporteContableBusquedaModel extends TAbstractBusquedaModel<Reporte>{
	
	private String nombre;
	
	@Override
	public List<Reporte> buscar() throws Exception {
		SystemReportesContabilidad locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemReportesContabilidad();  
		FiltroReporte locFiltro = new FiltroReporte();
		locFiltro.setNombre(this.getNombre());
		List<Reporte> locLista = locSystem.findListaReporteContable(locFiltro).getListaResultados();
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
