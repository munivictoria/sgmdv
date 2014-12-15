package com.trascender.contabilidad.gui.abmOrdenPago;

import java.util.Date;
import java.util.Set;

import com.trascender.compras.recurso.persistent.suministros.Proveedor;
import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.ComprobanteRetencion;
import com.trascender.contabilidad.recurso.persistent.LineaOrdenPago;
import com.trascender.contabilidad.recurso.persistent.MovimientoBancario;
import com.trascender.contabilidad.recurso.persistent.OrdenPago;
import com.trascender.contabilidad.recurso.persistent.DocumentoEgreso.Estado;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class OrdenPagoABMModel extends TAbstractABMModel<OrdenPago> {
	
	//private Set<LineaOrdenPago> listaLineasOrdenPago;
	
	@Override
	public void agregar() throws Exception {
		/*if (this.getListaLineasOrdenPago() != null) {
			if (this.objetoABM.getLineasOrdenPago() == null){
				this.objetoABM.setLineasOrdenPago(new HashSet<LineaOrdenPago>()); 
			}
			for (LineaOrdenPago linea: this.objetoABM.getLineasOrdenPago()) {
				linea.setOrdenPago(this.objetoABM);
			}
			this.objetoABM.getLineasOrdenPago().addAll(this.listaLineasOrdenPago);

			System.out.println(this.getObjetoABM().getIdDocumentoEgreso());
			for (LineaOrdenPago linea: this.getObjetoABM().getLineasOrdenPago()) {
				System.out.println(linea.getIdLineaOrdenPago());
				System.out.println(linea.getImporte());
			}
		}*/
		for(LineaOrdenPago lineaOrdenPago : this.getObjetoABM().getLineasOrdenPago()){
			lineaOrdenPago.setOrdenPago(this.getObjetoABM());
		}
		
//		for(MovimientoBancario locMovimientoBancario : this.getObjetoABM().getMovimientosBancarios()){
//			locMovimientoBancario.setOrdenesPago()
//		}
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().addOrdenPago(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {
//		for (LineaOrdenPago linea: this.getObjetoABM().getLineasOrdenPago()) {
//			linea.setOrdenPago(objetoABM);
//			
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().deleteOrdenPago(this.getObjetoABM());
		
	}

	@Override
	public void modificar() throws Exception {
		/*if (this.getListaLineasOrdenPago() != null) {
			this.objetoABM.getLineasOrdenPago().clear();
			this.objetoABM.getLineasOrdenPago().addAll(this.listaLineasOrdenPago);
		}
		
		System.out.println(this.getObjetoABM().getIdDocumentoEgreso());
		for (LineaOrdenPago linea: this.getObjetoABM().getLineasOrdenPago()) {
			System.out.println(linea.getIdLineaOrdenPago());
			System.out*/
		
		
		for (LineaOrdenPago linea: this.getObjetoABM().getLineasOrdenPago()) {
			linea.setOrdenPago(this.getObjetoABM());
		}			

		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().updateOrdenPago(this.getObjetoABM());
	}
	
	public void confirmar() throws Exception {
		for (LineaOrdenPago linea: this.getObjetoABM().getLineasOrdenPago()) {
			linea.setOrdenPago(this.getObjetoABM());
		}			

		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().confirmarOrdenPago(this.getObjetoABM());
	}

	public Set<MovimientoBancario> getMovimientoBancario() {
		return this.objetoABM.getMovimientosBancarios();
	}

	public void setMovimientoBancario(Set<MovimientoBancario> movimientoBancario) {
		this.objetoABM.setMovimientosBancarios(movimientoBancario);
	}

	public Date getFechaEmision() {
		return this.objetoABM.getFechaEmision();
	}

	public void setFechaEmision(Date fechaEmision) {
		this.objetoABM.setFechaEmision(fechaEmision);
	}

	public Date getFechaPago() {
		return this.objetoABM.getFechaPago();
	}

	public void setFechaPago(Date fechaPago) {
		this.objetoABM.setFechaPago(fechaPago);
	}

	
	public Proveedor getProveedor() {
		return this.objetoABM.getProveedor();
	}

	public void setProveedor(Proveedor proveedor) {
		this.objetoABM.setProveedor(proveedor);
	}

	public Set<LineaOrdenPago> getListaLineasOrdenPago() {
		return this.objetoABM.getLineasOrdenPago();
	}

	public void setListaLineasOrdenPago(Set<LineaOrdenPago> listaLineasOrdenPago) {
		this.objetoABM.setLineasOrdenPago(listaLineasOrdenPago);
	}

	public ComprobanteRetencion getComprobanteRetencion() {
		return this.objetoABM.getComprobanteRetencion();
	}

	public void setComprobanteRetencion(ComprobanteRetencion comprobanteRetencion) {
		this.objetoABM.setComprobanteRetencion(comprobanteRetencion);
	}

	public Estado getEstado() {
		return this.objetoABM.getEstado();
	}

	public void setEstado(Estado estado) {
		this.objetoABM.setEstado(estado);
	}

	public Double getImporte() {
		return this.objetoABM.getImporte();
	}

	public void setImporte(Double importe) {
		this.objetoABM.setImporte(importe);
	}

	public Integer getNumero() {
		return this.objetoABM.getNumero();
	}

	public void setNumero(Integer numero) {
		this.objetoABM.setNumero(numero);
	}

	public ComprobanteRetencion getRetencion() {
		return this.objetoABM.getComprobanteRetencion();
	}

	public void setRetencion(ComprobanteRetencion retencion) {
		this.objetoABM.setComprobanteRetencion(retencion);
	}

}
