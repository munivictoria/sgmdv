package com.trascender.contabilidad.recurso.persistent;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.trascender.compras.recurso.persistent.inventario.Articulo;


@Entity
@Table(name = "CUENTA_ARTICULO")
@PrimaryKeyJoinColumn(name = "ID_ASOCIACION_CUENTA")
public class CuentaArticulo extends AsociacionCuenta{
	public static final long serialVersionUID = -6541953438022150089L;
	
	@ManyToOne
	@JoinColumn(name = "ID_ARTICULO")
	private Articulo articulo;
	
	@OneToOne(mappedBy = "cuentaArticulo")
	private BajaArticulo bajaArticulo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_MOVIMIENTO_CUENTA_INGRESO")
	private MovimientoCuentaIngreso movimientoCuentaIngreso;
	
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public BajaArticulo getBajaArticulo() {
		return bajaArticulo;
	}
	public void setBajaArticulo(BajaArticulo bajaArticulo) {
		this.bajaArticulo = bajaArticulo;
	}
	public MovimientoCuentaIngreso getMovimientoCuentaIngreso() {
		return movimientoCuentaIngreso;
	}
	public void setMovimientoCuentaIngreso(
			MovimientoCuentaIngreso movimientoCuentaIngreso) {
		this.movimientoCuentaIngreso = movimientoCuentaIngreso;
	}
	
	@Override
	public String toString(){
		return "Cuenta: "+this.getCuenta()+", Articulo: "+this.articulo;
	}

}
