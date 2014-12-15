package com.trascender.contabilidad.gui.abmFactura;

import java.util.Date;
import java.util.List;

import com.trascender.compras.recurso.persistent.suministros.FacturaContrato;
import com.trascender.compras.recurso.persistent.suministros.LineaFactura;
import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.compras.recurso.persistent.suministros.Factura.Estado;
import com.trascender.compras.recurso.persistent.suministros.Factura.TipoFactura;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.framework.recurso.persistent.Contrato;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class FacturaContratoABMModel extends TAbstractABMModel<FacturaContrato> {

//	private Contrato contrato;
//	private Estado estado;
//	private Date FechaEmision;
//	private String numero;
//	private Proveedor proveedor;
//	private TipoFactura tipoFactura;
//	private Double total;
//	private List<LineaFactura> listaLineaFactura;
	
	@Override
	public void agregar() throws Exception {
		
	}

	@Override
	public void eliminar() throws Exception {
		
	}

	@Override
	public void modificar() throws Exception {
		System.out.println("---> " + this.getObjetoABM().getContrato());
		System.out.println("---> " + this.getObjetoABM().getNumero());
		System.out.println("---> " + this.getObjetoABM().getEstado());
		System.out.println("---> " + this.getObjetoABM().getCodigoProveedor());
		System.out.println("---> " + this.getObjetoABM().getProveedor());
		System.out.println("---> " + this.getObjetoABM().getFechaEmision());
		System.out.println("---> " + this.getObjetoABM().getTipoFactura());
		System.out.println("---> " + this.getObjetoABM().getTotal());
		for (LineaFactura l : this.objetoABM.getListaLineaFactura()) {
			System.out.println("getCuenta--->  " + l.getCuenta());
		}
		
		this.objetoABM = ContabilidadGUI.getInstance().getAdminSystemsCompras().getSystemAdministracionFacturaContrato().updateFacturaContrato(this.getObjetoABM());
	}

	public Contrato getContrato() {
		return this.objetoABM.getContrato();
	}

	public void setContrato(Contrato contrato) {
		this.objetoABM.setContrato(contrato);
	}

	public Estado getEstado() {
		return this.objetoABM.getEstado();
	}

	public void setEstado(Estado estado) {
		this.objetoABM.setEstado(estado);
	}

	public Date getFechaEmision() {
		return this.objetoABM.getFechaEmision();
	}

	public void setFechaEmision(Date fechaEmision) {
		this.objetoABM.setFechaEmision(fechaEmision);
	}

	public String getNumero() {
		return this.objetoABM.getNumero();
	}

	public void setNumero(String numero) {
		this.objetoABM.setNumero(numero);
	}

	public Proveedor getProveedor() {
		return this.objetoABM.getProveedor();
	}

	public void setProveedor(Proveedor proveedor) {
		this.objetoABM.setProveedor(proveedor);
	}

	public TipoFactura getTipoFactura() {
		return this.objetoABM.getTipoFactura();
	}

	public void setTipoFactura(TipoFactura tipoFactura) {
		this.objetoABM.setTipoFactura(tipoFactura);
	}

	public Double getTotal() {
		return this.objetoABM.getTotal();
	}

	public void setTotal(Double total) {
		this.objetoABM.setTotal(total);
	}

	public List<LineaFactura> getListaLineaFactura() {
		return this.objetoABM.getListaLineaFactura();
	}

	public void setListaLineaFactura(List<LineaFactura> listaLineaFactura) {
		this.objetoABM.setListaLineaFactura(listaLineaFactura);
	}

}
