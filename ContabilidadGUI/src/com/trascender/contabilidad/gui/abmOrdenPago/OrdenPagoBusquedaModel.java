package com.trascender.contabilidad.gui.abmOrdenPago;

import java.util.Date;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.OrdenPago;
import com.trascender.contabilidad.recurso.persistent.DocumentoEgreso.Estado;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionEgresos;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class OrdenPagoBusquedaModel extends TAbstractBusquedaModel<OrdenPago> {

	private Date fechaEmisionDesde;
	private Date fechaEmisionHasta;
	private Date fechaPagoDesde;
	private Date fechaPagoHasta;
	private Double importeDesde;
	private Double importeHasta;
	private Proveedor proveedor;
	private Estado estado;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenPago> buscar() throws Exception {
//		System.out.println("this.getFechaEmisionDesde() ---> " + this.getFechaEmisionDesde());
//		System.out.println("this.getFechaEmisionHasta() ---> " + this.getFechaEmisionHasta());
//		System.out.println("this.getFechaPagoDesde()    ---> " + this.getFechaPagoDesde());
//		System.out.println("this.getFechaPagoHasta()    ---> " + this.getFechaPagoHasta());
//		System.out.println("this.getImporteDesde()      ---> " + this.getImporteDesde());
//		System.out.println("this.getImporteHasta()      ---> " + this.getImporteHasta());
//		System.out.println("this.getProveedor()         ---> " + this.getProveedor());
//		System.out.println("this.gthis.getEstado()      ---> " + this.getEstado());
//		System.out.println("");
		if (this.getImporteDesde()!= null && this.getImporteDesde() == 0.0) {
			this.setImporteDesde(null);
		}
		if (this.getImporteHasta()!= null && this.getImporteHasta() == 0.0) {
			this.setImporteHasta(null);
		}
		
		SystemAdministracionEgresos locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos();
		List<OrdenPago> listaOrdenPago = locSystem.findListaOrdenPago(this.getFechaEmisionDesde(), this.getFechaEmisionHasta(), 
				this.getFechaPagoDesde(), this.getFechaPagoHasta(), 
				this.getImporteDesde(), this.getImporteHasta(), 
				this.getProveedor(), this.getEstado());
		return listaOrdenPago;
		
	}

	@Override
	public void reiniciar() {
		this.setFechaEmisionDesde(null);
		this.setFechaEmisionHasta(null);
		this.setFechaPagoDesde(null);
		this.setFechaPagoHasta(null);
		this.setImporteDesde(new Double(0.00));
		this.setImporteHasta(new Double(0.00));
		this.setProveedor(null);
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

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}
