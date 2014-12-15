package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "MOVIMIENTO_CAJA_INGRESO")
public class MovimientoCajaIngreso extends MovimientoCaja implements Serializable {

	public static final long serialVersionUID = -7760328306702131014L;
	
	@Transient
	private long idMovimientoCajaIngreso=-1;
	//Relacion con otros objetos
	
	@ManyToOne
	@JoinColumn(name = "ID_REGISTRO_CANCELACION")
	private DetalleTicketCaja detalleTicket;
	
	@ManyToOne
	@JoinColumn(name = "ID_LINEA_PRESUPUESTO")
	private LineaPresupuestoRecursos lineaPresupuestoRecursos;
	
	public long getIdMovimientoCajaIngreso() {
		return idMovimientoCajaIngreso;
	}

	public void setIdMovimientoCajaIngreso(long idMovimientoCajaIngreso) {
		this.idMovimientoCajaIngreso = idMovimientoCajaIngreso;
	}

	public DetalleTicketCaja getDetalleTicket() {
		return detalleTicket;
	}

	public void setDetalleTicket(DetalleTicketCaja detalleTicket) {
		this.detalleTicket = detalleTicket;
	}

	public LineaPresupuestoRecursos getLineaPresupuestoRecursos() {
		return lineaPresupuestoRecursos;
	}

	public void setLineaPresupuestoRecursos(
			LineaPresupuestoRecursos lineaPresupuestoRecursos) {
		this.lineaPresupuestoRecursos = lineaPresupuestoRecursos;
	}
	
	
}
