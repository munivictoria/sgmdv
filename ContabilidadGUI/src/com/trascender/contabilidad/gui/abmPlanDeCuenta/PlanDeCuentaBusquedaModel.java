package com.trascender.contabilidad.gui.abmPlanDeCuenta;

import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.PlanDeCuenta;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class PlanDeCuentaBusquedaModel extends TAbstractBusquedaModel<PlanDeCuenta>{

	private String descripcion;
	private Date fechaDesde;
	private Date fechaHasta;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PlanDeCuenta> buscar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		List<PlanDeCuenta> locLista = locSystem.findListaPlanDeCuenta(this.getDescripcion(), this.getFechaDesde(),this.getFechaHasta());
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setDescripcion(null);
		this.setFechaDesde(null);
		this.setFechaHasta(null);
		
		super.fireActualizarDatos();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
}
