package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Son los pagos hechos por el contribuyente, que componen al Ticket de Caja
 * @author ferna
 *
 */
@Entity
@Table(name = "PAGO_TICKET")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_PAGO", discriminatorType = DiscriminatorType.STRING)
public abstract class PagoTicket implements Serializable, Cloneable{
	
	public static final long serialVersionUID = 8193659141406551658L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_pago_ticket")
	@SequenceGenerator(name = "gen_id_pago_ticket", sequenceName = "gen_id_pago_ticket",allocationSize = 1)
	@Column(name="ID_PAGO_TICKET")
	private long idPagoTicket;
	
	@ManyToOne
	@JoinColumn(name = "ID_TICKET_CAJA")
	private TicketCaja ticketCaja;

	private Double monto;
	private String comentario;
	
	public abstract String getDescripcion();
	
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public long getIdPagoTicket() {
		return idPagoTicket;
	}
	public void setIdPagoTicket(long idPagoTicket) {
		this.idPagoTicket = idPagoTicket;
	}
	public TicketCaja getTicketCaja() {
		return ticketCaja;
	}
	public void setTicketCaja(TicketCaja ticketCaja) {
		this.ticketCaja = ticketCaja;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idPagoTicket ^ (idPagoTicket >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PagoTicket other = (PagoTicket) obj;
		if (idPagoTicket != other.idPagoTicket) {
			return false;
		}
		return true;
	}
	
	public PagoTicket getClon(TicketCaja cadaTicket) throws Exception{
		PagoTicket locPago = (PagoTicket) this.clone();
		locPago.setMonto(cadaTicket.getImporteTotal());
		locPago.setTicketCaja(cadaTicket);
		return locPago;
	}
	
}
