/**
 * 
 */
package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Column;
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

@Entity
@Table(name = "LINEA_HISTORICO_PRESUPUESTO")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class LineaHistoricoPresupuesto implements Serializable {

	
	private static final long serialVersionUID = -2444422862692649470L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_linea_histor_presupuesto")
	@SequenceGenerator(name = "gen_id_linea_histor_presupuesto", sequenceName = "gen_id_linea_histor_presupuesto",allocationSize = 1)
	@Column(name="ID_LIN_HIST_PRES")
	private long idLineaHistoricoPresupuesto = -1;
	
	@Column(name = "NOMBRE")
	private String nombreCuenta;
	
	@ManyToOne
	@JoinColumn(name = "ID_HIST_PRES")
	private HistoricoPresupuesto historicoPresupuesto;
	
	public long getIdLineaHistoricoPresupuesto() {
		return idLineaHistoricoPresupuesto;
	}
	public void setIdLineaHistoricoPresupuesto(long idLineaHistoricoPresupuesto) {
		this.idLineaHistoricoPresupuesto = idLineaHistoricoPresupuesto;
	}
	
	public String getNombreCuenta() {
		return nombreCuenta;
	}
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public HistoricoPresupuesto getHistoricoPresupuesto() {
		return historicoPresupuesto;
	}
	public void setHistoricoPresupuesto(HistoricoPresupuesto historicoPresupuesto) {
		this.historicoPresupuesto = historicoPresupuesto;
	}
	@Override
	public int hashCode() {
		if(this.idLineaHistoricoPresupuesto == -1){
			return super.hashCode();
		}
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idLineaHistoricoPresupuesto ^ (idLineaHistoricoPresupuesto >>> 32));
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
		LineaHistoricoPresupuesto other = (LineaHistoricoPresupuesto) obj;
		if (idLineaHistoricoPresupuesto != other.idLineaHistoricoPresupuesto) {
			return false;
		}
		return true;
	}
	
}
