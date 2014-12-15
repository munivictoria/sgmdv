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
@Table(name = "LINEA_RETENCION")
public class LineaRetencion implements Serializable {

	public static final long serialVersionUID = -8410323163712533148L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_linea_retencion")
	@SequenceGenerator(name = "gen_id_linea_retencion", sequenceName = "gen_id_linea_retencion",allocationSize = 1)
	@Column(name="ID_LINEA_RETENCION")
	private long idLineaRetencion = -1;
	
	@ManyToOne
	@JoinColumn(name = "ID_PARAMETRO_RETENCION")
	private ParametroRetencion parametroRetencion;
	private Double importe;
	
	@ManyToOne
	@JoinColumn(name = "ID_COMPROBANTE_RETENCION")
	private ComprobanteRetencion comprobanteRetencion;

	public long getIdLineaRetencion() {
		return idLineaRetencion;
	}

	public void setIdLineaRetencion(long idLineaRetencion) {
		this.idLineaRetencion = idLineaRetencion;
	}
	
	public ParametroRetencion getParametroRetencion() {
		return parametroRetencion;
	}
	
	public void setParametroRetencion(ParametroRetencion paramentroRetencion) {
		this.parametroRetencion = paramentroRetencion;
	}
	
	public Double getImporte() {
		return importe;
	}
	
	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public ComprobanteRetencion getComprobanteRetencion() {
		return this.comprobanteRetencion;
	}

	public void setComprobanteRetencion(ComprobanteRetencion comprobanteRetencion) {
		this.comprobanteRetencion = comprobanteRetencion;
	}
	  
	@Override
	public String toString() {
		return "LineaRetencion [idLineaRetencion=" + idLineaRetencion
				+ ", importe=" + importe + ", parametroRetencion="
				+ parametroRetencion + ", comprobanteRetencion=" + comprobanteRetencion + "]";
	}

	@Override
	public int hashCode() {
		if (idLineaRetencion == -1) {
			return super.hashCode();			
		}
		final int prime = 31;
		int result = 1;
		result = prime * result + ((importe == null) ? 0 : importe.hashCode());
		result = prime
				* result
				+ ((this.parametroRetencion == null) ? 0 : parametroRetencion
						.hashCode());
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
		LineaRetencion other = (LineaRetencion) obj;
		if (importe == null) {
			if (other.importe != null) {
				return false;
			}
		} else if (!importe.equals(other.importe)) {
			return false;
		}
		if (parametroRetencion == null) {
			if (other.parametroRetencion != null) {
				return false;
			}
		} else if (!parametroRetencion.equals(other.parametroRetencion)) {
			return false;
		}
		return true;
	}
}
