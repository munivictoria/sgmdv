package com.trascender.contabilidad.gui.abmRetencion;

import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionEgresos;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class RetencionBusquedaModel extends TAbstractBusquedaModel<ComprobanteRetencion> {

	private Proveedor proveedor;
	private Periodo periodo;
	
	@Override
	public List<ComprobanteRetencion> buscar() throws Exception {
		SystemAdministracionEgresos locSystem = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos();
		List<ComprobanteRetencion> locLista = locSystem.findListaRetencion(this.getPeriodo(), this.getProveedor());
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setProveedor(null);
		this.setPeriodo(null);
		
		this.fireActualizarDatos();
	}
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

}
