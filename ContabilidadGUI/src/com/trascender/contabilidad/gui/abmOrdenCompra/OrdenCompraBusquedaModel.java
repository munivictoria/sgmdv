package com.trascender.contabilidad.gui.abmOrdenCompra;

import java.util.Date;
import java.util.List;

import com.trascender.compras.recurso.filtros.FiltroOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.OrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.TipoOrdenCompra;
import com.trascender.compras.system.interfaces.SystemAdministracionOrdenCompra;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class OrdenCompraBusquedaModel extends TAbstractBusquedaModel<OrdenCompra> {

	private Date fechaDesde;
	private Date fechaHasta;
	private TipoOrdenCompra tipoOrdenCompra;
	private Proveedor proveedor;
	private OrdenCompra.Estado estado;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenCompra> buscar() throws Exception {
		SystemAdministracionOrdenCompra locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionOrdenCompra();
		FiltroOrdenCompra locFiltro = new FiltroOrdenCompra();
		locFiltro.setEstado(this.getEstado());
		locFiltro.setFechaDesde(this.getFechaDesde());
		locFiltro.setFechaHasta(this.getFechaHasta());
		locFiltro.setProveedor(this.getProveedor());
		List<OrdenCompra> locLista = locSystem.findOrdenCompra(locFiltro).getListaResultados();
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setFechaDesde(null);
		this.setFechaHasta(null);
		this.setTipoOrdenCompra(null);
		//this.setProveedor(null);
		this.setEstado(null);
		
		this.fireActualizarDatos();
	}

	public OrdenCompra.Estado getEstado() {
		return estado;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public TipoOrdenCompra getTipoOrdenCompra() {
		return tipoOrdenCompra;
	}

	public void setEstado(OrdenCompra.Estado estado) {
		this.estado = estado;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public void setTipoOrdenCompra(TipoOrdenCompra tipoOrdenCompra) {
		this.tipoOrdenCompra = tipoOrdenCompra;
	}

}
