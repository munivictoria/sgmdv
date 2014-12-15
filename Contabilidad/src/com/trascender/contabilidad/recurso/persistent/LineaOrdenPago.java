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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.compras.recurso.persistent.suministros.Factura;

@Entity
@Table(name = "LINEA_ORDEN_PAGO")
public class LineaOrdenPago implements Serializable{

	public static final long serialVersionUID = -663558691504594778L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_linea_orden_pago")
	@SequenceGenerator(name = "gen_id_linea_orden_pago", sequenceName = "gen_id_linea_orden_pago",allocationSize = 1)
	@Column(name="ID_LINEA_ORDEN_PAGO")
	private long idLineaOrdenPago =-1;
	private Double importe;
	//Relacion con otros objetos
	
	@ManyToOne
	@JoinColumn(name = "ID_FACTURA")
	private Factura factura;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOCUMENTO_EGRESO")
	private OrdenPago ordenPago;

	public long getIdLineaOrdenPago() {
		return idLineaOrdenPago;
	}

	public void setIdLineaOrdenPago(long idLineaOrdenPago) {
		this.idLineaOrdenPago = idLineaOrdenPago;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (this.idLineaOrdenPago == -1 ){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idLineaOrdenPago ^ (idLineaOrdenPago >>> 32));
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
		final LineaOrdenPago other = (LineaOrdenPago) obj;
		if (idLineaOrdenPago != other.idLineaOrdenPago) {
			return false;
		}
		return true;
	}
	
	public OrdenPago getOrdenPago() {
		return ordenPago;
	}

	public void setOrdenPago(OrdenPago ordenPago) {
		this.ordenPago = ordenPago;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
}
