package com.trascender.compras.recurso.persistent.inventario;

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

import com.trascender.compras.recurso.persistent.suministros.Bien;
import com.trascender.framework.util.AuditoriaIndirecta;
import com.trascender.framework.util.EntidadTrascender;

@Entity
@Table(name = "STOCK")
public class Stock implements Serializable, AuditoriaIndirecta{

	public static final long serialVersionUID = -2427673222146115489L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_stock")
	@SequenceGenerator(name = "gen_id_stock", sequenceName = "gen_id_stock",allocationSize = 1)
	@Column(name="ID_STOCK")
	private long idStock = -1;
	private Double cantidad = new Double(0);
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "ID_DEPOSITO")
	private Deposito deposito;
	
	@ManyToOne
	@JoinColumn(name = "ID_BIEN")
	private Bien bien;
	
	@Column(name = "CANTIDAD_LIMITE")
	private Double cantidadLimite = new Double(0);
	
	@Column(name = "CANTIDAD_A_COMPRAR")
	private Double cantidadAComprar = new Double(0);
	
//	@OneToMany(mappedBy = "stock")
//	private Set<MovimientoDeMercaderia> movimientosDeMercaderia = new HashSet<MovimientoDeMercaderia>();
	
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Deposito getDeposito() {
		return deposito;
	}
	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}
	
	public Bien getBien() {
		return bien;
	}
	public void setBien(Bien bien) {
		this.bien = bien;
	}
	public Double getCantidadLimite() {
		return cantidadLimite;
	}
	public void setCantidadLimite(Double cantidadLimite) {
		this.cantidadLimite = cantidadLimite;
	}
	public Double getCantidadAComprar() {
		return cantidadAComprar;
	}
	public void setCantidadAComprar(Double cantidadAComprar) {
		this.cantidadAComprar = cantidadAComprar;
	}
	@Override
	public String toString() {
		return "Stock [" + bien + "; Cantidad:" + cantidad + "]";
	}
	public void setIdStock(long idStock) {
		this.idStock = idStock;
	}
	@Override
	public int hashCode() {
		if (this.idStock == -1){
			return super.hashCode();
		}
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idStock ^ (idStock >>> 32));
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
		Stock other = (Stock) obj;
		if (idStock != other.idStock)
			return false;
		return true;
	}
	
	public long getIdStock() {
		return idStock;
	}
	
	public EntidadTrascender getEntidadTrascender() {
		return this.deposito;
	}
	
	public String getNombrePropiedad() {
		return "Stock [" + this.bien.getNombre() + "]";
	}
	
	public boolean concatenaNombre() {
		return true;
	}
}