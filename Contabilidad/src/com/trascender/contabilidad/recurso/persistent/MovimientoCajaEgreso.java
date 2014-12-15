package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "MOVIMIENTO_CAJA_EGRESO")
public class MovimientoCajaEgreso extends MovimientoCaja implements Serializable{

	public static final long serialVersionUID = 1403857363852809533L;
	
	@Transient
	private long idMovimientoCajaEgreso=-1;
	//Relacion con otros objetos
	
	@ManyToOne
	@JoinColumn(name = "ID_PLANILLA_DIARIA_CAJA")
	private PlanillaDiariaCaja planilla;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOCUMENTO_EGRESO")
	private DocumentoEgreso documentoEgreso;

	public long getIdMovimientoCajaEgreso() {
		return idMovimientoCajaEgreso;
	}

	public void setIdMovimientoCajaEgreso(long idMovimientoCajaEgreso) {
		this.idMovimientoCajaEgreso = idMovimientoCajaEgreso;
	}

	public PlanillaDiariaCaja getPlanilla() {
		return planilla;
	}

	public void setPlanilla(PlanillaDiariaCaja planilla) {
		this.planilla = planilla;
	}

	public DocumentoEgreso getDocumentoEgreso() {
		return documentoEgreso;
	}

	public void setDocumentoEgreso(DocumentoEgreso documentoEgreso) {
		this.documentoEgreso = documentoEgreso;
	}
	
	@Override
	public int hashCode() {
		if (this.idMovimientoCajaEgreso==-1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idMovimientoCajaEgreso ^ (idMovimientoCajaEgreso >>> 32));
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
		MovimientoCajaEgreso other = (MovimientoCajaEgreso) obj;
		if (idMovimientoCajaEgreso != other.idMovimientoCajaEgreso) {
			return false;
		}
		return true;
	}
	
}
