package com.trascender.contabilidad.gui.abmFactura;

import java.util.Date;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.Factura;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.Factura.Estado;
import com.trascender.compras.system.interfaces.SystemAdministracionFactura;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class FacturaBusquedaModel extends TAbstractBusquedaModel<Factura> {

	private Proveedor proveedor;
	private Date fechaDesde;
	private Date fechaHasta;
	private Estado estado;
	
	@Override
	public List<Factura> buscar() throws Exception {
		SystemAdministracionFactura locSystem = ContabilidadGUI.getInstance().getAdminSystemsCompras().getSystemAdministracionFactura();
		locSystem.setLlave(ContabilidadGUI.getInstance().getLlaveUsuarioConectado());
		List<Factura> locList = locSystem.findListaFactura(this.getProveedor(), this.getFechaDesde(), this.getFechaHasta(), this.getEstado()); 
		return locList;
	}

	@Override
	public void reiniciar() {
		this.setProveedor(null);
		this.setFechaDesde(null);
		this.setFechaHasta(null);
		this.setEstado(null);
		
		this.fireActualizarDatos();
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
