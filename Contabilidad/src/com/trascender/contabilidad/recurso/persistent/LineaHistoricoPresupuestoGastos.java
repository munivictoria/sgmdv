/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "LINEA_HIS_PRES_GASTOS")
@PrimaryKeyJoinColumn(name = "ID_LIN_HIS_PRES")
public class LineaHistoricoPresupuestoGastos extends LineaHistoricoPresupuesto{

	private static final long serialVersionUID = -6908415064981571149L;
	
	@Column(name = "MONTO_PRESUPUESTADO")
	private Double montoPresupuestado = 0d;
	
	@Column(name = "MONTO_COMPROMETIDO")
	private Double montoComprometido = 0d;
	
	@Column(name = "MONTO_PAGADO")
	private Double montoPagado= 0d;
	
	public Double getMontoPresupuestado() {
		return montoPresupuestado;
	}
	public void setMontoPresupuestado(Double montoPresupuestado) {
		this.montoPresupuestado = montoPresupuestado;
	}
	
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
}
