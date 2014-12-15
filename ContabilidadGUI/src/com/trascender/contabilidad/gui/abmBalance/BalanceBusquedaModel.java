package com.trascender.contabilidad.gui.abmBalance;

import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Balance;
import com.trascender.contabilidad.recurso.persistent.TipoBalance;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class BalanceBusquedaModel extends TAbstractBusquedaModel<Balance> {
	
	private TipoBalance tipoBalance;
	private String nombre;
	private Date fechaDesde;
	private Date fechaHasta;
	
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Balance> buscar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		List<Balance> locList = locSystem.findListaBalance(this.getNombre(), this.getFechaDesde(), this.getFechaHasta(), this.getTipoBalance()); 
		return locList;
	}
	
	@Override
	public void reiniciar() {
		this.setFechaDesde(null);
		this.setFechaHasta(null);
		this.setNombre(null);
		this.setTipoBalance(null);
		
		this.fireActualizarDatos();
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

	public TipoBalance getTipoBalance() {
		return tipoBalance;
	}

	public void setTipoBalance(TipoBalance tipoBalance) {
		this.tipoBalance = tipoBalance;
	}
	
}
