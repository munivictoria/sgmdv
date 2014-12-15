package com.trascender.contabilidad.recurso.persistent;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MOVIMIENTO_CUENTA")
@Inheritance(strategy = InheritanceType.JOINED)
public class MovimientoCuenta implements Serializable{
	
	private static final long serialVersionUID = 7028866034742505016L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_movimiento_cuenta")
	@SequenceGenerator(name = "gen_id_movimiento_cuenta", sequenceName = "gen_id_movimiento_cuenta",allocationSize = 1)
	@Column(name="ID_MOVIMIENTO_CUENTA")
	private long idMovimientoCuenta = -1;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	private Time hora;
	
	private Double importe;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA")
	private Cuenta cuenta;
	
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	public long getIdMovimientoCuenta() {
		return idMovimientoCuenta;
	}
	public void setIdMovimientoCuenta(long idMovimientoCuenta) {
		this.idMovimientoCuenta = idMovimientoCuenta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idMovimientoCuenta ^ (idMovimientoCuenta >>> 32));
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
		MovimientoCuenta other = (MovimientoCuenta) obj;
		if (idMovimientoCuenta != other.idMovimientoCuenta) {
			return false;
		}
		return true;
	}
	
}
