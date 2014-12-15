package com.trascender.contabilidad.recurso.persistent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
@Entity
@Table(name = "LINEA_HIST_PRES_RECURSOS")
@PrimaryKeyJoinColumn(name = "ID_LIN_HIST_PRES")
public class LineaHistoricoPresupuestoRecursos extends LineaHistoricoPresupuesto{

	private static final long serialVersionUID = -6893896402622434336L;
	
	@Column(name = "MONTO_ESTIMADO")
	private Double montoEstimado= 0d;
	
	@Column(name = "MONTO_RECAUDADO")
	private Double montoRecaudado= 0d;
	
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

}
