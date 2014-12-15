package com.trascender.contabilidad.gui.abmOrdenPagoDevolucion;

import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.OrdenPagoDevolucion;
import com.trascender.contabilidad.recurso.persistent.DocumentoEgreso.Estado;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionEgresos;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class OrdenPagoDevolucionBusquedaModel extends TAbstractBusquedaModel<OrdenPagoDevolucion> {

	private Date fechaEmisionDesde;
	private Date fechaEmisionHasta;
	private Date fechaPagoDesde;
	private Date fechaPagoHasta;
	private Double importeDesde;
	private Double importeHasta;
	private Persona persona;
	private Estado estado;
	
	@Override
	public List<OrdenPagoDevolucion> buscar() throws Exception {
		if (this.getImporteDesde() == 0.0) {
			this.setImporteDesde(null);
		}
		if (this.getImporteHasta() == 0.0) {
			this.setImporteHasta(null);
		}
		
		SystemAdministracionEgresos locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos();
		List<OrdenPagoDevolucion> listaOrdenPago = locSystem.findListaOrdenPagoDev(this.getFechaEmisionDesde(), this.getFechaEmisionHasta(), 
				this.getFechaPagoDesde(), this.getFechaPagoHasta(), this.getImporteDesde(), 
				this.getImporteHasta(), this.getPersona(), this.getEstado());
		return listaOrdenPago;
//		return null;
	}

	@Override
	public void reiniciar() {
		this.setFechaEmisionDesde(null);
		this.setFechaEmisionHasta(null); 
		this.setFechaPagoDesde(null); 
		this.setFechaPagoHasta(null); 
		this.setImporteDesde(new Double(0.00));
		this.setImporteHasta(new Double(0.00));
		this.setPersona(null); 
		this.setEstado(null);
		
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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
