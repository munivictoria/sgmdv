/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "LINEA_PRESUPUESTO_GASTOS")
@PrimaryKeyJoinColumn(name = "ID_LINEA_PRESUPUESTO")
public class LineaPresupuestoGastos extends LineaPresupuesto{

	public  static final long serialVersionUID = 4166309980998826756L;
	
	@Column(name = "MONTO_PRESUPUESTADO")
	private Double montoPresupuestado = 0D;
	
	@Column(name = "MONTO_COMPROMETIDO")
	private Double montoComprometido = 0D;
	
	@Column(name = "MONTO_DEVENGADO")
	private Double montoDevengado = 0D;
	
	@Column(name = "MONTO_PAGADO")
	private Double montoPagado = 0D;
	//Relacion con otros objetos
	
	public Double getMontoComprometido() {
		return montoComprometido;
	}

	public void setMontoComprometido(Double montoComprometido) {
		this.montoComprometido = montoComprometido;
	}

	public Double getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(Double montoPagado) {
		this.montoPagado = montoPagado;
	}

	public Double getMontoPresupuestado() {
		return montoPresupuestado;
	}

	public void setMontoPresupuestado(Double montoPresupuestado) {
		this.montoPresupuestado = montoPresupuestado;
	}
	
	@Override
	public String toString(){
		return super.toString() + " Monto Presupuestado: " + this.montoPresupuestado + " Monto Comprometido: "
		+ this.montoComprometido + " Monto Pagado: " + this.montoPagado;
	}
	public Double getMontoDevengado() {
		return montoDevengado;
	}
	public void setMontoDevengado(Double montoDevengado) {
		this.montoDevengado = montoDevengado;
	}
}
