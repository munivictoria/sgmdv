package com.trascender.contabilidad.gui.abmRetencion;

import java.util.Set;

import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion;
import com.trascender.contabilidad.recurso.persistent.LineaRetencion;
import com.trascender.contabilidad.recurso.persistent.OrdenPago;
import com.trascender.framework.recurso.transients.Periodo;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class RetencionABMModel extends TAbstractABMModel<ComprobanteRetencion> {

	private Proveedor proveedor;
	
	@Override
	public void agregar() throws Exception {
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().addComprobanteRetencion(this.objetoABM, this.getProveedor());
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().deleteComprobanteRetencion(this.objetoABM);
	}

	@Override
	public void modificar() throws Exception {
		
	}

	public Double getMonto() {
		return this.objetoABM.getImporte();
	}

	public void setMonto(Double monto) {
		this.objetoABM.setImporte(monto);
	}

	public OrdenPago getOrdenPago() {
		return this.objetoABM.getOrdenPago();
	}

	public void setOrdenPago(OrdenPago ordenPago) {
		this.objetoABM.setOrdenPago(ordenPago);
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Periodo getPeriodo() {
		return this.objetoABM.getPeriodo();
	}

	public void setPeriodo(Periodo periodo) {
		this.objetoABM.setPeriodo(periodo);
	}

	public Set<LineaRetencion> getListaLineaRetencion() {
		return this.objetoABM.getLineaRetencion();
	}

	public void setListaLineaRetencion(Set<LineaRetencion> listaLineaRetencion) {
		this.objetoABM.setLineaRetencion(listaLineaRetencion);
	}

}
