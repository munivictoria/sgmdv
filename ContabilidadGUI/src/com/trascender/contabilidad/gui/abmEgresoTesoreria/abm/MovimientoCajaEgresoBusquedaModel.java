package com.trascender.contabilidad.gui.abmEgresoTesoreria.abm;

import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaEgreso;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionEgresos;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class MovimientoCajaEgresoBusquedaModel extends TAbstractBusquedaModel<MovimientoCajaEgreso> {

	private Date fechaEmisionDesde;
	private Date fechaEmisionHasta;
	
	@Override
	public List<MovimientoCajaEgreso> buscar() throws Exception {
		SystemAdministracionEgresos locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos();  
		List<MovimientoCajaEgreso> locLista = locSystem.findListaMovimientoCajaEgreso(this.getFechaEmisionDesde(), this.getFechaEmisionHasta(), null, null);
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setFechaEmisionDesde(null);
		this.setFechaEmisionHasta(null);
		this.fireActualizarDatos();
	}

	public Date getFechaEmisionDesde() {
		return fechaEmisionDesde;
	}

	public void setFechaEmisionDesde(Date fechaEmisionDesde) {
		this.fechaEmisionDesde = fechaEmisionDesde;
	}

	public Date getFechaEmisionHasta() {
		return fechaEmisionHasta;
	}

	public void setFechaEmisionHasta(Date fechaEmisionHasta) {
		this.fechaEmisionHasta = fechaEmisionHasta;
	}

}
