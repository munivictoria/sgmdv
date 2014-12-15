/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "LINEA_PRESUPUESTO_RECURSOS")
@PrimaryKeyJoinColumn(name = "ID_LINEA_PRESUPUESTO")
public class LineaPresupuestoRecursos extends LineaPresupuesto{

	public  static final long serialVersionUID = 4874208688593728281L;
	
	@Column(name = "MONTO_ESTIMADO")
	private Double montoEstimado;
	
	@Column(name = "MONTO_RECAUDADO")
	private Double montoRecaudado;
	//Relacion con otros objetos
	
	@OneToMany(mappedBy = "lineaPresupuestoRecursos", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MovimientoCajaIngreso> movimientos;

	public Double getMontoEstimado() {
		return montoEstimado;
	}

	public void setMontoEstimado(Double montoEstimado) {
		this.montoEstimado = montoEstimado;
	}

	public Double getMontoRecaudado() {
		return montoRecaudado;
	}

	public void setMontoRecaudado(Double montoRecaudado) {
		this.montoRecaudado = montoRecaudado;
	}

	public Set<MovimientoCajaIngreso> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(Set<MovimientoCajaIngreso> movimientos) {
		this.movimientos = movimientos;
	}
	
}
