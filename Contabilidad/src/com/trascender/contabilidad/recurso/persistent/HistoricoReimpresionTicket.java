package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.persistent.Usuario;

@Entity
@Table(name = "HISTORICO_IMPRESION_TICKET")
public class HistoricoReimpresionTicket implements Serializable{

	public static final long serialVersionUID = 3931225176782860947L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_hist_reim_tick")
	@SequenceGenerator(name = "gen_id_hist_reim_tick", sequenceName = "gen_id_hist_reim_tick",allocationSize = 1)
	@Column(name="ID_HIST_REIM_TICK")
	private long idHistoricoReimpresionTicket = -1;
	private Calendar fecha;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "ID_TICKET")
	private TicketCaja ticket;
	
	public long getIdHistoricoReimpresionTicket() {
		return idHistoricoReimpresionTicket;
	}
	public void setIdHistoricoReimpresionTicket(long idHistoricoReimpresionTicket) {
		this.idHistoricoReimpresionTicket = idHistoricoReimpresionTicket;
	}
	
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public TicketCaja getTicket() {
		return ticket;
	}
	public void setTicket(TicketCaja ticket) {
		this.ticket = ticket;
	}
	
	@Override
	public String toString(){
		return this.fecha+" - "+this.usuario+" - Ticket Nº: "+this.ticket.getNumero();
	}
	
}
