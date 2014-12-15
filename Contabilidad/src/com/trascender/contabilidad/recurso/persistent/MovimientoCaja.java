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
@Entity
@Table(name = "MOVIMIENTO_CAJA")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class MovimientoCaja implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = 6022527065646805825L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_movimiento_caja")
	@SequenceGenerator(name = "gen_id_movimiento_caja", sequenceName = "gen_id_movimiento_caja",allocationSize = 1)
	@Column(name="ID_MOVIMIENTO_CAJA")
	private long idMovimientoCaja=-1;
	private Date fecha;
	private Time hora;
	private Double importe;
	//Relacion con otros objetos
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA")
	private Cuenta cuenta;
	
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

	public long getIdMovimientoCaja() {
		return idMovimientoCaja;
	}

	public void setIdMovimientoCaja(long idMovimientoCaja) {
		this.idMovimientoCaja = idMovimientoCaja;
	}
	
	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	
	@Override
	public int hashCode() {
		if (this.idMovimientoCaja==-1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idMovimientoCaja ^ (idMovimientoCaja >>> 32));
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
		final MovimientoCaja other = (MovimientoCaja) obj;
		if (idMovimientoCaja != other.idMovimientoCaja) {
			return false;
		}
		return true;
	}
	
}
