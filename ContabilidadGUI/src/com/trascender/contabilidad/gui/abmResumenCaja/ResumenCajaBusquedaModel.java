package com.trascender.contabilidad.gui.abmResumenCaja;

import java.util.Date;
import java.util.List;

import com.trascender.contabilidad.gui.main.ContabilidadGUI;
import com.trascender.contabilidad.recurso.persistent.Caja;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionIngresos;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;

public class ResumenCajaBusquedaModel extends TAbstractBusquedaModel<TicketCaja>{

	private Date fechaDesde;
	private Date fechaHasta;
	private Integer numero;
	private Usuario usuario;
	private Caja caja;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TicketCaja> buscar() throws Exception {
		SystemAdministracionIngresos locSystem = ContabilidadGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos();
		//TODO:No se ha podido cancelar el ticket. Por favor, intente nuevamente.
		//TODO at com.trascender.contabilidad.system.ejb.SystemAdministracionIngresosBean.cancelarTicketCaja(SystemAdministracionIngresosBean.java:678)
		//at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		List<TicketCaja> locLista = locSystem.findListaTicketCaja(this.getFechaDesde(), this.getFechaHasta(), this.getNumero(), this.getUsuario(), this.getCaja());
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setCaja(null);
		this.setFechaDesde(null);
		this.setFechaHasta(null);
		this.setNumero(null);
		this.setUsuario(null);
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
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

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = null;
	}

}
