package com.trascender.compras.recurso.persistent.suministros;

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

import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;


@Entity
@Table(name = "PAGO_ORDEN_COMPRA")
public class PagoOrdenCompra implements Serializable, AuditoriaIndirecta{
	
	private static final long serialVersionUID = 2422654460986861604L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_pago_orden_compra")
	@SequenceGenerator(name = "gen_id_pago_orden_compra", sequenceName = "gen_id_pago_orden_compra",allocationSize = 1)
	@Column(name="ID_PAGO_ORDEN_COMPRA")
	private long idPagoOrdenCompra = -1;
	
	@ManyToOne
	@JoinColumn(name = "ID_ORDEN_COMPRA")
	private OrdenCompra ordenCompra;
	
	private Double monto;
	
	@Column(name = "MONTO_AJUSTE")
	private Double montoAjuste;
	
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "ID_FACTURA")
	private Factura factura;
	
	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Double getMontoAjuste() {
		return montoAjuste;
	}

	public void setMontoAjuste(Double montoAjuste) {
		this.montoAjuste = montoAjuste;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getIdPagoOrdenCompra() {
		return idPagoOrdenCompra;
	}

	public void setIdPagoOrdenCompra(long idPagoOrdenCompra) {
		this.idPagoOrdenCompra = idPagoOrdenCompra;
	}

	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idPagoOrdenCompra ^ (idPagoOrdenCompra >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PagoOrdenCompra other = (PagoOrdenCompra) obj;
		if (idPagoOrdenCompra != other.idPagoOrdenCompra)
			return false;
		return true;
	}

	public EntidadTrascender getEntidadTrascender() {
		return ordenCompra;
	}

	public String getNombrePropiedad() {
		return "Pago ["+getNombre()+"]";
	}

	public boolean concatenaNombre() {
		return true;
	}

	@Override
	public String toString() {
		return "PagoOrdenCompra [monto=" + monto + ", nombre=" + nombre + "]";
	}

}
