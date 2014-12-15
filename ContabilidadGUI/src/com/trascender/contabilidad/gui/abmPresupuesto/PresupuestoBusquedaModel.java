package com.trascender.contabilidad.gui.abmPresupuesto;

import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Presupuesto;
import com.trascender.contabilidad.recurso.persistent.Presupuesto.Estado;
import com.trascender.contabilidad.recurso.persistent.Presupuesto.Tipo;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class PresupuestoBusquedaModel extends TAbstractBusquedaModel<Presupuesto>{
	
	private Tipo tipoPresupuesto;
	private Estado estado;
	private Date fechaDesde;
	private Date fechaHasta;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Presupuesto> buscar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		List<Presupuesto> locLista = locSystem.findListaPresupuesto(this.getTipoPresupuesto(), this.getEstado(), this.getFechaDesde(), this.getFechaHasta());
		return locLista;
	}
	
	@Override
	public void reiniciar() {
		this.setTipoPresupuesto(null);
		this.setEstado(null);
		this.setFechaDesde(null);
		this.setFechaHasta(null);
		
		fireActualizarDatos();
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Tipo getTipoPresupuesto() {
		return tipoPresupuesto;
	}
	
	public void setTipoPresupuesto(Tipo tipoPresupuesto) {
		this.tipoPresupuesto = tipoPresupuesto;
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
