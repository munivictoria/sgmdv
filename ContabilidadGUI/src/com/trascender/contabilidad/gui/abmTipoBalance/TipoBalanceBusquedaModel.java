package com.trascender.contabilidad.gui.abmTipoBalance;

import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.TipoBalance;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class TipoBalanceBusquedaModel extends TAbstractBusquedaModel<TipoBalance>{

	private String nombre;
	private Date fechaDesde;
	private Date fechaHasta;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoBalance> buscar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		List<TipoBalance> locLista = locSystem.findListaTipoBalance(this.getNombre(), this.getFechaDesde(), this.getFechaHasta());
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setNombre(null);
		this.setFechaDesde(null);
		this.setFechaHasta(null);
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
