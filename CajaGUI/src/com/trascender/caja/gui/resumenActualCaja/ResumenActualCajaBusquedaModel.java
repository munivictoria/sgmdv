package com.trascender.caja.gui.resumenActualCaja;

import java.util.Date;
import java.util.List;

import com.trascender.caja.gui.enumerations.EstadoTicket;
import com.trascender.caja.gui.main.CajaGUI;
import com.trascender.contabilidad.recurso.persistent.Caja;
import com.trascender.contabilidad.recurso.persistent.TicketCaja;
import com.trascender.contabilidad.recurso.persistent.TicketCaja.Estado;
import com.trascender.contabilidad.system.interfaces.SystemAdministracionIngresos;
import com.trascender.framework.recurso.persistent.Usuario;
import com.trascender.gui.framework.model.TAbstractBusquedaModel;
import com.trascender.gui.framework.util.Conversor;

public class ResumenActualCajaBusquedaModel extends TAbstractBusquedaModel<TicketCaja>{

	private Caja caja;
	private Usuario usuario;
	private EstadoTicket estadoTicket;
	private Date fechaHoraDesde;
	private Date fechaHoraHasta;
	private List<TicketCaja> listaResultado;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TicketCaja> buscar() throws Exception {
		SystemAdministracionIngresos locSystem = CajaGUI.getInstance().getAdminSystemsCaja().getSystemAdministracionIngresos();
		Estado estado = null;
		List<TicketCaja> locLista = null;
		
		if (this.getEstadoTicket() == EstadoTicket.TODOS){
			locLista =locSystem.findResumenCajaActual(this.getCaja(), this.getUsuario(), estado, fechaHoraDesde, fechaHoraHasta);
		}
		else if (this.getEstadoTicket() == EstadoTicket.COBROS){
			 estado = Estado.ACTIVO;
			 locLista =locSystem.findResumenCajaActual(this.getCaja(), this.getUsuario(), estado, fechaHoraDesde, fechaHoraHasta);
		}
		if (this.getEstadoTicket() == EstadoTicket.ANULACIONES){
			estado = Estado.CANCELADO;
			locLista =locSystem.findResumenCajaActual(this.getCaja(), this.getUsuario(), estado, fechaHoraDesde, fechaHoraHasta);
			estado = Estado.DEVUELTO;
			locLista.addAll(locSystem.findResumenCajaActual(this.getCaja(), this.getUsuario(), estado, fechaHoraDesde, fechaHoraHasta));
		}
		
		this.listaResultado = locLista;
		return locLista;
	}

	@Override
	public void reiniciar() {
		this.setCaja(null);
		this.setUsuario(null);
		this.setEstadoTicket(null);
		this.setFechaHoraDesde(Conversor.getDateConHora(Conversor.getString(new Date()), "00:00"));
		this.setFechaHoraHasta(Conversor.getDateConHora(Conversor.getString(new Date()), "23:59"));
		//this.fireActualizarDatos();		
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<TicketCaja> getListaResultado() {
		return listaResultado;
	}

	public EstadoTicket getEstadoTicket() {
		return estadoTicket;
	}
	
	public Date getFechaHoraDesde() {
		return fechaHoraDesde;
	}

	public void setFechaHoraDesde(Date fechaHoraDesde) {
		this.fechaHoraDesde = fechaHoraDesde;
	}

	public Date getFechaHoraHasta() {
		return fechaHoraHasta;
	}

	public void setFechaHoraHasta(Date fechaHoraHasta) {
		this.fechaHoraHasta = fechaHoraHasta;
	}

	public void setEstadoTicket(EstadoTicket estadoTicket) {
		this.estadoTicket = estadoTicket;
		this.fireActualizarDatos();	
	}

}
