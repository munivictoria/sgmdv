package com.trascender.contabilidad.gui.abmCheque;

import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Cheque;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionEgresos;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class ChequeBusquedaModel extends TAbstractBusquedaModel<Cheque> {

	private String numeroCheque;
	private Date fechaEmisionDesde;
	private Date fechaEmisionHasta;
	private Date fechaPagoDesde;
	private Date fechaPagoHasta;
	private Double imorteDesde;
	private Double importeHasta;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cheque> buscar() throws Exception {
		
		if (this.getImporteDesde()!= null && this.getImporteDesde() == 0.0) {
			this.setImporteDesde(null);
		}
		if (this.getImporteHasta()!= null && this.getImporteHasta() == 0.0) {
			this.setImporteHasta(null);
		}
		
		SystemAdministracionEgresos locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos();  
		List<Cheque> locLista = locSystem.findListaCheque(this.getNumeroCheque(), 
				this.getFechaEmisionDesde(), this.getFechaEmisionHasta(), 
				this.getFechaPagoDesde(), this.getFechaPagoHasta(), 
				this.getImporteDesde(), this.getImporteHasta());
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setNumeroCheque(null);
		this.setFechaEmisionDesde(null);
		this.setFechaEmisionHasta(null);
		this.setFechaPagoDesde(null);
		this.setFechaPagoHasta(null);
		this.setImporteDesde(new Double(0.00));
		this.setImporteHasta(new Double(0.00));
		
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

	public Date getFechaPagoDesde() {
		return fechaPagoDesde;
	}

	public void setFechaPagoDesde(Date fechaPagoDesde) {
		this.fechaPagoDesde = fechaPagoDesde;
	}

	public Date getFechaPagoHasta() {
		return fechaPagoHasta;
	}

	public void setFechaPagoHasta(Date fechaPagoHasta) {
		this.fechaPagoHasta = fechaPagoHasta;
	}

	public Double getImporteDesde() {
		return imorteDesde;
	}

	public void setImporteDesde(Double importeDesde) {
		this.imorteDesde = importeDesde;
	}

	public Double getImporteHasta() {
		return importeHasta;
	}

	public void setImporteHasta(Double importeHasta) {
		this.importeHasta = importeHasta;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
}

