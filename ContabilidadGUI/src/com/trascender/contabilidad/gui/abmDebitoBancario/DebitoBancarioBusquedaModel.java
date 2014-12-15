package com.trascender.contabilidad.gui.abmDebitoBancario;

import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Debito;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionEgresos;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class DebitoBancarioBusquedaModel extends TAbstractBusquedaModel<Debito>{

	private Date fechaEmisionDesde;
	private Date fechaEmisionHasta;
	private Double importeDesde;
	private Double importeHasta;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Debito> buscar() throws Exception {
		SystemAdministracionEgresos locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos();  
		List<Debito> locLista = locSystem.findListaDebito(this.getFechaEmisionDesde(), 
				this.getFechaEmisionHasta(), this.getImporteDesde(), this.getImporteHasta());
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setFechaEmisionDesde(null);
		this.setFechaEmisionHasta(null);
		this.setImporteDesde(null);
		this.setImporteHasta(null);
		
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

	public Double getImporteDesde() {
		return importeDesde;
	}

	public void setImporteDesde(Double imorteDesde) {
		this.importeDesde = imorteDesde;
	}

	public Double getImporteHasta() {
		return importeHasta;
	}

	public void setImporteHasta(Double importeHasta) {
		this.importeHasta = importeHasta;
	}
}
