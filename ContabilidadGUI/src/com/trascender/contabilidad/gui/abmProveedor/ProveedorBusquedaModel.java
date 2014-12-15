package com.trascender.contabilidad.gui.abmProveedor;

import java.util.List;

import com.trascender.compras.recurso.filtros.FiltroProveedores;
import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.GrupoProveedor;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.system.interfaces.SystemAdministracionProveedores;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class ProveedorBusquedaModel extends TAbstractBusquedaModel<Proveedor> {
	
	private String razonSocial;
	private String codigo;
	private Proveedor.Estado estado;

	@SuppressWarnings("unchecked")
	@Override
	public List<Proveedor> buscar() throws Exception {
		SystemAdministracionProveedores locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionProveedores();
		FiltroProveedores locFiltro = new FiltroProveedores();
		locFiltro.setRazonSocial(this.getRazonSocial());
		locFiltro.setEstado(this.getEstado());
		locFiltro.setCodigo(this.getCodigo());
		List<Proveedor> locLista = locSystem.findListadoProveedores(locFiltro).getListaResultados(); 
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setRazonSocial(null);
		this.setEstado(null);
		this.setCodigo(null);
		
		this.fireActualizarDatos();
	}
	
	public Proveedor.Estado getEstado() {
		return estado;
	}

	public void setEstado(Proveedor.Estado estado) {
		this.estado = estado;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
