package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "TICKET_CANCELADO")
public class TicketCancelado  implements Serializable{

	private static final long serialVersionUID = 9201440181028369873L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_ticket_cancelado")
	@SequenceGenerator(name = "gen_id_ticket_cancelado", sequenceName = "gen_id_ticket_cancelado",allocationSize = 1)
	@Column(name="ID_TICKET_CANCELADO")
	private long idTicketCancelado; 
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_TICKET_CAJA")
	private TicketCaja ticketCaja;
	
	@Column(name = "ID_DEUDA")
	private long idDeuda;

	public long getIdTicketCancelado() {
		return idTicketCancelado;
	}
	public void setIdTicketCancelado(long idTicketCancelado) {
		this.idTicketCancelado = idTicketCancelado;
	}
	public TicketCaja getTicketCaja() {
		return ticketCaja;
	}
	public void setTicketCaja(TicketCaja ticketCaja) {
		this.ticketCaja = ticketCaja;
	}
	public long getIdDeuda() {
		return idDeuda;
	}
	public void setIdDeuda(long idDeuda) {
		this.idDeuda = idDeuda;
	}
}
