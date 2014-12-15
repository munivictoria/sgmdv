package com.trascender.contabilidad.gui.abmIngresoTesoreria;

import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.MovimientoCajaIngreso;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionIngresos;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class MovimientoCajaIngresoBusquedaModel extends TAbstractBusquedaModel<MovimientoCajaIngreso> {

	private Date fechaEmisionDesde;
	private Date fechaEmisionHasta;
	
	@Override
	public List<MovimientoCajaIngreso> buscar() throws Exception {
		SystemAdministracionIngresos locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionIngresos();  
		List<MovimientoCajaIngreso> locLista = 
			locSystem.findListaMovimientoCajaIngreso(this.getFechaEmisionDesde(), this.getFechaEmisionHasta(), null, null, null);
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
