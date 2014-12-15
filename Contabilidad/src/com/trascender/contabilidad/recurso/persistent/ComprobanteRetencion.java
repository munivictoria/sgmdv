package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.framework.recurso.transients.Periodo;

@Entity
@Table(name = "COMPROBANTE_RETENCION")
public class ComprobanteRetencion implements Serializable {
	
	public static final long serialVersionUID = -9176119149009189317L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_comprobante_retencion")
	@SequenceGenerator(name = "gen_id_comprobante_retencion", sequenceName = "gen_id_comprobante_retencion",allocationSize = 1)
	@Column(name="ID_COMPROBANTE_RETENCION")
	private long idComprobanteRetencion = -1;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOCUMENTO_EGRESO")
	private OrdenPago ordenPago; //Orden de Pago sobre la cual se aplica la retención
	
	@OneToMany(mappedBy = "comprobanteRetencion")
	private Set<OrdenPago> listaOrdenesPago = new HashSet<OrdenPago>(); //Ordenes de Pago sobre las cuales se genera la retención
	
	@OneToMany(mappedBy = "comprobanteRetencion", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LineaRetencion> lineaRetencion = new HashSet<LineaRetencion>();
	
	@ManyToOne
	@JoinColumn(name = "ID_PERIODO")
	private Periodo periodo;
	private Double importe;

	public long getIdComprobanteRetencion() {
		return idComprobanteRetencion;
	}
	
	public void setIdComprobanteRetencion(long idComprobanteRetencion) {
		this.idComprobanteRetencion = idComprobanteRetencion;
	}
	
	public OrdenPago getOrdenPago() {
		return ordenPago;
	}
	
	public void setOrdenPago(OrdenPago ordenPago) {
		this.ordenPago = ordenPago;
	}
	
	public Set<OrdenPago> getListaOrdenesPago() {
		return listaOrdenesPago;
	}
	
	public void setListaOrdenesPago(Set<OrdenPago> listaOrdenesPago) {
		this.listaOrdenesPago = listaOrdenesPago;
	}
	
	public Set<LineaRetencion> getLineaRetencion() {
		return lineaRetencion;
	}

	public void setLineaRetencion(Set<LineaRetencion> lineaRetencion) {
		this.lineaRetencion = lineaRetencion;
	}

	public Double getImporte() {
		return importe;
	}
	
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	@Override
	public int hashCode() {
		if (this.idComprobanteRetencion == -1){
			return super.hashCode();
		}
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idComprobanteRetencion ^ (idComprobanteRetencion >>> 32));
		result = prime * result + ((importe == null) ? 0 : importe.hashCode());
		result = prime * result
				+ ((lineaRetencion == null) ? 0 : lineaRetencion.hashCode());
		result = prime
				* result
				+ ((listaOrdenesPago == null) ? 0 : listaOrdenesPago.hashCode());
		result = prime * result + ((periodo == null) ? 0 : periodo.hashCode());
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
		ComprobanteRetencion other = (ComprobanteRetencion) obj;
		if (idComprobanteRetencion != other.idComprobanteRetencion) {
			return false;
		}
		if (importe == null) {
			if (other.importe != null) {
				return false;
			}
		} else if (!importe.equals(other.importe)) {
			return false;
		}
		if (lineaRetencion == null) {
			if (other.lineaRetencion != null) {
				return false;
			}
		} else if (!lineaRetencion.equals(other.lineaRetencion)) {
			return false;
		}
		if (listaOrdenesPago == null) {
			if (other.listaOrdenesPago != null) {
				return false;
			}
		} else if (!listaOrdenesPago.equals(other.listaOrdenesPago)) {
			return false;
		}
		if (periodo == null) {
			if (other.periodo != null) {
				return false;
			}
		} else if (!periodo.equals(other.periodo)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Comprobante de Retención [importe=" + importe + ", Orden de pago=" + ordenPago
				+ ", período=" + periodo + "]";
	}
}