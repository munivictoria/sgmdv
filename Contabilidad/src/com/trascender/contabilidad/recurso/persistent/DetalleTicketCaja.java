package com.trascender.contabilidad.recurso.persistent;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.trascender.saic.recurso.persistent.RegistroCancelacion;

@Entity
@Table(name = "DETALLE_TICKET_CAJA")
@PrimaryKeyJoinColumn(name = "ID_REGISTRO_CANCELACION")
public class DetalleTicketCaja extends RegistroCancelacion{
	private static final long serialVersionUID = 8051310394650459766L;
	
	@Column(name = "NUMERO_LINEA")
	private Integer numeroLinea;
	private Double importe;
	
	@OneToMany(mappedBy = "detalleTicket", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MovimientoCajaIngreso> movimientosCajaIngreso=new HashSet<MovimientoCajaIngreso>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TICKET_CAJA")
	private TicketCaja ticketCaja;

	/**
	 * Es el ID del registro deuda que cancela este detalle.
	 */
	@Column(name = "ID_REGISTRO_DEUDA")
	private Long idRegistroDeuda;
	
	/**
	 * Bandera que indica que se reatacho la deuda en un momento 
	 * posterior al cobro de la misma.
	 */
	@Column(name = "REGISTRO_DEUDA_REATACHADO")
	private Boolean registroDeudaReatachado = false;

	public Boolean getRegistroDeudaReatachado() {
		return registroDeudaReatachado;
	}

	public void setRegistroDeudaReatachado(Boolean registroDeudaReatachado) {
		this.registroDeudaReatachado = registroDeudaReatachado;
	}

	public Long getIdRegistroDeuda() {
		return idRegistroDeuda;
	}

	public void setIdRegistroDeuda(Long idRegistroDeuda) {
		this.idRegistroDeuda = idRegistroDeuda;
	}

	public boolean addAll(Collection<? extends MovimientoCajaIngreso> c) {
		for (MovimientoCajaIngreso locMovimiento: c){
			locMovimiento.setDetalleTicket(this);
		}
		return movimientosCajaIngreso.addAll(c);
	}
	
	public TicketCaja getTicketCaja() {
		return ticketCaja;
	}

	public void setTicketCaja(TicketCaja ticketCaja) {
		this.ticketCaja = ticketCaja;
	}

	@Override
	public long getIdRegistroCancelacion() {
		return super.getIdRegistroCancelacion();
	}
	
	@Override
	public void setIdRegistroCancelacion(long idRegistroCancelacion) {
		super.setIdRegistroCancelacion(idRegistroCancelacion);
	}
	
	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Integer getNumeroLinea() {
		return numeroLinea;
	}

	public void setNumeroLinea(Integer numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	public Set<MovimientoCajaIngreso> getMovimientosCajaIngreso() {
		return movimientosCajaIngreso;
	}

	public void setMovimientosCajaIngreso(Set<MovimientoCajaIngreso> movimientosCajaIngreso) {
		this.movimientosCajaIngreso = movimientosCajaIngreso;
	}

	@Override
	public String toString(){
		return this.ticketCaja.toString() + " - Linea:" + this.numeroLinea + " - Importe: " +this.importe;
	}
	
}
