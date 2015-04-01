package com.trascender.compras.recurso.persistent.suministros;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.compras.recurso.persistent.inventario.LineaMovimientoMercaderia;
import com.trascender.framework.recurso.persistent.referencia.CuentaRfr;
import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;

@Entity
@Table(name = "LINEA_ORDEN_COMPRA")
public class LineaOrdenCompra implements Serializable, AuditoriaIndirecta {

	/**
	 * 
	 */
	public static final long serialVersionUID = -8332864947180711167L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_linea_orden_compra")
	@SequenceGenerator(name = "gen_id_linea_orden_compra", sequenceName = "gen_id_linea_orden_compra",allocationSize = 1)
	@Column(name="ID_LINEA_ORDEN_COMPRA")
	private long idLineaOrdenCompra=-1;
	
	private Double cantidad;
	
	@Column(name = "MONTO_UNITARIO")
	private Double montoUnitario;
	
	@Column(name = "MONTO_TOTAL")
	private Double montoTotal;
	
	@ManyToOne
	@JoinColumn(name = "ID_ORDEN_COMPRA")
	private OrdenCompra ordenCompra;
	
	@ManyToOne
	@JoinColumn(name = "ID_BIEN")
	private Bien bien;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA")
	private CuentaRfr cuentaRfr;
	
	@ManyToOne
	@JoinColumn(name = "ID_LINEA_CONTRATACION")
	private LineaContratacion lineaContratacion;
	
	@OneToOne(mappedBy = "lineaOrdenCompra")
	private LineaMovimientoMercaderia lineaMovimientoMercaderia;
	
	public LineaMovimientoMercaderia getLineaMovimientoMercaderia() {
		return lineaMovimientoMercaderia;
	}

	public void setLineaMovimientoMercaderia(
			LineaMovimientoMercaderia lineaMovimientoMercaderia) {
		this.lineaMovimientoMercaderia = lineaMovimientoMercaderia;
	}

	@Override
	public String toString(){
		return this.bien+" - "+this.cantidad+" - "+this.montoTotal;
	}

	public LineaContratacion getLineaContratacion() {
		return lineaContratacion;
	}

	public void setLineaContratacion(LineaContratacion lineaContratacion) {
		this.lineaContratacion = lineaContratacion;
	}



	public LineaOrdenCompra(){
	}

	public Double getCantidad() {
		return cantidad;
	}

	public long getIdLineaOrdenCompra() {
		return idLineaOrdenCompra;
	}

	public Double getMontoTotal() {
		if (montoUnitario != null && cantidad != null){
			return montoUnitario * cantidad;
		}
		else return 0d;
	}

	public Double getMontoUnitario() {
		return montoUnitario;
	}

	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public void setIdLineaOrdenCompra(long idLineaOrdenCompra) {
		this.idLineaOrdenCompra = idLineaOrdenCompra;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public void setMontoUnitario(Double montoUnitario) {
		this.montoUnitario = montoUnitario;
	}

	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public Bien getBien() {
		return bien;
	}


	public void setBien(Bien bien) {
		this.bien = bien;
	}


	public CuentaRfr getCuentaRfr() {
		return cuentaRfr;
	}


	public void setCuentaRfr(CuentaRfr cuentaRfr) {
		this.cuentaRfr = cuentaRfr;
	}


	@Override
	public int hashCode() {
		if (this.idLineaOrdenCompra == -1){
			return super.hashCode();
		}
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idLineaOrdenCompra ^ (idLineaOrdenCompra >>> 32));
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
		LineaOrdenCompra other = (LineaOrdenCompra) obj;
		if (idLineaOrdenCompra != other.idLineaOrdenCompra)
			return false;
		return true;
	}

	public EntidadTrascender getEntidadTrascender() {
		return ordenCompra;
	}

	public String getNombrePropiedad() {
		return "Linea ["+bien.toString()+"]";
	}

	public boolean concatenaNombre() {
		return true;
	}

}
