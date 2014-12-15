package com.trascender.contabilidad.gui.abmBoletaDeposito;

import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.BoletaDeposito;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionEgresos;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class BoletaDepositoBusquedaModel extends TAbstractBusquedaModel<BoletaDeposito> {
	
	private String numeroBoleta;
	private Double importeDesde;
	private Double importeHasta;
	private Date fechaDesde;
	private Date fechaHasta;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BoletaDeposito> buscar() throws Exception {
		if (this.getImporteDesde() == 0.0) {
			this.setImporteDesde(null);
		}
		if (this.getImporteHasta() == 0.0) {
			this.setImporteHasta(null);
		}
		
		System.out.println("");
		System.out.println("getNumeroBoleta --" + this.getNumeroBoleta());
		System.out.println("getImporteDesde --" + this.getImporteDesde());
		System.out.println("getImporteHasta --" + this.getImporteHasta());
		System.out.println("getFechaDesde   --" + this.getFechaDesde());
		System.out.println("getFechaHasta   --" + this.getFechaHasta());
		System.out.println("");
		
		SystemAdministracionEgresos locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos();
		List<BoletaDeposito> locLista = locSystem.findListaBoletaDeposito(this.getNumeroBoleta(), 
				this.getImporteDesde(), 
				this.getImporteHasta(), 
				this.getFechaDesde(), 
				this.getFechaHasta());

		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setNumeroBoleta(null);
		this.setImporteDesde(new Double(0.00));
		this.setImporteHasta(new Double(0.00));
		this.setFechaDesde(null);
		this.setFechaHasta(null);
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

	public Double getImporteDesde() {
		return importeDesde;
	}

	public void setImporteDesde(Double importeDesde) {
		this.importeDesde = importeDesde;
	}

	public Double getImporteHasta() {
		return importeHasta;
	}

	public void setImporteHasta(Double importeHasta) {
		this.importeHasta = importeHasta;
	}

	public String getNumeroBoleta() {
		return numeroBoleta;
	}

	public void setNumeroBoleta(String numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}
}
