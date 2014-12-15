package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "MOVIMIENTO_CAJA_CHICA")
public class MovimientoCajaChica implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = 7749071671545396160L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_movimiento_caja_chica")
	@SequenceGenerator(name = "gen_id_movimiento_caja_chica", sequenceName = "gen_id_movimiento_caja_chica",allocationSize = 1)
	@Column(name="ID_MOVIMIENTO_CAJA_CHICA")
	private long idMovimientoCajaChica=-1;
	
	@Column(name = "FECHA_HORA")
	private Date fechaHora;
	private double importe;
	//Relacion con otros objetos
	
	@ManyToOne
	@JoinColumn(name = "ID_CAJA_CHICA")
	private CajaChica cajaChica;
	
	@ManyToOne
	@JoinColumn(name = "ID_CONCEPTO_MOV_CAJA_CHICA")
	private ConceptoMovimientoCajaChica conceptoMovimiento;

	public CajaChica getCajaChica() {
		return cajaChica;
	}

	public void setCajaChica(CajaChica cajaChica) {
		this.cajaChica = cajaChica;
	}
	
	public long getIdMovimientoCajaChica() {
		return idMovimientoCajaChica;
	}

	public void setIdMovimientoCajaChica(long idMovimientoCajaChica) {
		this.idMovimientoCajaChica = idMovimientoCajaChica;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public ConceptoMovimientoCajaChica getConceptoMovimiento() {
		return conceptoMovimiento;
	}

	public void setConceptoMovimiento(ConceptoMovimientoCajaChica conceptoMovimiento) {
		this.conceptoMovimiento = conceptoMovimiento;
	}
	
	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idMovimientoCajaChica== -1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idMovimientoCajaChica ^ (idMovimientoCajaChica >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
		final MovimientoCajaChica other = (MovimientoCajaChica) obj;
		if (idMovimientoCajaChica != other.idMovimientoCajaChica) {
			return false;
		}
		return true;
	}
}
