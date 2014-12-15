package com.trascender.compras.recurso.persistent.inventario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.compras.recurso.persistent.suministros.LineaOrdenCompra;
import com.trascender.compras.recurso.persistent.suministros.LineaSolicitudSuministro;
import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;

@Entity
@Table(name = "LINEA_MOVIMIENTO_MERCADERIA")
public class LineaMovimientoMercaderia implements Serializable, AuditoriaIndirecta{
	
	private static final long serialVersionUID = -8835271451577755913L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_linea_movimiento_mercaderia")
	@SequenceGenerator(name = "gen_id_linea_movimiento_mercaderia", sequenceName = "gen_id_linea_movimiento_mercaderia",allocationSize = 1)
	@Column(name="ID_LINEA_MOVIMIENTO_MERCADERIA")
	private long idLineaMovimientoMercaderia = -1;
	
	@ManyToOne
	@JoinColumn(name = "ID_MOVIMIENTO_DE_MERCADERIA")
	private MovimientoDeMercaderia movimientoDeMercaderia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_STOCK")
	private Stock stock;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_STOCK_DESTINO")
	private Stock stockDestino;
	
	private Double cantidad = new Double(0);
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_BIEN")
	private Bien bien;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_LINEA_SOL_SUMINISTRO")
	private LineaSolicitudSuministro lineaSolicitudSuministro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_LINEA_ORDEN_COMPRA")
	private LineaOrdenCompra lineaOrdenCompra;
	
	public Bien getBien() {
		return bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}

	public LineaOrdenCompra getLineaOrdenCompra() {
		return lineaOrdenCompra;
	}

	public void setLineaOrdenCompra(LineaOrdenCompra lineaOrdenCompra) {
		this.lineaOrdenCompra = lineaOrdenCompra;
	}

	public MovimientoDeMercaderia getMovimientoDeMercaderia() {
		return movimientoDeMercaderia;
	}

	public void setMovimientoDeMercaderia(
			MovimientoDeMercaderia movimientoDeMercaderia) {
		this.movimientoDeMercaderia = movimientoDeMercaderia;
	}
	
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Stock getStockDestino() {
		return stockDestino;
	}

	public void setStockDestino(Stock stockDestino) {
		this.stockDestino = stockDestino;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	
	public long getIdLineaMovimientoMercaderia() {
		return idLineaMovimientoMercaderia;
	}

	public void setIdLineaMovimientoMercaderia(long idLineaMovimientoMercaderia) {
		this.idLineaMovimientoMercaderia = idLineaMovimientoMercaderia;
	}
	
	public LineaSolicitudSuministro getLineaSolicitudSuministro() {
		return lineaSolicitudSuministro;
	}

	public void setLineaSolicitudSuministro(
			LineaSolicitudSuministro lineaSolicitudSuministro) {
		this.lineaSolicitudSuministro = lineaSolicitudSuministro;
		lineaSolicitudSuministro.getListaLineasMovimientosMercaderia().add(this);
	}

	public String toString(){
		return "Cantidad: "+ this.cantidad+ " ["+ com.trascender.framework.util.Util.getString(this.movimientoDeMercaderia.getFecha().getTime())+ "]";
	}
	
	public Double getCantidadEntregasPrevias(){
		Double locCantidadPrevia = new Double(0);
		if (this.lineaSolicitudSuministro != null){
			locCantidadPrevia = lineaSolicitudSuministro.getCantidadEntregasPrevias();
		} else if (this.lineaOrdenCompra != null && 
				this.lineaOrdenCompra.getLineaMovimientoMercaderia() != null) {
			locCantidadPrevia = lineaOrdenCompra.getCantidad();
		}
		return locCantidadPrevia;
	}
	
	public String getOrdenOSolicitud(){
		return this.lineaSolicitudSuministro != null ? 
				this.lineaSolicitudSuministro.getSolicitudSuministro().toString() :
				this.lineaOrdenCompra.getOrdenCompra().toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (idLineaMovimientoMercaderia ^ (idLineaMovimientoMercaderia >>> 32));
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
		LineaMovimientoMercaderia other = (LineaMovimientoMercaderia) obj;
		if (idLineaMovimientoMercaderia != other.idLineaMovimientoMercaderia)
			return false;
		return true;
	}

	public EntidadTrascender getEntidadTrascender() {
		return this.movimientoDeMercaderia;
	}

	public String getNombrePropiedad() {
		return "Movimiento [" + this.bien.getNombre() + "]";
	}

	public boolean concatenaNombre() {
		return true;
	}
}
