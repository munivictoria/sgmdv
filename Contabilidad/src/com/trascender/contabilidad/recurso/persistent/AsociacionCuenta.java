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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ASOCIACION_CUENTA")
@Inheritance(strategy = InheritanceType.JOINED)
public class AsociacionCuenta implements Serializable{

	private static final long serialVersionUID = -4602669724591398667L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="gen_id_asociacion_cuenta")
	@SequenceGenerator(name = "gen_id_asociacion_cuenta", sequenceName = "gen_id_asociacion_cuenta",allocationSize = 1)
	@Column(name="ID_ASOCIACION_CUENTA")
	private long idAsociacionCuenta = -1;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA")
	private Cuenta cuenta;
	
	@ManyToOne
	@JoinColumn(name = "ID_CUENTA_PAGOS_ATRASADOS")
	private Cuenta cuentaPagosAtrasados;
	
	public Cuenta getCuentaPagosAtrasados() {
		return cuentaPagosAtrasados;
	}
	public void setCuentaPagosAtrasados(Cuenta cuentaPagosAtrasados) {
		this.cuentaPagosAtrasados = cuentaPagosAtrasados;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	public long getIdAsociacionCuenta() {
		return idAsociacionCuenta;
	}
	public void setIdAsociacionCuenta(long idAsociacionCuenta) {
		this.idAsociacionCuenta = idAsociacionCuenta;
	}
	@Override
	public int hashCode() {
		if (this.idAsociacionCuenta==-1){
			return super.hashCode();
		}
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (int) (idAsociacionCuenta ^ (idAsociacionCuenta >>> 32));
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
		final AsociacionCuenta other = (AsociacionCuenta) obj;
		if (idAsociacionCuenta != other.idAsociacionCuenta) {
			return false;
		}
		return true;
	}
	
}
