package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "BAJA_ARTICULO")
public class BajaArticulo implements Serializable{
	
	private static final long serialVersionUID = 1101189041953813695L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_baja_articulo")
	@SequenceGenerator(name = "gen_id_baja_articulo", sequenceName = "gen_id_baja_articulo",allocationSize = 1)
	@Column(name="ID_BAJA_ARTICULO")
	private long idBajaArticulo = -1;
	
	private Date fecha;
	private String comentario;
	
	@Transient
	private Cuenta cuentaIngreso;
	
	@Transient
	private Cuenta cuentaEgreso;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA_ARTICULO")
	private CuentaArticulo cuentaArticulo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_MOVIMIENTO_CUENTA_EGRESO")
	private MovimientoCuentaEgreso movimientoCuentaEgreso;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_MOVIMIENTO_CUENTA_INGRESO")
	private MovimientoCuentaIngreso movimientoCuentaIngreso;
	
	public long getIdBajaArticulo() {
		return idBajaArticulo;
	}
	public void setIdBajaArticulo(long idBajaArticulo) {
		this.idBajaArticulo = idBajaArticulo;
	}
	public Cuenta getCuentaIngreso() {
		if (this.movimientoCuentaIngreso != null){
			return this.movimientoCuentaIngreso.getCuenta();
		}
		return cuentaIngreso;
	}
	public void setCuentaIngreso(Cuenta cuentaIngreso) {
		this.cuentaIngreso = cuentaIngreso;
	}
	public Cuenta getCuentaEgreso() {
		if (this.movimientoCuentaEgreso != null){
			return this.movimientoCuentaEgreso.getCuenta();
		}
		return cuentaEgreso;
	}
	public void setCuentaEgreso(Cuenta cuentaEgreso) {
		this.cuentaEgreso = cuentaEgreso;
	}
	public MovimientoCuentaIngreso getMovimientoCuentaIngreso() {
		return movimientoCuentaIngreso;
	}
	public void setMovimientoCuentaIngreso(
			MovimientoCuentaIngreso movimientoCuentaIngreso) {
		this.movimientoCuentaIngreso = movimientoCuentaIngreso;
	}
	public CuentaArticulo getCuentaArticulo() {
		return cuentaArticulo;
	}
	public void setCuentaArticulo(CuentaArticulo cuentaArticulo) {
		this.cuentaArticulo = cuentaArticulo;
	}
	public MovimientoCuentaEgreso getMovimientoCuentaEgreso() {
		return movimientoCuentaEgreso;
	}
	public void setMovimientoCuentaEgreso(
			MovimientoCuentaEgreso movimientoCuentaEgreso) {
		this.movimientoCuentaEgreso = movimientoCuentaEgreso;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idBajaArticulo ^ (idBajaArticulo >>> 32));
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
		BajaArticulo other = (BajaArticulo) obj;
		if (idBajaArticulo != other.idBajaArticulo) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString(){
		return "Cuenta ingreso: "+this.getCuentaIngreso()+", Cuenta egreso: " + this.getCuentaEgreso(); 
	}
	
	
}
