package com.trascender.contabilidad.gui.abmAsientoContable;

import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.AsientoContable;
import com.trascender.contabilidad.recurso.persistent.FolioLibroDiario;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionConsultaContable;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class AsientoContableBusquedaModel extends TAbstractBusquedaModel<AsientoContable> {
	
	private Integer numeroAsiento;
	private Date fechaDesde;
	private Date fechaHasta;
	private FolioLibroDiario folioLibroDiario;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AsientoContable> buscar() throws Exception {
		SystemAdministracionConsultaContable locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionConsultaContable();
		List<AsientoContable> locList = locSystem.findListaAsientoContable(this.getNumeroAsiento(), this.getFechaDesde(), this.getFechaHasta(), this.getFolioLibroDiario());
		return locList;
	}

	@Override
	public void reiniciar() {
		this.setNumeroAsiento(null);
		this.setFechaDesde(null);
		this.setFechaHasta(null);
		this.setFolioLibroDiario(null);
		
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

	public FolioLibroDiario getFolioLibroDiario() {
		return folioLibroDiario;
	}

	public void setFolioLibroDiario(FolioLibroDiario folioLibroDiario) {
		this.folioLibroDiario = folioLibroDiario;
	}

	public Integer getNumeroAsiento() {
		return numeroAsiento;
	}

	public void setNumeroAsiento(Integer numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}
	
}
