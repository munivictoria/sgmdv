package com.trascender.contabilidad.gui.abmOrdenPagoDevolucion;

import java.util.Date;
import java.util.Set;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.LineaOrdenPagoDevolucion;
import com.trascender.contabilidad.recurso.persistent.MovimientoBancario;
import com.trascender.contabilidad.recurso.persistent.OrdenPagoDevolucion;
import com.trascender.contabilidad.recurso.persistent.DocumentoEgreso.Estado;
import com.trascender.framework.recurso.persistent.Persona;
import com.trascender.gui.framework.model.TAbstractABMModel;

public class OrdenPagoDevolucionABMModel extends TAbstractABMModel<OrdenPagoDevolucion> {

	@Override
	public void agregar() throws Exception {
		for(LineaOrdenPagoDevolucion lineaOrdenPagoDevolucion : this.getObjetoABM().getLineaOrdenPagoDev()){
			lineaOrdenPagoDevolucion.setOrdenPago(this.getObjetoABM());
		}
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().addOrdenPago(this.getObjetoABM());
	}

	@Override
	public void eliminar() throws Exception {
		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().deleteOrdenPago(this.getObjetoABM());
	}

	@Override
	public void modificar() throws Exception {
		for (LineaOrdenPagoDevolucion linea: this.getObjetoABM().getLineaOrdenPagoDev()) {
			linea.setOrdenPago(this.getObjetoABM());
		}			

		ContabilidadGUI.getInstance().getAdminSystemsContabilidad().getSystemAdministracionEgresos().updateOrdenPago(this.getObjetoABM());
	}
	
	public void confirmar() throws Exception {
		for (LineaOrdenPagoDevolucion linea: this.getObjetoABM().getLineaOrdenPagoDev()) {
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

	
	public Persona getPersona() {
		return this.objetoABM.getPersona();
	}

	public void setPersona(Persona persona) {
		this.objetoABM.setPersona(persona);
	}

	public Set<LineaOrdenPagoDevolucion> getListaLineasOrdenPagoDev() {
		return this.objetoABM.getLineaOrdenPagoDev();
	}

	public void setListaLineasOrdenPagoDev(Set<LineaOrdenPagoDevolucion> listaLineasOrdenPagoDev) {
		this.objetoABM.setLineaOrdenPagoDev(listaLineasOrdenPagoDev);
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

}
