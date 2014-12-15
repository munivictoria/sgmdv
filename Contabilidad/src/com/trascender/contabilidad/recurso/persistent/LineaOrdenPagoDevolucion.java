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
@Table(name = "LINEA_O_P_DEVOLUCION")
public class LineaOrdenPagoDevolucion implements Serializable{

	public static final long serialVersionUID = 970703321760949418L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_linea_o_p_devolucion")
	@SequenceGenerator(name = "gen_id_linea_o_p_devolucion", sequenceName = "gen_id_linea_o_p_devolucion",allocationSize = 1)
	@Column(name="ID_LINEA_O_P_DEVOLUCION")
	private long idLineaOrdenPagoDevolucion = -1;
	
	@ManyToOne
	@JoinColumn(name = "ID_TICKET_CAJA")
	private TicketCaja ticketCaja;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOCUMENTO_EGRESO")
	private OrdenPagoDevolucion ordenPago;
	
	private Double importe;
	
	public TicketCaja getTicketCaja() {
		return ticketCaja;
	}
	public void setTicketCaja(TicketCaja ticketCaja) {
		this.ticketCaja = ticketCaja;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	@Override
	public int hashCode() {
		if (this.idLineaOrdenPagoDevolucion == -1){
			return super.hashCode();
		}
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idLineaOrdenPagoDevolucion ^ (idLineaOrdenPagoDevolucion >>> 32));
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
		LineaOrdenPagoDevolucion other = (LineaOrdenPagoDevolucion) obj;
		if (idLineaOrdenPagoDevolucion != other.idLineaOrdenPagoDevolucion) {
			return false;
		}
		return true;
	}
	public OrdenPagoDevolucion getOrdenPago() {
		return ordenPago;
	}
	public void setOrdenPago(OrdenPagoDevolucion ordenPago) {
		this.ordenPago = ordenPago;
	}
	public long getIdLineaOrdenPagoDevolucion() {
		return idLineaOrdenPagoDevolucion;
	}
	public void setIdLineaOrdenPagoDevolucion(long idLineaOrdenPagoDevolucion) {
		this.idLineaOrdenPagoDevolucion = idLineaOrdenPagoDevolucion;
	}
	
	@Override
	public String toString(){
		return this.ticketCaja.toString();
	}

}
